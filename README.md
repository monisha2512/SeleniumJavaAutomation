# Hybrid TestNG Framework

## Overview
The **Hybrid TestNG Framework** is a robust automation framework that combines **Data-Driven Testing**, **Page Object Model (POM)**, **Extent Reports**, and **Jenkins Integration** to enable efficient test execution.

---

## 1. Setting Up the Framework

### **Create a Maven Project**
- Use `maven-archetype-quickstart`.
- Add the following dependencies in `pom.xml`:
  - **TestNG** (`org.testng:testng`)
  - **Selenium Java** (`org.seleniumhq.selenium:selenium-java`)
  - **Extent Reports** for test reporting
- Remove JUnit dependencies (if present).

### **Install TestNG Plugin in Eclipse**
- Go to **Eclipse Marketplace** and install TestNG.

---

## 2. Automating Test Cases

### **Login Test Cases:**
- Login with **valid credentials**
- Login with **invalid credentials** (wrong email/password)
- Login with **empty fields**

### **Register Test Cases:**
- Register **with valid details**
- Register **without mandatory fields**
- Register with **duplicate email**

### **Create Utility & Base Classes:**
- **Utility Class:** Manages reusable functions.
- **Base Class:** Handles browser setup (`@BeforeMethod`) and teardown (`@AfterMethod`).

---

## 3. Running & Managing Test Cases

### **Run Tests in Batch:**
- Use `testng.xml` for batch execution.
- Organize test files under `src/test/resources`.

### **Remove Hardcoding:**
- Store browser, URL, and credentials in `config.properties`.
- Read data using `Properties` class.

### **Implement Data-Driven Testing:**
- Store test data in Excel (`.xlsx`).
- Use **Apache POI** to read data dynamically.

---

## 4. Optimizing & Enhancing the Framework

### **Implement Page Object Model (POM) & Page Factory:**
- **POM:** Create separate **Page classes** for Login, Registration, etc.
- **Page Factory:** Use `@FindBy` and `PageFactory.initElements(driver, this)`.

### **Reduce Redundant Code:**
- Move browser setup, URL initialization, and common functions to **Base Class**.

### **Implement Listeners:**
- Capture screenshots on test failures using `ITestListener`.

---

## 5. Extent Reports & Logging

### **Generate Extent Reports:**
1. Configure Extent Reports in the Base Class.
2. Capture logs using **log4j** or TestNG logging.
3. Embed screenshots for failed test cases:
   ```java
   String screenshotPath = "path/to/screenshot.png";
   test.addScreenCaptureFromPath(screenshotPath);
   ```
4. Auto-generate reports after test execution.

---

## 6. Jenkins Integration

### **Install & Run Jenkins:**
- Download and install **Jenkins**.
- Start Jenkins using:
  ```sh
  java -jar jenkins.war --httpPort=9092
  ```
- Open Jenkins in a browser: `http://localhost:9092/`.

### **Configure Jenkins for Test Execution:**
1. **Install Plugins:** TestNG, Maven, Git.
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
