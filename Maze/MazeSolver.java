package Maze;

public class MazeSolver {

    /* Boolean function to return if the maze is solved or not. It is considered to be solved if the path of the maze
     * reaches the bottom right cell of the grid.  */
    public boolean solveMaze(int row, int column) {
        int sleepTime = 10;
        if (MazeGenerator.maze[row][column] == MazeGenerator.emptyCode) {

            /* Adds the cell to the path */
            MazeGenerator.maze[row][column] = MazeGenerator.pathCode;

            /* The path has reached it's goal */
            if (row == MazeGenerator.rows - 2 && column == MazeGenerator.columns - 2) {
                return true;
            }

            try {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e) { }

            /* If the maze has not been solved, it tries to solve the maze by extending its path */
            if (solveMaze(row - 1,column)  || solveMaze(row,column - 1)  || solveMaze(row + 1, column)
                    || solveMaze(row,column + 1) ){
                return true;
            }

            /* The maze could not have been solved this way so now it backtracks itself out of the cell and goes back
             * to the known path */

            /* Marks the cell as a visited cell */
            MazeGenerator.maze[row][column] = MazeGenerator.visitedCode;

            /* https://www.geeksforgeeks.org/synchronized-in-java/ */
            /* https://stackoverflow.com/questions/13264726/java-syntax-synchronized-this */
            synchronized(this) {
                try {
                    wait(sleepTime);

                }
                catch (InterruptedException e) { }
            }
        }
        return false;
    }
}
