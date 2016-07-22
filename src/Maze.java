/* 
 * Oscar Hedblad
 * PID: o3415424	
 * COP3503 - Assignment #6, Maze
 * 
 * DESCRIPTION: Program that takes in the parameters "width" and "height" and randomly 
 * generates a maze. This program is built with several methods which allows the maze 
 * to be generated with the wanted dimensions.
 * 
 * */

// Import packages
import java.io.*;
import java.util.*;

// New Wall-type
class Wall{
	int x, y;

	Wall(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Maze {
	
	// Declare array for disjoint sets.
	private static int[] djParent;
	private static int[] rank;

	// Make-set method. Basically initializes each parent to itself.
	public static int[] makeSet(int n){
		djParent = new int[n];
		rank = new int[n];

		for(int i = 0; i < n; i++){
			djParent[i] = i;
			rank[i] = 0;
		}

		return djParent;
	}
	// Method that finds the 'actual' parent.
	public static int findSet(int x){
		if(djParent[x] == x){
			return x;
		}

		djParent[x] = findSet(djParent[x]);
		return findSet(djParent[x]);
	}
	// Union by rank method.Figures out the rank of the parents.
	public static boolean unionByRank(int x, int y){
		int parentX = findSet(x);
		int parentY = findSet(y);

		if(parentX != parentY){
			if(rank[parentX] < rank[parentY]){
				djParent[parentX] = parentY;
			}else if(rank[parentY] < rank[parentX]){
				djParent[parentY] = parentX;
			}else{
				djParent[parentY] = parentX;
				rank[parentX]++;
			}
			return true;
		}
		return false;
	}
	// Required main function. Creates the maze.
	public static char [][] create(int width, int height){

		int actualWidth = (2 * width) + 1;
		int actualHeight = (2 * height) + 1;
		int cells = width * height;


		char [][] labWall = new char [actualHeight][actualWidth];
		ArrayList<Wall> deleteWall = new ArrayList<Wall>();
		for(int i = 0; i < actualHeight; i++){

			for(int j = 0; j < actualWidth; j++){

				if(i % 2 == 0){
					labWall[i][j] = '#';
				}
				else if(j % 2 == 0)
					labWall[i][j] = '#';

				else{
					labWall[i][j] = ' ';
				}
		// Figures out if the wall is deletable. If so, it must follow the requirements below.
				if((i > 0) && (j > 0) && ((i + j) %2 == 1) && (j < actualWidth - 1) && (i < actualHeight - 1)){
					deleteWall.add(new Wall(i,j));
				}
			}
		}
		rank = new int[width * height];
		djParent = makeSet(cells);

		//		// DELETE LATER
		//		for(int k = 0; k < deleteWall.size(); k++){
		//			System.out.print(deleteWall.get(k).x + "," + deleteWall.get(k).y + "   ");
		//		}
		//		System.out.println();
		
		
		// Shuffles around the deletable walls. Way of getting random wall.
		Collections.shuffle(deleteWall);

//		// DELETE LATER
//		for(int k = 0; k < deleteWall.size(); k++){
//			System.out.print(deleteWall.get(k).x + "," + deleteWall.get(k).y + "   ");
//		}

		int move = 1;
		int cnt = 0;
		while(move == 1){
			
			if(cells ==1) break;
			
			move = 0;
			for(int i = 0; i < djParent.length-1; i++){
				if(findSet(i) != findSet(i + 1)){
					move = 1;
				}
			}

			int wallX = deleteWall.get(cnt).x;
			int wallY = deleteWall.get(cnt).y;
			
			// Finds the cells surrounding the deletable wall
			int left = (wallX/2) * width + ((wallY + 1)/2);
			int right = (wallX/2) * width + ((wallY - 1)/2);
			int up = (((wallX + 1)/2) * width + (wallY/2));
			int down = (((wallX - 1)/2) * width + (wallY/2));
			cnt++;

//			if((findSet(up) != findSet(down)) || (findSet(left) != findSet(right))){
				if(wallX % 2 == 0){

					if(unionByRank(up, down) == true){
						labWall[wallX][wallY] = ' '; 
					}

				}
				else{
					if(unionByRank(left, right) == true){
						labWall[wallX][wallY] = ' ';
					}
				}
//			}
			if(cnt == deleteWall.size())
				break;
		}
		// Adds the 's' to the start and the 'e' to the exit.
		labWall[1][1] = 's';
		labWall[actualHeight - 2][actualWidth - 2] = 'e';

		return labWall;

	}

}
