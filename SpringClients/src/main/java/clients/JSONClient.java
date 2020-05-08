package clients;
import models.Response;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import models.Temperature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        RestTemplate restTemplate = new RestTemplate();
        Temperature[] resultArray = restTemplate.getForObject(uri, Temperature[].class);
        List<Temperature> result = Arrays.asList(resultArray);
        for (Temperature b : result){
            System.out.println("Id: " + b.getId()+" Temperature: "+b.getTemperature() + " Humidity: " + b.getHumidity()
            + " Lum: " + b.getLum());
        }
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

    public static void updateTemperature(float humidity, float temperature, int lum, int id){
        final String uri = "http://localhost:8080/temperature/update";
        Temperature upTemp = new Temperature(humidity, temperature, lum, id);
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.postForObject(uri, upTemp, Response.class);
        System.out.println(result.getMessage());

    }

    public static void main(String[] args){

        getTemperatures();
        //createTemperature(12, 22, 22, 10);
        //updateTemperature(88, 12, 89, 1);
        //getTemperatureById(1);

    }
}