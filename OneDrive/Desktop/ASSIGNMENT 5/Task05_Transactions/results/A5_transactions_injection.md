# A5 - Transactions and Parameterized SQL

## Atomic transfer

For a wallet transfer, disable auto-commit, debit only when sufficient funds
exist, credit the receiving wallet, and commit only after both updates affect
the expected rows. If either update fails, roll back. Restore the original
auto-commit setting when finished.

## Keep input separate from SQL

Concatenating user text into a query can alter the query's meaning. A
`PreparedStatement` keeps the SQL structure fixed and binds user values through
placeholders. Parameter binding is the preferred protection; quote escaping or
regular-expression checks are not equivalent substitutes.

The runnable unsafe example only prints a sample query and does not execute it.
Database-backed examples are restricted to a local assignment database.
