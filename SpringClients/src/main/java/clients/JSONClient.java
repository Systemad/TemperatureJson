package clients;
import models.Response;
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

    public static void main(String[] args){

        getTemperature();

    }
}