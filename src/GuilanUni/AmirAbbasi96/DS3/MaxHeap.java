package GuilanUni.AmirAbbasi96.DS3;


public class MaxHeap{
private char [] array;
private int size;//size
Hash hash=new Hash();//hashe makhsoos

	public MaxHeap(int size) {
		array=new char [size];
		size=0;
	}
	
/**
 * insert()
 * dar akharin khane migozarad va inghdar ba pedar moghyese mikonad va swap mikonad ta jaye dorost peyda shavad
 * @param ch
 */
	public void insert(char ch) {
	
		array[size]=ch;
		int index=size;
		/**
		 * heapify
		 */
		while(index>=0 && hash.getKey(array[index])>hash.getKey(array[(index-1)/2])) {
			char tmp=array[index];
			array[index]=array[(index-1)/2];
			array[(index-1)/2]=tmp;
			index=(index-1)/2;
		}

		size++;
	}
/**
 * farzande bozrgtar ra midahad
 * @param n
 * @return
 */
public int maxChild(int n) {
	if(2*n+1>size)//khodash barg ast
		return -1;
	if(2*n+2>=size)//bache rast nadarad
		return 2*n+1;
	else {//moghayese
		if(hash.getKey(array[2*n+1])>hash.getKey(array[2*n+2]))
		return 2*n+1;
		else
			return 2*n+2;
			
	}
}
/**
 * avali va akhari swap mishavand 
 * @return
 */
public char delete() {
	char ans=array[0];
	array[0]=array[size-1];
	size--;
	int max=maxChild(0);
	int index=0;
	/**
	 * inghdar ba farzande bozorgtar moghayese va swap mishavad ta jaye dorost peyda shavad
	 */
	while(max!=-1 && hash.getKey(array[max])>hash.getKey(array[index])) {
		char tmp=array[max];
		array[max]=array[index];
		array[index]=tmp;
		index=max;
		max=maxChild(index);
	}
	return ans;
}
/**
 * tarkibe kode insert() va delete()
 * yani ya bala miravad ya payin
 * @param ch
 */
public void heapify(char ch) {
	
	int index=-1;
	for(int i=0;i<size;i++) {
		if(array[i]==ch) {
			index=i;
			break;
		}
	}
	if(index==-1)
		return;
	boolean flag=false;
	while(index>0 && hash.getKey(array[index])>hash.getKey(array[(index-1)/2])) {
		char tmp=array[index];
		array[index]=array[(index-1)/2];
		array[(index-1)/2]=tmp;
		index=(index-1)/2;
		flag=true;
	}
	if(flag==false) {
	int max=maxChild(index);
	
	while(max!=-1 && hash.getKey(array[max])>hash.getKey(array[index])) {
		char tmp=array[max];
		array[max]=array[index];
		array[index]=tmp;
		index=max;
		max=maxChild(index);
}}

}
/**
 * size
 * @return
 */
public int getSize() {
	return size;
}
/**
 * check miknad ch ra darad ya na
 * @param ch
 * @return
 */
public boolean consistOf(char ch) {
	boolean flag=false;
	for(int i=0;i<size;i++) {
		if(array[i]==ch) {
			flag=true;
			break;
		}
	}
	return flag;
}
/**
 * copy az khodash
 * @return
 */
public MaxHeap Copy() {
	MaxHeap ans=new MaxHeap(26);
	for(int i=0;i<array.length;i++)
	ans.array[i]=array[i];
	ans.size=size;
	ans.hash=this.hash.copy();
	return ans;
}
public String toString() {
	String ans="";
	for(int i=0;i<size;i++)
		ans+=array[i]+":"+hash.getKey(array[i])+" ";
	return ans;
}
}