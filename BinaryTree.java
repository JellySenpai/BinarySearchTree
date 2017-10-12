
public class BinaryTree 
{
	Node root;
	
	public void addNode(int key, String name)
	{
		Node newNode = new Node(key, name);
		
		if(root == null)
		{
			root = newNode;
		}
		else
		{
			Node focusNode = root;
			
			Node parent;
			
			while(true)
			{
				parent = focusNode;
				
				if(key < focusNode.key)
				{
					focusNode = focusNode.leftLeaf;
					
					if(focusNode ==null)
					{
						parent.leftLeaf = newNode;
						return;
					}
					else
					{
						focusNode = focusNode.rightLeaf;
						
						if(focusNode ==null)
						{
							parent.rightLeaf = newNode;
							return;
						}
						
					}
				}
			}
		}
	}
	
	public void inOrderTree(Node focusNode)
	{
		if (focusNode != null)
		{
			inOrderTree(focusNode.leftLeaf);
			System.out.println(focusNode);
			inOrderTree(focusNode.rightLeaf);
		}
	}
	
	public void preOrderTree(Node focusNode)
	{
		if (focusNode != null)
		{
			System.out.println(focusNode);
			preOrderTree(focusNode.leftLeaf);
			preOrderTree(focusNode.rightLeaf);
		}
	}
	public void postOrderTree(Node focusNode)
	{
		if (focusNode != null)
		{
			postOrderTree(focusNode.leftLeaf);
			postOrderTree(focusNode.rightLeaf);
			System.out.println(focusNode);
		}
	}
	
	public Node findNode(int key)
	{
		Node focusNode = root;
		
		while(focusNode.key!=key)
		{
			if (key<focusNode.key)
			{
				focusNode = focusNode.leftLeaf;
			}
			else
			{
				focusNode = focusNode.rightLeaf;
			}
			if (focusNode == null)
			{
				return null;
			}		
		}
		return focusNode;
	}
	
	public boolean remove(int key)
	{
		Node focusNode = root;
		Node parent = root;
		
		boolean isLeftLeaf = true;
		
		while(focusNode.key!=key)
		{
			parent = focusNode;
			
			if(key < focusNode.key)
			{
				isLeftLeaf = true;
				
				focusNode = focusNode.leftLeaf;
			}
			else
			{
				isLeftLeaf = false;
				
				focusNode = focusNode.rightLeaf;
			}
			if(focusNode==null)
			{
				return false;
			}
		}
		if (focusNode.leftLeaf == null && focusNode.rightLeaf == null)
		{
			if (focusNode == root)
			{
				root = null;
			}
			else if(isLeftLeaf)
			{
				parent.leftLeaf = null;
			}
			else
			{
				parent.rightLeaf = null;
			}
		}
		
		else if (focusNode.rightLeaf == null)  //This will run when there is no right child
		{
			if (focusNode == root)
				root = focusNode.leftLeaf;
			else if(isLeftLeaf)
				parent.leftLeaf = focusNode.leftLeaf;
			else
				parent.rightLeaf = focusNode.leftLeaf; //All three of these functions
		}
		else if (focusNode.leftLeaf == null)
		{
			if (focusNode == root)
			{
				root = focusNode.rightLeaf;
			}
			else if(isLeftLeaf)
			{
				parent.leftLeaf = focusNode.rightLeaf;
			}
			else
				parent.rightLeaf = focusNode.leftLeaf;
		}
		else
		{
			Node replace = findReplacementNode(focusNode);
			
			if (focusNode == root)
			{
				root = replace;
			}
			else if (isLeftLeaf)
			{
				parent.leftLeaf = replace;
			}
			else
			{
				parent.rightLeaf = replace;
			}
			replace.leftLeaf = focusNode.leftLeaf;
		}
		return true;
	}
	
	public Node findReplacementNode(Node replacedNode)
	{
		Node replaceParent = replacedNode;
		Node replace = replacedNode;
		
		Node focusNode = replacedNode.rightLeaf;
		
		while (focusNode != null)
		{
			replaceParent = replace;
			replace = focusNode;
			
			focusNode = focusNode.leftLeaf;
		}
		
		if (replace != replacedNode.rightLeaf)
		{
			replaceParent.leftLeaf = replace.rightLeaf;
			replace.rightLeaf = replacedNode.rightLeaf;
		}
		return replace;
	}
	
	public Node findMaxKey (int key)
	{
		Node focusNode = root;
		
		return focusNode;
	}
	
	public static void main(String[] args)
	{
		BinaryTree daTree = new BinaryTree();
		
		daTree.addNode(50, "haseo");
		daTree.addNode(25, "haseo1");
		daTree.addNode(15, "haseo2");
		daTree.addNode(30, "haseo3");
		daTree.addNode(75, "haseo4");
		
		daTree.inOrderTree(daTree.root);
	}
	
	
	
}
 class Node 
 {
	 int key;
	 String name;
	 
	 Node leftLeaf;
	 Node rightLeaf;
	 
	 Node (int key, String name)
	 {
		 this.key = key;
		 
		 this.name=name;
	 }
	 public String toString()
	 {
		 return name + "has a key " + key;
	 }
 }
