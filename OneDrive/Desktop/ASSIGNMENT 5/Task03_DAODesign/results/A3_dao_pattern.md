# A3 - Data-Access Object Pattern

A DAO keeps SQL, parameter binding, row mapping, and resource handling outside
the service or user-interface code. Each entity-specific DAO supplies its own
table, key, and mapping while implementing a shared generic interface.

The Java example defines `Dao<T, K>` and a `StudentDao`. The student mapping is:

| Property | Database column |
|---|---|
| studentId | student_id |
| name | name |
| email | email |
| department | department |

`StudentDao` uses prepared parameters and try-with-resources. A keyed lookup
uses the database's key index; returning all rows requires O(n) work and
materialized-list memory. A DAO centralizes schema-related changes without
changing those database costs.
