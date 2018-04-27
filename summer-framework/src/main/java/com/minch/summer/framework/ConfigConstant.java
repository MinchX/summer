package com.minch.summer.framework;

/**
 * Created with IntelliJ IDEA.
 * User: Minch
 * Date: 2018/4/25
 * Time: 16:38
 */
public interface ConfigConstant {

    String CONFIG_FILE="summer.properties";

    String JDBC_DRIVERCLASSNAME = "summer.framework.jdbc.driverClassName";
    String JDBC_url= "summer.framework.jdbc.url";
    String JDBC_username= "summer.framework.jdbc.username";
    String JDBC_password= "summer.framework.jdbc.password";
    String JDBC_maxActive= "summer.framework.jdbc.maxActive";
    String JDBC_initialSize= "summer.framework.jdbc.initialSize";
    String JDBC_maxWait= "summer.framework.jdbc.maxWait";
    String JDBC_maxIdle= "summer.framework.jdbc.maxIdle";
    String JDBC_minIdle= "summer.framework.jdbc.minIdle";

    String APP_BASE_PACKAGE= "summer.framework.app.base.package";
    String APP_JSP_PATH= "summer.framework.app.jsp.path";
    String APP_ASSET_PATH= "summer.framework.app.asset.path";


}
