package lpoo.logic;

public abstract class Item {

    protected Point pos;

    /**
     * @param pos
     * @brief alternative constructor with parameters for class 'Item'
     */
    protected Item(Point pos) {
        this.pos = pos;
    }

    /**
     * @return 
     * @brief returns the item's current position x coordinate
     */
    public final int getX() {
        return pos.x;
    }

    /**
     * @return 
     * @brief returns the item's current position y coordinate
     */
    public final int getY() {
        return pos.y;
    }

    /**
     * @return returns the item's current position
     */
    public final Point getPosition() {
        return this.pos;
    }

    /**
     * @param maze
     * @brief draws an item on the game board
     */
    protected abstract void draw(Maze maze);
}