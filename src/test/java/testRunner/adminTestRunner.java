package testRunner;

import com.github.javafaker.Faker;
import config.SetUp;
import config.UserModel;
import controller.AdminController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

import java.io.IOException;

public class adminTestRunner extends SetUp {

    @Test(priority = 1 , description = "Admin login with incorrect email")
    public void adminLoginIncorrectEmail() throws ConfigurationException {

        AdminController adminController = new AdminController(props);
        UserModel userModel = new UserModel();
        userModel.setEmail("admin123@test.com");
        userModel.setPassword("admin123");

        Response response = adminController.login(userModel);
        //System.out.println(response.asString());
        Assert.assertEquals( response.getStatusCode() , 401  );

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals( jsonPath.get("message") , "Invalid email or password"  );


    }

    @Test(priority = 2 , description = "Admin login with incorrect password")
    public void adminLoginIncorrectPass() throws ConfigurationException {

        AdminController adminController = new AdminController(props);
        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin12345");

        Response response = adminController.login(userModel);
        //System.out.println(response.asString());
        Assert.assertEquals( response.getStatusCode() , 401  );

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals( jsonPath.get("message") , "Invalid email or password"  );


    }

    @Test(priority = 3 , description = "Admin login with valid creds")
    public void adminLogin() throws ConfigurationException {

        AdminController adminController = new AdminController(props);
        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");

        Response response = adminController.login(userModel);
        //System.out.println(response.asString());
        Assert.assertEquals( response.getStatusCode() , 200  );

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals( jsonPath.get("role") , "admin"  );

        String token = jsonPath.get("token");
        System.out.println(token);
        Utils.setEnvironmentVariable("adminToken", token);


    }

    @Test(priority = 4 , description = "Admin can get the list of all users" )
    public void getUserList(){

        AdminController adminController = new AdminController(props);
        Response response = adminController.userList();
        System.out.println(response.asString());
        Assert.assertEquals( response.getStatusCode() , 200 );

    }

    @Test(priority = 5, description = "Admin can search user by user id")
    public void searchUserByID() {

        AdminController adminController = new AdminController(props);
        Response response = adminController.searchUser(props.getProperty("userID"));
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode() , 200);


    }

    @Test(priority = 6, description = "Search user by invalid id")
    public void searchInvalidUserByID() {

        AdminController adminController = new AdminController(props);
        Response response = adminController.searchUser("dgbweyweqdfwef123323256");
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode() , 404);
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals( jsonPath.get("message") , "User not found" );


    }


    @Test(priority = 7, description = "Admin can update user info")
    public void updateUser() throws IOException, InterruptedException {

        AdminController adminController = new AdminController(props);
        UserModel UserModel = new UserModel();
        Faker faker = new Faker();

        UserModel.setFirstName(faker.name().firstName());
        UserModel.setLastName(faker.name().lastName());
        UserModel.setEmail(props.getProperty("userEmail"));
        UserModel.setPhoneNumber("0170" + Utils.generateRandomID(1000000, 9999999));
        UserModel.setAddress("Alaska");
        UserModel.setGender("Male");



        Response response = adminController.updateUser(UserModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 200 );

    }


}
