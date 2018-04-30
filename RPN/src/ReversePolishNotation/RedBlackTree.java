package ReversePolishNotation;

import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * build redblacktree
 * @author mingyiliang
 *
 */
public class RedBlackTree {
	private RedBlackNode root;
	private RedBlackNode nil;
	private int nodeCount;
	private int recentCompares;

	public RedBlackTree() {
		//set null node object
		root = nil = new RedBlackNode("-1",RedBlackNode.BLACK, null, null, null,null);
	
		
		//construct root node
		
		nodeCount = 0;
		recentCompares = 0;
	}
	
	/**
	 * return the value according to the key
	 * @param key
	 * @return
	 */
	public BigInteger get(String key) {
		if(key == null) throw new IllegalArgumentException("argument to get() is null");
		return get (root, key);
	}
	/**
	 * value associated with the given key in subtree rooted at x
	 * @param x
	 * @param key
	 * @return
	 */
	private BigInteger get (RedBlackNode x, String key) {
		while (x!= null){
			if(key.compareTo(x.getData())<0) {
				x = x.getLc();
			}else if(key.compareTo(x.getData())>0) {
				x = x.getRc();
			}else {
				return x.value;
			}
		}
		return null;
	}
	
	/**
	 * check if the tree has the key
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return get(key) == null;
	}
	


	
	/**
	 * method detail
	 * @throws OverflowException 
	 */
	
	//number of the value insert into the tree
	/**
	 * Returns the number of nodes in the tree
	 * @return
	 * speed is O(1)
	 */
	public int getSize(){
//		if(root == nil) {
//			return 0;
//		}
//		Queue newQueue = new Queue();
//		newQueue.enQueue(root);
//		int i = 0;
//		while(!newQueue.isEmpty()) {
//			RedBlackNode current = (RedBlackNode) newQueue.deQueue();
//			i = i + 1;
//			if (current.getLc()!=nil) {
//				newQueue.enQueue(current.getLc());
//			}
//			if (current.getRc()!=nil) {
//				newQueue.enQueue(current.getRc());
//			}
//		}
//		return i;
		return this.nodeCount;
	}
			

	//recursive and displays the content of the tree
	/**
	 * return the tree in order
	 * @param t
	 * running time is 0(n)
	 */
	public void inOrderTraversal(RedBlackNode t) {
		
		
		if (t.getLc() != this.nil) {
            inOrderTraversal(t.getLc());
        }
        System.out.println(t);
        if (t.getRc() != this.nil) {
            inOrderTraversal(t.getRc());
        }
	
	}
	
	/**
	 * Performs in-order traversal of the tree and displays the content
	 * running time is O(N)
	 */
	public void inOrderTraversal() {
		inOrderTraversal(root);
	}
	
	//recursive and displays the content of the tree in reverse order
	
	/**
	 * display data in a reverse order
	 * running time is O(n)
	 * @param t
	 */
	public void reverseOrderTraversal(RedBlackNode t) {
		if (t.getLc() != this.nil) {
            inOrderTraversal(t.getLc());
        }
        
        if (t.getRc() != this.nil) {
            inOrderTraversal(t.getRc());
        }
        System.out.println(t);
	}
	
	/**
	 * running time is O(n)
	 */
	public void reverseOrderTraversal() {
		reverseOrderTraversal(root);
	}
	
	
	//places a data item into the tree
	/**
	 * Inserts the data in the tree
	 * @param value
	 * best case is O(1) worst case is O(log(n))
	 */
	public void insert(String key, BigInteger value) {
		
		RedBlackNode y = nil;
        RedBlackNode x = root;
        while(x!=nil){
            y = x;
            if(x.getData().compareTo(key) > 0){
                x = x.getLc();
            }else{
                x = x.getRc();
            }
        }
        RedBlackNode z = new RedBlackNode(key,RedBlackNode.RED,null,null,null, value);
        z.setP(y);
        if(y==nil){
            root = z;
        }else{
            if(y.getData().compareTo(z.getData())>0){
                y.setLc(z);
            }else{
                y.setRc(z);
            }
        }
        z.setLc(nil);
        z.setRc(nil);
        z.setColor(RedBlackNode.RED);
        RBInsertFixup(z);

	}
	

	// a single left node Rotate
	/**
	 * display a single rotate
	 * @param x
	 * running time is O(1)
	 */
	public void leftRotate(RedBlackNode x) {

		if (x.getRc() == this.nil) {
            return;
        }

        if (this.root.getP() != this.nil) {
            return;
        }

        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());

        if (x.getP() == this.nil) {
            this.root = y;
        } else {
            if (x.getP().getLc() == x) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
	}
	
	//performs a single right rotation
	/**
	 * display a single right rotate
	 * @param x
	 * running time is O(1)
	 */
	public void rightRotate(RedBlackNode x) {

		if (x.getLc() == this.nil) {
            return;
        }

        if (this.root.getP() != this.nil) {
            return;
        }

        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());

        if (x.getP() == this.nil) {
            this.root = y;
        } else {
            if (x.getP().getLc() == x) {
                x.getP().setLc(y);
            } else {
                x.getP().setRc(y);
            }
        }
        y.setRc(x);
        x.setP(y);
	}
	
	//Fixing up the tree so that Red Black Properties are preserved
	/**
	 * set up a well tree
	 * @param z
	 * best case is O(1). worst case is O(log(n))
	 */
	public void RBInsertFixup(RedBlackNode z) {
		RedBlackNode y;
		
        while (z.getP().getColor() == RedBlackNode.RED) {
            if (z.getP().getP().getLc() == z.getP()) {
                y = z.getP().getP().getRc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z.getP().getRc() == z) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getLc();
                if (y.getColor() == RedBlackNode.RED) {
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                } else {
                    if (z.getP().getLc() == z) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        this.root.setColor(RedBlackNode.BLACK);			
	}
	
	//returns true if the String v is in the RedBlackTree and false otherwise
	/**
	 * Returns true if the String v is in the RedBlackTree and false otherwise.
	 * @param v
	 * @return
	 * best running time is O(1), worst case is O(log(n))
	 */
	public boolean contains(String v) {
		recentCompares = 0;
        return checkForData(root, v);
	}
	
	
	/**
	 * Returns the comparisons made for the most recent compare
	 * @return
	 * running time O(n)
	 */
	public int getRecentCompares() {
        return this.recentCompares;
    }
	
	
	/**
	 * find the existence of the data
	 * @param node
	 * @param data
	 * @return
	 * running time is O(log(n))
	 */
	private boolean checkForData(RedBlackNode node, String data) {
        if (node == nil) {
        	    recentCompares = recentCompares + 1;
            return false;
        } else if (node.getData().compareTo(data) == 0) {
            
        	    recentCompares = recentCompares + 1;
            return true;
        } else if (node.getData().compareTo(data) < 0) {
            
          	recentCompares = recentCompares + 1;
            return checkForData(node.getRc(), data);
        } else {
            
          	recentCompares = recentCompares + 1;
            return checkForData(node.getLc(), data);
        }
    }
	
	/**
	 * Returns a value close to v in the tree. 
	 * @param v
	 * @return
	 * running time is O(1) and O(log (n))
	 */
	public String closeBy(String v) {
        return getCloseNode(root, v);
    }
	
	private String getCloseNode(RedBlackNode node, String data) {
        String returnData;

        if (node == nil) {           
            return null;
        } else if (node.getData().compareTo(data) == 0) {           
            return node.getData();
        } else if (node.getData().compareTo(data) < 0) {
            
            if ((returnData = getCloseNode(node.getRc(), data)) == null) {
                return node.getData();
            } else {
                return returnData;
            }
        } else {
           
            if ((returnData = getCloseNode(node.getLc(), data)) == null) {
                return node.getData();
            } else {
                return returnData;
            }
        }
    }
	
	//compute the height of the red black tree.
	/**
	 * the hight of the whole tree
	 * @param node
	 * @return
	 * the running time is O(LOG(N))
	 */
	private int height(RedBlackNode node) {
        if (node == nil || (node.getLc() == nil) && (node.getRc() == nil)) {
            // If it is the leaf, then the height is 0
            return 0;
        }
        
        return 1 + Math.max(height(node.getLc()), height(node.getRc()));
    }
	
	/**
	 * Returns the height of the tree
	 * @return
	 */
	public int height() {
        return height(root);
    }
	
	//displays the RedBlackTree in level order
	/**
	 * display the tree in level order
	 * the running time is O(n)
	 */
	public void levelOrderTraversal() {
        Queue sb = new Queue();
        RedBlackNode node;
        if (this.root == this.nil) {
            return;
        }

        sb.enQueue(this.root);
        while (!sb.isEmpty()) {
            node = (RedBlackNode) sb.deQueue();
            System.out.println(node);
            if (node.getLc() != this.nil) {
                sb.enQueue(node.getLc());
            }
            if (node.getRc() != this.nil) {
                sb.enQueue(node.getRc());
            }
        }
    }
	
	/**
	 * test program
	 * @param args
	 */
	public static void main(String[] args) {

        RedBlackTree rbt = new RedBlackTree();

        for(int j = 1; j <= 50; j++) {
        	    rbt.insert("var"+String.valueOf(j),BigInteger.valueOf(j));
        }
        
        
        // after 1..5 are inserted
        System.out.println("RBT in order");
        rbt.inOrderTraversal();
        System.out.println("RBT level order");
        rbt.levelOrderTraversal();
        
        
        
       // is 3 in the tree
        Scanner scn = new Scanner(System.in);
        String input;
        input = scn.nextLine();
        // display the height
        System.out.println("The height is " + rbt.height());   
        System.out.println("lookup  = "+ " " + rbt.get(input));
    }
	
}
