# Hybrid TestNG Framework

## Overview
The **Hybrid TestNG Framework** is a robust automation framework that combines **Data-Driven Testing**, **Page Object Model (POM)**, **Extent Reports**, and **Jenkins Integration** to enable efficient test execution.

---
## Project Structure
TutorialNinjaProj/
├── src/main/java #main source file
    ├── com.tutorialsninja.qa.base #Base file
        ├── Base.java #base java code
    ├── com.tutorialsninja.qa.config #properties file
        ├── config.properties #properties java code
    ├── com.tutorialsninja.qa.listeners #listeners file
        ├── MyListeners.java #listeners java code
    ├── com.tutorialsninja.qa.pages
        ├── AccountPage.java
        ├── AcccountSuccessPage.java
        ├── HomePage.java
        ├── LoginPage.java
        ├── RegisterPage.java
        ├── SearchPage.java
    ├── com.tutorialsninja.qa.testdata
        ├── testdata..properties
        ├── TutorialsNinjaTestData.xlsx
    ├── com.tutorialsninja.qa.utils
        ├── ExtentReporter.java
        ├── Utilities.java
├── src/test/java #test source file
    ├── com.tutorialsninja.qa.testcases #testcases file
        ├── LoginTest.java #login java code
        ├── RegisterTest.java #register java code
        ├── SearchTest.java #search java code
├── src/test/resources #test resource file
    ├── testing.xml 
├── Screenshots
    ├── verifySearchWithInvalidProduct.png
├── test-output
    ├── ExtentReports
        ├── extentReport.html
├── pom.xml
├── README.md
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
   - Link to the GitHub repository.
3. **Run Tests via Jenkins:**
   - Configure **Maven Goals:**
     ```sh
     clean test
     ```
   - Schedule periodic test runs using **cron jobs**.

---

## 7. Git & GitHub Workflow

### **Set Up Git & GitHub:**
1. **Create a GitHub Repository.**
2. **Clone the Repository Locally:**
   ```sh
   git clone https://github.com/username/repo.git
   ```
3. **Add & Commit Code:**
   ```sh
   git add .  
   git commit -m "Initial Commit"  
   git push origin main  
   ```

### **Branching & Merging:**
1. **Create a New Branch:**
   ```sh
   git checkout -b feature-branch  
   ```
2. **Switch Between Branches:**
   ```sh
   git checkout main  
   ```
3. **Merge Changes:**
   ```sh
   git merge feature-branch  
   ```

### **GitHub & Jenkins Integration:**
- Connect Jenkins with GitHub to trigger automated test runs on **each commit**.

---

## **Final Workflow of the Framework**
1. **Develop Tests using TestNG & Selenium.**
2. **Store test data in Excel for Data-Driven Testing.**
3. **Use Page Object Model (POM) for better structure.**
4. **Generate Extent Reports for test execution results.**
5. **Run tests automatically using Jenkins.**
6. **Use GitHub for version control and CI/CD integration.**
