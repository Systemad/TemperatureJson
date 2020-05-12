package temperaturejson.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Temperature implements Serializable{

    private float humidity;
    private float temperature;
    private int lum;
    private int id;
    private int consumption;
    //private LocalDate localDate;

    public Temperature(){}

    public Temperature(int id, float temperature, float humidity, int lum, int consumption /*LocalDate localDate*/) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.lum = lum;
        this.consumption = consumption;
        //this.localDate = localDate;
    }

    //public LocalDate getLocalDate() {return localDate;}

    //public void setLocalDate(LocalDate localDate) {this.localDate = localDate;}


    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
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