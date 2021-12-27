package com.zhaoxfan.snake;

import javax.swing.*;

/**gi
 * Created on 2021/12/27.
 *
 * @author zhaoxfan
 */
public class StartGames {

    public static void main(String[] args) {
        //1.绘制一个静态窗口 JFrame
        JFrame frame = new JFrame("贪吃蛇小游戏");
        frame.setBounds(10, 10, 900, 720);  //设置界面大小
        frame.setResizable(false);  //窗口大小不可改变
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //设置关闭事件

        //2.面板 JPanel
        frame.add(new GamePanel());
        frame.setVisible(true);     //让窗口能够展现出来
    }
}
