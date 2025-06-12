package nextoo.weather.sport.app.config;

public class Database {
    private String cityName;
    private int nbDays;

    public Database() {
        this.cityName = null;
        this.nbDays = -1;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getNbDays() {
        return nbDays;
    }

    public void setNbDays(int nbDays) {
        this.nbDays = nbDays;
    }
}
