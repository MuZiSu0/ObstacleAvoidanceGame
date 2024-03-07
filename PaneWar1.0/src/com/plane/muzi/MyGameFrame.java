package com.plane.muzi;
//当前版本1.0

import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

//游戏主窗口 
public class MyGameFrame extends Frame {
    //图片初始化
    Image Baby = GameUtil.getImage("images/baby.png");
    Image bg = GameUtil.getImage("images/background.png");

   Plane plane = new Plane(Baby,600,600,3,50,50);//飞机初始化

    Shell[] dan = new Shell[Shell.dans];//炮弹数量

    Explode explode ;//创建爆炸对象

    Date pstart = new Date();//定义游戏开始时间 获取当前时间
    long pend = 0;//定义游戏结束时间
    long period = 0;//定义游戏经历的时间
    int k;
    //重写paint方法
    @Override
    public void paint(Graphics g) {//把g当作一支画笔
        /*
        Color gColor = g.getColor();//获取当前画笔g的颜色
        g.setColor(Color.red);//设置画笔颜色
        g.setColor(new Color(32,100,255));//自定义颜色
        g.drawLine(100,100,400,400);//绘制一条直线
        g.drawRect(100,100,300,200);//绘制一个矩形
        g.drawOval(100,100,300,200);//绘制一个椭圆
        g.drawString("muzi111",300,300);//绘制字符串
        g.setColor(gColor);//最后把笔的颜色调回来
         */
        g.drawImage(bg,0,0,900,900,null);//绘制图片坐标以背景

        drawTimes(g);//调用时间判断函数

       plane.drawMyself(g);//绘制飞行的物体

        for (int i=0;i<dan.length;i++)
        {
            dan[i].drawMyself(g);
           boolean peng = dan[i].geterct().intersects(plane.geterct());
           if (peng)
           {
               //System.out.println("飞机被击中了");
               plane.live = false;//让飞机死亡
               if (explode==null) {//判断是否已经创建了爆炸对象，避免重复爆炸
                   explode = new Explode(plane.x, plane.y);
               }
               explode.drawMyself(g);
           }
        }


    }

    //游戏时间引擎
public void drawTimes(Graphics g){
        Color gcolor = g.getColor();//先存储和画笔原本的颜色
        Font gFont = g.getFont();//存储画笔原本的字体

        g.setColor(Color.green);//设置画笔颜色为绿色
        if (plane.live==true){//判断飞机是否存活
            period = (System.currentTimeMillis()- pstart.getTime())/1000;//实时记录当前时间
           g.drawString("已坚持时间"+period+"秒",50,70);//绘制出已经过去的时间
        }else{
            if (pend == 0){//判断pend是否已经被赋值，只用赋值一次
                pend = (System.currentTimeMillis()-pstart.getTime())/1000;
            }
            g.setColor(Color.red);//设置画笔颜色为红色
            g.setFont(new Font("微软雅黑",Font.BOLD,50));//设置画笔字体和大小
            g.drawString("游戏结束，您的记录为"+pend+"秒",200,450);//游戏结束绘制窗口
            g.setColor(Color.green);//设置画笔颜色为绿色
            g.drawString("【按下R重生】",300,520);//提示重生按钮
           //判断历史最高分数
          if (pend>=Constant.MuZiMax)
          {
              Constant.MuZiMax = pend;
          }
            g.setColor(Color.yellow);//设置画笔颜色为黄色
            g.drawString("历史最高成绩为"+Constant.MuZiMax,280,600);


        }
        g.setFont(gFont);//归还画笔的字体
        g.setColor(gcolor);//归还画笔的颜色
       if (plane.relive==true){//判断飞机重生按钮
           pend =0;//把一切参数还原
           period =0;
           plane.live=true;
           plane.relive =false;
           pstart = new Date();
           explode = null;

       }
}





//创建游戏窗口初始化
    public void CreatFrame()
    {
          this.setTitle("海绵宝宝逃跑记");//设置窗口标题

          setVisible(true);//设置窗口可视化

          setSize(Constant.GAME_WIDTH,Constant.GAME_HIGH);//设置窗口大小

          setLocation(400,100);//设置窗口初始化坐标

        //新增窗口监听关闭功能
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              System.exit(0);
            }
     });

        //启动重画窗口的线程
        new PaintThread().start();

        //开启键盘监听
        this.addKeyListener(new keyMonitor());

        //初始化炮弹
        for (k=0;k< dan.length;k++) {
        dan[k] = new Shell();
        }
    }


    //使用线程使窗口自动反复绘制 定义了重画窗口的线程 于内部类方便使用父类i线程
    class PaintThread extends Thread{
        @Override
        public void run() {
            while (true)
            {
                repaint();//重构画笔

                try {
                    Thread.sleep(17);//1000毫秒，相当于每秒绘图多少张
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

//定义一个内部类，用于键盘监听操作
    class keyMonitor extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println("按下"+e.getKeyCode());
           plane.addDirection(e);
           plane.ReLive(e);

    }


    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("松开"+e.getKeyCode());
       plane.minusDirection(e);
    }


}

    //双缓冲技术防止屏幕闪烁
    private Image offScreenImage = null;

    public void update( Graphics g){
        if (offScreenImage == null)
        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HIGH);
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

//主函数入口
    public static void main(String[] args) {
        MyGameFrame gameFrame = new MyGameFrame();
        gameFrame.CreatFrame();

    }

}
