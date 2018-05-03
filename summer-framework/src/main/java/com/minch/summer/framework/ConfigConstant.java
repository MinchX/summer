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
    String JDBC_URL= "summer.framework.jdbc.url";
    String JDBC_USERNAME= "summer.framework.jdbc.username";
    String JDBC_PASSWORD= "summer.framework.jdbc.password";
    String JDBC_MAXACTIVE= "summer.framework.jdbc.maxActive";
    String JDBC_INITIALSIZE= "summer.framework.jdbc.initialSize";
    String JDBC_MAXWAIT= "summer.framework.jdbc.maxWait";
    String JDBC_MAXIDLE= "summer.framework.jdbc.maxIdle";
    String JDBC_MINIDLE= "summer.framework.jdbc.minIdle";

    String APP_BASE_PACKAGE= "summer.framework.app.base.package";
    String APP_JSP_PATH= "summer.framework.app.jsp.path";
    String APP_ASSET_PATH= "summer.framework.app.asset.path";


}
