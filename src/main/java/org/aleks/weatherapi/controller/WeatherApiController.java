package org.aleks.weatherapi.controller;

import org.aleks.weatherapi.client.WeatherClient;
import org.aleks.weatherapi.model.Weather;
import org.aleks.weatherapi.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class WeatherApiController {

    final WeatherClient weatherClient;

    @Autowired
    public WeatherApiController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/weather/{city}")
    public ResponseEntity<?> getWeatherForCity(@PathVariable String city) {
        try {
            Weather weather = weatherClient.getWeatherForCity(city);
            return ResponseEntity.ok(weather);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST){
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse("Weather data could not be found for " + city));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_GATEWAY)
                        .body(new ErrorResponse("Error contacting external weather service"));
            }
        }
    }
}