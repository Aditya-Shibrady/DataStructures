import java.util.ArrayList;

public class SplayTree {
	
	private Node rootNode;
	
	public boolean add(int value)
	{
		if(rootNode == null)
		{
			rootNode = new Node(value, null, null);
			return true;
		}
	    rootNode = splay(rootNode, value);
	    if (rootNode.data == value) 
	    	return true;
	    Node temp = new Node(value, null, null);
	    
	    if (rootNode.data > value)
	    {
	    	temp.rightChild = rootNode;
	    	temp.leftChild = rootNode.leftChild;
	    	rootNode.leftChild = null;
	    }
	    else
	    {
	        temp.leftChild = rootNode;
	        temp.rightChild = rootNode.rightChild;
	        rootNode.rightChild = null;
	    }
	    
	    rootNode = temp;	 
	    return true;
		
	}
	
	public boolean find(int value)
	{
		rootNode = splay(rootNode,value);
        if(rootNode.data == value )
        	return true;
        return false; 
	}
	
	public int leafCount()
	{
		return leafCount(rootNode);
	}
	private int leafCount(Node currentNode) {
		if(currentNode == null)
			return 0;
		if(currentNode.leftChild == null && currentNode.rightChild == null)
			return leafCount(currentNode.leftChild) + leafCount(currentNode.rightChild) + 1;
		else
			return leafCount(currentNode.leftChild) + leafCount(currentNode.rightChild);
	}
	
	public int treeSum()
	{
		return treeSum(rootNode);
	}
	private int treeSum(Node currentNode) {
		if(currentNode == null)
			return 0;
		return treeSum(currentNode.leftChild) + treeSum(currentNode.rightChild) + currentNode.data;
	}
	
	private Node LRotate(Node currentNode)
	{
		Node temp = currentNode.rightChild;
		currentNode.rightChild = temp.leftChild;		
		temp.leftChild=currentNode;
		return temp;
	}
	private Node RRotate(Node currentNode)
	{
		Node temp  = currentNode.leftChild;
		currentNode.leftChild = temp.rightChild;
		temp.rightChild = currentNode;
		return temp;
	}
	private Node splay(Node root,int value)
	{
		if (root == null || root.data == value)
	        return root;
	 
	    if (root.data > value)
	    {
	        if (root.leftChild == null) 
	        	return root;
	        if (root.leftChild.data > value)
	        {
	            root.leftChild.leftChild = splay(root.leftChild.leftChild, value);
	            root = RRotate(root);
	        }
	        else if (root.leftChild.data < value)
	        {
	            root.leftChild.rightChild = splay(root.leftChild.rightChild, value);
	            if (root.leftChild.rightChild != null)
	                root.leftChild = LRotate(root.leftChild);
	        }
	        if(root.leftChild == null)
	        	return root;
	        else
	        	return RRotate(root);
	    }
	    else 
	    {
	        if (root.rightChild == null) 
	        	return root;
	 
	        if (root.rightChild.data > value)
	        {
	            root.rightChild.leftChild = splay(root.rightChild.leftChild, value);
	            if (root.rightChild.leftChild != null)
	                root.rightChild = RRotate(root.rightChild);
	        }
	        else if (root.rightChild.data < value)
	        {
	            root.rightChild.rightChild = splay(root.rightChild.rightChild, value);
	            root = LRotate(root);
	        }
	        if (root.rightChild == null) 
	        	return root;
	        else
	        	return LRotate(root);
	    }
	}

	private static class Node
	{
		int data;
		Node leftChild;
		Node rightChild;
		
		public Node(int d, Node lc, Node rc)
		{
			data = d;
			leftChild = lc;
			rightChild = rc;
		}
	}
	
	public String toString()
	{
		return printinOrder(rootNode);
	}
	
	private String printinOrder(Node currentNode) {
		if(currentNode == null)
			return "";
		return printinOrder(currentNode.leftChild)+" " + currentNode.data +" "+ printinOrder(currentNode.rightChild);
	}
	
	public void printLevels()
	{
		ArrayList<Node> nodesInLevel = new ArrayList<>();
		if (rootNode != null) {
			nodesInLevel.add(rootNode);
		}
		printNodes(nodesInLevel);
	}
	
	private void printNodes(ArrayList<Node> nodesInLevel) {
		if (nodesInLevel.size() == 0) {
			return;
		}
		ArrayList<Node> childNodes = new ArrayList<>();
		for (Node node : nodesInLevel) {
			System.out.print(node.data+" ");
			if(node.leftChild != null)
				childNodes.add(node.leftChild);
			if(node.rightChild != null)
				childNodes.add(node.rightChild);
		}
		System.out.println();
		printNodes(childNodes);
	}

	public static void main(String[] args) {
		SplayTree stree = new SplayTree();
		for (int i = 1; i <= 5; i++) {
			stree.add(i*10);
		}
		stree.printLevels();
		System.out.println();
		stree.add(11);
		stree.add(45);
		stree.add(16);
		stree.add(8);
		stree.add(9);
		stree.printLevels();
		System.out.println();
		System.out.println("Leaf Count: "+stree.leafCount());
		System.out.println("Tree Sum: "+stree.treeSum());
		System.out.println("15 exists? : "+stree.find(15));
		stree.printLevels();
		System.out.println();
		System.out.println("30 exists? : "+stree.find(30));
		stree.printLevels();
		System.out.println();
	}

}
