Feature:
  As a user I would like to login to the website.
  Place a bet of 0.05£ on any football event.
  Verify the values of "To return" and "Total stake" on the bet receipt.

Scenario Outline: Place a bet on any sports and verify (Happy Path)
  Given I navigate to "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html" with browser type <browser_type>
  And I login with username "WHATA_FOG2" and password "F0gUaTtest"
  And I navigate to any <sports_type> event
  When I add the first active selection to the betslip
  And Place a <bet_value> £ bet and assert the following
  Then Verify the return value on the bet receipt
  And Verify stake value on the bet receipt
  And User balance must be 0.05£ less than what it was
  And I logged off
  Examples:
  | sports_type     | bet_value  | browser_type |
  | "nav-football"  | "0.05"     | "normal"     |
  | "nav-football"  | "0.05"     | "mobile"     |
  | "nav-tennis"    | "0.05"     | "normal"     |

Scenario Outline: Warning for Insufficient funds will be displayed, if customer bets for more than whats in the account
    Given I navigate to "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html" with browser type <browser_type>
    And I login with username "WHATA_FOG2" and password "F0gUaTtest"
    And I navigate to any <sports_type> event
    When I add the first active selection to the betslip
    And Place a <bet_value> £ bet and assert the following
    Then the warning message "Alert - your bet has not been placed" will be displayed
    And I logged off
    Examples:
    | sports_type     | bet_value   | browser_type |
    | "nav-football"  | "999999.00" | "normal"     |
    | "nav-football"  | "999999.00" | "mobile"     |
    | "nav-tennis"    | "999999.00" | "normal"     |

Scenario Outline: A notification message must appear in the bet slip are where there are no active slips
  Given I navigate to "http://sports.williamhill.com/sr-admin-set-white-list-cookie.html" with browser type <browser_type>
  And I navigate to any <sports_type> event
  When I added the first active selection to the betslip
  Then I can clear the betslip
  And Verify that "Your bet slip is currently empty" appears in bet slip section
  Examples:
  | sports_type     | browser_type |
  | "nav-football"  | "normal"     |
  | "nav-football"  | "mobile"     |
  | "nav-tennis"    | "normal"     |

# There are more areas to cover and the above is just to give an idea of the framework e.g.
# Free bets and their messages (but it cannot be tested due to lack of business knowledge thats applied their)
# Test for multiple slips on multiple sports
# Verification of content on the betslip that it matches its respective active first bet section details
# Lower and upper limit of the betvalue