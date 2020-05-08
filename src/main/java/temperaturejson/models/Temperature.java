package temperaturejson.models;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Temperature implements Serializable{

    private int humidity;
    private int temperature;
    private int lum;
    private int id;

    public Temperature(){}

    public Temperature(int humidity, int temperature, int lum, int id) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.lum = lum;
        this.id = id;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getLum() {
        return lum;
    }

    public void setLum(int lum) {
        this.lum = lum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}