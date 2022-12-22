package factoryRequest;

import io.restassured.response.Response;
//crear familia de peticiones
public interface IRequest {

    //envia una respuesta,como parametro envia el RequiestInfo que hemos creado, es el metodo que todas las peticiones tiene que implementar
    Response send(RequestInfo info);
    Response sendAuthToken(RequestInfo info);
}

