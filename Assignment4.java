import java.util.*;
public class Assignment4 {
	
	private final static String right = "Ok";
	private final static String wrong = "not Ok";
	
	

private static <T> String convertTestResultToString(T result, T expectedResult){
	if (result == null){
		if (expectedResult == null){
			return right;
		}
		else
			return wrong;
	}
	if (result.equals(expectedResult))
		return right;
	return wrong;
}
public static void test1(){
	boolean[][] map = new boolean[4][4];
	map[0][1] = true;
	map[0][3] = true;
	map[1][0] = true;
	map[1][1] = true;
	map[2][2] = true;
	map[3][2] = true;
	IslandCounter islandCounter=new IslandCounter(map);
	islandCounter.start();
	System.out.println(convertTestResultToString(islandCounter.getResult(), 3));
}
public static void test2(){
	boolean[][] map = new boolean[4][4];
	map[0][0] = true;
	map[0][1] = true;
	map[0][3] = true;
	map[1][0] = true;
	map[1][1] = true;
	map[2][2] = true;
	map[3][2] = true;
	IslandCounter islandCounter = new IslandCounter(map);
	islandCounter.start();
	System.out.println(convertTestResultToString(islandCounter.getResult(), 3));
}
public static void test3(){
	boolean[][] map = new boolean[5][5];
	map[0][1] = true;
	map[0][2] = true;
	map[0][3] = true;
	map[0][4] = true;
	map[1][1] = true;
	map[1][4] = true;
	map[2][2] = true;
	map[2][4] = true;
	map[3][0] = true;
	map[3][1] = true;
	map[3][3] = true;
	map[3][4] = true;
	map[4][1] = true;
	map[4][2] = true;
	map[4][3] = true;
	IslandCounter islandCounter = new IslandCounter(map);
	islandCounter.start();
	System.out.println(convertTestResultToString(islandCounter.getResult(), 2));
}
public static void test4(){
	boolean[][] map = new boolean[2][6];
	map[0][0] = true;
	map[0][2] = true;
	map[0][3] = true;
	map[1][1] = true;
	map[1][2] = true;
	map[1][3] = true;
	map[1][5] = true;	
	IslandCounter islandCounter = new IslandCounter(map);
	islandCounter.start();
	System.out.println(convertTestResultToString(islandCounter.getResult(), 3));
}

public static void test(){
	test1();
	test2();
	test3();
	test4();
}


	public static void main(String[] args) {
		test();
	}

}

class IslandCounter{
	boolean [][] map;
	IslandSet islandSet;
	public IslandCounter(boolean [][]map){
		this.map = map;
		islandSet = new IslandSet(map.length,map[0].length);
	}
	public void start(){
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[i].length; j++){
				if (map[i][j]){
					Point point = new Point(i,j);
					islandSet.MakeSet(point);
					if (i-1 >= 0 && map[i-1][j])
						islandSet.Unite(point, new Point(i-1,j));
					if (j-1 >= 0 && map[i][j-1])
						islandSet.Unite(point, new Point(i, j-1));
				}
			}
		}
	}
	public int getResult(){
		return islandSet.getCounter();
	}
}

class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append(x);
        s.append(" ");
        s.append(y);
        return s.toString();
    }
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Point)) return false;
        Point other=(Point) obj;
        return x == other.x && other.y == y;
    }
}
class IslandSet{
        Point [][] matrix;
        int [][] rank;
        int counter;
        public IslandSet(int n, int m){
            matrix = new Point [n][m];
            rank = new int [n][m];
            counter = 0;
        }
        public void MakeSet(Point x)
        {
            matrix[x.x][x.y] = x;
            rank[x.x][x.y] = 1;
            counter++;
        }
        public Point Find(Point x)
        {
            if (matrix[x.x][x.y] == x) return x;
            return this.Find(matrix[x.x][x.y]);
        }
        public void Unite(Point x, Point y)
        {
            x = Find(x);
            y = Find(y);
            if (x==y){
                return;
            }
            if (rank[x.x][x.y] < rank[y.x][y.y]){
                matrix[x.x][x.y] = y;
                rank[y.x][y.y] += rank[x.x][x.y];
                counter--;
            }
            else{
                matrix[y.x][y.y] = x;
                rank[x.x][x.y] += rank[y.x][y.y];
                counter--; 
            }
        }
        public int getCounter(){
        	return counter;
        }
}