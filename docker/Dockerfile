FROM maven:3-eclipse-temurin-21-noble

# Native libraries for SWT on GTK 3 and the JDK's AWT, a virtual display for the UI tests, and unzip for ./mvnw,
# which otherwise downloads the .tar.gz that doesn't match its checksum.
RUN apt-get update \
    && apt-get install -y --no-install-recommends libgtk-3-0t64 librsvg2-common libxtst6 unzip xvfb \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace
