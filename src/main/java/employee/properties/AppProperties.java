package employee.properties;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by employee on 12/6/16.
 */
public class AppProperties {
    private  static URI uri;
    private String userName;
    private String userPassword;
    private String dataBase;
    private String driver;
    private String showSql;
    private String dialect;
    private String hibernateHbm2Dll;


    public AppProperties(String uriStr) throws URISyntaxException {

      uri = new URI(uriStr);
        String userInfo[] = uri.getUserInfo().split(":");
        userName = userInfo[0];
        userPassword = userInfo[1];
        dataBase = uri.getPath().split("/")[1];
        showSql = "false";
        driver = "org.postgresql.Driver";
        hibernateHbm2Dll = "update";
        dialect="org.hibernate.dialect.PostgreSQLDialect";
    }

    public static String getHost(){
        return uri.getHost();
    }

    public int getPort(){
        return uri.getPort();
    }

    public String getUserName(){
        return userName;
    }

    public String getUserPassword(){
        return userPassword;
    }

    public String getDataBase(){
        return dataBase;
    }

    public String getUrl(){
        return "dbc:postgresql://"+getHost()+":"+getPort()+"/"+getDataBase();
    }

    public String getDriver(){
        return driver;
    }

    public String getShowSql() {
        return showSql;
    }

    public String getHibernateHbm2Dll() {
        return hibernateHbm2Dll;
    }

    public URI getUri() {
        return uri;
    }

    public String getDialect() {
        return dialect;
    }
}
