Feature: Light switch 
Scenario: Clicking on the light 
    Given User navigates to lightswitchpage
    When I click the light switch
    Then I should see the light turned on at intensity 3