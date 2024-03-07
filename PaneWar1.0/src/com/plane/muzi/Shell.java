package com.plane.muzi;

import java.awt.*;

public class Shell extends GameObject{
    static int dans=30;//炮弹数量
    double degree;//炮弹自己的角度

    public Shell(){//创建一个无参构造器
        x = 300;//炮弹初始化的横坐标
        y = 300;//炮弹初始化的横坐标
        speed = 3;//炮弹飞行的速度
        width = 30;
        high = 30;
        degree = Math.random()*Math.PI*2;//炮弹飞行的角度，以数学随机数来随机

    }

    @Override//重写绘制自我的函数，改成炮弹的绘制
    public void drawMyself(Graphics g) {
      Color gcolor = g.getColor();
      g.setColor(Color.pink);
      g.fillOval((int)x,(int)y,width,high);//画出一个实心球
      g.setColor(gcolor);
      //自己设计炮弹移动路径
       x+=speed*Math.cos(degree);//以随机角度发射
       y+=speed*Math.sin(degree);

       //碰到边界改变 方向
        if (y>Constant.GAME_HIGH-30 || y<15)
        {
           degree = -degree;//竖直方向反弹直接是反方向角度
        }

        if (x>Constant.GAME_WIDTH-30 || x<15)
        {
          degree = Math.PI-degree;//水平方向反弹直接用派减去原本的角度
        }
    }


}
