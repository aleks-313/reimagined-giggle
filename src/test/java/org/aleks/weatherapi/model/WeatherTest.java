package org.aleks.weatherapi.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class WeatherTest {

    @Test
    public void weatherCorrectlyConvertsFtoC(){
        List<Double> minTemps = Arrays.asList(60.0, 50.4, 77.1);
        List<Double> maxTemps = Arrays.asList(80.1, 70.3, 91.9);
        List<Double> averageTemps = Arrays.asList(72.0, 62.2, 84.7);
        List<Double> precipitationChances = Arrays.asList(12.1, 6.2, 0.0);
        String description = "A bit cloudy, probably no rain";
        String city = "Las Vegas";

        Weather weather = new Weather(city, description, minTemps, maxTemps, averageTemps, precipitationChances);

        assertEquals(weather.getMinTemps(), Arrays.asList(15.6, 10.2, 25.1));
        assertEquals(weather.getMaxTemps(), Arrays.asList(26.7, 21.3, 33.3));
        assertEquals(weather.getAverageTemps(), Arrays.asList(22.2, 16.8, 29.3));
    }
}
