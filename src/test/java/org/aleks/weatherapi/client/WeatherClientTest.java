package org.aleks.weatherapi.client;

import org.aleks.weatherapi.model.Weather;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherClientTest {
    final String expectedCity = "София, България";
    final String expectedDescription = "Cooling down with a chance of rain multiple days & a chance of snow Sunday.";
    final List<Double> expectedMinTemps = Arrays.asList(-0.1, 1.3, 2.3, 2.9, 2.7, -0.2, -1.9, -2.2, 0.2, 0.2, 1.3, 3.1, 5.2, 7.1, 7.4);
    final List<Double> expectedMaxTemps = Arrays.asList(9.9, 8.2, 8.7, 8.7, 5.2, 5.6, 2.2, 4.1, 5.9, 8.8, 11.3, 13.7, 15.1, 15.3, 15.9);
    final List<Double> expectedAverageTemps = Arrays.asList(3.5, 4.1, 5.0, 5.3, 4.1, 1.7, -0.2, 0.5, 2.9, 4.2, 5.9, 7.9, 9.9, 10.9, 11.1);
    final List<Double> precipitationChances = Arrays.asList(100.0, 0.0, 45.2, 45.2, 58.1, 61.3, 48.4, 35.5, 16.1, 16.1, 6.5, 3.2, 12.9, 12.9, 32.3);

    @Test
    public void weatherClientCorrectlyMapsJsonToWeather() throws IOException {
        WeatherClient weatherClient = new WeatherClient();
        File jsonResponse = new File("src/test/resources/jsonTestResponse.json");

        Weather weather = weatherClient.mapJsonToWeather(Files.readString(jsonResponse.toPath()));

        assertEquals(weather.getCity(), expectedCity);
        assertEquals(weather.getDescription(), expectedDescription);
        assertEquals(weather.getMinTemps(), expectedMinTemps);
        assertEquals(weather.getMaxTemps(), expectedMaxTemps);
        assertEquals(weather.getAverageTemps(), expectedAverageTemps);
        assertEquals(weather.getPrecipitationChances(), precipitationChances);
    }
}
