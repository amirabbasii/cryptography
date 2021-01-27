package GuilanUni.AmirAbbasi96.DS3;

import java.util.Random;
import java.util.Scanner;

public class Alen {
	static Scanner scanner=new Scanner(System.in);
public static void main(String[] args) {
	System.out.println("Welcome to encoding and decoding project.\nPlease choose:\n1)encode\n2)decode");
	if(scanner.nextInt()==1)
	encode();
	else
	decode();
}
/**
 * encode
 * be soorate random kelid misazad 
 */
public static void encode() {
	MaxHeap mh=new MaxHeap(26);//priorit queue

Queue queue=new Queue();//queue

Random rnd=new Random();
int[] nums=getRadnomNumbers();//araye adad

	for(int i=0;i<26;i++) {
		
		mh.hash.set(nums[i],(char)(97+i) );
		mh.insert((char)(97+i));
	}
	String key=mh.hash.print();//key
	String word=scanner.next();//kalame
	String ans="";//code shode
	
	for(int i=0;i<word.length();i++) {
		if(mh.hash.getKey(word.charAt(i))>mh.getSize()) {
			while(!queue.isEmpty()) {
			mh.insert(queue.dequeue());
		}
		}
		for(int j=1;j<mh.hash.getKey(word.charAt(i));j++) {
			queue.enqueue(mh.delete());
		}
		char temp=mh.delete();//harfe jaygozin
		queue.enqueue(temp);
		ans+=temp;

		mh.hash.swap(temp, word.charAt(i));//swap mishavand
		mh.heapify(word.charAt(i));
		mh.heapify(temp);

	}
	System.out.println(key+"\n"+ans);
}
/**
 * decode
 */
public static void decode() {
	MaxHeap mh=new MaxHeap(26);//priority queue


	String bu="";
	String[] key;
	for(int i=0;i<26;i++)
		bu+=scanner.next()+"#";
	bu.replace(" ", "");
		key=bu.split("#");
		
		for(int i=0;i<26;i++) {//priority queue sakhte mishavad
			String [] tmp=key[i].split("_");
			int num=Integer.parseInt(tmp[1]);
			mh.hash.set(num,tmp[0].charAt(0));
			mh.insert(tmp[0].charAt(0));
		}
		String word=scanner.next();
	
		Queue queue=new Queue();//queue
	decoder("",word, mh,queue);
		
}
/**
 * be soorate random adadi midahad
 * be in soorat ke adad 1 ta 26 be tartib dakhele araye migozarad sepas be soorate ranodm va be tedade random adadi ra swap mikona
 * @return
 */
public static int[] getRadnomNumbers(){
	
	int[] list=new int [26];
	for(int i=1;i<=26;i++)
		list[i-1]=i;
	Random rnd=new Random();
	
	///random swap
	int t=(10+rnd.nextInt(10))*100;
	for(int i=1;i<t;i++) {
		int index1=rnd.nextInt(26);
		int index2=rnd.nextInt(26);
		int tmp=list[index1];
		list[index1]=list[index2];
		list[index2]=tmp;
	}
	return list;
}

/**
 * decode
 * @param ans
 * @param str
 * @param mh
 * @param queue
 */
public static void decoder(String ans,String str,MaxHeap mh,Queue queue) {
	
	if(str.length()==0) {
		System.out.println(ans);
		return;}
	/////agar harf dar safe olaviar bashad///
	if(mh.consistOf(str.charAt(0))) {
	
		/////////////shakhe aval
	char tmp=' ';
	int n=0;
	do {
		n++;
		tmp=mh.delete();
		queue.enqueue(tmp);
	}
	while(tmp!=str.charAt(0)) ;
	char ch=mh.hash.getCh(n);
MaxHeap cp=mh.Copy();//copy
cp.hash.swap(ch, str.charAt(0));//swap
cp.heapify(str.charAt(0));//heapify
cp.heapify(ch);
	decoder(ans+ch, str.substring(1),cp, queue.copy());

	
	//////shake dovom//////////
	while(!queue.isEmpty()) {
		mh.insert(queue.dequeue());
	}
	 tmp=' ';
	 n=0;
	do {
		n++;
		tmp=mh.delete();
		queue.enqueue(tmp);
	}
	while(tmp!=str.charAt(0)) ;
	
	 ch=mh.hash.getCh(n);

	 MaxHeap cp2=mh.Copy();//copy
	 cp2.hash.swap(ch, str.charAt(0));//swap
	 cp2.heapify(str.charAt(0));//heapify
	 cp2.heapify(ch);
	 decoder(ans+ch, str.substring(1), cp2,queue.copy());
	
	}
	
	else////////////agar nabashad
	{
		
		while(!queue.isEmpty()) 
			mh.insert(queue.dequeue());
		char tmp=' ';
		int n=0;
		do {
			n++;
			tmp=mh.delete();
			queue.enqueue(tmp);		
		}
		while(tmp!=str.charAt(0)) ;
		
		char ch=mh.hash.getCh(n);
		MaxHeap cp=mh.Copy();//copy
		cp.hash.swap(ch, str.charAt(0));//swap
		cp.heapify(str.charAt(0));//heapify
		cp.heapify(ch);
		decoder(ans+ch, str.substring(1), cp,queue.copy());	
	}
}
}
/**
 * Queue
 * @author Asus
 *
 */
class Queue{
	char[] array=new char [26];
	int start=0;//avali
	int end=-1;//akhari
	int size=0;
	/**
	 * enqueu
	 * @param ch
	 */
	public void enqueue(char ch) {
		end=(end+1)%26;
		array[end]=ch;
		size++;
	}
	/**
	 * dequeue
	 * @return
	 */
	public char dequeue() {
		char ans=array[start];
		start=(start+1)%26;
		size--;
		return ans;
	}
	public boolean isEmpty() {
		return size==0;
	}
	/**
	 * copy az khodash ra midahad
	 * @return
	 */
	public Queue copy() {
		Queue queue=new Queue();
		for(int i=0;i<26;i++)
			queue.array[i]=this.array[i];
		queue.size=this.size;
		queue.start=this.start;
		queue.end=this.end;
		return queue;
		
	}
}