package com.example.user.weatherappproject;

import java.util.List;

/**
 * Created by User on 16/02/2018.
 *
 * {"coord":
 {"lon":145.77,"lat":-16.92},
 "weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}],
 "base":"cmc stations",
 "main":{"temp":293.25,"pressure":1019,"humidity":83,"temp_min":289.82,"temp_max":295.37},
 "wind":{"speed":5.1,"deg":150},
 "clouds":{"all":75},
 "rain":{"3h":3},
 "dt":1435658272,
 "sys":{"type":1,"id":8166,"message":0.0166,"country":"AU","sunrise":1435610796,"sunset":1435650870},
 "id":2172797,
 "name":"Cairns",
 "cod":200}
 */

public class WeatherData {
    private Coord coord;
    private int cod;
    private String base;
    private String name;
    private Main main;
    private List<Weather> weather;
    private Sys sys;
    private Wind wind;
    private Clouds clouds;



    public WeatherData(Coord coords, int code, String base, String name, Main main, List<Weather> weather, Sys sys,Wind wind,Clouds clouds) {
        this.coord = coords;
        this.cod = code;
        this.base = base;
        this.name = name;
        this.main = main;
        this.weather= weather;
        this.sys=sys;
        this.wind=wind;
        this.clouds=clouds;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int code) {
        this.cod = code;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord= coord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }







    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }

    public double getPressure() {
        return main.getPressure();
    }

    public double getHumidity(){
        return main.getHumidity();
    }

    public double getTemp() {
        return main.getTemp();
    }

    public String getCountry() {
        return sys.getCountry();
    }

    public long getSunrise() {
        return sys.getSunrise();
    }

    public long getSunset() {
        return sys.getSunset();
    }

    public String getWeatherMain() {return weather.get(0).getMain();}

    public String getWeatherDesc() {return weather.get(0).getDescription();}

    public String getWeatherIcon() {return weather.get(0).getIcon();}

    public Double getWindSpd() {return wind.getSpeed(); }

    public Double getWindDeg() {return wind.getDeg();}

    public int getCloudPer() {return clouds.getAll();}







    class Main {
        double temp;
        double pressure;
        int humidity;
        double temp_min;
        double temp_max;

        public Main(double temp, int pressure, int humidity, double temp_min, double temp_max) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
            this.temp_min=temp_min;
            this.temp_max=temp_max;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    class Coord {
        double lat;
        double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    class Weather {
        private int id;
        String main;
        String description;
        String icon;

        public Weather() {
        }

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        public Sys() {
        }

        public Sys(int type, int id, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public long getSunrise() {
            return sunrise;
        }

        public void setSunrise(long sunrise) {
            this.sunrise = sunrise;
        }

        public long getSunset() {
            return sunset;
        }

        public void setSunset(long sunset) {
            this.sunset = sunset;
        }
    }

    class Wind {
        double speed,deg;

        public Wind(double speed, double deg) {
            this.speed = speed;
            this.deg = deg;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }
    }

    class Clouds {
        int all;

        public Clouds(int all) {
            this.all = all;
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }



}
