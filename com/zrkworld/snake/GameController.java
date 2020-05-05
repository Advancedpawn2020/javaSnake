package com.zrkworld.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @author zrk
 */

/*
 * 接收窗体SnakeApp传递过来的有意义的事件，然后传递给Grid，让Grid即时的更新状态。
 * 同时根据最新状态渲染出游戏界面让SnakeApp显示
 * 属于MVC中的Controller部分：负责转发用户操作事件，对事件进行处理
 *
 */
public class GameController implements KeyListener, Runnable{
	private Grid grid;
	private boolean running;

	public GameController(Grid grid){
		this.grid = grid;
		this.running = true;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
			case KeyEvent.VK_UP:
				grid.changeDirection(Direction.UP);
				break;
			case KeyEvent.VK_DOWN:
				grid.changeDirection(Direction.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				grid.changeDirection(Direction.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				grid.changeDirection(Direction.RIGHT);
				break;
		}
		isOver(grid.nextRound());
		GameView.draw();
	}

	private void isOver(boolean flag) {
		if(!flag) {//如果下一步更新棋盘时，出现游戏结束返回值（如果flag为假）则
			this.running = false;
			GameView.showGameOverMessage();
			System.exit(0);
		}
	}

	@Override
	/*run()函数中的核心逻辑是典型的控制器（Controller）逻辑：
	修改模型（Model）：调用Grid的方法使游戏进入下一步
	更新视图（View）：调用GameView的方法刷新页面*/
	public void run() {
		while(running) {
			try {
				Thread.sleep(Settings.DEFAULT_MOVE_INTERVAL);
				isOver(grid.nextRound());
				GameView.draw();
			} catch (InterruptedException e) {
				break;
			}
			// 进入游戏下一步
			// 如果结束，则退出游戏
			// 如果继续，则绘制新的游戏页面
		}
		running = false;
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
}