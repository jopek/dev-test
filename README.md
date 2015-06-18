Proposed solution
=================

This solution does not involve any application frameworks.
It uses libraries for the HTTP client and JSON conversion, Apache HttpComponents Client and FasterXML jackson respectively.
The build tool of choice is Gradle.

## using the tool

[Gradle](https://gradle.org/) is required to compile the sources.
Otherwise, if you don't want to compile from sources, download the [fat jar](https://github.com/jopek/dev-test/releases/download/1.0.1/GoEuroTest.jar) and run the following command:

    java -jar GoEuroTest.jar "CITY_NAME"

In case you have another webservice running that responds with the same JSON structure, you can pass the ```apiurl``` property:

    java -Dapiurl=http://localhost:8000/apiresponses_bkk.json -jar GoEuroTest.jar "CITY_NAME"

(In this particular example, the "CITY_NAME" is not passed to the URL. The first found ```%s``` will be replaced with the "CITY_NAME": ```java -Dapiurl=http://localhost:8000/rest_%s.json -jar GoEuroTest.jar berlin``` will call ```http://localhost:8000/rest_berlin.json``` )


Java Developer Test
===================

The purpose of this test is to see how you approach a problem and what your solutions look like. The requirements for this test are simple and should be straightforward to grasp. When implementing a solution, please keep things simple but well engineered - as you would do for production code. 
That said:
Implement an API query and transform this data into a csv file
Create a Java command line tool that takes as an input parameter a string

    java -jar GoEuroTest.jar "CITY_NAME"

The program takes this string and queries with it our Location JSON API:
The app should use this API endpoint:

    http://api.goeuro.com/api/v2/position/suggest/en/CITY_NAME

Where CITY_NAME is the string that the user has entered as a parameter when calling the tool, e.g.

    http://api.goeuro.com/api/v2/position/suggest/en/Berlin

The API endpoint returns JSON documents like these:

```javascript
[

 {

 _id: 377078,
 key: null,
 name: "Potsdam",
 fullName: "Potsdam, Germany",
 iata_airport_code: null,
 type: "location",
 country: "Germany",

 geo_position: {
 latitude: 52.39886,
 longitude: 13.06566
 },
 location_id: 377078,
 inEurope: true,
 countryCode: "DE",
 coreCountry: true,
 distance: null
 },

 {
 _id: 410978,
 key: null,
 name: "Potsdam",
 fullName: "Potsdam, USA",
 iata_airport_code: null,
 type: "location",
 country: "USA",

 geo_position: {
 latitude: 44.66978,
 longitude: -74.98131
 },

 location_id: 410978,
 inEurope: false,
 countryCode: "US",
 coreCountry: false,
 distance: null
 }
 ]
```

The endpoint always responds with a JSON array that contains JSON objects as elements. Each object, among other keys, has a name and a geo_position key.
The geo_position key is an object with latitude and longitude fields.
If no matches are found an empty JSON array is returned.

The program should query the API with the user input and create a CSV file from it. The CSV file should have the form: ```_id, name, type, latitude, longitude```

Your solution
=============

Please implement your solution as a stand alone application which can be started from the command line, i.e. send us a fat jar file with all dependencies. You can use Java 7 and open source libraries that you think help you to fulfill this task.
Also send us the source code to your solution. We use GitHub, so if you put your source code into a GitHub repository, it will make our life easier.
We will evaluate your source code as well as the functionality of the program: Does it run, how does it handle errors, how well-engineered is the architecture etc.
Thank you!
