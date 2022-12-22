package factoryRequest;

import io.restassured.response.Response;
import util.GetProperties;

import static io.restassured.RestAssured.given;

public class RequestDELETE implements IRequest{

    @Override
    public Response send(RequestInfo info) {
        Response response = given()
                .auth()
                .preemptive()
                .basic(GetProperties.getInstance().getUser(), GetProperties.getInstance().getPwd())
                .body(info.getBody())
                .log().all()
        .when()
                .delete(info.getUrl());
        response.then().log().all();

        return response;
    }
    @Override
    public Response sendAuthToken(RequestInfo info) {
        Response response = given()
                .header("token", GetProperties.getInstance().getToken())
                .body(info.getBody())
                .log().all()
                .when()
                .delete(info.getUrl());

        response.then().log().all();

        return response;
    }
}
