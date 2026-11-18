# Final Verification Report

This project was written in the current workspace using the public reference
repository for file names, task organization, and topic coverage. Source
implementations and documentation were authored for this workspace; Git
history, `.git` data, and remote configuration were not copied.

## Verification

- 24 Java source files were found; each contains
  `public static void main(String[] args)`.
- `javac --release 17 -Xlint:all` compiled all Java sources with no errors or
  warnings.
- Database-independent examples ran, including `PassStoreTest`,
  `SerializationDemo`, `B1Benchmark`, `LineCounter`, and
  `AdversarialFileTest`.
- MySQL-dependent programs were not run because they require a configured
  local MySQL database. No database execution or timing is claimed.

The benchmark uses a temporary 1 MiB file and deletes it when complete. Its
timings are local observations only.

**Author:** Saba Shaik
