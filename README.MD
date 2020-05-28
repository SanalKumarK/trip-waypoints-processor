Peter Griffin is a customer with Risky Insurances Inc. He pays his insurance fee based on how well he can stick to the speed limits while driving.

Peter's car has reported Waypoints from a trip (e.g. where it has driven). The following data is known for each Waypoint:

latitude (degrees)
longitude (degrees)
speed (meters per second)
speed_limit (meters per second)
timestamp (ISO 8601 date-time)
The data for Peter's Waypoints needs to be organized in these categories, to make it possible to decide his insurance fee:

Distance Speeding
Duration Speeding
Total Distance
Total Duration

Please follow the below instructions to run the application.
1) Downlod the zip file or clone the repository:
2) git clone https://github.com/SanalKumarK/trip-waypoints-processor.git
3) Open a terminal and navigate to the downloaded folder.
4) run *gradlew clean build*
    Runs the 
5) cd build/libs
6) run java -jar springworks-test-1.0-SNAPSHOT.jar <path_to_waypoints_json>
   1) eg: java -jar springworks-test-1.0-SNAPSHOT.jar C:\waypoints.json
   2) Trip Report will be printed in the console as below.
   TripReport{distanceSpeeding='67.67430454853171 meters', durationSpeeding='5.0 seconds', totalDuration='20.0 seconds', totalDistance='201.132793549162 meters'}    