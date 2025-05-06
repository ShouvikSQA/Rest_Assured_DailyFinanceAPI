package testRunner;

import com.github.javafaker.Faker;
import config.SetUp;
import config.UserModel;
import controller.RegistrationController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Utils;

public class registrationTestRunner extends SetUp {



    @Test(priority = 1 , description = "New user registration with Invalid Email"  )
    public void registerWithInvalidEmail() throws ConfigurationException {
        RegistrationController registrationController = new RegistrationController(props);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(faker.name().firstName().toLowerCase() + Utils.generateRandomID(1000,9000) );
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0170" + Utils.generateRandomID(1000000, 9999999));
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response response = registrationController.register(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 400 );




    }

    @Test(priority = 2 , description = "New user registration with Invalid Phone Number" )
    public void registerWithInvalidPhoneNumber() throws ConfigurationException {
        RegistrationController registrationController = new RegistrationController(props);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(faker.name().firstName().toLowerCase() + Utils.generateRandomID(1000,9000) +"@gmail.com" );
        userModel.setPassword("1234");
        userModel.setPhoneNumber( "yhs$#!" + Utils.generateRandomID(10000,99999)  );
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response response = registrationController.register(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 400 );




    }

    @Test(priority = 3 , description = "New user registration with only mandatory data" )
    public void registrationUsingMandatoryFields() throws ConfigurationException {
        RegistrationController registrationController = new RegistrationController(props);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName("");
        userModel.setEmail( faker.name().firstName().toLowerCase()+ Utils.generateRandomID(1000,9000) + "@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0170" + Utils.generateRandomID(1000000, 9999999));
        userModel.setAddress("");
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response response = registrationController.register(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 201 );


    }

    @Test(priority = 4 , description = "New user registration with all data" )
    public void newUserRegistration() throws ConfigurationException {
        RegistrationController registrationController = new RegistrationController(props);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(faker.name().firstName().toLowerCase() + Utils.generateRandomID(1000,9000) + "@gmail.com");
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0170" + Utils.generateRandomID(1000000, 9999999));
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response response = registrationController.register(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 201 );

        JsonPath jsonPath = response.jsonPath();
        String userID = jsonPath.get("_id");
        String userEmail = jsonPath.getString("email");

        Utils.setEnvironmentVariable("userID", userID);

        Utils.setEnvironmentVariable("userEmail", userEmail);

    }

    @Test(priority = 5 , description = "New user registration with duplicate Email" )
    public void registrationWithDuplicateEmail() throws ConfigurationException {
        RegistrationController registrationController = new RegistrationController(props);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();

        userModel.setFirstName(faker.name().firstName());
        userModel.setLastName(faker.name().lastName());
        userModel.setEmail(props.getProperty("userEmail"));
        userModel.setPassword("1234");
        userModel.setPhoneNumber("0170" + Utils.generateRandomID(1000000, 9999999));
        userModel.setAddress(faker.address().fullAddress());
        userModel.setGender("Male");
        userModel.setTermsAccepted(true);

        Response response = registrationController.register(userModel);
        System.out.println(response.asString());

        Assert.assertEquals( response.getStatusCode() , 400 );

        JsonPath jsonPath = response.jsonPath();
        String errorMsg = jsonPath.get("message");
      Assert.assertTrue( errorMsg.contains("User already exists with this email address") );
    }
}
