Feature: LG Account Creation in Registration Page with random email and verifies the account from mailBox
       
 Scenario: Generates Random email, creates Account and Verifies from Mailbox
 Given  User navigates to Email Site and generates random mail 
 And    User navigates to LG Registration SignUp Page and enters all of required details
 And    Selects the Terms and conditions for Account Creation
 When   Clicks on Signup Button
 Then   LG Account should be created with provided email ID
 And    Activate the account from Mailbox

 
