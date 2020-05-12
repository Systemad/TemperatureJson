package clients;
import models.Response;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import models.Temperature;

import java.util.*;


public class JSONClient {

    private static Temperature getTemperature(){
        final String uri = "http://localhost:8080/temperature.json";
        RestTemplate restTemplate = new RestTemplate();
        Temperature result = restTemplate.getForObject(uri, Temperature.class);
        System.out.println(result.getId()+" by "+result.getTemperature());
        return result;
    }

    private static Temperature getTemperatureById(int id){
        final String uri = "http://localhost:8080/temperature/{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        Temperature result = restTemplate.getForObject(uri, Temperature.class, params);
        System.out.println(result.getId()+" by "+result.getTemperature());
        return result;
    }

    private static List<Temperature> getTemperatures(){
        final String uri = "http://localhost:8080/temperatures.json";
        float temp = 0;
        float hum = 0;
        int lum = 0;
        int consumption = 0;

        RestTemplate restTemplate = new RestTemplate();
        Temperature[] resultArray = restTemplate.getForObject(uri, Temperature[].class);
        List<Temperature> result = Arrays.asList(resultArray);
        for (Temperature b : result){
            temp += b.getTemperature();
            hum += b.getHumidity();
            lum += b.getLum();
            consumption += b.getConsumption();
            System.out.println("Id: " + b.getId()+" Temperature: "+b.getTemperature() + " Humidity: " + b.getHumidity()
            + " - Lum: " + b.getLum() + " - Energy Consumption: "  + b.getConsumption());
        }
        System.out.println("Temperature Average: " + temp / result.size() +
                " - Humidity Average: " + hum / result.size() + " - Lum Average: " + lum / result.size());
        System.out.println("Total Energy Consumption: " + consumption + " kWh");

        return result;
    }

    /*
    * Not working, not needed
    private static void createTemperature(float humidity, float temperature, int lum, int id)
    {
        final String uri = "http://localhost:8080/temperature/add";
        Temperature newTemp = new Temperature(humidity, temperature, lum, id);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject( uri, newTemp, Response.class);
        System.out.println(result.getMessage());
    }
     */

    public static void updateTemperature(int id, float temperature, float humidity, int lum, int consumption){
        final String uri = "http://localhost:8080/temperature/update";
        Temperature upTemp = new Temperature(id, temperature, humidity, lum, consumption);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject(uri, upTemp, Response.class);
        System.out.println(result.getMessage());

    }

    public static void main(String[] args){

        //getAverage();
        getTemperatures();
        //createTemperature(12, 22, 22, 10);
        //updateTemperature(88, 12, 89, 1);
        //getTemperatureById(1);

    }
}