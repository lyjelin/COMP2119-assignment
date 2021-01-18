package comp2119Ass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * 
 * @author Lee Yoon Jeong
 *
 */
public class findSubset {
	
	public static void main(String[] args) throws FileNotFoundException {
		findSubset f = new findSubset();
		f.addLeaf();
		
	}
	
	public String[] readInputFile() throws FileNotFoundException {
		String[] set=null;
		
		try {
			String fileName = "/Users/elin/Dropbox/comp2119Ass/src/comp2119Ass/input.txt";
			
			File input = new File(fileName);
			
			BufferedReader d = new BufferedReader(new FileReader(input));
			
			String line = null;
			int n=0;
			
			while ((line = d.readLine())!=null) {
				if (n==0) {
					set = new String[Integer.parseInt(line)];
					n++;
				}
				else {
					set[n-1]=line;
					n++;
				}
			}
			d.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return set;
	
	}
	
	/**
	 * 
	 * @throws FileNotFoundException
	 */
	public void addLeaf() throws FileNotFoundException {
		Tree tree = new Tree();
		//Node root = tree.root;
	
				
		String[] set = readInputFile();
		int size = 2^set.length*2-1;
		//int setSize = set.length;
				
		for (int i=0;i<set.length;i++) {
			tree.insert(tree.root, set[i]);
		}
		
		tree.print(tree.root, size);
	
	}
	
	
}

/**
 * 
 * Class node for nodes in Binary tree
 *
 */
class Node {
	
	String item;
	Node left, right;
	
	Node (String item){
		this.item = item;
		left = null;
		right = null; 
	}
}

/**
 * 
 * Class tree for binary tree implementation
 *
 */
class Tree {
	
	Node root;
	String[] subset = new String[100];
	int count=0;
	
	public Tree() {
		root= new Node(null);
	}
	

	public void insert(Node node, String value) {
		if (node.item!=value) {
			
			if (node.left!=null) {
				insert(node.left, value);
				insert(node.right, value);
			}
			else {
				if (node!=root) {
					node.left = new Node(node.item+" "+value);
					node.right = new Node(node.item+"");
				}
				else {
					node.left = new Node(value);
					node.right = new Node("");
				}
			}
		}
		
	}
	
	public void print(Node node, int size) {
		
		if (node.left==null) {
			subset[count]=node.item;
			String[] splitted = subset[count].trim().split(" ");
			//if (splitted.length!=1) {
				System.out.print("{");
				for (int i=0;i<splitted.length;i++) {
					if (i!=splitted.length-1)
						System.out.print(splitted[i]+", ");
					else System.out.print(splitted[i]+"}");
				}
				System.out.println();
				count++;
			//}
		}
		
		else {
			print(node.left, size);
			print(node.right, size);
		}
		
	}

	

}

