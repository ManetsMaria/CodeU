import java.util.*;
public class Assignment3 {
	
	private final static String right="Ok";
	private final static String wrong="not Ok";
	
	

private static <T> String convertTestResultToString(T result, T expectedResult){
	if (result==null){
		if (expectedResult==null){
			return right;
		}
		else
			return wrong;
	}
	if (result.equals(expectedResult))
		return right;
	return wrong;
}

public static  void test1(){
	String [] w ={"car","card","cart","cat"};
	List <String> words=Arrays.asList(w);
	ArrayList <String> prefics =new ArrayList(words);
	prefics.add("c");
	prefics.add("ca");
	Dictionary dictionary=new Dictionary(words, prefics);
	char[][] grid={{'a','a','r'},{'t','c','d'}};
	ForGridWalker forGridWalker=new ForGridWalker(grid,dictionary);
	forGridWalker.start();
	TreeSet <String> answer=new TreeSet();
	answer.add("cat"); answer.add("card"); answer.add("car");
	System.out.println(convertTestResultToString(forGridWalker.finish(), answer));
}
public static void test2(){
	String [] w ={"word","dor","or","wdw"};
	List <String> words=Arrays.asList(w);
	ArrayList <String> prefics =new ArrayList(words);
	prefics.add("w");
	prefics.add("o");
	prefics.add("wo");
	prefics.add("wor");
	prefics.add("d");
	prefics.add("do");
	prefics.add("wd");
	Dictionary dictionary=new Dictionary(words, prefics);
	char[][] grid={{'o','d'},{'r','w'}};
	ForGridWalker forGridWalker=new ForGridWalker(grid,dictionary);
	forGridWalker.start();
	TreeSet <String> answer=new TreeSet();
	answer.add("word"); answer.add("dor"); answer.add("or");
	//System.out.println(forGridWalker.finish());
	System.out.println(convertTestResultToString(forGridWalker.finish(), answer));
}
public static void test3(){
	String [] w ={"abba","abet","faab","kgcbtk","cab"};
	List <String> words=Arrays.asList(w);
	ArrayList <String> prefics =new ArrayList(words);
	prefics.add("a");
	prefics.add("ab");
	prefics.add("abb");
	prefics.add("abe");
	prefics.add("f");
	prefics.add("fa");
	prefics.add("faa");
	prefics.add("k");
	prefics.add("kg");
	prefics.add("kgc");
	prefics.add("kgcb");
	prefics.add("kgcbt");
	prefics.add("c");
	prefics.add("ca");
	Dictionary dictionary=new Dictionary(words, prefics);
	char[][] grid={{'a','b','c'},{'a','f', 'a'},{'g','c','b'},{'k','t','e'}};
	ForGridWalker forGridWalker=new ForGridWalker(grid,dictionary);
	forGridWalker.start();
	TreeSet <String> answer=new TreeSet();
	answer.add("abet"); answer.add("faab"); answer.add("cab");
	//System.out.println(forGridWalker.finish());
	System.out.println(convertTestResultToString(forGridWalker.finish(), answer));
}
public static void test(){
	test1();
	test2();
	test3();
}


	public static void main(String[] args) {
		test();
	}

}

class ForGridWalker{
	TreeSet <String> words;
	char [][] grid;
	Dictionary dictionary;
	public ForGridWalker(char[][]g, Dictionary d){
		dictionary=d;
		grid=g;
		words=new TreeSet();
	}
	public void start(){
		for (int i=0; i<grid.length; i++){
			for (int j=0; j<grid[i].length; j++){
				lookFor(i, j, "", new ArrayList <Point>());
			}
		}
	}
	private void lookFor(int i, int j, String str, ArrayList <Point> points){
		str+=grid[i][j];
		//System.out.println(str);
		if (dictionary.isWord(str))
			words.add(str);
		if (!dictionary.isPrefix(str)){
			return;
		}
		points.add(new Point(i,j));
		if ((i-1)>=0 && !points.contains(new Point(i-1,j))){
			//System.out.println("i: "+(i-1)+" j: "+j);
			lookFor(i-1,j,str,points);
			points.remove(new Point(i-1,j));
		}
		if ((j-1)>=0 && !points.contains(new Point(i,j-1))){
			//System.out.println("i: "+i+" j: "+(j-1));
			lookFor(i,j-1,str,points);
			points.remove(new Point(i,j-1));
		}
		if ((i+1)<grid.length && !points.contains(new Point(i+1,j))){
			//System.out.println("i: "+(i+1)+" j: "+j);
			lookFor(i+1,j,str,points);
			points.remove(new Point(i+1,j));
		}
		if ((j+1)<grid[i].length && !points.contains(new Point(i,j+1))){
			//System.out.println("i: "+i+" j: "+(j+1));
			lookFor(i,j+1,str,points);
			points.remove(new Point(i,j+1));
		}
		if ((i-1)>=0 && (j-1)>=0 && !points.contains(new Point(i-1,j-1))){
			//System.out.println("i: "+(i-1)+" j: "+(j-1));
			lookFor(i-1,j-1,str,points);
			points.remove(new Point(i-1,j-1));
		}
		if ((i+1)<grid.length && (j+1)<grid[i].length && !points.contains(new Point(i+1,j+1))){
			//System.out.println("i: "+(i+1)+" j: "+(j+1));
			lookFor(i+1,j+1,str,points);
			points.remove(new Point(i+1,j+1));
		}
		if ((i-1)>=0 && (j+1)<grid[i].length && !points.contains(new Point(i-1,j+1))){
			//System.out.println("i: "+(i-1)+" j: "+(j+1));
			lookFor(i-1,j+1,str,points);
			points.remove(new Point(i-1,j+1));
		}
		if ((i+1)<grid.length && (j-1)>=0 && !points.contains(new Point(i+1,j-1))){
			//System.out.println("i: "+(i+1)+" j: "+(j-1));
			lookFor(i+1,j-1,str,points);
			points.remove(new Point(i+1,j-1));
		}
	}
	public TreeSet <String> finish(){
		return words;
	}
}

class Point{
	int i;
	int j;
	public Point(int i, int j){
		this.i=i;
		this.j=j;
	}
	@Override
	public boolean equals(Object other){
		boolean result;
	    if((other == null) || (getClass() != other.getClass())){
	        result = false;
	    } 
	    else{
	        Point otherPoint = (Point)other;
	        result = (i==otherPoint.i && j==otherPoint.j);
	    } 
	    return result;
}
}
class Dictionary{
	HashSet <String> words;
	HashSet <String> prefics;
	public Dictionary(List <String> w, List <String> p){
		words=new HashSet(w);
		prefics=new HashSet(p);
	}
	public boolean isWord(String word){
		return words.contains(word);
	}
	public boolean isPrefix(String p){
		return prefics.contains(p);
	}
}
