package top.lfy.lfy.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int width = 120;
        int height = 50;
        //1.创建一对象，在内存中图片(验证码图片对象)
          BufferedImage image=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        // 2.美化图片
        // 2.1 填充背景色
        // 画笔对象
        Graphics g=image.getGraphics();
        // 设置画笔颜色
          g.setColor(Color.WHITE);
         g.fillRect(0,0,width,height);
        // 2.2画边框
         g.setColor(Color.BLUE);
          g.drawRect(0,0,width-1,height-1);
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        // 生成随机⻆标
         Random ran=new Random();
        for(int i=1;i<=5;i++){
            int index=ran.nextInt(str.length());
        // 获取随机字符
            char ch=str.charAt(index);
        // 2.3写验证码
        g.drawString(ch+"",width/5*i,height/3);}
        // 2.4 画干扰线
          g.setColor(Color.GREEN);
        // 随机生成坐标点
         for(int i=0;i<10;i++){
         int x1=ran.nextInt(width);
          int x2=ran.nextInt(width);
          int y1=ran.nextInt(height);
          int y2=ran.nextInt(height);
         g.drawLine(x1,y1,x2,y2);
    }
    //3.将图片输出到⻚面展示
      ImageIO.write(image,"jpg",response.getOutputStream());
}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   this.doPost(request,response);
    }

}