package lpoo.logic;

import java.util.Random;

public class Maze {

    private final Random m_rand;
    private Point m_exit;
    private final int m_size;
    private char[][] m_matrix;

    public Maze(int n) {
        m_matrix = new char[n][n];
        m_rand = new Random();
        m_size = n;
    }
    
    protected void setExitPosition(Point e) {
        this.m_exit = e;
    }

    public char[][] getMatrix() {
        return this.m_matrix;
    }

    public Point getExitPosition() {
        return this.m_exit;
    }

    public int getSize() {
        return this.m_size;
    }

    protected void setMatrix(char[][] m_matrix) {
        this.m_matrix = m_matrix;
    }

    protected final boolean isWall(Point pos) {
        return (pos.x == 0 || pos.x == m_size - 1 || pos.y == 0 || pos.y == m_size - 1);
    }

    protected final void printMaze() {
        for (int y = 0; y < m_size; y++) {
            for (int x = 0; x < m_size; x++) {
                System.out.print(m_matrix[y][x] + " ");
            }

            System.out.println("");
        }
    }

    public final char symbolAt(int x, int y) {
        return m_matrix[y][x];
    }

    protected void clearSymbol(int x, int y) {
        if (x >= 0 && x < m_size && y >= 0 && y < m_size) {
            m_matrix[y][x] = ' ';
        }
    }

    protected void placeSymbol(int x, int y, char s) {
        if (x >= 0 && x < m_size && y >= 0 && y < m_size) {
            m_matrix[y][x] = s;
        }
    }

    protected final Point placeEntity(char symbol) {
        int initialX = 0;
        int initialY = 0;

        while (m_matrix[initialY][initialX] != ' ') {
            initialX = 1 + m_rand.nextInt(m_size - 2);
            initialY = 1 + m_rand.nextInt(m_size - 2);
        }

        placeSymbol(initialX, initialY, symbol);

        return new Point(initialX, initialY);
    }
}