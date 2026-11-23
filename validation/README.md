# Validation records

The JSON/text results in this directory were recorded for the tested V2 build
on 2026-10-20. They include real Minecraft runtime checks and the unedited
benchmark samples. They are historical test evidence, not a claim that GitHub
Actions or a new full gameplay suite ran on the publication date.

The `git-checkout-build.json` record is generated separately for this repository
publication. It compares a clean Git checkout's compiled classes and resource
payloads against the installable V2 JAR already tested in Minecraft. Git does
not retain obsolete empty directories, so complete JAR hashes can differ while
all file payloads remain identical. Resource line endings are preserved by
`.gitattributes` for this comparison.

The earlier build-ready ZIP also included screenshots and raw startup logs;
those generated media/host logs are not added to this source repository.
The hashes in `artifact-audit.json` and `TEST_REPORT.txt` identify that tested
archive build, not a promise about every future local ZIP container hash.
