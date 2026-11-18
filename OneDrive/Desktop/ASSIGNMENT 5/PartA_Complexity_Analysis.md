# Part A - Complexity Summary

Let `n` be the number of records, `L` the number of bytes, `B` the I/O buffer
size, and `q` the number of file searches.

| Operation | Time | Additional space |
|---|---:|---:|
| Scan a text file for one pass | O(L) | O(1), apart from the returned record |
| List records on a route | O(L) | O(n) for the returned list |
| Buffered line count | O(L) | O(B) |
| Read all lines into memory | O(L) | O(L) |
| Repeated file search for q IDs | O(qn) | O(1) per search |
| Build an in-memory ID map | O(n) expected | O(n) |
| DAO lookup by indexed key | Usually O(1) database lookup | O(1) for one result |
| Prepared JDBC batch | O(n) row work | O(B) for a bounded batch |
| Prepared input binding | O(m) for m input characters | O(m) driver/input storage |

These are algorithmic estimates rather than machine measurements. Actual
database and disk timings must be collected on the target environment.
