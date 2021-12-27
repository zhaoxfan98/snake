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
    String fx;    //�ߵķ���
    boolean isStart = false;

    Timer timer = new Timer(100, this);  //��ʱ��
    //1.����ʳ��
    int foodx;
    int foody;
    Random random = new Random();

    //�����ж�
    boolean isFail = false;
    //����
    int score;

    //������
    public GamePanel() {
        init();
        //��ȡ���̵ļ����¼�
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //��ʼ��
    public void init() {
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;   //ͷ������
        snakeX[1] = 75; snakeY[1] = 100;   //����1����
        snakeX[2] = 50; snakeY[2] = 100;   //����2����
        fx = "R";

        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);

        score = 0;
    }

    //����
    //Graphics ����
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);    //����
        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11); //����ͷ����
        g.fillRect(25, 75, 850, 600); //������Ϸ����

        //��һ����̬��С��
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
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]); //�ߵ����峤��ͨ��length������
        }
        //��ʳ��
        Data.food.paintIcon(this, g, foodx, foody);
        //����
        g.setColor(Color.WHITE);
        g.setFont(new Font("΢���ź�" , Font.BOLD, 18));
        g.drawString("����:"+length, 750, 35);
        g.drawString("����:"+score, 750, 50);
        //�Ƿ�ʼ
        if (isStart == false) {
            //������
            g.setColor(Color.WHITE);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));
            g.drawString("���¿ո�ʼ��Ϸ", 300, 300);
        }

        //�Ƿ�ʧ��
        if (isFail) {
            //������
            g.setColor(Color.RED);
            g.setFont(new Font("΢���ź�", Font.BOLD, 40));
            g.drawString("��Ϸʧ�ܣ����¿ո����¿�ʼ", 300, 300);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //���̰��£�δ�ͷ�
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {   //ʧ��
                isFail = false;
                init(); //���³�ʼ����Ϸ
            } else {        //��ͣ
                isStart = !isStart;
            }
            repaint();  //ˢ�½���
        }
        //���Ƽ�������
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
        //���̰��£�����
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //�ͷ�ĳ����
    }

    //ִ�ж�ʱ����
    @Override
    public void actionPerformed(ActionEvent e) {
        //��Ϸ���ڿ�ʼ״̬ ������Ϸû�н���
        if (isStart && isFail == false) {
            //����
            for (int i = length-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            //ͷ���ƶ� ���ݷ���
            if (fx.equals("R")) {
                snakeX[0] = snakeX[0] + 25;
                //�߽��ж�
                if (snakeX[0] > 850) {snakeX[0] = 25;}
            } else if (fx.equals("L")) {
                snakeX[0] = snakeX[0] - 25;
                //�߽��ж�
                if (snakeX[0] < 25) {snakeX[0] = 850;}
            } else if (fx.equals("U")) {
                snakeY[0] = snakeY[0] - 25;
                //�߽��ж�
                if (snakeY[0] < 75) {snakeY[0] = 650;}
            } else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                //�߽��ж�
                if (snakeY[0] > 650) {snakeY[0] = 75;}
            }
            //�����ͷ��ʳ���غ�
            if (snakeX[0] == foodx && snakeY[0] == foody) {
                length++;
                score += 10;
                //��������ʳ��
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }
            //�����ж�
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
