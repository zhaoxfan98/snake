package com.zhaoxfan.snake;

import javax.swing.*;
import java.net.URL;

/**
 * Created on 2021/12/27.
 *
 * @author zhaoxfan
 */
public class Data {
    //ͷ����ͼƬ URL ImageIcon
    public static URL headerURL = Data.class.getResource("/statics/header.png");
    public static ImageIcon header = new ImageIcon(headerURL);
    //��ͷ
    public static URL upURL = Data.class.getResource("/statics/up.png");
    public static URL downURL = Data.class.getResource("/statics/down.png");
    public static URL leftURL = Data.class.getResource("/statics/left.png");
    public static URL rightURL = Data.class.getResource("/statics/right.png");
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    //����
    public static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //ʳ��
    public static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}