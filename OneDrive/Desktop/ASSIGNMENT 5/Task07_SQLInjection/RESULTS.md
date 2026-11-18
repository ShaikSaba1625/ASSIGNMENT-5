# B2 - Security and File Validation

`UnsafeLogin` builds a demonstration query but deliberately does not execute
it. `SecureLogin` shows JDBC parameter binding. Any database exercise must use
the local test schema only; never use real credentials or a third-party host.

`AdversarialFileTest` reads the included fixture and reports malformed rows.
Run it from the project root with:

```powershell
java -cp build-out AdversarialFileTest
```

Database results are not included because they require a locally configured
MySQL service. No test outcome is claimed until it is run locally.
