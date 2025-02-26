Weather-API

This is a simple solution to the task https://roadmap.sh/projects/weather-api-wrapper-service
It's a wrapper for the VisualCrossing weather API.
API responses are cached thanks to Caffeine.

Just clone it, run it, and access the api by going to
localhost:8080/weather/Rome    (or whatever other city you want)

The response will contain the name of the city, a short description of the weather for the week, and the min, max, and avg temperatures for the next 15 days, together with the precipitation chance for those days.

IMPORTANT!
For the application to work, you must create an account on VisualCrossing (totally free) and copy paste your API key (found in your profile section) in application.properties
