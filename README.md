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
- http://34.72.241.183:90/map/Taiwan
  - This will return a map image
- Prometheus: http://34.125.79.181:9090/
- Grafana: http://34.125.79.181:3000/
