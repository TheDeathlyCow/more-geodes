# Reproducing the runtime checks

Use an isolated test world. The test mod deliberately places blocks, generates
chunks, spawns temporary entities, and creates inventory fixtures. It is excluded
from the normal installable JAR.

1. Build with `gradlew.bat clean build testmodJar` (or `sh gradlew ...`).
2. Prepare `run/test-server/eula.txt` according to Minecraft's EULA and put
   `eula=true` there if you accept it.
3. Put a `server.properties` in that directory with `server-port=25569`,
   `server-ip=127.0.0.1`, `white-list=false`, `online-mode=false`,
   `max-tick-time=180000`, and `pause-when-empty-seconds=0` for the local fixture.
   Use this isolated local test configuration only for these tests.
4. Run `gradlew.bat runTestmodServer`. It stops automatically after writing
   `run/test-server/test-results.json`. Run it twice to check saved inventories
   and block states after a real world save/reload.
5. Add `-PwithCarpet` to test the optional Carpet rule and budding-block drops.
6. Set all eight values in its `config/geodes.properties` to `false` and repeat
   to verify disabled biome generation without losing registered/saved content.

For client and actual scheduler checks, run the server with `-PkeepTestServer`.
Launch `runTestmodClient` from another terminal. The test client joins localhost,
resolves every item model, checks synchronized tint data, activates a locator,
opens the More Geodes creative tab, and saves screenshots. It then asks the
isolated server to stop. Scheduled clocks are tested while a player makes their
chunks tick; results are in `scheduled-clock-results.json`.

Client tests use Minecraft's standard `-XX:StackShadowPages=32` launch setting
through Loom. They do not need or read an account access token. Realms and online
authentication are outside this local test.

The completed validation also installed the compiled release JAR into a separate
Fabric server and client, with an unmodified Minecraft client JAR. Test-only code
was packaged separately. See TEST_REPORT.txt and validation/ for the evidence.
