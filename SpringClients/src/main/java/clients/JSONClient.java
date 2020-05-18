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
        System.out.println("Id: " + result.getId()+" Temperature: "+result.getTemperature() + " Humidity: " + result.getHumidity()
                + " - Lum: " + result.getLum() + " - Energy Consumption: "  + result.getConsumption());
        return result;
    }

    private static Temperature getTemperatureById(int id){
        final String uri = "http://localhost:8080/temperature/{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        Temperature result = restTemplate.getForObject(uri, Temperature.class, params);
        System.out.println("Id: " + result.getId()+" Temperature: "+result.getTemperature() + " Humidity: " + result.getHumidity()
                + " - Lum: " + result.getLum() + " - Energy Consumption: "  + result.getConsumption());
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
            System.out.println("Id: " + b.getId()+" Temperature: " +b.getTemperature() + " Humidity: " + b.getHumidity()
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

    private static List<Temperature> CalculateCost(int cost){
        final String uri = "http://localhost:8080/temperatures.json";
        int consumption = 0;

        RestTemplate restTemplate = new RestTemplate();
        Temperature[] resultArray = restTemplate.getForObject(uri, Temperature[].class);
        List<Temperature> result = Arrays.asList(resultArray);
        for (Temperature b : result){
            consumption += b.getConsumption();
        }

        System.out.println("Total Energy Consumption: " + consumption * cost + " SEK");
        return result;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Scanner test = new Scanner(System.in);

        boolean quit = false;
        int choice;

        System.out.println("1. One day");
        System.out.println("2. Get all temperatures each day and average value + consumption");
        System.out.println("3. Get temperature by id");
        System.out.println("4. Change values");
        System.out.println("5. Calculate Cost");
        System.out.println("0. Quit");

        do{
            System.out.println("Choose an option: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    getTemperature();
                    break;
                case 2:
                    getTemperatures();
                    break;
                case 3:
                    System.out.println("What temperature by id?: ");
                    int ind = test.nextInt();
                    getTemperatureById(ind);
                    break;
                case 4:
                    System.out.println("Change value in order: id, temperature, humidity, lum, consumption");
                    int id = test.nextInt();
                    float temperature = test.nextFloat();
                    float humidity = test.nextFloat();
                    int lum = test.nextInt();
                    int consumption = test.nextInt();
                    updateTemperature(id, temperature, humidity, lum, consumption);
                    break;
                case 5:
                    System.out.println("How much per sek kWH?");
                    int cost = test.nextInt();
                    CalculateCost(cost);
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }while(!quit);

    }
}