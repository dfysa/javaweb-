package top.lfy.lfy.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import top.lfy.lfy.util.StringUtil;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import java.util.Set;

@Slf4j
@WebServlet("/request/*")
public class RequestServletDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        System.out.println(username);
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String method = StringUtil.subUri(uri);
        switch (method) {
            case "demo1"-> {
                this.demo1(req, resp);
            }
            case "demo2"-> {
                this.demo2(req, resp);
            }
            case "demo3"->{
                this.demo3(req, resp);
            }
            case "demo4"-> {
                this.demo4(req, resp);
            }
            case "demo5"->{
                this.demo5(req, resp);
            }
            case "demo6"-> {
                this.demo6(req, resp);
            }
            case "demo7"-> {
                this.demo7(req, resp);
            }
            case "demo8"-> {
                this.demo8(req, resp);
            }
            case "demo9"-> {
                this.demo9(req, resp);
            }
        }
    }


    private void demo1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setCharacterEncoding("UTF-8");
////        resp.setContentType("text/html;charset=UTF-8");
//        //通过setHeader设置content-type响应头
//
//
//
//
//        resp.setHeader("content-type", "text/html;charset=UTF-8");
//        //获取请求方法并保存到method变量
//        String method = req.getMethod();
//        log.info(method);
//        //获取上下文路径保存到contextPath变量
//        String contextPath = req.getContextPath();
//        log.info(contextPath);
//        //获取查询字符串保存到queryString变量
//        String queryString = req.getQueryString();
//
//        //5. 获取请求URI：/day02/requestDemo1StringrequestUri=request.getRequestURI();StringBufferrequestUrl=request.getRequestURL();System.out.println(requestUri);System.out.println(requestUrl);//6. 获取协议及版本：HTTP/1.1Stringprotocol=request.getProtocol();System.out.println(protocol);//7. 获取客户机的IP地址：StringremoteAddress=request.getRemoteAddr();System.out.println(remoteAddres
//
//        if (queryString != null) {
//            log.info(queryString);
//            //使用&分割queryString到字符串数组s
//            String[] s = queryString.split("&");
//            log.info(s[0]);
//            log.info(s[1]);
//
//        }
//        resp.getWriter().println("请求方式：" + method + "<br/>");
//
//



        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        //1.获取请求方式:GET
        String method = req.getMethod();
        log.info(method);
        //2.获取虚拟目录
        String contextPath = req.getContextPath();
        log.info(contextPath);
        //3.获取Servlet路径（要判断字符串是否为空）
        String servletPath =req.getServletPath();
        System.out.println(servletPath);
        //4.获取get方式请求参数： name=zhangsan&age=22
        String queryString =req.getQueryString();
        if (queryString != null) {
            log.info(queryString);
            String[] s = queryString.split("&");
            log.info(s[0]);
            log.info(s[1]);
        }
//        5.获取请求URI：/day02/requestDemo1
        String requestUri=req.getRequestURI();
        StringBuffer requestUrl=req.getRequestURL();
        System.out.println(requestUri);
        System.out.println(requestUrl);
        //6. 获取协议及版本：HTTP/1.1
        String protocol=req.getProtocol();
        System.out.println(protocol);
        // 7. 获取客户机的IP地址：
        String remoteAddress=req.getRemoteAddr();
        System.out.println(remoteAddress);
        resp.getWriter().println("请求方式：" + method + "<br/>");
    }








    private void demo2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取所有请求头名称
        Enumeration<String> headerNames = req.getHeaderNames();
        // 2.遍历
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + "---" + value);
        }



    }

    private void demo3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求头数据:user-agen
        String agent = req.getHeader("user-agent");
        //判断agent的浏览器版本
        if (agent.contains("Chrome")) {
            System.out.println("谷歌浏览器...");
        } else if (agent.contains("Microsoft Edge")) {
            System.out.println("微软边缘浏览器...");
        }
    }

    private void demo4(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求消息体--请求参数
        // 1.获取字符流
        BufferedReader br = req.getReader();
        //2.读取数据
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }



    private void demo5(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //post 获取请求参数
        // 根据参数名称获取参数值
        System.out.println("根据参数名称获取参数值");
        String username = req.getParameter("username");
        System.out.println(username);
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby :
                hobbies) {
            System.out.println(hobby);
        }

        // 获取所有参数的map集合
        System.out.println("***********************");
        System.out.println("获取所有请求的参数名称");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            System.out.println(name);
            String value = req.getParameter(name);
            System.out.println(value);
            System.out.println("----------------");
        }
        //get 获取请求参数
        // 根据参数名称获取参数值
        System.out.println("***********************");
        System.out.println("获取所有参数的map集合");
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String name :
                keySet) {
            String[] values = parameterMap.get(name);
            System.out.println(name);
            for (String value :
                    values) {
                System.out.println(value);
            }
            System.out.println("-----------------");
        }
    }

    private void demo6(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置请求的编码
        req.setCharacterEncoding("utf-8");
        //2.获取请求参数username
        String username = req.getParameter("username");
        System.out.println(username);
    }

    private void demo7(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("demo7被访问了...");
        //存储数据到request域中
        req.setAttribute("msg", "hello~ Niko");
        //转发到requestDemo9资源
//        req.getRequestDispatcher("/request/demo8").forward(req, resp);

        // 无法跨域转发
         req.getRequestDispatcher("http://www.baidu.com").forward(req,resp);


    }

    private void demo8(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从request域中获取数据
        Object msg = req.getAttribute("msg");
        System.out.println(msg);
        System.out.println("demo8被访问了。。。");

    }

    private void demo9(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        System.out.println(servletContext);
    }
}
