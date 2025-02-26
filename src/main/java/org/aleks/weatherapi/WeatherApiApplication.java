package org.aleks.weatherapi;

import org.aleks.weatherapi.client.WeatherClient;
import org.aleks.weatherapi.controller.WeatherApiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApiApplication.class, args);
    }

}
