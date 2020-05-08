package temperaturejson.repositories;
import java.util.List;

import temperaturejson.models.Temperature;

public interface ITemperatureDao {

    public List<Temperature> getAllTemperatures();
    public void persistTemperatures(List<Temperature> temperatureList);
}
