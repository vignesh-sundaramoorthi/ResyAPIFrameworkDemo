Feature: Validating Place APIs
  @AddPlace
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given Add Place payload with "<name>" "<language>"  "<address>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
        |name     |language | address     |
        |28 house  |french  | point cook  |
        #|29 House  |spanish | seabrook    |

  @DeletePlace
  Scenario: Verify if Delete place functionality is working
    Given DeletePlace payload
    When user calls "deletePlaceAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"