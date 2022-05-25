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
