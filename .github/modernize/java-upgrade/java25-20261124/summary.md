# Java 25 Upgrade Summary

## Project

- **Project:** Projeto_Restaurante
- **Target runtime:** Java 25.0.5.1 LTS
- **Working branch:** appmod/java-upgrade-java25-20261124

## Files Changed

| File | Change |
| --- | --- |
| `pom.xml` | Changed `<java.version>` from 17 to 25 |
| `README.md` | Added Java 25 and Maven Wrapper requirements |

## Validation

| Validation | Result |
| --- | --- |
| Baseline compilation/test | SUCCESS with Java 25 before Java target change |
| `mvnw.cmd clean test-compile -q` | SUCCESS; 46 main sources compiled with release 25 |
| `mvnw.cmd clean test` | SUCCESS; no test sources; no tests to run |
| `mvnw.cmd clean verify -q` | SUCCESS |

## Dependency Assessment

- Maven Wrapper: 3.9.16
- Spring Boot: 3.4.12
- Lombok: 1.18.42
- H2: 2.3.232
- Direct dependency inventory succeeded.
- The dedicated CVE automation endpoint was unavailable in this environment, so it could not provide a CVE scan result.

## Key Risks

- No test source files exist, so the project has 0 automated tests.
- JDK 17 was not available locally for an exact pre-upgrade JDK baseline; baseline compilation used JDK 25 with the original Java 17 project configuration.
- JDK 25 portable installation succeeded. A machine-wide MSI install was blocked by insufficient privileges.
