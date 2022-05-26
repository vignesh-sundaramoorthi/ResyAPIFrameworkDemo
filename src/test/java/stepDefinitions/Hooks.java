package stepDefinitions;

//import org.junit.Before;
import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //write code to get place id
        //execute this only when place id is null
        StepDefinition m = new StepDefinition();
        if(StepDefinition.place_id == null)
        {
            m.add_place_payload_with("Sindhu", "Tamil", "Wheelers hill");
            m.user_calls_with_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Sindhu", "getPlaceAPI");
        }
    }
}
