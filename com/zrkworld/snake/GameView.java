package com.zrkworld.snake;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
/*
 * 属于View，用于绘制地图、蛇、食物
 *
 * Graphics 相当于一个画笔。对象封装了 Java 支持的基本呈现操作所需的状态信息。此状态信息包括以下属性：

	要在其上绘制的 Component 对象。
	呈现和剪贴坐标的转换原点。
	当前剪贴区。
	当前颜色。
	当前字体。
	当前逻辑像素操作函数（XOR 或 Paint）。
	当前 XOR 交替颜色

	回调概念：
	 A调用B中的方法b，需要传入A自身引用，方法b执行完毕后，再利用传入的A的引用，调用A中的方法

	java.awt.Component的repaint()方法
	 作用：更新组件。
		如果此组件不是轻量级组件，则为了响应对 repaint 的调用，AWT 调用 update 方法。可以假定未清除背景。
		Component 的 update 方法调用此组件的 paint 方法来重绘此组件。为响应对 repaint 的调用而需要其他工作的子类通常重写此方法。重写此方法的 Component 子类应该调用 super.update(g)，或者直接从其 update 方法中调用 paint(g)。
		图形上下文的原点，即它的（0，0）坐标点是此组件的左上角。图形上下文的剪贴区域是此组件的边界矩形。
 */
/**
 * @author zrk
 */

public class GameView {
	private final Grid grid;
	private static JPanel canvas;//画板，用于在这上面制作画面，然后返回。

	public GameView(Grid grid) {
		this.grid = grid;
	}
	//重新绘制游戏界面元素，不断重新调用paintComponent方法覆盖原本的面板。
	public static void draw() {
		canvas.repaint();
	}
	//获取画板对象的接口
	public JPanel getCanvas() {
		return canvas;
	}
	//对画板进行初始化
	public void initCanvas() {
		canvas = new JPanel() {//指向一个方法被覆盖的新面板子类对象
			//paintComponent()绘制此容器中的每个组件，Swing会在合适的时机去调用这个方法，展示出合适的界面，这就是典型的回调（callback）的概念。
			public void paintComponent(Graphics graphics) {
				super.paintComponent(graphics); //这里必须调用一下父类 也就是 container的重绘方法，否则表现为之前的绘图不会覆盖
				drawGridBackground(graphics);//画出背景网格线
				drawSnake(graphics, grid.getSnake());//画蛇
				drawFood(graphics, grid.getFood());//画食物
			}
		};
	}
	//画蛇
	public void drawSnake(Graphics graphics, Snake snake) {
		for(Iterator<Node> i = snake.body.iterator();i.hasNext();) {
			Node bodyNode = (Node)i.next();
			drawSquare(graphics, bodyNode,Color.BLUE);
		}
	}
	//画食物
	public void drawFood(Graphics graphics, Node food) {
		drawCircle(graphics,food,Color.ORANGE);
	}

	//画格子背景，方便定位Snake运动轨迹,横竖各以10为单位的50个线。
	public void drawGridBackground(Graphics graphics) {
		graphics.setColor(Color.GRAY);
		canvas.setBackground(Color.BLACK);
		for(int i=0 ; i < 50 ; i++) {
			graphics.drawLine(0, i*Settings.DEFAULT_NODE_SIZE, this.grid.getWidth(), i*Settings.DEFAULT_NODE_SIZE);
		}
		for(int i=0 ; i <50 ; i++) {
			graphics.drawLine(i*Settings.DEFAULT_NODE_SIZE, 0,  i*Settings.DEFAULT_NODE_SIZE , this.grid.getHeight());
		}
		graphics.setColor(Color.red);
		graphics.fillRect(0, 0, this.grid.width, Settings.DEFAULT_NODE_SIZE);
		graphics.fillRect(0, 0, Settings.DEFAULT_NODE_SIZE, this.grid.height);
		graphics.fillRect(this.grid.width, 0, Settings.DEFAULT_NODE_SIZE,this.grid.height);
		graphics.fillRect(0, this.grid.height, this.grid.width+10,Settings.DEFAULT_NODE_SIZE);

	}
	/*
     * public abstract void drawLine(int x1,int y1,int x2,int y2)
        在此图形上下文的坐标系中，使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线。
        参数：
                x1 - 第一个点的 x 坐标。
                y1 - 第一个点的 y 坐标。
                x2 - 第二个点的 x 坐标。
                y2 - 第二个点的 y 坐标。
     */
	//提供直接出现游戏结束的选项框的功能。
	public static void showGameOverMessage() {
		JOptionPane.showMessageDialog(null,"游戏结束","短暂的蛇生到此结束", JOptionPane.INFORMATION_MESSAGE);
	}


	//画图形的具体方法：
	private void drawSquare(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillRect(squareArea.getX(), squareArea.getY(), size - 1, size - 1);
	}

	private void drawCircle(Graphics graphics, Node squareArea, Color color) {
		graphics.setColor(color);
		int size = Settings.DEFAULT_NODE_SIZE;
		graphics.fillOval(squareArea.getX(), squareArea.getY(), size, size);
	}

}