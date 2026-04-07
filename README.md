"# CodeCoverage"

## SonarQube analysis

Use the following Maven command to run tests, generate coverage data, and submit analysis to SonarQube:

```bash
./mvnw clean verify sonar:sonar \
  -Dsonar.projectKey='Javacoverage' \
  -Dsonar.projectName='Javacoverage' \
  -Dsonar.host.url='http://localhost:9000/' \
  -Dsonar.branch.name='develop' \
  -Dsonar.login='squ_454790fc796698fae62c9c52bbf477e164e87350' \
  -Dsonar.qualitygate.wait=true \
  -Dsonar.scm.provider=git \
  -Dsonar.junit.reportPaths=target/surefire-reports \
  -Dsonar.jacoco.reportPaths=target/jacoco.exec \
  -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco-report/jacoco.xml \
  -Dsonar.exclusions=''
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

This project now includes explicit security-vulnerability patterns that should be detected by SonarQube during analysis.

> Note: the previous command had an incorrect case/property name: use `-Dsonar.qualitygate.wait=true` (not `-DSonar.quality.gate.wait=true`).


## Security Vulnerability Test Scenarios

This project now includes security-focused utility methods and tests covering common vulnerability classes:

- SQL injection risk detection in user-supplied text
- HTML escaping to prevent XSS-style payloads
- Path traversal prevention for filename validation
- Strong password validation rules
- Consistent password hashing with SHA-256

### Run the tests

Use Maven to execute the test suite:

```bash
mvn test
```
 
