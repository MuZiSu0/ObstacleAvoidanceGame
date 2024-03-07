package com.plane.muzi;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
    boolean left,right,up,down;
    boolean relive = false;
    int Nan=1;
   boolean live=true;
    @Override//重写父类飞机绘制方法
    public void drawMyself(Graphics g) {

//判断飞机是否死亡
        if (live) {
            super.drawMyself(g);//绘制飞机本身
            //判断飞机飞行的方向并且绘制
            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }

        }
    }


//按钮按压改变飞行方向为true
    public void addDirection(KeyEvent e){
        if (e.getKeyCode() == 87)
        {
            up = true;
        }
        if (e.getKeyCode() == 83)
        {
            down = true;
        }
        if (e.getKeyCode() == 65)
        {
            left = true;
        }
        if (e.getKeyCode() == 68)
        {
            right = true;
        }
    }
//按钮释放导致飞行方向为false
    public void minusDirection(KeyEvent e){
        if (e.getKeyCode() == 87)
        {
            up = false;
        }
        if (e.getKeyCode() == 83)
        {
            down = false;
        }
        if (e.getKeyCode() == 65)
        {
            left = false;
        }
        if (e.getKeyCode() == 68)
        {
            right = false;
        }
    }

    //定义物体重生按钮方法
    public void ReLive(KeyEvent e){
        if (e.getKeyCode()==82){
            relive = true;
        }

    }



    //建立构造器
    public Plane(Image img, double x, double y, int speed, int width, int high){
        super(img, x,  y,  speed,  width, high);
    }


}
