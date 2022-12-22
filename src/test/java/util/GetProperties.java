package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private static GetProperties instance = null;
    String host;
    String user;
    String pwd;
    String token;
    private GetProperties(){
        Properties properties= new Properties();
        String nameFile= "todo.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(nameFile);

        if(inputStream != null){
            try{
               properties.load(inputStream);
            } catch (IOException e){
                throw new RuntimeException(e);

            }
        }
        host = properties.getProperty("host");
        user = properties.getProperty("user");
        pwd = properties.getProperty("pwd");
        token ="";
    }

    public static GetProperties getInstance(){
        if (instance ==null)
            instance = new GetProperties();
        return instance;
    }

    public String getUser() {
        return user;
    }
    public String getHost() {
        return host;
    }
    public String getPwd() {
        return pwd;
    }
    public String getToken() {return token;}

    public void setToken(String vStrAccessToken) {
        this.token = token;
    }
}