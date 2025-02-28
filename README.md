# Hybrid TestNG Framework

## Overview
The **Hybrid TestNG Framework** is a robust automation framework that combines **Data-Driven Testing**, **Page Object Model (POM)**, **Extent Reports**, and **Jenkins Integration** to enable efficient test execution.

---
## Project Structure
TutorialNinjaProj/
├── src/main/java # Main source files
│   ├── com.tutorialsninja.qa.base
│   │   ├── Base.java # Base class for test setup
│   ├── com.tutorialsninja.qa.config
│   │   ├── config.properties # Configuration properties file
│   ├── com.tutorialsninja.qa.listeners
│   │   ├── MyListeners.java # TestNG Listeners implementation
│   ├── com.tutorialsninja.qa.pages
│   │   ├── AccountPage.java # Page Object for Account
│   │   ├── AccountSuccessPage.java # Page Object for Account Success
│   │   ├── HomePage.java # Page Object for Home Page
│   │   ├── LoginPage.java # Page Object for Login
│   │   ├── RegisterPage.java # Page Object for Register
│   │   ├── SearchPage.java # Page Object for Search
│   ├── com.tutorialsninja.qa.testdata
│   │   ├── testdata.properties # Test data properties file
│   │   ├── TutorialsNinjaTestData.xlsx # Excel test data file
│   ├── com.tutorialsninja.qa.utils
│   │   ├── ExtentReporter.java # Extent Report integration
│   │   ├── Utilities.java # Utility methods
├── src/test/java # Test source files
│   ├── com.tutorialsninja.qa.testcases
│   │   ├── LoginTest.java # Login test cases
│   │   ├── RegisterTest.java # Register test cases
│   │   ├── SearchTest.java # Search test cases
├── src/test/resources # Test resource files
│   ├── testing.xml # TestNG test suite configuration
│   ├── Screenshots # Screenshots folder
│   │   ├── verifySearchWithInvalidProduct.png # Example screenshot
├── test-output # Test output folder
│   ├── ExtentReports # Extent Reports output
│   │   ├── extentReport.html # Test execution report
├── pom.xml # Maven Project Object Model
├── README.md # Project documentation

## 1. Setting Up the Framework

### **Create a Maven Project**
- Use `maven-archetype-quickstart`.
- Add the following dependencies in `pom.xml`:
  - **TestNG** (`org.testng:testng`)
  - **Selenium Java** (`org.seleniumhq.selenium:selenium-java`)
  - **Extent Reports** for test reporting
- Remove JUnit dependencies (if present).

### **Install TestNG Plugin in Eclipse**
- Go to **Maven Repository** and add TestNG Library to pom.xml file.

---

## 2. Automating Test Cases

### **Login Test Cases:**
- Login with **valid credentials**
- Login with **invalid credentials**
- Login with **invalid email and valid password**
- Login with **valid email and invalid password**
- Login with **empty fields**

### **Register Test Cases:**
- Register **with mandatory fields**
- Register **with all fields**
- Register with **duplicate email**
- Register with **empty fields**

### **Create Utility & Base Classes:**
- **Utility Class:** Manages reusable functions(`generateEmailWithTimeStampMethod()` is moved).
- **Base Class:** Handles browser setup (initializing browser and opening application URL).

---

## 3. Running & Managing Test Cases

### **Run Tests in Batch:**
- Use `testng.xml` for batch execution.
- Organize test files under `src/test/resources`.

### **Remove Hardcoding:**
- Store browser, URL, and credentials in `config.properties`.
- Read data using `testdata.properties` class.

### **Implement Data-Driven Testing:**
- Store test data in Excel (`TutorialsNinjaTestData.xlsx`).
- Use **Apache POI** to read data dynamically.

---

## 4. Optimizing & Enhancing the Framework

### **Implement Page Object Model (POM) & Page Factory:**
- **POM:** Create separate **Page classes** for Login, Registration, etc.
- **Page Factory:** Use `@FindBy` for locating elements and `PageFactory.initElements(driver, this)` for initializing elements automatically.

### **Reduce Redundant Code:**
- Move browser setup, URL initialization, and common functions to **Base Class**.

### **Implement Listeners:**
- Capture screenshots on test failures using `ITestListener`.

---

## 5. Extent Reports & Logging

### **Generate Extent Reports:**
1. Configure Extent Reports in the ExtentReport Class in Utils package.
2. Embed screenshots for failed test cases:
   ```java
   String screenshotPath = "path/to/screenshot.png";
   test.addScreenCaptureFromPath(screenshotPath);
   ```
3. Auto-launching extent report after test execution.

---

## 6. Jenkins Integration

### **Install & Run Jenkins:**
- Download Jenkin.war file.
- Run Jenkins using:
  ```sh
  java -jar jenkins.war 
  ```
- Open Jenkins in a browser: `http://localhost:8080/`.
- To run Jenkins at different port:  `java -jar jenkins.war --httpPort=9192`.

### **Configure Jenkins for Test Execution:**
1. **Install Plugins:** TestNG.
2. **Create a Job:**
   - Choose **Freestyle Project**.
   - Enter Description
   - Build Steps
       - Invoke top-level Maven targets
         - Maven Version: MAVEN_HOME
         - Goals: path of the project
       - Post-build Actions
         -  Publish TestNG Results
3. **Run Tests via Jenkins:**
   - Select Triggers
     - Build periodically
   - Save and Apply
   - Select Build Now to run  
---

## 7. Git & GitHub Workflow

### **Set Up Git & GitHub:(Lead)**
1. **Create a GitHub Repository.**
   - Open Git Repositories
   - Team > Share Project
       - Create New Repository
   - Team > Add to Index
       - This adds the project to Staging area
   - Team > Commit
       - This adds the project to Local Repo
  #This creates the remote Repo at GitHub - Copy Rep git URL
   - Team > Remote > Push
      - Give the above copied URL
      - Provide Username and Password of GitHub
      - Next > SelectMasterBranch > AddSpec
      - Finish
          - Username: user GitHub email id
          - Password:
              -  `https://github.com/settings/tokens`
              -  Generated token code in place of password
   -  Invite the Team members as collaborator for that Repo
3. **Clone the Repository Locally:(Testers under Lead)**
   - Testershould accept the invitation sent by the Lead to become the collaborator for that Repo
   -  Search for Git Repositories in Eclipse IDE
   -  Clone a Git Repository using Import the Repository to the local workspace
   -  Update changes in existing files
   -  Team > Add to Index
   -  Git Repo > Branches > Switch to new branch
   -  Team > Commit #Commit will go for new branch
   -  Select new branch > Push Branch
       - Username: tester GitHub email id
       - Password:
              -  `https://github.com/settings/tokens`
              -  Generated token code in place of password
   - Check whether the code pushed to new Branch
   - Create Pull Request

4. **Pull the Code:**
   - Go to Pull Requests tab
   - Merge the changes to the Main branch

### **GitHub & Jenkins Integration:**
1. **Install Plugins:** Maven.
2. **Create a Job:**
   - Choose **Maven Project**.
   - Enter Description
   - Source Code Management
       - Select Git
       - Add Repository URL
   - Build Steps
       - Invoke top-level Maven targets
         - Maven Version: MAVEN_HOME
         - Goals: path of the project
   - Triggers
     - Select Build periodically
   - Post-build Actions
     -  Publish TestNG Results
   - Save and Apply  
3. **Run Tests via Jenkins:**
   - Select Build Now to run 

---

## **Final Workflow of the Framework**
1. **Develop Tests using TestNG & Selenium.**
2. **Store test data in Excel for Data-Driven Testing.**
3. **Use Page Object Model (POM) for better structure.**
4. **Generate Extent Reports for test execution results.**
5. **Run tests automatically using Jenkins.**
6. **Use GitHub for version control and CI/CD integration.**
