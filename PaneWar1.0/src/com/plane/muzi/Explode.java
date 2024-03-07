package com.plane.muzi;

import java.awt.*;

public class Explode {
    double x, y;//定义爆炸的位置
    static Image[] imags = new Image[16];
    int count = 0;//构造画笔计数器
    static {
              for (int i=0;i<16;i++)
              {
                  imags[i] = GameUtil.getImage("images/explode/e" + (i+1) +".png");//图片数组初始化
                  imags[i].getWidth(null);//加载图片类 后续用于解决图片加载问题
              }
    }
    public void drawMyself(Graphics g){//写爆炸和绘制函数
        if (count<16)//判断爆炸图片轮播
        {
       g.drawImage(imags[count],(int)x,(int)y,null);//画出图片爆炸数组
       count++;//计数器加1
        }
    }

    public Explode() {//构造无参构造器方便其他类调用
    }


    public Explode(double x, double y) {//带参构造器，便于控制爆炸地点

        this.x = x;
        this.y = y;
    }


    public static void main(String[] args) {
        System.out.println(imags[10]);//打印判断图片对象是否传入

    }
}
