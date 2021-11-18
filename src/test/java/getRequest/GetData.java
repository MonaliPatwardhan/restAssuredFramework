package getRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class GetData {
	
	@Test
	public void testResponseCode()
	{
		//Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		Response resp = get("https://reqres.in/api/users?page=2");
		System.out.println("Status code is " + resp.getStatusCode());
		System.out.println("Response from SERVER " + resp.asString());
		Assert.assertEquals(resp.getStatusCode(), 200);
	}

	@Test
	public void testResponseBody() 
	{
		//Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		Response resp = get("https://reqres.in/api/users?page=2");
		System.out.println("Response from SERVER " + resp.asString());
		
		JSONParser js = new JSONParser();
		try {
			Object obj = js.parse(resp.asString());
			
			//Convert to java object to json object(TYPECAST to jason object)
			JSONObject jsonobj= (JSONObject)obj;
			
			//TYPECAST to string
			//String page = (String)jsonobj.get("page");
			Integer page = (Integer)jsonobj.get("page");
			System.out.println("PAGE = " + page);
			
			//TYPECAST to JSONArray
			JSONArray array = (JSONArray)jsonobj.get("data");
			for(int i=0; i < array.size(); i++)
			{
				System.out.println("-----------------------------");
				JSONObject data = (JSONObject)array.get(i);
				System.out.println("id = " + data.get("id"));
				System.out.println("Email = " + data.get("email"));
				System.out.println("first_name = " + data.get("first_name"));
				System.out.println("last_named = " + data.get("last_name"));
				System.out.println("avatar = " + data.get("avatar"));
				System.out.println("-----------------------------");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResponseTime()
	{
		//Response resp = RestAssured.get("https://reqres.in/api/users?page=2");
		Response resp = get("https://reqres.in/api/users?page=2");
		System.out.println("Response time from SERVER " + resp.getTime());
	}
	
	
}
