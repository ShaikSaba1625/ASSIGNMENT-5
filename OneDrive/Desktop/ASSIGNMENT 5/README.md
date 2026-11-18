# Java Assignment 5

This project contains Java programs for Assignment 5, organized into the same
eight task areas as the public reference project.

**Author:** Saba Shaik  
**Repository:** [ASSIGNMENT-5](https://github.com/ShaikSaba1625/ASSIGNMENT-5)

The Java examples and explanations in this workspace are independently
written. The project does not include Git history or remote settings from the
reference repository.

## Purpose

The project provides beginner-friendly Java examples and design notes for the
Assignment 5 topics represented in the reference repository.

## Project Structure

```text
.
|-- Task01_FileFormat/
|   `-- results/
|-- Task02_BufferedIO/
|   `-- results/
|-- Task03_DAODesign/
|   |-- results/
|   `-- src/
|-- Task04_Serialization/
|   |-- results/
|   `-- src/
|-- Task05_Transactions/
|   `-- results/
|-- Task06_FileStoreImplementation/
|   |-- input/
|   |-- output/
|   |-- results/
|   `-- src/
|-- Task07_SQLInjection/
|   |-- input/
|   |-- results/
|   |-- sql/
|   `-- src/
|-- Task08_JDBC_Batch_Migration/
|   |-- input/
|   |-- output/
|   |-- results/
|   |-- sql/
|   `-- src/
|-- Report/
|-- config/
|-- lib/
|-- screenshots/
|-- FINAL_VERIFICATION_REPORT.md
|-- PartA_Complexity_Analysis.md
|-- .gitignore
|-- pom.xml
`-- README.md
```

The `src` folders contain the Java files for the corresponding tasks. Programs
use the default package, so each file can be compiled and run on its own or
alongside the other source files in that task.

## Tasks

1. **File formats:** bus-pass text/binary layout and file operations.
2. **Buffered I/O:** count line breaks using streaming and buffered reads.
3. **DAO pattern:** separate student persistence operations from application
   code.
4. **Serialization:** persist object values while excluding live resources.
5. **Transactions:** explain atomic updates and safe parameter binding.
6. **File store:** add, search, validate, and list bus-pass records; compare
   line-reading approaches.
7. **SQL injection and validation:** contrast unsafe SQL construction with
   prepared parameters and test malformed file rows.
8. **JDBC, batch processing, and migration:** database metadata, inserts, and
   CSV migration examples.

## Requirements

- JDK 17 or newer.
- Maven 3.9 or newer (optional for the Maven build).
- A local MySQL server and MySQL Connector/J for database-backed examples.

## How to Compile

Compile a standalone file from its source directory:

```powershell
javac FileName.java
```

To compile all Java sources together without Maven:

```powershell
$sources = Get-ChildItem -Recurse -Filter *.java
New-Item -ItemType Directory -Force build-out | Out-Null
javac --release 17 -d build-out $sources.FullName
```

## How to Run

Run a class from its compiled output directory:

```powershell
java -cp build-out ClassName
```

Database examples require a local database configured with
`config/db.properties`. Start from `config/db.properties.example`; keep local
credentials out of Git. The SQL setup scripts in the task folders create the
tables used by those examples.

## Technologies Used

- Java
- JDBC
- MySQL
- Maven
- Git
- GitHub
- Visual Studio Code

## Version Control

This project is intended for the GitHub repository
[`ShaikSaba1625/ASSIGNMENT-5`](https://github.com/ShaikSaba1625/ASSIGNMENT-5).
No remote URL or Git history was copied from the reference repository.

## Author

Saba Shaik
