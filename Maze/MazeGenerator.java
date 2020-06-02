package Maze;

public class MazeGenerator {
    public static int [][] maze;

    /* Codes to set each cell/room to */
    public final static int backgroundCode = 0;
    public final static int wallCode = 1;
    public final static int pathCode = 2;
    public final static int emptyCode = 3;
    public final static int visitedCode = 4;

    public final static int rows = 51;
    public final static int columns = 51;
    public final static int cellSize = 15;
    public static int mazeWidth, mazeHeight;

    public static boolean mazeExists = false;

    /* Method to change the room codes, used in generateMaze() when tearing down walls in the multi-dimensional array */
    public static void setRoomCode(int row, int column, int selectedCell, int replacementCode) {
        if (maze[row][column] == selectedCell) {
            maze[row][column] = replacementCode;
            setRoomCode(row + 1, column, selectedCell,replacementCode);
            setRoomCode(row - 1, column, selectedCell, replacementCode);
            setRoomCode(row,column + 1, selectedCell, replacementCode);
            setRoomCode(row,column - 1, selectedCell, replacementCode);
        }
    }

    public static void generateMaze() {
        if (maze == null) {
            maze = new int[rows][columns];
        }

        /* Number of rooms and walls */
        int emptyCellCount = 0;
        int wallCount = 0;

        /* Positions of the walls between the rooms */
        int[] rowPositions = new int[(rows * columns) / 2];
        int[] columnPositions = new int[(rows * columns) / 2];

        /* Starts the maze off by setting everything to be a wall */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                maze[i][j] = wallCode;
        }

        /* Then starts to make the grid of empty rooms */
        for (int i = 1; i < rows - 1; i += 2) {
            for (int j = 1; j < columns - 1; j += 2) {

                emptyCellCount++;
                /* Checks the wall to the right of the room */
                if (j < columns - 2) {
                    rowPositions[wallCount] = i;
                    columnPositions[wallCount] = j + 1;
                    wallCount++;
                }

                /* Checks the wall beneath the room */
                if (i < rows - 2) {
                    rowPositions[wallCount] = i + 1;
                    columnPositions[wallCount] = j;
                    wallCount++;
                }

                /* For clarity, each room is represented by a different negative number */
                maze[i][j] = -emptyCellCount;
            }
        }

        mazeExists = true;

        /* Choose a wall randomly and maybe tear it down (if it does not form a loop).
         * Tearing down a wall joins two cells into one cell.
         * If the cells on both sides of the wall have the same code, it will form a loop, so it is left alone. */
        int randomInt;
        for (int i = wallCount-1; i > 0; i--) {
            randomInt = (int)(Math.random() * i);

            /* Separates the cells horizontally if row is odd */
            if (rowPositions[randomInt] % 2 == 1 && maze[rowPositions[randomInt]][columnPositions[randomInt] - 1]
                    != maze[rowPositions[randomInt]][columnPositions[randomInt] + 1]) {

                setRoomCode(rowPositions[randomInt], columnPositions[randomInt] - 1,
                        maze[rowPositions[randomInt]][columnPositions[randomInt] - 1],
                        maze[rowPositions[randomInt]][columnPositions[randomInt] + 1]);

                maze[rowPositions[randomInt]][columnPositions[randomInt]] =
                        maze[rowPositions[randomInt]][columnPositions[randomInt] + 1];

            }

            /* Separates the cells vertically if row is even */
            else if (rowPositions[randomInt] % 2 == 0 && maze[rowPositions[randomInt] - 1][columnPositions[randomInt]]
                    != maze[rowPositions[randomInt] + 1][columnPositions[randomInt]]) {

                setRoomCode(rowPositions[randomInt] - 1, columnPositions[randomInt],
                        maze[rowPositions[randomInt] - 1][columnPositions[randomInt]],
                        maze[rowPositions[randomInt] + 1][columnPositions[randomInt]]);

                maze[rowPositions[randomInt]][columnPositions[randomInt]] =
                        maze[rowPositions[randomInt] + 1][columnPositions[randomInt]];

            }
            rowPositions[randomInt] = rowPositions[i];
            columnPositions[randomInt] = columnPositions[i];
        }

        /* Replace the negative values set in each room with the emptyCode */
        for (int i = 1; i< rows - 1; i++)
            for (int j = 1; j< columns - 1; j++)
                if (maze[i][j] < 0) {
                    maze[i][j] = emptyCode;
                }
    }
}