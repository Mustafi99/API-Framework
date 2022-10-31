package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Location;
import pojo.SetAddress;

public class TestDataBuild {
	public SetAddress AddPlacePayLoad(String name, String language, String address,String website) {
		
		
	SetAddress add = new SetAddress();
	add.setAccuracy(50);
	add.setAddress(address);
	add.setLanguage(language);
	add.setPhone_number("(+91) 983 893 3937");
	add.setName(name);
	add.setWebsite(website);
	List<String>myList = new ArrayList<String>();
	myList.add("shoe park");
	myList.add("shop");
	add.setTypes(myList);
	Location l =new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	add.setLocation(l);
	return add;

}
	public String deletePlacePayload(String placeId) {
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
