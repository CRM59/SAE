package nextoo.weather.sport.app.data.dto;

import nextoo.weather.sport.app.models.WeatherType;

import java.util.List;


public class SportDTO {

    private String name;

    private boolean indoor;

    private boolean outdoor;

    private List<WeatherType> weatherTypes;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public boolean isOutdoor() {
        return outdoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public List<WeatherType> getWeatherTypes() {
        return weatherTypes;
    }

    public void setWeatherTypes(List<WeatherType> weatherTypes) {
        this.weatherTypes = weatherTypes;
    }
}
