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

## V2 behavior and performance comparisons

The server suite now also compares scan positions and order with V1's independent
bounding-box iterator, including negative and world-border coordinates. It checks
all seven built-in tunings, custom location predicates, unloaded boundaries,
temporary display state/order, exact wave delays, saved countdowns and metadata.
Three test items are registered in the `geodes-test` namespace before registry
freeze; they are absent from the release JAR and normal gameplay.

For the activation benchmark, install the release JAR, Fabric API and the separate
`*-testmod.jar` in a fresh isolated Fabric server. Use the same Java/JVM settings,
seed 7402163 and server settings for every run. Add the JVM property
`-Dgeodes.tests.benchmarkOnly=true` before `-jar`. This skips the regular suite,
loads/forces fixture chunks, waits 40 ticks for entity sections, and performs
12 warmups plus 24 measured activations for each of five cases. It writes
`locator-benchmark.json` and stops. The helper uses V1-compatible public APIs,
so run the very same helper JAR with the actual V1 and V2 release JARs.
Do not install both release JARs at once.

Use separate fresh directories, one Java process at a time, in V1/V2/V2/V1
order. Avoid concurrent clients, builds, profiling or CPU-heavy applications.
Compare target counts before timing, retain every raw sample, and report both
per-run medians and pooled medians. Setup/cleanup are outside the measured
activation interval; cleanup asserts all created entities are accessible.
Thread allocations use the JVM's actual current-thread allocation counter.
These measurements exclude player tracking and are not FPS, total heap usage,
startup or chunk-generation benchmarks. Entity metadata is separately encoded
with vanilla's real packet codec for 100 displays over their 42-tick lifetime;
packet counts/bytes exclude spawn, removal, framing, compression and transport.

The client suite checks that pending invisible displays are culled before model
extraction and visible outline states remain valid. Optionally add
`-Dgeodes.tests.renderBenchmark=true` to the client JVM. It runs 60 warmup and
120 measured synthetic frames, each with 256 pending displays, and writes
`locator-render-benchmark.json`. Use the same renderer, graphics backend, options
and fixture server for both releases. This measures visibility/model preparation
CPU time only; it cannot establish a general FPS or GPU improvement.

For a Vulkan compatibility run, add Sodium 0.9.2 for 26.3 and the game arguments
`--graphicsBackend VULKAN`. The release mod does not require Sodium.
