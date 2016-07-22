// Sean Szumlanski
// COP 3503, Fall 2013

// TestCases.java
// ==============
// When you have your Maze class implemented properly, you should be able to
// run this program and produce output similar to that given in SampleOutput.txt.
// Note that your mazes will necessarily be different, because they are being
// generated with an element of randomness.
//
// Recall that your Maze.create() method should not print anything to the screen.


import java.io.*;

public class TestCases
{
	// A method for printing mazes.
	public static void printMaze(char [][] maze)
	{
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze[i].length; j++)
				System.out.print(maze[i][j]);
			System.out.println();
		}

		System.out.println();
	}

	public static void main(String [] args)
	{
		printMaze(Maze.create(6, 3));

		// Notice from the attached output file that if width = height = 1, the
		// 'e' should overwrite the 's' in the maze you generate.
		printMaze(Maze.create(1, 1));

		// Ensure that your code uses the first parameter as 'width' and the
		// second parameter as 'height', and not vice-versa.
		printMaze(Maze.create(1, 6));

		// Ensure that your code uses the first parameter as 'width' and the
		// second parameter as 'height', and not vice-versa.
		printMaze(Maze.create(6, 1));

		// A few other things to note:
		// - There should be NO CYCLES in your mazes. If there are cyclic paths
		//   through any of your mazes, you have done something wrong.
		// - There should be no unreachable cells in your mazes.
		// - Each maze should have exactly one path from 's' to 'e'.

		printMaze(Maze.create(2, 2));
		printMaze(Maze.create(10, 5));
		printMaze(Maze.create(10, 10));
		printMaze(Maze.create(30, 20));
	}
}
