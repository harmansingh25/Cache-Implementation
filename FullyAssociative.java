package fullyAssociative;

import java.util.*;

class QueueLL {
	class Node{
    	int data;
    	Node next;
    	
    	public Node(int x) {
    		data=x;
    		next=null;
    	}
    	
    }
    Node front;
    Node rear;
    int size=0;
    
    boolean isEmpty() {
    	if(front==null&&rear==null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    void enqueue(int x) {
    	
    	Node p=new Node(x);
    	if(rear==null) {
    		front=rear=p;
    		size++;
    	}
    	else {
    		rear.next=p;
        	rear=p;
        	size++;
    		
    	}
    	
    	
    }
    int dequeue() {
    	if(front==null) {
    		return -1;
    	}
    	Node temp=front;
    	
    	int h=front.data;
    	front=front.next;
    	if(front==null) {
    		rear=null;  
    	}
    	size--;
    	return h;
    }
    
    void inc(int x,int d) {
    	Node n=front;
    	int c =0;
    	while(c!=size-x) {
    		n=n.next;
    		c++;
    	}
    	while(n!=null) {
    		n.data=n.data+d;
    		n=n.next;
    		
    		
    	}
    	
    }
} 
public class FullyAssociative {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner vt=new Scanner(System.in);
		QueueLL q=new QueueLL();
		
		//taking inputs
		int cl=vt.nextInt();
		int b=vt.nextInt();
		vt.nextLine();
		System.out.println("FULLY ASSOCIATIVE CACHE");
		int offset= (int)(Math.log(b)/Math.log(2));
		int lineIn=(int)(Math.log(cl)/Math.log(2)); //finding no. of bits
		int tagN=16-offset-lineIn;
		//System.out.println(tagN);
		String[] l1=new String[cl];
		
		for(int j=0;j<l1.length;j++) {
			l1[j]="-";
		}
		ArrayList<Integer> lol=new ArrayList<Integer>();
		for(int k=0;k<cl;k++) {
			lol.add(k);
			
		}
		Collections.shuffle(lol); //shuffling lines for randomness
//		for(int aa=0;aa<cl;aa++) {
//			System.out.println(lol.get(aa));
//		}
//		for(int i=1;i<l1.length;i++) {
//			System.out.println("L"+i+" "+":"+"-");
//		}
		int nBlock=16-offset;
		int nBlocks=(int)Math.pow(2,nBlock);
		//System.out.println(nBlocks);
		
		int[][] blockInfo=new int[nBlocks][b]; //2d array to maintain data
//		int[] arr=new int[nBlocks];
		ArrayList<Integer> arr=new ArrayList<Integer>();
		int[] lineno=new int[nBlocks];
		for(int yy=0;yy<lineno.length;yy++) {
			lineno[yy]=-1;
		}
		int c=0;
		for(int k=0;k<100000;k++) {
			
			String h=vt.next();
			if(h.equals("write")) {
			String s=vt.next();
			int data=vt.nextInt();
			int l=s.length();
			String blockBits=s.substring(0,16-offset);
			String offsetBits=s.substring(tagN+lineIn);
			String lineBits=s.substring(tagN,16-offset);
			int blockNo=Integer.parseInt(blockBits,2); //finding bits of specific address
			int lineNo=Integer.parseInt(lineBits,2);
			
			int wordNo=Integer.parseInt(offsetBits,2);
			//System.out.println(lineBits);
			//System.out.println(blockNo+" "+lineNo+" "+wordNo);
			
			blockInfo[blockNo][wordNo]=data;
			String ff="B"+blockNo;
//			int jj=lol.get(c);
//			
//			lineno[blockNo]=jj;
			
			int hw=0;
			for(int u=0;u<arr.size();u++) {
				if(blockNo==arr.get(u)) {
					hw++;
					break;
					
				}
				
			}
			if(hw!=0) {
				for(int i=0;i<l1.length;i++) {
					System.out.println("L"+i+" "+":"+l1[i]);
					//System.out.println(blockInfo[blockNo][wordNo]);
					
				}
				
				continue;
				
			}
			
			if(c<cl) { //if all the lines arent occupied
				
			
			int jj=lol.get(c);
//			
			lineno[blockNo]=jj;
			q.enqueue(jj);
			c++;
			
			
//			if(l1[lineNo]!="-" && l1[lineNo].equals(ff)==false) {
//				System.out.println("Block Removed :"+l1[lineNo]);
//			}
			System.out.println();
			l1[jj]="B"+blockNo;
			arr.add(blockNo);
			for(int i=0;i<l1.length;i++) {
			System.out.println("L"+i+" "+":"+l1[i]);
			//System.out.println(blockInfo[blockNo][wordNo]);
			
			}
			}
			else { //if all the lines are occupied, proceeding towards fifo
				int fgf=q.dequeue();
				//System.out.println(fgf);
				q.enqueue(fgf);
				
				int pop=0;
				for(int v=0;v<lineno.length;v++) {
					if(lineno[v]==fgf) {
						pop=v;
						//System.out.println("haha");
						break;
						
					}
				}
				//arr.remove(new Integer(pop));
				System.out.println("BLOCK EVICTED : "+"B"+pop);
				lineno[blockNo]=fgf;
				//System.out.println(pop);
				arr.remove(new Integer(pop));
				lineno[pop]=-1;
				System.out.println();
				l1[fgf]="B"+blockNo;
				arr.add(blockNo);
				for(int i=0;i<l1.length;i++) {
				System.out.println("L"+i+" "+":"+l1[i]);
				//System.out.println(blockInfo[blockNo][wordNo]);
				
				}
				
			}
			}
			else {
				String s=vt.next();
				int l=s.length();
				String blockBits=s.substring(0,16-offset);
				String offsetBits=s.substring(tagN+lineIn);
				String lineBits=s.substring(tagN,16-offset);
				int blockNo=Integer.parseInt(blockBits,2);
				int lineNo=Integer.parseInt(lineBits,2);
				
				int wordNo=Integer.parseInt(offsetBits,2);
				String ff="B"+blockNo;
				int gh=0;
				for(int p=0;p<cl;p++) {
					if(arr.get(p)==blockNo) {
						gh++;
						//System.out.println(arr.get(p));
						break;
						
					}
					
				}
				//System.out.println(l1[lineNo]);
				if(gh==0) {
					System.out.println("cache miss");
				}
				else { // hit or miss declaration
					if(blockInfo[blockNo][wordNo]!=0)
					{
					System.out.println("cache hit");
					System.out.println("Data"+"="+blockInfo[blockNo][wordNo]);
					System.out.println("Line No"+": "+"L"+lineno[blockNo]);
					}
					else System.out.println("empty word");
				}
				
			}
		}
		

	}

}
