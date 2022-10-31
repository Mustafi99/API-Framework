Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify if place is being Successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>" "<website>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
	Examples:
	|name| language| address|website|
	|RahamanHouse|English|Murshidabad|www.rahaman.com|
#	|Mustafi|Bengali|Kolkata|www.google.com|

@DeletePlace
Scenario: Verify if Delete place functionality working fine using deletePlaceAPI
	Given DeletePlace payload 
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"