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
    //private LocalDate localDate;

    public Temperature(){}

    public Temperature(float humidity, float temperature, int lum, int id /*LocalDate localDate*/) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.lum = lum;
        this.id = id;
        //this.localDate = localDate;
    }

    //public LocalDate getLocalDate() {return localDate;}

    //public void setLocalDate(LocalDate localDate) {this.localDate = localDate;}



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