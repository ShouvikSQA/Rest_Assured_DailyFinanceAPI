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


4. Now automate the collection using Rest Assured. Add necessary negative test cases while needed
