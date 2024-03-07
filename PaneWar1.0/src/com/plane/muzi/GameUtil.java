package com.plane.muzi;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

//建立游戏工具类
public class GameUtil {

    private GameUtil() {//构造器私有

    }

    public static Image getImage(String path) {//创建图片阅读器

        BufferedImage bi = null;//创建图片返回指针

        URL u = GameUtil.class.getClassLoader().getResource(path);//关键图片资源管理器调用

        try {
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
           return bi;
    }

    public static void main(String[] args) {
      Image img = GameUtil.getImage("images/baby1.jpeg");
        System.out.println(img);

    }

}