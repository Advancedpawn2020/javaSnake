package zhang.ri.kang.snakeMain;

import java.util.Iterator;
import java.util.LinkedList;
/*
 * 蛇类，实现了自身数据结构，以及移动的功能
 */
public class Snake implements Cloneable{
	public LinkedList<Node> body = new LinkedList<>();
	

	public Node move(Direction direction) {
		//根据方向更新贪吃蛇的body
		//返回移动之前的尾部Node(为了吃到时候后增加尾部长度做准备)
		Node n;//临时存储新头部移动方向的结点
		switch (direction) {
	    case UP:
	    	n = new Node(this.getHead().getX(),this.getHead().getY()-Settings.DEFAULT_NODE_SIZE); 
	    	break;
	    case DOWN:
	    	n = new Node(this.getHead().getX(),this.getHead().getY()+Settings.DEFAULT_NODE_SIZE); 
	    	break;
    	case RIGHT: 
    		n = new Node(this.getHead().getX()+Settings.DEFAULT_NODE_SIZE,this.getHead().getY()); 
    		break;
    	default: 
    		n = new Node(this.getHead().getX()-Settings.DEFAULT_NODE_SIZE,this.getHead().getY());
    	
	}
		Node temp = this.body.getLast();
		this.body.addFirst(n);
		this.body.removeLast();
		return temp;
	}
	public Node getHead() {
		return body.getFirst();
	}
	public Node getTail() {
		return body.getLast();
	}
	public Node addTail(Node area) {
		this.body.addLast(area);
		return area;
	}
	public LinkedList<Node> getBody(){
		return body;
	}
	//判断参数结点是否在蛇身上
	public boolean hasNode(Node node) {
		Iterator<Node> it = body.iterator();
		Node n = new Node(0,0);
		while(it.hasNext()) {
			n = it.next();
			if(n.getX() == node.getX() && n.getY() == node.getY()) {
				return true;
			}
		}
		return false;
	}
	
//	
//	public Node eat(Node food) {
//		if(food == body.getFirst()) {
//			Node last1 = body.getLast();
//			Node last2 = body.get(body.size()-1);
//			if(last1.getX()==last2.getX()) {
//				if(last1.getY()>last2.getY())
//				body.addLast(new Node(last1.getX(),last1.getY()+1));
//				else if(last1.getY()<last2.getY()) {
//					body.addLast(new Node(last1.getX(),last1.getY()-1));
//				}
//			}else if(last1.getY() == last2.getY()) {
//				if(last1.getX()>last2.getX()) {
//					body.addLast(new Node(last1.getX()+1,last1.getY()));
//				}else if(last1.getX()<last2.getX()) {
//					body.addLast(new Node(last1.getX()-1,last1.getY()));
//				}
//			}
//			return food;
//		}
//		return null;
//	}
//    public Node eat(Node food) {
//
//        if (isNeighbor(body.getFirst(), food)) {
//        	// 相邻情况下的处理
//        }
//    }
//    
//    private boolean isNeighbor(Node a, Node b) {
//        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) == 1;
//    }
}
