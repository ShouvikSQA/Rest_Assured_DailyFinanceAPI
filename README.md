# Daily Finance API Automation Using Rest Assured
This project automates key functions of the Daily Finance APIs using Rest Assured. Here I have collected all the APIs by inspecting network tab of Daily Finance Website.
Comprehensive test suites are executed with TestNG, and detailed reports are generated with Allure, providing clear insights into the API Automation and Chaining.

## Problem scenario 
Do following steps :

1. Visit the url https://dailyfinance.roadtocareer.net/

2. Create a Postman collection by inspecting the API requests for the following features from the Network tab:

    - Register a new user  
    - Login by admin  
    - Get user list  
    - Search the new user by user ID  
    - Edit the user info (e.g., `firstname`, `phonenumber`)  
    - Login by any user  
    - Get item list  
    - Add any item  
    - Edit any item name  
    - Delete any item from the item list


3. Now automate the collection using Rest Assured. Add necessary negative test cases while needed.

## Technology used:
- Java
- Intellij idea

## Framework used:
  - TestNG
  - Rest Assured
  - Allure


## How to Run the Project
1. Clone this project
2. Open cmd in the root folder.
3. Give the following command:  _````gradle clean test````_

## To generate Allure Report:
1. Open cmd in the root folder.
2. Give the following commands:
   
  *  _````allure generate allure-reports --clean -output````_
  *  _````allure serve allure-results````_
## API Collection Documentation
  [Click Here To See the API Collection Documentation ](https://documenter.getpostman.com/view/28923318/2sB2j7c8q3)
## Test Cases For API
   [Click Here to see the Test Cases for API Collection](https://docs.google.com/spreadsheets/d/1RUCVZOHitbYrhqRl6NeQlEx0172XidJ3/edit?usp=sharing&ouid=108139447743460312613&rtpof=true&sd=true)
## Allure Report
![image](https://github.com/user-attachments/assets/59795a38-ef1d-4c5c-b2bc-0ec23ff8e605)
![image](https://github.com/user-attachments/assets/e59a5deb-c01b-4787-a78b-e9480584ff3b)


