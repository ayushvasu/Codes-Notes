package com.start;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LightOnTree {
	Values val = new Values();
	TNode root;
	public static Map<Integer, TNode> lmap = new HashMap<Integer, TNode>();

	public static void treverseTree(TNode r, int h) {
		h++;
		if (r == null) {
			return;
		}
		if (lmap.get(h) == null && r.right != null) {
			lmap.put(h, r.right);
		} else if(lmap.get(h) == null && r.left != null){
			lmap.put(h, r.left);
		}
		treverseTree(r.right, h);
		treverseTree(r.left, h);
	}

	public static void printMap() {
		Object[] s = lmap.keySet().toArray();
		Arrays.sort(s);
		for (Object i : s) {
			System.out.println(lmap.get(i).data);
		}
	}

	public static void main(String args[]) {
		LightOnTree tree = new LightOnTree();
		tree.root = new TNode(1);
		tree.root.left = new TNode(2);
		tree.root.right = new TNode(3);
		tree.root.left.left = new TNode(4);
		tree.root.left.right = new TNode(5);
		tree.root.right.left = new TNode(6);
		tree.root.right.right = new TNode(7);
		tree.root.right.left.right = new TNode(8);
		//tree.root.right.right.right = new TNode(9);
		System.out.println("Node Under Light :");
		lmap.put(0, tree.root);
		treverseTree(tree.root, 0);
		printMap();
	}
}

class TNode {
	int data;
	TNode left, right;

	TNode(int d) {
		data = d;
		left = right = null;
	}
}

class NValue {
	int max, min;
}