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