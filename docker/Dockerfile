ARG MAVEN_IMAGE=maven:3.9.16-eclipse-temurin-21-noble
FROM ${MAVEN_IMAGE}

# Eclipse 2026-06 uses SWT/GTK 3. GTK's package dependencies provide the
# remaining native libraries. librsvg supplies GTK's SVG icon loader, and
# libXtst is required by the JDK's X11 AWT library.
RUN apt-get update \
    && apt-get install -y --no-install-recommends \
        libgtk-3-0t64 \
        librsvg2-common \
        libxtst6 \
        xvfb \
    && rm -rf /var/lib/apt/lists/*

# Default working directory for mounted DDK workspace.
WORKDIR /workspace
