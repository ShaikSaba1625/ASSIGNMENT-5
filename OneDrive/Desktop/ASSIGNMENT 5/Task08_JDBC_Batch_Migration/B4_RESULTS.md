# B4 - Migration and Deployment Notes

`Migration` validates each CSV row before submitting accepted rows through a
prepared batch. Invalid rows are reported with their line number. The database
transaction is committed as a unit or rolled back on failure.

For a multi-campus application, a relational database provides indexed lookups,
constraints, concurrent access control, and transactions. A local text file
may be suitable for a small offline example but requires application-managed
locking and recovery. No database migration or metadata run is claimed until
the local MySQL prerequisites are configured.
