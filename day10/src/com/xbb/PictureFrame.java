package com.xbb;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PictureFrame extends JFrame {
    private int[][] datas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    private int x0;
    private int y0;
    private JButton shangButton;
    private JButton zuoButton;
    private JButton xiaButton;
    private JButton youButton;
    private JButton qiuZhuButton;
    private JButton chongZhiButton;
    private JLabel backgroundLabel;
    private JPanel imagePanel;
    private JLabel canZhaoTuLabel;
    private JLabel titleLabel;
    //定义移动成功后的数组
    private int[][] winDatas = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public PictureFrame() {
        //用于窗体的基本设置
        initFrame();
        //二维数组元素打乱
        randomData();
        //窗体上组件的绘制
        paintView();
        //给按钮添加事件
        addButtonEvent();
        //设置窗体可见
        this.setVisible(true);
    }

    //用于窗体的基本设置
    public void initFrame() {
        //窗体大小
        this.setSize(960,565);
        //窗体标题
        this.setTitle("动漫拼图");
        //窗体居中
        this.setLocationRelativeTo(null);
        //窗体关闭时退出应用程序
        this.setDefaultCloseOperation(3);
        //窗体位于其他窗口之上
        this.setAlwaysOnTop(true);
        //取消窗体默认布局
        this.setLayout(null);
    }

    //窗体上组件的绘制
    public void paintView() {
        //标题图片
        titleLabel = new JLabel(new ImageIcon(".\\day10\\images\\title.png"));
        titleLabel.setBounds(354,27,232,57);
        this.add(titleLabel);

//        //定义一个二维数组，用来存储图片的编号
//        int[][] datas = {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
//        };

        //创建面板
        imagePanel = new JPanel();
        imagePanel.setBounds(150,114,360,360);
        imagePanel.setLayout(null);
        //遍历二维数组，得到图片编号
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                //创建JLabel对象，加载图片资源
                JLabel imageLabel = new JLabel(new ImageIcon(".\\day10\\images\\"+datas[i][j]+".png"));
                //调整图片的位置
                imageLabel.setBounds(j*90,i*90,90,90);
                imagePanel.add(imageLabel);
            }
        }
        //把面板添加到窗体上
        this.add(imagePanel);

        //动漫参照图
        canZhaoTuLabel = new JLabel(new ImageIcon(".\\day10\\images\\canzhaotu.png"));
        canZhaoTuLabel.setBounds(574,114,122,121);
        this.add(canZhaoTuLabel);

        //上下左右，求助，重置按钮
        shangButton = new JButton(new ImageIcon(".\\day10\\images\\shang.png"));
        shangButton.setBounds(732,265,57,57);
        this.add(shangButton);

        zuoButton = new JButton(new ImageIcon(".\\day10\\images\\zuo.png"));
        zuoButton.setBounds(650,347,57,57);
        this.add(zuoButton);

        xiaButton = new JButton(new ImageIcon(".\\day10\\images\\xia.png"));
        xiaButton.setBounds(732,347,57,57);
        this.add(xiaButton);

        youButton = new JButton(new ImageIcon(".\\day10\\images\\you.png"));
        youButton.setBounds(813,347,57,57);
        this.add(youButton);

        qiuZhuButton = new JButton(new ImageIcon(".\\day10\\images\\qiuzhu.png"));
        qiuZhuButton.setBounds(626,444,108,45);
        this.add(qiuZhuButton);

        chongZhiButton = new JButton(new ImageIcon(".\\day10\\images\\chongzhi.png"));
        chongZhiButton.setBounds(786,444,108,45);
        this.add(chongZhiButton);

        //展示背景图
        backgroundLabel = new JLabel(new ImageIcon(".\\day10\\images\\background.png"));
        backgroundLabel.setBounds(0,0,960,530);
        this.add(backgroundLabel);
    }

    //二维数组元素打乱
    public void randomData() {
        Random r = new Random();
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                int x = r.nextInt(datas.length);
                int y = r.nextInt(datas[i].length);

                int temp = datas[i][j];
                datas[i][j] = datas[x][y];
                datas[x][y] = temp;
            }
        }

        //纪录0号图片的位置
        wc:for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if(datas[i][j] == 0) {
                    this.x0 = i;
                    this.y0 = j;
                    break wc;
                }
            }
        }
//        System.out.println(this.x0+","+this.y0);
    }

    //给按钮添加事件
    public void addButtonEvent() {
        shangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //边界处理
                if (x0 == 3) {
                    return;
                }
                //位置交换
                datas[x0][y0] = datas[x0 + 1][y0];
                datas[x0 + 1][y0] = 0;
                x0 = x0 + 1;
                //判断是否恢复成功
                if (isSuccess()) {
                    success();
                }
                //重绘方法调用
                rePaintView();
            }
        });
        zuoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //边界处理
                if (y0 == 3) {
                    return;
                }
                //位置交换
                datas[x0][y0] = datas[x0][y0 + 1];
                datas[x0][y0 + 1] = 0;
                y0 = y0 + 1;
                //判断是否恢复成功
                if (isSuccess()) {
                    success();
                }
                //重绘方法调用
                rePaintView();
            }
        });
        xiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //边界处理
                if (x0 == 0) {
                    return;
                }
                //位置交换
                datas[x0][y0] = datas[x0-1][y0];
                datas[x0-1][y0 ] = 0;
                x0 = x0 - 1;
                //判断是否恢复成功
                if (isSuccess()) {
                    success();
                }
                //重绘方法调用
                rePaintView();
            }
        });
        youButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //边界处理
                if (y0 == 0) {
                    return;
                }
                //位置交换
                datas[x0][y0] = datas[x0][y0-1];
                datas[x0][y0-1] = 0;
                y0 = y0-1;
                //判断是否恢复成功
                if (isSuccess()) {
                    success();
                }
                //重绘方法调用
                rePaintView();
            }
        });
        qiuZhuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                success();
                //重绘方法调用
                rePaintView();
            }
        });
        chongZhiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datas = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                randomData();
                rePaintView();
                shangButton.setEnabled(true);
                zuoButton.setEnabled(true);
                xiaButton.setEnabled(true);
                youButton.setEnabled(true);
            }
        });
    }
    //移动的图形重新绘制
    public void rePaintView() {
        //移除所有
        imagePanel.removeAll();

        //遍历二维数组，得到每一个图片编号
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                //在遍历的过程中，创建 JLabel 对象，加载图片资源
                JLabel imageLabel = new JLabel(new ImageIcon(".\\day10\\images\\" + datas[i][j] + ".png"));
                //调整图片资源的摆放位置
                imageLabel.setBounds(j * 90, i * 90, 90, 90);
                imagePanel.add(imageLabel);
            }
        }

        //重新绘制窗体
        imagePanel.repaint();
    }

    //重置
    public void success() {
        datas = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        shangButton.setEnabled(false);
        zuoButton.setEnabled(false);
        xiaButton.setEnabled(false);
        youButton.setEnabled(false);
    }

    //判断是否恢复成功
    public boolean isSuccess() {
        for (int i = 0; i < datas.length; i++) {
            for (int j = 0; j < datas[i].length; j++) {
                if (datas[i][j] != winDatas[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
