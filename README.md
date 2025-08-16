# GitHub CRUD (BDD) with Rest Assured + Cucumber + TestNG

A small API-testing project that performs **CRUD operations on GitHub repositories** using **Rest Assured**, written in **Java**, and organized in **Cucumber (BDD)** with **TestNG** runner.

> ⚠️ **Safety first:** This suite hits the real GitHub REST API (create/update/delete). Run it only with a **throwaway/test account** or against disposable repos.

## Stack
- Java (11+ recommended)
- Maven
- Rest Assured 5.x
- Cucumber 7.x (Gherkin BDD)
- TestNG
- Jackson Databind

## Project layout
```
src/
  main/java/genericLibraries/
    AutoConstant.java      # Endpoints + path to property file
    DataUtilities.java     # Reads test data from properties
    PojoClass.java         # Payload for repo name/description
  test/java/
    runner/TestRunner.java # Cucumber + TestNG runner
    stepDefinitions/       # CRUD step definitions
  test/resources/
    features/GitHubRepo.feature
    Data.properties        # (ignored) your local secrets and data
pom.xml
```

## Configuration
Create `src/test/resources/Data.properties` locally (it is **.gitignored**). Use the template below and fill your values:

```
owner = <your-github-username>
reporistory = <an-existing-repo-to-GET>     # (spelling kept to match code)
token = <a-personal-access-token>
reponame = <name-for-new-repo>
repodescription = <initial-description>
updatedrepodescription = <updated-description>
```

> 🔐 **Never commit tokens.** Keep `Data.properties` local. If a token ever leaks, **revoke/rotate** it immediately.

## How it works
- **GET** `/repos/{owner}/{repo}` — verifies an existing repo is retrievable
- **POST** `/user/repos` — creates a new repo using `reponame`/`repodescription`
- **PATCH** `/repos/{owner}/{repo}` — updates description
- **DELETE** `/repos/{owner}/{repo}` — deletes the created repo

All four scenarios are defined in `src/test/resources/features/GitHubRepo.feature` and glued to step definitions under `src/test/java/stepDefinitions/`.

## Run
```bash
# from the project root
mvn -q test
```

Reports:
- Cucumber report: `target/CucumberReport.html`
- (If enabled) surefire reports under `target/surefire-reports`

## CI (safe mode)
This repo includes a minimal GitHub Actions workflow that **builds** the project but **skips tests** (to avoid creating/deleting live repos). You can run the tests locally instead.

If you want CI to run the tests, consider:
1) using a sandbox GitHub account,
2) scoping a fine-grained token via repository secrets, and
3) generating unique repo names per run and cleaning up after.

## Notes & Improvements
- Keep `Data.properties` out of version control (already ignored).
- Consider refactoring `DataUtilities` to support environment variables for CI.
- Tag the BDD scenarios (e.g., `@local`) and configure your runner to exclude them in CI.
- Add negative tests (e.g., 401/403/404).
- Validate response bodies beyond status codes.
- Parameterize repo names to avoid collisions.

---

Made with ❤️ for API testing practice.
