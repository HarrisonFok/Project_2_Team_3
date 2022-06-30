# TravelAdvisory
Revature Training: Project2

API 1:
  Output location status:
    - get covid info from https://disease.sh/
    - convert info into a score using algorithm
    - save score in DB
    
API 2:
  Location search:
    - Use https://developers.google.com/maps/documentation/places/web-service/overview  OR https://developers.google.com/maps/documentation/maps-static 
    to get a location
      - parse google map location info into something API 1 can use
    - use API 1 to get that location's score
    - Output location and all relevant info

Sample calls (the link might not work anymore, since I may have paused GKE already): 
- http://34.72.241.183:90/map/structured?street=20 W 34th St&city=New York&county=New York County&state=New York&country=United States of America&postalCode=10001&format=json
- http://34.72.241.183:90/map/state-info?state=Texas&format=json&countrycodes=us
- http://34.72.241.183:90/map/landmark?q=Canada
- http://34.72.241.183:90/map/taiwan
  - This will return a map image
  ![image](https://user-images.githubusercontent.com/28497032/176506007-d9eca2f0-a4a0-48c9-a124-5a1d341002d7.png)
- Prometheus: http://34.125.79.181:9090/
- Grafana: http://34.125.79.181:3000/
- Jenkins: http://34.125.79.181:8080/

Sonar Cloud:
![image](https://user-images.githubusercontent.com/28497032/176509646-13b4a352-7c6b-458b-9b1f-81fd3093faba.png)
- https://sonarcloud.io/project/overview?id=com.Group_3%3AProject_2

Google Cloud Platform:
![image](https://user-images.githubusercontent.com/28497032/176734168-9ee44c35-855f-45a0-92d8-4578fb2502e1.png)
![image](https://user-images.githubusercontent.com/28497032/176734238-b8c722ba-cc5b-446a-b109-4e48983a29d8.png)
![image](https://user-images.githubusercontent.com/28497032/176734317-9990a8ed-104e-439a-8226-e0a1f10af3f3.png)


