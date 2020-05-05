package com.zrkworld.snake;
/**
 * @author zrk
 */

public class Node {
	private  int x;
	private  int y;
	public Node(int x, int y) {
		this.x = ((int)(x/10))*10;
		this.y = ((int)(y/10))*10;
	}//使用这种方法可以使得节点坐标不会出现个位数

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	@Override
	//判断两个Node是否相同
	public boolean equals(Object n) {
		Node temp;
		if(n instanceof Node) {
			temp = (Node)n;
			if(temp.getX()==this.getX() && temp.getY()==this.getY())
				return true;
		}
		return false;
	}
}