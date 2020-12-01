package directMapping;

import java.util.*;

public class DirectMapping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// following the fill operation pg 427 sarangi
		Scanner vt=new Scanner(System.in);
		
		//taking inputs
		int cl=vt.nextInt();
		int b=vt.nextInt();
		vt.nextLine();
		System.out.println("DIRECT MAPPED CACHE");
		int offset= (int)(Math.log(b)/Math.log(2));
		int lineIn=(int)(Math.log(cl)/Math.log(2)); //finding no. of bits
		int tagN=16-offset-lineIn;
		//System.out.println(tagN);
		String[] l1=new String[cl];
		for(int j=0;j<l1.length;j++) {
			l1[j]="-";
		}
//		for(int i=1;i<l1.length;i++) {
//			System.out.println("L"+i+" "+":"+"-");
//		}
		int nBlock=16-offset;
		int nBlocks=(int)Math.pow(2,nBlock);
		//System.out.println(nBlocks);
		
		int[][] blockInfo=new int[nBlocks][b];
		
		for(int k=0;k<100000;k++) {
		
		String h=vt.next();
		if(h.equals("write")) {
		String s=vt.next();
		int data=vt.nextInt();
		int l=s.length();
		String blockBits=s.substring(0,16-offset);
		String offsetBits=s.substring(tagN+lineIn);
		String lineBits=s.substring(tagN,16-offset); //finding bits of specific address
		int blockNo=Integer.parseInt(blockBits,2);
		int lineNo=Integer.parseInt(lineBits,2);
		
		int wordNo=Integer.parseInt(offsetBits,2);
		//System.out.println(lineBits);
		//System.out.println(blockNo+" "+lineNo+" "+wordNo);
		
		blockInfo[blockNo][wordNo]=data; // 2d array to maintain data
		String ff="B"+blockNo;
		
		if(l1[lineNo]!="-" && l1[lineNo].equals(ff)==false) {
			System.out.println("BLOCK EVICTED :"+l1[lineNo]); //printing cache structure
		}
		System.out.println();
		l1[lineNo]="B"+blockNo;
		for(int i=0;i<l1.length;i++) {
		System.out.println("L"+i+" "+":"+l1[i]);
		//System.out.println(blockInfo[blockNo][wordNo]);
		
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
			//System.out.println(l1[lineNo]);
			if(l1[lineNo].equals(ff)==false) {
				System.out.println("cache miss");
			}
			else { //declaring a hit or a miss
				if(blockInfo[blockNo][wordNo]!=0)
				{
				System.out.println("cache hit");
				System.out.println("Data"+"="+blockInfo[blockNo][wordNo]);
				System.out.println("Line No"+": "+"L"+lineNo);  
				}
				else System.out.println("empty word");
			}
			
		}
		}
		//System.out.println(blockInfo[blockNo][wordNo]);
		
		
		
		
		

	}

}
