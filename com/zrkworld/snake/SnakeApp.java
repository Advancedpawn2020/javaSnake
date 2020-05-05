package com.zrkworld.snake;

import com.zrkworld.snake.GameController;
import com.zrkworld.snake.GameView;
import com.zrkworld.snake.Grid;
import com.zrkworld.snake.Settings;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
/*
 * 	积分功能：可以创建得分规则的类（模型类的一部分）， 在GameController的run()方法中计算得分

	变速功能：比如加速功能，减速功能，可以在GameController的keyPressed()方法中针对特定的按键设置每一次移动之间的时间间隔，将Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);替换为动态的时间间隔即可

	更漂亮的游戏界面：修改GameView中的drawXXX方法，比如可以将食物渲染为一张图片，Graphics有drawImage方法
 */

/*
 * 属于View，用来根据相应的类展示出对应的游戏主界面，也是接收控制信息的第一线。
 */
/**
 * @author zrk
 */

public class SnakeApp {
    public void init() {
        //创建游戏窗体
        JFrame window = new JFrame("一只长不大的蛇");
        //初始化500X500的棋盘,用来维持各种游戏元素的状态，游戏的主要逻辑部分
        Grid grid = new Grid(50* Settings.DEFAULT_NODE_SIZE,50*Settings.DEFAULT_NODE_SIZE);
        //传入grid参数，新建界面元素对象
        //绘制游戏元素的对象
        GameView gameView = new GameView(grid);
        //初始化面板
        gameView.initCanvas();
        //根据棋盘信息建立控制器对象
        GameController gameController = new GameController(grid);
       
        //设置窗口大小
        window.setPreferredSize(new Dimension(526,548));
        
        //往窗口中添加元素，面板对象被加入到窗口时，自动调用其中的paintComponent方法。
        window.add(gameView.getCanvas(),BorderLayout.CENTER);
        
        //画出蛇和棋盘和食物
        GameView.draw();
        
        //注册窗口监听器
        window.addKeyListener((KeyListener)gameController);
        
        //启动线程
        new Thread(gameController).start();
        
        //窗口关闭的行为
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小不可变化
        window.setResizable(false);
        //渲染和显示窗口
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SnakeApp snakeApp = new SnakeApp();
        snakeApp.init();
    }
}
