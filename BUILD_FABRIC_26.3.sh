#!/usr/bin/env sh
set -eu
cd -- "$(dirname -- "$0")"
if [ -n "${JAVA_HOME:-}" ]; then PATH="$JAVA_HOME/bin:$PATH"; export PATH; fi
major=$(javac -version 2>&1 | awk '{split($2,a,".");print a[1]}')
if [ "$major" -lt 25 ]; then echo 'A JDK 25 or newer is required.' >&2; exit 1; fi
sh ./gradlew --no-daemon clean build
test -f build/libs/more-geodes-fabric-26.3-1.9.3-portV2.jar
echo 'Install build/libs/more-geodes-fabric-26.3-1.9.3-portV2.jar'
