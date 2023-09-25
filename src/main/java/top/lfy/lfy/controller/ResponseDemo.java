package top.lfy.lfy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import top.lfy.lfy.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

/**
 * @author dfysa
 * @data 25/9/2023 下午7:15
 * @description
 */
@Slf4j
@WebServlet("/response/*")
public class ResponseDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();
        String method = StringUtil.subUri(uri);
        switch (method) {
            case "demo1" -> {
                this.demo1(req, resp);
            }
            case "demo2"-> {
                this.demo2(req, resp);
            }
            case "demo3"-> {
                this.demo3(req, resp);
            }
            case "demo4"-> {
                this.demo4(req, resp);
            }
            case "demo5"-> {
                this.demo5(req, resp);
            }

        }
    }


    private void demo1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ResponseDemo1........");
        //访问/responseDemo1，会自动跳转到/responseDemo2资源
        // 1. 设置状态码为302


        response.setStatus(302);
        response.setHeader("location", "/response/demo2");
        request.setAttribute("msg", "response");

//        String contextPath = request.getContextPath();

        response.sendRedirect("/response/demo2");
    }

    private void demo2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ResponseDemo1........");
        System.out.println("responseDemo2被访问...");
        //从request对象中获取名为"msg"的attribute属性， 以便共享
        Object msg = request.getAttribute("msg");
        System.out.println(msg);

    }

    private void demo3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发
        request.getRequestDispatcher("demo2").forward(request, response);
    }

    private void demo4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取流对象之前，设置流的默认编码：ISO-8859-1 设置为：GBK
        response.setCharacterEncoding("utf-8");
        //通过setHeader设置content-type响应头
        response.setHeader("content-type", "text/html;charset=utf-8");
        // 简单形式设置编码
//        response.setContentType("text/html;charset=utf-8");
        // 1.获取字符输出流
        PrintWriter pw = response.getWriter();
        //2.输出数据
        // pw.write("<h1>hello response</h1>");
        pw.write("你好 response");
    }

    private void demo5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.获取字节输出流
        ServletOutputStream sos=response.getOutputStream();
        // 2.输出数据
        sos.write("你好".getBytes(StandardCharsets.UTF_8));
    }

}
