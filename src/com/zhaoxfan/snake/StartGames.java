package com.zhaoxfan.snake;

import javax.swing.*;

/**gi
 * Created on 2021/12/27.
 *
 * @author zhaoxfan
 */
public class StartGames {

    public static void main(String[] args) {
        //1.����һ����̬���� JFrame
        JFrame frame = new JFrame("̰����С��Ϸ");
        frame.setBounds(10, 10, 900, 720);  //���ý����С
        frame.setResizable(false);  //���ڴ�С���ɸı�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //���ùر��¼�

        //2.��� JPanel
        frame.add(new GamePanel());
        frame.setVisible(true);     //�ô����ܹ�չ�ֳ���
    }
}
