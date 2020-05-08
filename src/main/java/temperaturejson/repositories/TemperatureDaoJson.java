package temperaturejson.repositories;
import com.google.gson.Gson;
import temperaturejson.models.Temperature;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class TemperatureDaoJson implements ITemperatureDao {

    Gson gson = new Gson();
    String temperatureListPath = "src/main/java/temperaturejson/repositories/alltemp.json";

    public List<Temperature> getAllTemperatures(){
        String json = new String();
        List<Temperature> temperatureList = new ArrayList<Temperature>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(temperatureListPath));) {
            Type listType = new TypeToken<ArrayList<Temperature>>(){}.getType();
            temperatureList = new Gson().fromJson(br, listType);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temperatureList;
    }

    public void persistTemperatures(List <Temperature> temperatureList){
        String json = gson.toJson(temperatureList);

        try (FileWriter writer = new FileWriter(temperatureListPath);) {
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void persistTemperatures(String temperatureList){

        try (FileWriter writer = new FileWriter(temperatureListPath);) {
            writer.write(temperatureList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
