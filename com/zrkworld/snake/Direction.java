package com.zrkworld.snake;
/**
 * @author zrk
 */

/*
 * 用来控制蛇的移动方向
 */
public enum Direction {
	//调用构造方法对方向枚举实例进行代码初始化
	UP(0),
	DOWN(1),
	LEFT(2),
	RIGHT(3);
	//成员变量
	private final int directionCode;

	//成员方法
	public int directionCode() {
		return directionCode;
	}
	Direction(int directionCode){
		this.directionCode = directionCode;
	}
}