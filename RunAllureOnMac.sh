#!/bin/sh
echo "------ Step 01: Set project path to variable -------"
project_path="/Users/tminht/Desktop/Automation FC/03-Java Framework"
echo "------ Step 02: Go to project path folder -------"
cd "$project_path"
echo "------ Step 03: Run the testcases -------"
java -javaagent:"$project_path/libAllure/aspectjweaver-1.8.10.jar" -classpath "$project_path/bin:$project_path/libAllure/*:$project_path/libExtendV2/*:$project_path/libLog4J/*:$project_path/libSelenium/*:$project_path/libReportNG/*:$project_path/libWebDriverManager/*" org.testng.TestNG "$project_path/bin/runUserNopCommerceTestcases.xml"
echo "------ Step 04: Load allure command line setting -------"
allure --version
echo "------ Step 05: Generate Allure HTML Report -------"
allure serve ./allure-json/