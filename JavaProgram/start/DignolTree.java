package com.start;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DignolTree {
	
	Values val = new Values();
	DNode root;
	public static Map<Integer, List<DNode>> lmap = new HashMap<Integer, List<DNode>>();
	
	public static void treTree(DNode r, int h){
		if (r == null) {
			return;
		}
		if (lmap.get(h + 1) == null && r.right != null) {
			List<DNode> lst = new ArrayList<DNode>();
			lst.add(r.right);
			lmap.put(h + 1, lst);
			treTree(r.right,h+1);
		} else if(r.right != null){
			lmap.get(h+1).add(r.right);
			treTree(r.right,h+1);
		}
		
		if(r.left != null){
			lmap.get(h).add(r.left);
			treTree(r.left,h);
		}
	}
	public static void printMap() {
		Object[] s = lmap.keySet().toArray();
		Arrays.sort(s);
		for (Object i : s) {
			for(DNode opn : lmap.get(i))
				System.out.print(opn.data+" ");
			System.out.println("");
		}
	}
	public static void main(String args[]) {
		DignolTree tree = new DignolTree();
		tree.root = new DNode(1);
		tree.root.left = new DNode(2);
		tree.root.right = new DNode(3);
		tree.root.left.left = new DNode(4);
		tree.root.left.right = new DNode(5);
		tree.root.right.left = new DNode(6);
		tree.root.right.right = new DNode(7);
		tree.root.right.left.right = new DNode(8);
		//tree.root.right.right.right = new DNode(9);
		List<DNode> lst = new ArrayList<DNode>();
		lst.add(tree.root);
		lmap.put(1,lst);
		treTree(tree.root,1);
		printMap();
	}

}
class DNode {
	int data;
	DNode left, right;

	DNode(int d) {
		data = d;
		left = right = null;
	}
}

