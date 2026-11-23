More Geodes 1.9.3 - unofficial Fabric 26.3 compatibility port, portV1
=================================================================

INSTALL
-------
The compiled, installable mod is:
  build/libs/more-geodes-fabric-26.3-1.9.3-portV1.jar

Use Minecraft Java Edition 26.3, Fabric Loader 0.19.5 or newer,
Fabric API 0.164.0+26.3, and Java 25 or newer. Testing used Java 26.0.2.1.
Install the port JAR and Fabric API in the instance's mods folder, on both
the client and server. Remove the old More Geodes JAR from that instance.
The original/ JAR is an archival reference, not another mod to install.

Fabric installer: https://fabricmc.net/use/installer/
Fabric API: https://modrinth.com/mod/fabric-api

New geodes appear in newly generated chunks. Existing generated terrain is
not retroactively populated. Content IDs and existing block-state properties
are preserved. Make a backup before upgrading an existing Minecraft world.
The test report states exactly which migration cases were exercised.

BUILD
-----
Windows: double-click BUILD_FABRIC_26.3.bat, or run gradlew.bat clean build
with JAVA_HOME pointing to a JDK 25 or newer.
Linux/macOS: run sh BUILD_FABRIC_26.3.sh with a JDK 25 or newer.
The first build needs internet access for Gradle and dependencies.

The wrapper pins Gradle 9.7.1 and verifies its distribution checksum.
Loom 1.17.21 builds against the unobfuscated 26.3 game. No remap step or Yarn
mappings are needed. The JAR targets Java 25 bytecode, even with JDK 26.
The ordinary build compiles/packages the mod; it does not launch Minecraft.

CONFIGURATION
-------------
config/geodes.properties is created on first startup. All options default true:
  generate_emerald_geodes
  generate_quartz_geodes
  generate_diamond_geodes
  generate_echo_geodes
  generate_lapis_geodes
  generate_gypsum_patches
  generate_certus_geodes
  generate_bismuth_geodes
Restart the game/server after changing generation options or biome tags.
Disabling generation preserves blocks, recipes, and saved content.

OPTIONAL INTEGRATIONS
---------------------
Carpet: tested with its 26.3 release. It remains optional.
AE2, Spectrum, and Create Fabric: their original conditional data is retained,
but no 26.3 Fabric releases were listed in the checked release feeds on
2026-10-19. Their runtime integration is unverified. They are not required
to use this port. Definitions that reference absent mods are conditionally
excluded so they cannot prevent the standalone mod from loading.

SOURCE AND LICENSE
------------------
Original author: TheDeathlyCow; original contributors are retained in metadata.
Source baseline: https://github.com/TheDeathlyCow/more-geodes/tree/1.9.3
Commit: c0e1246b7366857351238244d59a0b8a7710b0a0
This archive includes the complete modified source and required build files.
The mod remains LGPL-3.0-only. LICENSE contains LGPLv3; COPYING contains GPLv3.
This compatibility port is not an official upstream More Geodes release.
See CHANGES_PORTV1.txt, TEST_REPORT.txt, and TESTING.md for details.
