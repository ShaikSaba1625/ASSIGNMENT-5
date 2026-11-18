# A2 - Buffered File Reading

## One-byte reads

For a file of L bytes, a loop that calls `FileInputStream.read()` once per
byte performs L Java-level read calls. It still scans in O(L) time, but incurs
many method and stream-boundary operations.

## Buffered redesign

Read into a byte array (for example, 8192 bytes), then count newline bytes in
the portion actually returned by each read. This keeps O(L) scan time while
using O(B) working memory and roughly `ceil(L / B)` bulk reads.

```text
FileInputStream -> buffer -> inspect returned bytes -> count line feeds
```

`Files.readAllLines` is convenient when all text lines are needed, but retains
the entire file and decoded strings in memory. Streaming is usually preferable
for a large log. Buffering offers less benefit for an interactive stream that
must respond to each byte immediately.
