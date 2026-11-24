# Upgrade Plan: restaurante (java25-20261124)

- **Generated**: 2026-11-24 10:10 -03:00
- **HEAD Branch**: appmod/java-upgrade-java25-20261124
- **HEAD Commit ID**: current working branch; commit ID unavailable until commit

## Available Tools

**JDKs**
- JDK 17: not available (baseline at project source level skipped)
- JDK 25: C:\Users\gr072\.jdk\jdk-25.0.5 (required for final validation)
- JDK 26: C:\Program Files\Java\jdk-26.0.1 (available but not target runtime)

**Build Tools**
- Maven Wrapper: 3.9.16

## Guidelines

- User requested auto-execution.
- Keep scope to Projeto_Restaurante.
- Upgrade the Java runtime to Java 25 LTS.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-java25-20261124
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java runtime and compiler target: 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| --- | --- | --- | --- |
| Java | 17 | 25 | User requested |
| Spring Boot | 3.4.12 | 3.4.12 | Compatible with Java 25 if compilation dependencies support it |
| Maven Wrapper | 3.9.16 | 3.9.0 | Compatible and current |
| Lombok | managed by Spring Boot | 1.18.36+ | Must compile annotation processing on Java 25 |

## Derived Upgrades

- Set `<java.version>` to 25.
- Document Java 25 and wrapper usage in README.
- No framework major migration is required.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
| --- | --- | --- | --- | --- | --- |
| pom.xml | java.version | 17 | upgrade | 25 | User-requested runtime and compiler target |

### Source Code Changes

No Java source changes required; no deprecated JDK internals or javax namespace usages were found.

### Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
| --- | --- | --- | --- | --- |
| pom.xml | java.version | 17 | 25 | Configure Maven compilation and Spring Boot target |

### CI/CD Changes

No CI/CD Java version references were found.

### Risks & Warnings

- **Annotation processing**: The project uses Lombok. Java 25 compatibility may require a newer Lombok release if the inherited managed version is insufficient. Mitigation: compile with JDK 25 and upgrade Lombok within the same dependency line if needed.
- **Tests**: No test source files were found during assessment. Mitigation: clean test-compile and clean test still validate compilation and report zero tests.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Ensure Java 25 is installed for target validation.
  - **Changes to Make**: Install/use Microsoft OpenJDK 25.
  - **Verification**: `java -version`; JDK 25 path available.
- Step 2: Setup Baseline
  - **Rationale**: Establish pre-upgrade compilation/test behavior.
  - **Changes to Make**: Run baseline before changing java.version.
  - **Verification**: With JDK 25, baseline uses Java 17 configuration and compiles/tests.
- Step 3: Upgrade Maven Java Target and Documentation
  - **Rationale**: Apply the complete minimal runtime upgrade.
  - **Changes to Make**: Apply Dependency and Configuration Changes; update README.
  - **Verification**: `mvnw.cmd clean test-compile -q` with JDK 25.
- Step 4: CVE Validation & Fix
  - **Rationale**: Validate direct dependencies and address reported vulnerabilities.
  - **Changes to Make**: Scan direct dependencies, upgrade only vulnerable dependencies with patches, and re-scan.
  - **Verification**: Dependency scan and Maven compilation.
- Step 5: Final Validation
  - **Rationale**: Validate the target runtime and project success criteria.
  - **Changes to Make**: Remove any workarounds/TODOs and perform clean rebuild/tests.
  - **Verification**: `mvnw.cmd clean test` with JDK 25; report exact test count.
