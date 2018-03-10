package com.start;
class printverticaltree{
	Values val=new Values();
	NodeT root;
	public void findmaxmin(NodeT node,Values val,int hd)
	{
		if(node==null)
			return;
		
		if(hd<val.min)
			val.min=hd;
		else if(hd>val.max)
			val.max=hd;
		findmaxmin(node.left,val,hd-1);
		findmaxmin(node.right,val,hd+1);
	}
	public void printvertical(NodeT node,int line,int hd)
	{
		if(node==null)
			return;
		if(line==hd)
			System.out.print(node.data+" ");
		printvertical(node.left, line, hd-1);
		printvertical(node.right, line, hd+1);
	}
	public void verticalorder(NodeT node)
	{
		findmaxmin(node,val,0);
		System.out.println("-------   "  +val.min + " " +val.max);
		for(int i=val.min;i<=val.max;i++)
		{
			printvertical(node,i,0);
			System.out.println("");
		}
	}
	public static void main(String args[])
	{
		printverticaltree tree = new printverticaltree();
		  
        /* Let us construct the tree shown in above diagram */
        tree.root = new NodeT(1);
        tree.root.left = new NodeT(2);
        tree.root.right = new NodeT(3);
        tree.root.left.left = new NodeT(4);
        tree.root.left.right = new NodeT(5);
        tree.root.right.left = new NodeT(6);
        tree.root.right.right = new NodeT(7);
        tree.root.right.left.right = new NodeT(8);
        tree.root.right.right.right = new NodeT(9);
  
        System.out.println("vertical order traversal is :");
        tree.verticalorder(tree.root);
	}
}


class NodeT{
	int data;
	NodeT left,right;
	NodeT(int d)
	{
		data=d;
		left=right=null;	
	}	
}

class Values{
	int max,min;
}
