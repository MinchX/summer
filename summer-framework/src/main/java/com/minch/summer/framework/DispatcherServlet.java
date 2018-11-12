package com.minch.summer.framework;

import com.minch.summer.framework.bean.Data;
import com.minch.summer.framework.bean.Handler;
import com.minch.summer.framework.bean.Param;
import com.minch.summer.framework.bean.View;
import com.minch.summer.framework.helper.BeanHelper;
import com.minch.summer.framework.helper.ConfigHelper;
import com.minch.summer.framework.helper.ControllerHelper;
import com.minch.summer.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Minch
 * Date: 2018/5/2
 * Time: 16:25
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");
        super.init(config);
    }


    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        //根据访问地址获取Handler对象
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);

        if (handler!=null){
            //从handler对象中获取controller，从beanhelper里获取controller对象
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //将参数整合到paramMap中
            Map<String,Object> paramMap = new HashMap<String, Object>();
            Enumeration<String> paramNames= req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)){
                String[] params = StringUtil.splitString(body,"&");
                if (ArrayUtil.isNotEmpty(params)){
                    for (String param : params){
                        String [] array = StringUtil.splitString(param,"=");
                        if (ArrayUtil.isNotEmpty(array) && array.length==2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            //调用controller中的action方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean,actionMethod,param);

            if (result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)){
                    if (path.startsWith("/")){
                        res.sendRedirect(req.getContextPath()+path);
                    } else {
                        Map<String,Object> model = view.getModel();
                        for (Map.Entry<String,Object> entry:model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,res);
                    }
                } else if (result instanceof Data){
                    Data data = (Data) result;
                    Object model = data.getModel();
                    if (model != null){
                        res.setContentType("application/json");
                        res.setCharacterEncoding("UTF-8");
                        PrintWriter printWriter = res.getWriter();
                        String json = JsonUtil.toJson(model);
                        printWriter.write(json);
                        printWriter.flush();
                        printWriter.close();
                    }
                }
            }
        }
    }
}
