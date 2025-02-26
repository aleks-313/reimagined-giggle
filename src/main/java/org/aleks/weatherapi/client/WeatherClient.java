package org.aleks.weatherapi.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aleks.weatherapi.model.Weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherClient {
    @Value("${weather.api.key}")
    private String apiKey;
    @Value("${weather.api.base.url}")
    private String baseURL;

    @Cacheable(value = "WeatherCache", key="{#city}")
    public Weather getWeatherForCity(String city) {
        RestClient restClient = RestClient.builder()
                .baseUrl(baseURL + city + "/?key=" + apiKey)
                .build();

        String jsonResponse = restClient.get().retrieve().body(String.class);
        return mapJsonToWeather(jsonResponse);
    }

    public Weather mapJsonToWeather(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to parse json response", e);
        }

        String city = jsonNode.get("resolvedAddress").asText();
        String description = jsonNode.get("description").asText();
        List<Double> minTemps = new ArrayList<>();
        List<Double> maxTemps = new ArrayList<>();
        List<Double> averageTemps = new ArrayList<>();
        List<Double> precipitationChances = new ArrayList<>();

        for (JsonNode dayNode : jsonNode.get("days")){
            minTemps.add(dayNode.get("tempmin").asDouble());
            maxTemps.add(dayNode.get("tempmax").asDouble());
            averageTemps.add(dayNode.get("temp").asDouble());
            precipitationChances.add(dayNode.get("precipprob").asDouble());
        }

        return new Weather(city, description, minTemps, maxTemps, averageTemps, precipitationChances);
    }
}
