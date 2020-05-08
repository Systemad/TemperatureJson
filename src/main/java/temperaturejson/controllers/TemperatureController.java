package temperaturejson.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import temperaturejson.models.Temperature;
import temperaturejson.models.Response;
import temperaturejson.repositories.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class TemperatureController {

    ITemperatureDao temperatureDao = new TemperatureDaoJson();

    List<Temperature> temperatureList = temperatureDao.getAllTemperatures();


    @RequestMapping("/temperatures")
    public List<Temperature> index() {
        return temperatureList;
    }

    @RequestMapping("/temperature")
    public Temperature oneTemperature() {
        return new Temperature(2, 18, 10, 111);
    }


    @RequestMapping("/temperature/{id}")
    public Temperature getBookById(@PathVariable int id){
        System.out.println("hej");
        Temperature res = new Temperature();
        for (Temperature b : temperatureList){
            if (b.getId() == id){
                res = b;
            }
        }
        return res;
    }

    @RequestMapping("/temperaturesBetween/{idFrom}/{idTo}")
    public List<Temperature> getBooksBetween(@PathVariable int idFrom, @PathVariable int idTo){
        List<Temperature> res = new ArrayList();
        for (Temperature b : temperatureList){
            int id = b.getId();
            if (id >= idFrom && id <= idTo){
                res.add(b);
            }
        }
        return res;
    }

    @RequestMapping("/temperature/{id}/delete")
    public Response deleteBookById(@PathVariable("id") int id){
        Response res = new Response("Temperature deleted", Boolean.FALSE);

        int indexToRemove = -1;
        for (int i = 0; i < temperatureList.size(); i++){
            if (temperatureList.get(i).getId() == id){
                indexToRemove = i;
            }
        }

        if (indexToRemove != -1){
            temperatureList.remove(indexToRemove);
            res.setStatus(Boolean.TRUE);
        }
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }


    @PostMapping("/temperature/add")
    public Response addTemperature(@RequestBody Temperature b){
        System.out.println("Id: " + b.getId()+" Temperature: "+b.getTemperature() + " Humidity: " + b.getHumidity()
                + " Lum: " + b.getLum());
        Response res = new Response("Temperature added", Boolean.FALSE);
        temperatureList.add(b);
        res.setStatus(Boolean.TRUE);
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }

    @PostMapping("/temperature/update")
    public Response upsertTemperature(@RequestBody Temperature b){
        Response res = new Response("Book updated", Boolean.FALSE);

        int indexToUpdate = -1;
        for (int i = 0; i < temperatureList.size(); i++){
            if (temperatureList.get(i).getId() == b.getId()){
                indexToUpdate = i;
            }
        }

        if (indexToUpdate == -1){
            temperatureList.add(b);
            res.setMessage("Temperature inserted");
            res.setStatus(Boolean.TRUE);
        }
        else{
            temperatureList.set(indexToUpdate, b);
            res.setStatus(Boolean.TRUE);
        }
        temperatureDao.persistTemperatures(temperatureList);
        return res;
    }
}
