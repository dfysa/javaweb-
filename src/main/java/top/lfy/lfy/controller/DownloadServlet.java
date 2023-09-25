package top.lfy.lfy.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author dfysa
 * @data 25/9/2023 下午2:44
 * @description
 */



@WebServlet("/download")
public class DownloadServlet extends HttpServlet {



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {






        // 获取请求参数，文件名称
        String filename=request.getParameter("filename");
   // 找到文件服务器路径
   ServletContext servletContext=this.getServletContext();
   //使用ServletContext的getRealPath()获取文件在服务器的真实路径
   String realPath=servletContext.getRealPath("/img/"+filename);
   // 用字节流关联
   FileInputStream fis=new FileInputStream(realPath);
   // 获取文件的 mime 类型
   String mimeType=servletContext.getMimeType(filename);
   // 设置响应头类型：content-type
   response.setHeader("content-type",mimeType);
   // 设置响应头打开方式:content-disposition
   response.setHeader("content-disposition","attachment;filename="+filename);
   // 将输入流的数据写出到输出流中
   ServletOutputStream sos=response.getOutputStream();
   byte[]buff=new byte[1024*8];
   int len;
   while((len=fis.read(buff))!=-1){
   sos.write(buff,0,len);
   }fis.close();
   }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request,response);
    }

}
