#!/usr/bin/env python3
"""
Script to fix JUnit 5 test visibility issues.
- Converts @SelectClasses to @SelectPackages in test suites
- Makes test classes package-private
- Makes test methods (@Test, @BeforeEach, @AfterEach, etc.) package-private
"""

import os
import re
from pathlib import Path
from typing import List, Tuple, Set

# Root directory of the project
PROJECT_ROOT = Path(r"c:\dev\dsl-devkit")

# Test source directories pattern
TEST_SRC_PATTERN = "*test*/**/src/**/*.java"

# JUnit 5 test annotations that indicate test methods
TEST_ANNOTATIONS = {
    "@Test",
    "@BeforeEach",
    "@AfterEach",
    "@BeforeAll",
    "@AfterAll",
    "@ParameterizedTest",
    "@RepeatedTest",
    "@TestFactory",
    "@TestTemplate",
}

# Lifecycle annotations for Xtend files
XTEND_TEST_ANNOTATIONS = {
    "@Test",
    "@BeforeAll",
    "@AfterAll",
}


def find_test_files(root: Path) -> List[Path]:
    """Find all Java test files in the project."""
    test_files = []
    for test_dir in root.glob("com.avaloq.tools.ddk.*/src"):
        if "test" in str(test_dir.parent.name).lower():
            for java_file in test_dir.rglob("*.java"):
                test_files.append(java_file)
    return test_files


def find_xtend_test_files(root: Path) -> List[Path]:
    """Find all Xtend test files in the project."""
    test_files = []
    for test_dir in root.glob("com.avaloq.tools.ddk.*/src"):
        if "test" in str(test_dir.parent.name).lower():
            for xtend_file in test_dir.rglob("*.xtend"):
                test_files.append(xtend_file)
    return test_files


def is_test_class(content: str) -> bool:
    """Check if the file contains JUnit 5 test annotations."""
    for annotation in TEST_ANNOTATIONS:
        if annotation in content:
            return True
    return False


def is_suite_class(content: str) -> bool:
    """Check if the file is a JUnit 5 suite."""
    return "@Suite" in content


def extract_packages_from_select_classes(content: str) -> Set[str]:
    """Extract package names from @SelectClasses imports."""
    packages = set()
    # Find all imports of test classes
    import_pattern = r"import\s+([\w.]+)\.\w+;"
    for match in re.finditer(import_pattern, content):
        package = match.group(1)
        # Only include test-related packages
        if any(x in package for x in ["test", "Test"]):
            packages.add(package)
    return packages


def make_class_package_private(content: str) -> str:
    """Convert public class declarations to package-private."""
    # Match: public class ClassName or public class ClassName extends/implements
    # But not: public static class (inner classes)
    pattern = r"^(\s*)public\s+(class\s+\w+)"
    replacement = r"\1\2"
    return re.sub(pattern, replacement, content, flags=re.MULTILINE)


def make_test_methods_package_private(content: str) -> str:
    """Convert public test methods to package-private."""
    lines = content.split("\n")
    result = []
    i = 0
    while i < len(lines):
        line = lines[i]
        # Check if this line has a test annotation
        has_test_annotation = any(ann in line for ann in TEST_ANNOTATIONS)

        if has_test_annotation:
            result.append(line)
            i += 1
            # Look at next non-empty, non-annotation lines for method signature
            while i < len(lines):
                next_line = lines[i]
                # Skip empty lines and additional annotations
                if next_line.strip() == "" or next_line.strip().startswith("@"):
                    result.append(next_line)
                    i += 1
                    continue
                # Check if this is a public method declaration
                if re.match(r"\s*public\s+(void|[\w<>\[\]]+)\s+\w+\s*\(", next_line):
                    # Remove 'public ' from the method
                    next_line = re.sub(r"^(\s*)public\s+", r"\1", next_line)
                result.append(next_line)
                i += 1
                break
        else:
            result.append(line)
            i += 1

    return "\n".join(result)


def convert_suite_to_select_packages(content: str, file_path: Path) -> Tuple[str, bool]:
    """Convert a suite using @SelectClasses to use @SelectPackages."""
    if "@SelectClasses" not in content:
        return content, False

    # Extract packages from the imports
    packages = extract_packages_from_select_classes(content)

    if not packages:
        print(f"  Warning: Could not extract packages from {file_path}")
        return content, False

    # Remove the test class imports (keep other imports)
    lines = content.split("\n")
    new_lines = []
    imports_to_remove = set()

    # First pass: identify imports to remove
    select_classes_pattern = r"@SelectClasses\s*\(\s*\{([^}]+)\}"
    match = re.search(select_classes_pattern, content, re.DOTALL)
    if match:
        classes_block = match.group(1)
        # Extract class names from the block
        class_names = re.findall(r"(\w+)\.class", classes_block)
        for class_name in class_names:
            # Find the import for this class
            import_pattern = rf"import\s+([\w.]+\.{class_name});"
            import_match = re.search(import_pattern, content)
            if import_match:
                imports_to_remove.add(import_match.group(0))

    # Second pass: rebuild content without those imports
    for line in lines:
        if line.strip() in imports_to_remove or any(
            imp in line for imp in imports_to_remove
        ):
            continue
        new_lines.append(line)

    content = "\n".join(new_lines)

    # Replace @SelectClasses import with @SelectPackages
    content = content.replace(
        "import org.junit.platform.suite.api.SelectClasses;",
        "import org.junit.platform.suite.api.SelectPackages;",
    )

    # Build the @SelectPackages annotation
    packages_str = ",\n  ".join(f'"{pkg}"' for pkg in sorted(packages))
    select_packages_annotation = f"@SelectPackages({{\n  {packages_str}\n}})"

    # Replace @SelectClasses({...}) with @SelectPackages({...})
    content = re.sub(
        r"@SelectClasses\s*\(\s*\{[^}]+\}\s*\)",
        select_packages_annotation,
        content,
        flags=re.DOTALL,
    )

    # Make the suite class package-private
    content = make_class_package_private(content)

    return content, True


def process_test_class(file_path: Path) -> bool:
    """Process a single test class file."""
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            original_content = f.read()
    except Exception as e:
        print(f"  Error reading {file_path}: {e}")
        return False

    if not is_test_class(original_content):
        return False

    content = original_content

    # Make class package-private
    content = make_class_package_private(content)

    # Make test methods package-private
    content = make_test_methods_package_private(content)

    if content != original_content:
        try:
            with open(file_path, "w", encoding="utf-8") as f:
                f.write(content)
            return True
        except Exception as e:
            print(f"  Error writing {file_path}: {e}")
            return False

    return False


def process_suite_class(file_path: Path) -> bool:
    """Process a single suite class file."""
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            original_content = f.read()
    except Exception as e:
        print(f"  Error reading {file_path}: {e}")
        return False

    if not is_suite_class(original_content):
        return False

    content, changed = convert_suite_to_select_packages(original_content, file_path)

    if changed and content != original_content:
        try:
            with open(file_path, "w", encoding="utf-8") as f:
                f.write(content)
            return True
        except Exception as e:
            print(f"  Error writing {file_path}: {e}")
            return False

    return False


def make_xtend_class_package_private(content: str) -> str:
    """Convert public class declarations to package-private in Xtend files."""
    # In Xtend, 'class' without modifier is package-private
    # We need to add 'package' keyword before 'class'
    pattern = r"^(\s*)class\s+(\w+)"

    # Check if it's already package or has other modifier
    if re.search(
        r"^\s*(public|private|protected|package)\s+class", content, re.MULTILINE
    ):
        # Replace 'public class' with 'package class'
        content = re.sub(
            r"^(\s*)public\s+class", r"\1package class", content, flags=re.MULTILINE
        )

    return content


def process_xtend_test_class(file_path: Path) -> bool:
    """Process a single Xtend test class file."""
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            original_content = f.read()
    except Exception as e:
        print(f"  Error reading {file_path}: {e}")
        return False

    # Check if it has test annotations
    has_tests = any(ann in original_content for ann in XTEND_TEST_ANNOTATIONS)
    if not has_tests:
        return False

    content = make_xtend_class_package_private(original_content)

    if content != original_content:
        try:
            with open(file_path, "w", encoding="utf-8") as f:
                f.write(content)
            return True
        except Exception as e:
            print(f"  Error writing {file_path}: {e}")
            return False

    return False


def main():
    print("=" * 60)
    print("JUnit 5 Visibility Fixer")
    print("=" * 60)

    # Find all test files
    print("\nFinding test files...")
    java_files = find_test_files(PROJECT_ROOT)
    xtend_files = find_xtend_test_files(PROJECT_ROOT)
    print(f"Found {len(java_files)} Java test files")
    print(f"Found {len(xtend_files)} Xtend test files")

    # Process suite classes first
    print("\n" + "-" * 60)
    print("Processing Suite classes (converting @SelectClasses to @SelectPackages)...")
    print("-" * 60)
    suites_modified = 0
    for file_path in java_files:
        if process_suite_class(file_path):
            print(f"  Modified suite: {file_path.relative_to(PROJECT_ROOT)}")
            suites_modified += 1
    print(f"Modified {suites_modified} suite files")

    # Process test classes
    print("\n" + "-" * 60)
    print("Processing Test classes (making package-private)...")
    print("-" * 60)
    tests_modified = 0
    for file_path in java_files:
        if process_test_class(file_path):
            print(f"  Modified: {file_path.relative_to(PROJECT_ROOT)}")
            tests_modified += 1
    print(f"Modified {tests_modified} Java test files")

    # Process Xtend test classes
    print("\n" + "-" * 60)
    print("Processing Xtend Test classes (making package-private)...")
    print("-" * 60)
    xtend_modified = 0
    for file_path in xtend_files:
        if process_xtend_test_class(file_path):
            print(f"  Modified: {file_path.relative_to(PROJECT_ROOT)}")
            xtend_modified += 1
    print(f"Modified {xtend_modified} Xtend test files")

    print("\n" + "=" * 60)
    print("Summary:")
    print(f"  Suites converted: {suites_modified}")
    print(f"  Java tests fixed: {tests_modified}")
    print(f"  Xtend tests fixed: {xtend_modified}")
    print("=" * 60)


if __name__ == "__main__":
    main()
