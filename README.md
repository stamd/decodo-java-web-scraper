This shows how to set up and run the example Java web-scraping scripts from this repository using **IntelliJ IDEA** and **Maven**.

> **Note:** The project was created and tested in IntelliJ IDEA with Maven. Other IDEs (like VS Code) may require additional setup and are not covered here.

## Prerequisites

Before you start, make sure you have:

- **Java JDK** installed (e.g. 17 or newer)
- **IntelliJ IDEA** (Community Edition is enough)
- **Git** (optional, if you want to clone the repo instead of downloading ZIP)
- A working internet connection (Maven needs to download dependencies)

You can verify Java with:

```bash
java -version
````

## 1. Create a New Maven Project in IntelliJ IDEA

1. Open **IntelliJ IDEA**
2. On the welcome screen, choose **“New Project”**
3. For a build system, select **“Maven”**
4. Check "Add sample code
5. Click **Create**
6. IntelliJ will create a sample Maven project with:

   * `pom.xml`
   * `src/main/java/org/example/Main.java`

![](https://i.imgur.com/2wXVCHj.png)*

## 2. Replace the Generated Files with the Repository Files

### Update `pom.xml`

1. Open the **GitHub repository** in your browser
2. Open the `pom.xml` file from the repo
3. Copy its entire contents
4. In IntelliJ, open the `pom.xml` that was generated when you created the Maven project
5. **Replace** the IntelliJ-generated contents with the contents from GitHub
6. Save the file

This step ensures all necessary dependencies (e.g. **Jsoup**, **Gson**, **OpenCSV**, etc.) are declared.

### Update `Main.java`

1. In the GitHub repo, open `Main.java` 

2. Copy its entire contents

3. In IntelliJ, open the `Main.java` file created by the project wizard:

   * Path should be something like:
     `**src/main/java/org/example/Main.java**`

4. **Replace** the contents with the code from GitHub.

5. Make sure the `package` line at the top matches your project package, e.g.:

   ```java
   package org.example;
   ```

6. Save the file.

### Add the Other Example Classes

If the repo contains additional example files, such as:

* `MainPagination.java`
* `MainParallelScraper.java`

Add them to your IntelliJ project:

1. In IntelliJ’s **Project** view, go to:
   `src/main/java/org/example`

2. Right-click on the `org.example` package > **New > Java Class**.

3. Name the class exactly as in the repo, e.g.:

   * `MainPagination`
   * `MainParallelScraper`

4. For each class:

   * Open the corresponding file on GitHub.
   * Copy its entire contents.
   * Paste into the new class in IntelliJ.
   * Ensure the `package` line at the top is the same as in other files, e.g.:

     ```java
     package org.example;
     ```

5. Save all files.

## 3. Update Maven Repositories in IntelliJ

To make sure Maven downloads and refreshes all dependencies:

1. Open **Settings**:

   * On Windows/Linux: **File > Settings**
   * On macOS: **IntelliJ IDEA > Settings** (or **Preferences**)
2. Go to:
   **Build, Execution, Deployment > Build Tools > Maven > Repositories**
3. You should see at least:

   * **Local** repository
   * One or more **Remote** repositories
4. Select each repository (local and remote) and click **“Update”**.
5. Click **OK** to close the dialog.

![](https://i.imgur.com/axLGcAc.png)

This forces Maven to refresh metadata and ensure it can resolve all dependencies.

## 4. Sync `pom.xml` with Maven

1. In the **Project** view, right-click on `pom.xml`.
2. Select **Maven > Sync** (or **Reimport** / **Reload Project**, depending on IntelliJ version).
3. Wait for IntelliJ to finish downloading and indexing dependencies (you’ll see progress at the bottom of the window).

![](https://i.imgur.com/n41i1Y6.png)

If everything is configured correctly, all imports like `Jsoup`, `Document`, `Elements`, `Gson`, `CSVWriter`, etc. should now resolve without errors.

## 5. Running the Examples

Each example has its own `main` method and can be run separately.

1. Open `Main.java` (or `MainPagination.java` or `MainParallelScraper.java`)
2. Right-click anywhere inside the file
3. Click **“Run 'Main.main()'”** (or similar)
4. Check the **Run** tool window for output

