package util;

public class ApiConfiguration {

    /** API AUTHENTICATION TOKEN*/
    public static final String AUTH_VERIFY = GetProperties.getInstance().getHost() + "/api/authentication/isauthenticated.json";
    public static final String AUTH_TOKEN = GetProperties.getInstance().getHost() + "/api/authentication/token.json";

    /** API ITEM*/
    public static final String CREATE_ITEM=GetProperties.getInstance().getHost()+"/api/items.json";
    public static final String UPDATE_ITEM=GetProperties.getInstance().getHost()+"/api/items/%s.json";
    public static final String READ_ITEM=GetProperties.getInstance().getHost()+"/api/items/%s.json";
    public static final String DELETE_ITEM=GetProperties.getInstance().getHost()+"/api/items/%s.json";

}
