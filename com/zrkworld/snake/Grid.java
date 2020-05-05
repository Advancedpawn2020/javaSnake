package com.zrkworld.snake;
/*
 * 随机生成食物，维持贪吃蛇的状态，根据SnakeApp中的用户交互来控制游戏状态。
 */
/**
 * @author zrk
 */

public class Grid {
	private Snake snake;
	int width;
	int height;
	Node food;
	private Direction snakeDirection =Direction.LEFT;
	public Grid(int length, int high) {
		super();
		this.width = length;
		this.height = high;
		initSnake();
		food = creatFood();
	}

	//在棋盘上初始化一个蛇
	private void initSnake() {
		snake = new Snake();
		int x = width/2;
		int y = height/2;
		for(int i = 0;i<5;i++) {
			snake.addTail(new Node(x, y));
			x = x+Settings.DEFAULT_NODE_SIZE;
		}
	}
	//棋盘上随机制造食物的功能。
	//一直循环获取随机值，直到三个条件都不满足。
	private Node creatFood() {
		int x,y;
		do {
			x =(int)(Math.random()*100)+10;
			y =(int)(Math.random()*100)+10;
			System.out.println(x);
			System.out.println(y);
			System.out.println(this.width);
			System.out.println(this.height);
		}while(x>=this.width-10 || y>=this.height-10 || snake.hasNode(new Node(x,y)));
		food = new Node(x,y);
		return food;
	}

	//提供下一步更新棋盘的功能，移动后更新游戏和蛇的状态。
	public boolean nextRound() {
		Node trail = snake.move(snakeDirection);
		Node snakeHead = snake.getBody().removeFirst();//将头部暂时去掉，拿出来判断是否身体和头部有重合的点
		if(snakeHead.getX()<=width-10 && snakeHead.getX()>=10
				&& snakeHead.getY()<=height-10 && snakeHead.getY()>=10
				&& !snake.hasNode(snakeHead)) {//判断吃到自己和撞到边界
			if(snakeHead.equals(food)) {
				//原本头部是食物的话，将move操作删除的尾部添加回来
				snake.addTail(trail);
				food = creatFood();
			}
			snake.getBody().addFirst(snakeHead);
			return true;//更新棋盘状态并返回游戏是否结束的标志
		}
		return false;
	}

	public Node getFood() {
		return food;
	}

	public Snake getSnake() {
		return snake;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	//提供一个更改贪吃蛇前进方向的方法
	public void changeDirection(Direction newDirection){
		snakeDirection = newDirection;
	}
}