package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import util.ApiConfiguration;
import util.GetProperties;

import static org.hamcrest.Matchers.equalTo;

public class ItemTest {
    Response response;
    JSONObject body= new JSONObject();
    RequestInfo requestInfo = new RequestInfo();

    @BeforeEach
    public void verifyAuthenticated() {
        // VERIFY TOKEN AUTHENTICATED
        requestInfo.setUrl(ApiConfiguration.AUTH_VERIFY);
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("get").sendAuthToken(requestInfo);

        if (!response.getBody().as(Boolean.class)) {
            /// AUTH WHIT TOKEN
            requestInfo.setUrl(ApiConfiguration.AUTH_TOKEN);
            requestInfo.setBody(body.toString());
            response = FactoryRequest.make("get").send(requestInfo);
            response.then().body("UserEmail", equalTo(GetProperties.getInstance().getUser())).statusCode(200);

            GetProperties.getInstance().setToken(
                    response.then().extract().path("TokenString"));

        }
    }

    @Test
    @DisplayName("Verfy the test CRUD")
    //@Description("This test case is to verify.......")
    @Epic("Todo.ly")
    @Feature("Login")
    @Owner("Clasur")
    //@Link("")
    public void verifyCRUDItem(){
        DateTime currentDate = DateTime.now();
        String itemName = "" + currentDate.getYear();
        itemName += currentDate.getMonthOfYear();
        itemName += currentDate.getDayOfMonth();
        itemName += "TestItemCreate";

        //CREATE ITEM
        body.put("Content",itemName);
        body.put("ProjectId",4048707);
        requestInfo.setUrl(ApiConfiguration.CREATE_ITEM);
        requestInfo.setBody(body.toString());

        response= FactoryRequest.make("post").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);
        int idItem=response.then().extract().path("Id");

        //UPDATE ITEM
        itemName +="AndUpdate";
        body.put("Content", itemName);
        requestInfo.setUrl(String.format(ApiConfiguration.UPDATE_ITEM, idItem));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("put").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        //GET ITEM
        requestInfo.setUrl(String.format(ApiConfiguration.READ_ITEM,idItem));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("get").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

        //DELETE ITEM
        requestInfo.setUrl(String.format(ApiConfiguration.DELETE_ITEM ,idItem));
        requestInfo.setBody(body.toString());
        response = FactoryRequest.make("delete").send(requestInfo);
        response.then().body("Content",equalTo(body.get("Content"))).statusCode(200);

    }


}
