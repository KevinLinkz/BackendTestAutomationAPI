import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class API {
    @Test
    public void hitAPI() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        Assert.assertEquals(response.getStatusCode(), 200);

    }
    @Test
    public void hitAPIFail() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        Assert.assertNotEquals(response.getStatusCode(), 222);

    }
}
