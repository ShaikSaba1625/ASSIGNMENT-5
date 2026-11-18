# A1 - Bus-Pass File Format

## Record layout

A text record stores seven values in this order: pass ID, student register
number, student name, route number, boarding stop, validity date, and amount
paid. One UTF-8 line represents one pass. The `|` separator, backslash, and
line-break characters must be escaped so that values can be recovered exactly.

A binary alternative can use `DataOutputStream`: store a version integer,
write text values with `writeUTF`, write the route as an integer, and write the
amount as a double. The reader must use the same order and reject versions it
does not understand.

## Add, find, and list

- **Add:** validate required values, positive route, non-negative finite
  amount, and ISO date; encode and append one record using a buffered writer.
- **Find:** scan lines, parse and validate each one, and return the matching ID.
- **List by route:** scan the records and collect valid rows for that route.

An absent file can represent an empty read-only store. I/O errors should be
reported rather than mistaken for a successful search with no match.

## Damaged data and alternative

Malformed rows should be identified by line number and skipped without hiding
later valid records. An interrupted append may leave a partial final line; the
parser should reject it. For stronger rewrites, write a temporary file, flush
it, and replace the old file atomically where supported.

A fixed-size `RandomAccessFile` record has predictable offsets, but wastes
space and still needs an index for fast ID lookup. A text scan costs O(n);
an ID-to-offset index can provide expected O(1) lookup at the cost of O(n)
index storage and recovery work.
