Feature: API User Creation

  # Scenarios for validating user creation functionality in the API
  @ApiUserCreation
  Scenario Outline: User Creation Flow
    Given request with name "<name>", email "<emailPrefix>" and password "<password>"
    When Post call is made
    Then the status code for this is <statusCode>
    And the error message appears "<message>"

    Examples:
      | name   | emailPrefix | password      | statusCode | message                                           |
      | Donna   | ella.storch2311@gmail.com   | PrettyBabu  | 201        | User Created                                     |
      | Donna  |   ella.storch2311@gmail.com  | PrettyBabu  | 200        | The email address you have entered is already registered |


  @ApiUserCreationInvalidFields
  Scenario Outline: User Creation with Invalid or Missing Fields
    Given request is prepared with name "<name>", email "<email>" and password "<password>"
    When Post call is made
    Then the status code for this is <status_code>
    And the error message appears "<error_message>"

    Examples:
      | name  | email                  | password     | status_code | error_message            |
      | Ella  | Ella.storch231l.com    | PrettyBabu   | 400         | Invalid Email            |
      | Ella  | Ella@gmail.com         |              | 400         | Please fill all inputs   |
      |       | Ella@gmail.com         | PrettyBabu   | 400         | Please fill all inputs   |

