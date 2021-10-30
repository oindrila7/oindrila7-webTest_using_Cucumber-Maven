Feature: Login to an e-commerce website

  Scenario Outline: Verify users can login to portal with valid credentials
    Given User visits e-commerce website
    When User enters valid email "<email>" and valid password "<password>"
    Then User can logged in successfully
    Examples:
      |email|password|
      |jam123@gmail.com|easy1234|


  Scenario Outline: Verify users cannot login to portal without valid credentials
    Given User visits e-commerce website
    When User enters invalid email "<email>" and invalid password "<password>"
    Then User can not logged in
    Examples:
      |email|password|
      |wrong12@test.com|password123|
      |tor1265@test.com|password123|
      | | |