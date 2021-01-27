package GuilanUni.AmirAbbasi96.DS3;

/**
 * vaset beyne horoof va adad
 * 
 * @author Asus
 *
 */
public class Hash {
 int[] list=new int [26];//araye adad
 /**
  * set kardane kardane adad baraye harf
  * @param n
  * @param ch
  */
public   void set(int n,char ch) {
	list[(int)ch-97]=n;
}
/**
 * adade harf ra midahad
 * @param ch
 * @return
 */
public int getKey(char ch) {
	return list[(int)ch-97];
}
/**
 * kelid ra chap mikonad
 * @return
 */
public  String print() {
	String ans="";
	for(int i=0;i<25;i++)
		ans+=(char)(97+i)+"_"+list[i]+" ";
	ans+=(char)(122)+"_"+list[25];
	return ans;
}
/**
 * swap
 * @param a
 * @param b
 */
public void swap(char a,char b) {
	
	int tmp=list[(int)a-97];
	list[(int)a-97]=list[(int)b-97];
	list[(int)b-97]=tmp;
}
/**
 * harfe adad ra midahad
 * @param n
 * @return
 */
public char getCh(int n) {
	for(int i=0;i<26;i++) {
		if(list[i]==n)
			return (char)(97+i);
	}
	return ' ';
}
/**
 * copy az khodash ra midahad
 * @return
 */
public Hash copy() {
	Hash hash=new Hash();
	for(int i=0;i<26;i++)
		hash.list[i]=this.list[i];
	return hash;
}
}
