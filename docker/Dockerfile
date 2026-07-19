# No Maven in the image: the build uses ./mvnw, which pins the Maven version the project can build with.
FROM eclipse-temurin:21-jdk-noble

# Native libraries for SWT on GTK 3 and the JDK's AWT, a virtual display for the UI tests, tini as PID 1, and unzip
# for ./mvnw, which otherwise downloads the .tar.gz that doesn't match its checksum.
RUN apt-get update \
    && apt-get install -y --no-install-recommends libgtk-3-0t64 librsvg2-common libxtst6 tini unzip xvfb \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace

# Without an init process, xvfb-run runs as PID 1 and can hang before starting Maven.
ENTRYPOINT ["/usr/bin/tini", "--"]
