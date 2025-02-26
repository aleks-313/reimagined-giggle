package org.aleks.weatherapi.controller;

import org.aleks.weatherapi.client.WeatherClient;
import org.aleks.weatherapi.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class WeatherApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient weatherClient;

    Weather mockWeather = new Weather("Sofia", "Sunny" ,new ArrayList<>(List.of(1.1, 2.2)), new ArrayList<>(List.of(3.4, 5.2)), new ArrayList<>(List.of(2.1, 7.2)), new ArrayList<>(List.of(12.1, 24.2)));

    @Test
    void weatherApiControllerReturnsOKResponse() throws Exception {
        when(weatherClient.getWeatherForCity(anyString())).thenReturn(mockWeather);

        mockMvc.perform(get("/weather/Sofia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Sofia"))
                .andExpect(jsonPath("$.description").value("Sunny"))
                .andExpect(jsonPath("$.averageTemps").value(new ArrayList<>(List.of(-16.6, -13.8))));
    }

    @Test
    void weatherApiControllerReturnsBadRequestResponse() throws Exception {
        when(weatherClient.getWeatherForCity(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        mockMvc.perform(get("/weather/Sofia"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void weatherApiControllerReturnsBadGatewayResponse() throws Exception {
        when(weatherClient.getWeatherForCity(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_GATEWAY));

        mockMvc.perform(get("/weather/Sofia"))
                .andExpect(status().isBadGateway());
    }
}
