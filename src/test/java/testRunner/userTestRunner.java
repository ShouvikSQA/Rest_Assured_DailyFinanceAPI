package testRunner;

import config.CostModel;
import config.SetUp;
import config.UserModel;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

public class userTestRunner extends SetUp {

    @Test(priority = 1, description = "User can login with valid creds" )
    public void login() throws ConfigurationException, InterruptedException {

        UserController userController = new UserController(props);
        UserModel userModel = new UserModel();

        userModel.setEmail(props.getProperty("userEmail"));
        userModel.setPassword("1234");
        Response response = userController.login(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 200 );

        JsonPath jsonPath = response.jsonPath();
        String userToken = jsonPath.get("token");
        System.out.println(userToken);
        Utils.setEnvironmentVariable("userToken", userToken);

    }

    @Test(priority = 2, description = "User can add items")
    public void addItem() throws ConfigurationException {

        UserController userController = new UserController(props);
        CostModel costModel = new CostModel();

        costModel.setItemName("Laptop");
        costModel.setQuantity(1);
        costModel.setAmount(Utils.generateRandomID(3000, 8000)+"" );
        costModel.setPurchaseDate("2025-04-25");
        costModel.setMonth("April");
        costModel.setRemarks("Very Good Product");

        Response response = userController.addItem(costModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 201 );

        JsonPath jsonPath = response.jsonPath();
        String itemID = jsonPath.get("_id");

        System.out.println(itemID);

        Utils.setEnvironmentVariable("itemID", itemID);


    }

    @Test(priority = 3, description = "User can check item list" )
    public void getItemList() {

        UserController userController = new UserController(props);
        Response response = userController.getItemList();
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 200  );

    }

    @Test(priority = 4, description = "User can edit item name")
    public void editItemName() {

        UserController userController = new UserController(props);
        CostModel costModel = new CostModel();

        costModel.setItemName("Mobile Phone");
        costModel.setQuantity(1);
        costModel.setAmount("100");
        costModel.setPurchaseDate("2025-04-19");
        costModel.setMonth("April");
        costModel.setRemarks("Apple");

        Response response = userController.editItemName(costModel);
        System.out.println(response.asString());
        Assert.assertEquals( response.statusCode() , 200 );
    }

    @Test(priority = 5, description = "User can delete item")
    public void deleteItem() throws InterruptedException {

        UserController userController = new UserController(props);
        Response response = userController.deleteItem();
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 200 );

        JsonPath jsonPath = response.jsonPath();
        String messageActual = jsonPath.get("message");
        Assert.assertTrue(messageActual.contains("Cost deleted successfully"));

    }



}
