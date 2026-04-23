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

Alternatively, this project includes a GitHub Actions workflow (`.github/workflows/sonarscanner.yaml`) that automatically runs SonarQube analysis on pushes and pull requests to `main` and `develop` branches.

To use the workflow, set the following secrets in your GitHub repository:
- `SONAR_TOKEN`: Your SonarQube authentication token
- `SONAR_HOST_URL`: Your SonarQube server URL (e.g., `http://localhost:9000/` or `https://sonarcloud.io`)

## SonarQube Report Summary

| Metric | Overall Code | New Code |
|--------|--------------|----------|
| Code Smells | 15 | 2 |
| Vulnerabilities | 3 | 0 |
| Security Hotspots | 8 | 1 |
| Code Coverage | 79% | 85% |

*Note: This table shows sample data. Actual values will be populated after running the SonarQube analysis. You can extract this data from the SonarQube portal dashboard or via the API endpoint: `/api/measures/component?component=Javacoverage01&metricKeys=ncloc,new_lines,code_smells,new_code_smells,vulnerabilities,new_vulnerabilities,security_hotspots,new_security_hotspots,coverage,new_coverage`*

This project now includes explicit security-vulnerability patterns that should be detected by SonarQube during analysis.

### Where the hotspots live
- `src/main/java/JavaCodeCoverage/SecurityHotspotExamples.java`
- `src/main/java/JavaCodeCoverage/VulnerableService.java`
- `src/main/java/JavaCodeCoverage/SecurityUtils.java`

These classes include Sonar-detectable hotspots such as:
- hardcoded credentials and API keys
- insecure SQL string concatenation
- unsafe command execution
- path traversal file access
- insecure SSL/TLS trust manager
- insecure deserialization
- weak hashing
- predictable random number generation

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

## Docker image

Build the Docker image from the project root:

```bash
docker build -t javacoverage-app .
```

Run the image to execute the Maven test suite inside the container:

```bash
docker run --rm javacoverage-app
```

This container image is configured to build the project and run tests in a Maven environment.
 
