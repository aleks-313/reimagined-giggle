package org.aleks.weatherapi.model;

import lombok.Data;

import java.util.List;

@Data
public class Weather {
    private String city;
    private String description;
    private List<Double> minTemps;
    private List<Double> maxTemps;
    private List<Double> averageTemps;
    private List<Double> precipitationChances;

    public Weather(String city, String description, List<Double> minTemps, List<Double> maxTemps, List<Double> averageTemps, List<Double> precipitationChances) {
        this.city = city;
        this.description = description;
        this.minTemps = convertFtoC(minTemps);
        this.maxTemps = convertFtoC(maxTemps);
        this.averageTemps = convertFtoC(averageTemps);
        this.precipitationChances = precipitationChances;
    }

    private List<Double> convertFtoC(List<Double> fTemps) {
        for (int i = 0; i < fTemps.size(); i++) {
            fTemps.set(i, (fTemps.get(i) - 32) * 5 / 9);
            fTemps.set(i, Math.round(fTemps.get(i) * 10) / 10.0);
        }

        return fTemps;
    }
}
