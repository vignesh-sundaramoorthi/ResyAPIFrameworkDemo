package resources;

import pojo.Location;
import pojo.addPlace;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public addPlace addPlacePayload(String name, String language, String address)
    {
        addPlace place = new addPlace();
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        place.setLocation(l);
        place.setAccuracy(50);
        place.setName(name);
        place.setPhone_number("(+91) 983 893 3937");
        place.setAddress(address);
        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        place.setTypes(myList);
        place.setWebsite("http://google.com");
        place.setLanguage(language);
        return place;
    }

    public String deletePlacePayload(String placeId)
    {
        return "{\r\n     \"place_id\": \""+placeId+"\"\r\n}";
    }
}
