package com.plane.muzi;

import java.awt.*;

//游戏物体类
public class GameObject {

    Image img;//物体图片
    double x,y;//物体坐标
    int speed;
    int width,high;
//构造器构造物体初始化参数
    public GameObject(Image img, double x, double y, int speed, int width, int high) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.high = high;
    }

//绘制自己物体
    public void drawMyself(Graphics g){
       g.drawImage(img,(int)x,(int)y,width,high,null);
    }

//所有的物体都是矩形，此方法用于检测区域和碰撞
    public Rectangle geterct(){
        return new Rectangle((int)x,(int)y,width,high);
    }

//构造无参构造器 使后续的类能够继承
    public GameObject(){}
}
