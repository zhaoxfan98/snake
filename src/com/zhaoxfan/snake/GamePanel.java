package com.zhaoxfan.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Created on 2021/12/27.
 *
 * @author zhaoxfan
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String fx;    //蛇的方向
    boolean isStart = false;

    Timer timer = new Timer(100, this);  //定时器
    //1.定义食物
    int foodx;
    int foody;
    Random random = new Random();

    //死亡判断
    boolean isFail = false;
    //积分
    int score;

    //构造器
    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化
    public void init() {
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;   //头部坐标
        snakeX[1] = 75; snakeY[1] = 100;   //身体1坐标
        snakeX[2] = 50; snakeY[2] = 100;   //身体2坐标
        fx = "R";

        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);

        score = 0;
    }

    //画板
    //Graphics 画笔
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //清屏
        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11); //绘制头部栏
        g.fillRect(25, 75, 850, 600); //绘制游戏区域

        //画一条静态的小蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        }else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        }else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); //蛇的身体长度通过length来控制
        }
        //画食物
        Data.food.paintIcon(this, g, foodx, foody);
        //积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑" , Font.BOLD, 18));
        g.drawString("长度:"+length, 750, 35);
        g.drawString("分数:"+score, 750, 50);
        //是否开始
        if (isStart == false) {
            //画文字
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
        }

        //是否失败
        if (isFail) {
            //画文字
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("游戏失败，按下空格重新开始", 300, 300);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //键盘按下，未释放
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {   //失败
                isFail = false;
                init(); //重新初始化游戏
            } else {        //暂停
                isStart = !isStart;
            }
            repaint();  //刷新界面
        }
        //控制键盘走向
        if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //键盘按下，弹起
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //游戏处于开始状态 并且游戏没有结束
        if (isStart && isFail == false) {
            //右移
            for (int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //头部移动 根据方向
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if (snakeX[0] > 850) {snakeX[0] = 25;}
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if (snakeX[0] < 25) {snakeX[0] = 850;}
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                //边界判断
                if (snakeY[0] < 75) {snakeY[0] = 650;}
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                //边界判断
                if (snakeY[0] > 650) {snakeY[0] = 75;}
            }
            //如果蛇头和食物重合
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                length++;
                score += 10;
                //重新生成食物
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }
            //结束判断
            for (int i = 1; i < length; i++) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }

            repaint();
        }
        timer.start();
    }
}
