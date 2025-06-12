package nextoo.weather.sport.app.data.repositories;

import nextoo.weather.sport.app.config.Database;

public class WeatherRepository {

    private static Database database;

    public static void setDatabase(Database database) {
        WeatherRepository.database = database;
    }


    public void updateCityName(String cityName) {
        if (database.getCityName() != null) database.setCityName(cityName);
    }

    public void addCityName(String cityName) {
        if (database.getCityName() == null) database.setCityName(cityName);
    }

    public void updateNbDays(int nbDays) {
        if (database.getNbDays() != -1) database.setNbDays(nbDays);
    }

    public void addNbDays(int nbDays) {
        if (database.getNbDays() == -1) database.setNbDays(nbDays);
    }

    public void removeInfos() {
        database.setCityName(null);
        database.setNbDays(-1);
    }
}
