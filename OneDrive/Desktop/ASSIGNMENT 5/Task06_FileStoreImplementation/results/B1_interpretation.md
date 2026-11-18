# B1 - File-Store Experiments

`PassStore` appends, finds, and lists escaped seven-field pass records.
`PassStoreTest` exercises an escaped ID, route search, and continuation after
a malformed row. `LineCounter` compares single-byte and buffered streaming.
`B1Benchmark` creates and removes a temporary 1 MiB test file.

Run the programs to produce measurements on the current machine. No measured
timings are stored in this repository; benchmark results depend on hardware,
operating-system caching, and the Java runtime.
