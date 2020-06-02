package Maze;

import javax.swing.*;
import java.awt.*;

public class MazeAnimator extends JPanel implements Runnable{
    private int panelWidth, panelHeight, leftWall, topWall;
    MazeSolver solver = new MazeSolver();

    public MazeAnimator() {
        setPreferredSize(new Dimension(MazeGenerator.cellSize * MazeGenerator.columns,
                MazeGenerator.cellSize * MazeGenerator.rows));

        /* https://www.geeksforgeeks.org/java-lang-thread-class-java/ */
        new Thread(this).start();
    }

    /* Sets the size of the width and height needed for drawing before animating/generating the maze */
    public void setSize() {
        int border = 10;
        if (getWidth() != MazeGenerator.mazeWidth || getHeight() != MazeGenerator.mazeHeight) {
            MazeGenerator.mazeWidth = getWidth();
            MazeGenerator.mazeHeight = getHeight();

            int width = (MazeGenerator.mazeWidth - 2 * border) / MazeGenerator.columns;
            int height = (MazeGenerator.mazeHeight - 2 * border) / MazeGenerator.rows;

            leftWall = (MazeGenerator.mazeWidth - width * MazeGenerator.columns) / 2;
            topWall = (MazeGenerator.mazeHeight - height * MazeGenerator.rows) / 2;

            panelWidth = width * MazeGenerator.columns;
            panelHeight = height * MazeGenerator.rows;
        }
    }

    public void animateMaze(Graphics graphics) {
        if (MazeGenerator.mazeExists) {
            int width = panelWidth / MazeGenerator.columns;
            int height = panelHeight / MazeGenerator.rows;

            for (int i = 0; i < MazeGenerator.columns; i++){
                for (int j = 0; j < MazeGenerator.rows; j++) {
                    if (MazeGenerator.maze[i][j] < 0) {
                        graphics.setColor(Color.WHITE);
                    }
                    else {
                        if ((MazeGenerator.maze[i][j] == MazeGenerator.backgroundCode) ||
                                (MazeGenerator.maze[i][j] == MazeGenerator.wallCode)) {
                            graphics.setColor(Color.BLACK);
                        }
                        else if (MazeGenerator.maze[i][j] == MazeGenerator.pathCode){
                            graphics.setColor(Color.magenta);
                        }
                        else {
                            graphics.setColor(Color.WHITE);
                        }
                    }
                    graphics.fillRect( (i * width) + leftWall, (j * height) + topWall, width, height);
                }
                repaint();
            }
        }
    }

    /* While-loop to repeatedly generate, solve, and animate a new maze until the user makes it force-quit */
    public void run() {
        int sleepTime = 1000;
        while (true) {
            MazeGenerator.generateMaze();
            solver.solveMaze(1,1);
            synchronized(this) {
                try {
                    wait(sleepTime);
                }
                catch (InterruptedException e) {
                }
            }
            MazeGenerator.mazeExists = false;
            repaint();
        }
    }

    /* https://www.oracle.com/technetwork/java/painting-140037.html */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setSize();
        animateMaze(graphics);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("Maze Solver");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(new MazeAnimator());
        window.pack();
        window.setVisible(true);
    }
}
