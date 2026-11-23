# MoreGeodes 26.3

An unofficial **Minecraft Java Edition 26.3 / Fabric** port of
[More Geodes 1.9.3 by TheDeathlyCow](https://github.com/TheDeathlyCow/more-geodes).
The original mod's authorship, content identifiers, textures, sounds and LGPL
license are retained. This fork is not an official upstream release.

## Requirements

- Minecraft Java Edition **26.3**.
- Fabric Loader **0.19.5 or newer**.
- Fabric API **0.164.0+26.3** (the version used for validation).
- Java **25 or newer**; the recorded local tests used JDK 26.0.2.1.

## Build and install

```powershell
.\gradlew.bat clean build
```

```sh
sh ./gradlew clean build
```

Install `build/libs/more-geodes-fabric-26.3-1.9.3-portV2.jar` together with
Fabric API on both client and server. Use the normal JAR, not a sources or test
JAR. The complete build helpers and dependency details are in
[BUILD_README.txt](BUILD_README.txt). This is a source repository: the JAR is
created by the build; the earlier build-ready ZIP also included the compiled JAR.

The first build downloads Gradle and dependencies. The wrapper pins Gradle
9.7.1 and Loom 1.17.21. The output targets Java 25 bytecode.

## Changes

- Ports registries, components, recipes, loot, mixins, rendering and data formats
  to the actual 26.3 APIs.
- Preserves all 50 blocks, 56 items, geode types, generation settings and gameplay.
- Resolves locator tuning once per activation and visits exactly the original
  eligible positions in their original order.
- Keeps temporary-display countdowns on the server, avoiding redundant metadata.
- Skips pending invisible displays before model extraction; visible outlines
  retain their existing rendering.

See [CHANGES_PORTV1.txt](CHANGES_PORTV1.txt) for the initial port and
[CHANGES_PORTV2.txt](CHANGES_PORTV2.txt) for the optimization update.
**World-generation code and data are unchanged between V1 and V2.** The update
makes no claim of faster chunk generation or generally higher FPS.

## Validation

The completed validation on **2026-10-20** used real dedicated servers and
independent graphical clients, including **Sodium 0.9.2 with Vulkan**. Core and
Carpet suites passed 22 checks; disabled generation passed 20 checks on fresh
load and restart. Saved V1 inventories/block states and V1-client/V2-server
interoperability were exercised.

Controlled comparisons found approximately 70-75% less tuned-locator activation
time and 97.6% fewer dirty countdown metadata batches in their respective
fixtures. These are scoped measurements, not universal FPS or network claims.
The short-range Echo Locator showed no reliable timing improvement.

[TEST_REPORT.txt](TEST_REPORT.txt) records methods and limits;
[TESTING.md](TESTING.md) explains reproduction. Selected raw results are in
[validation/](validation/). Test sources are excluded from the installable JAR.
The original report describes the delivered ZIP; archive-only files such as
screenshots, raw host logs and `original/` are not required for this Git checkout.
Git also omits obsolete empty resource directories, which may change the JAR's
ZIP-directory entries without changing any compiled class or resource payload.
See the Git-checkout build audit in `validation/` for the repository build.

## Worlds, configuration and optional integrations

Geodes appear in newly generated chunks; existing chunks are not repopulated.
Configuration remains in `config/geodes.properties`. Original generation flags,
rarities, biome restrictions, detection behavior and content IDs are preserved.
The 1.20.1-to-26.3 terrain transition cannot promise identical same-seed terrain.

Carpet is optional and was tested with its 26.3 release. AE2, Spectrum and Create
conditional data is retained; their 26.3 runtime integrations remain unverified.
The inherited `datapack_addons/` directory contains legacy upstream add-ons and
is separate from the tested 26.3 mod resources.

## Credits and license

Original author: **TheDeathlyCow**. Upstream contributors are retained in mod
metadata. The source baseline is upstream commit
`c0e1246b7366857351238244d59a0b8a7710b0a0` (tag `1.9.3`).

Licensed under **LGPL-3.0-only**; see [LICENSE](LICENSE) and [COPYING](COPYING).
The [original upstream README](docs/UPSTREAM_README_1.9.3.md) is preserved for
historical feature documentation.
