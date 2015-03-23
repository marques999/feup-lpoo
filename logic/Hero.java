package lpoo.logic;

public final class Hero extends Entity 
{
    private Item sword = null;
    private Item dart = null;
    private Item shield = null;

    /**
     * @param pos
     * @brief default constructor for class 'Hero'
     */
    protected Hero(Point pos) 
    {
        super(pos, 100);
    }

    /**
     * @return returns 'true' if the player has the sword; 'false' otherwise
     */
    public final boolean hasSword() 
    {
        return (this.sword != null);
    }

    /**
     * @return returns 'true' if the player has darts; 'false' otherwise
     */
    public final boolean hasDarts() 
    {
        return (this.dart != null);
    }

    /**
     * @return returns 'true' if player has the shield; 'false' otherwise
     */
    public final boolean hasShield() 
    {
        return (this.shield != null);
    }

    public boolean attackDarts(Maze maze, Direction direction) 
    {
        if (!hasDarts()) 
        {
            return false;
        }

        int incrementX = 0;
        int incrementY = 0;
        int positionX = pos.x;
        int positionY = pos.y;

        switch (direction) 
        {
            case UP:
                incrementX = 0;
                incrementY = -1;
                break;
            case DOWN:
                incrementX = 0;
                incrementY = 1;
                break;
            case LEFT:
                incrementX = -1;
                incrementY = 0;
                break;
            case RIGHT:
                incrementX = 1;
                incrementY = 0;
                break;
            case NONE:
                return false;
        }

        for (; maze.symbolAt(positionX, positionY) != 'X'; positionX += incrementX, positionY += incrementY) 
        {
            Dragon target = GameState.dragonAt(new Point(positionX, positionY));

            if (target != null) 
            {
                maze.placeSymbol(positionX, positionY, '*');
                target.setHealth(0);
                dart = null;

                return true;
            }
        }

        dart = null;

        return false;
    }

    /**
     * @brief checks if player can move in specified direction
     * @param direction
     * @param maze
     * @return returns 'true' if player is still alive and can move to the
     * specified direction, 'false' otherwise
     */
    public final boolean validMove(Maze maze, Direction direction) 
    {
        if (getHealth() <= 0) 
        {
            return false;
        }

        Point newPosition = new Point();

        switch (direction) 
        {
            case UP:
                newPosition.x = pos.x;
                newPosition.y = pos.y - 1;
                break;
            case DOWN:
                newPosition.x = pos.x;
                newPosition.y = pos.y + 1;
                break;
            case LEFT:
                newPosition.x = pos.x - 1;
                newPosition.y = pos.y;
                break;
            case RIGHT:
                newPosition.x = pos.x + 1;
                newPosition.y = pos.y;
                break;
            case NONE:
                return false;
        }

        return (maze.symbolAt(newPosition.x, newPosition.y) != 'X');
    }

    public void pickItem(Maze maze, Point pos) 
    {
        Item itemPicked = null;

        if (maze.symbolAt(pos.x, pos.y) != ' ') 
        {
            itemPicked = GameState.itemAt(pos);
        }

        if (itemPicked != null) 
        {
            if (itemPicked instanceof Sword) // SWORD
            {
                if (!hasSword()) 
                {
                    GameState.removeItem(itemPicked);
                    sword = itemPicked;
                }
            } 
            else if (itemPicked instanceof Shield) // SHIELD
            {
                if (!hasShield()) 
                {
                    GameState.removeItem(itemPicked);
                    shield = itemPicked;
                }
            } 
            else if (itemPicked instanceof Dart) 
            {
                if (!hasDarts()) 
                {
                    GameState.removeItem(itemPicked);
                    dart = itemPicked;
                }
            }
        }
    }

    @Override
    public void move(Maze maze, Direction direction) 
    {
        Point newPosition = new Point();

        if (validMove(maze, direction)) 
        {
            switch (direction) 
            {
                case UP:
                    newPosition.y = pos.y - 1;
                    newPosition.x = pos.x;
                    break;
                case DOWN:
                    newPosition.y = pos.y + 1;
                    newPosition.x = pos.x;
                    break;
                case LEFT:
                    newPosition.y = pos.y;
                    newPosition.x = pos.x - 1;
                    break;
                case RIGHT:
                    newPosition.y = pos.y;
                    newPosition.x = pos.x + 1;
                    break;
                case NONE:
                    return;
            }

            if (maze.symbolAt(newPosition.x, newPosition.y) == 'S' && !GameState.canExit()) 
            {
                return;
            }

            pickItem(maze, newPosition);
            maze.clearSymbol(pos.x, pos.y);
            pos = newPosition;
        }
    }

    /**
     * @brief checks if player is close enough to attack an enemy dragon
     * @param dragon
     * @return returns 'true' if player has the sword and can attack the dragon; 'false' otherwise
     */
    public final boolean canAttack(Dragon dragon) 
    {
        if (getHealth() <= 0 || dragon.getHealth() <= 0 || !hasSword()) 
        {
            return false;
        }

        if (pos.equals(dragon.getPosition())) 
        {
            return true;
        }

        Point dpos = dragon.getPosition();

        if (pos.x <= dpos.x + 1 && pos.x >= dpos.x - 1 && pos.y == dpos.y) 
        {
            return true;
        }

        return pos.y <= dpos.y + 1 && pos.y >= dpos.y - 1 && pos.x == dpos.x;
    }

    public final void attackSword(Maze maze, Dragon dragon) 
    {
        if (canAttack(dragon)) 
        {
            maze.clearSymbol(dragon.getX(), dragon.getY());
            dragon.setHealth(0);
        }
    }

    /**
     * @param maze
     * @brief draws a symbol on the screen representing the player
     */
    @Override
    protected void draw(Maze maze) 
    {
        if (getHealth() > 0) 
        {
            if (hasShield()) 
            {
                maze.placeSymbol(pos.x, pos.y, hasSword() ? 'A' : 'H');
            } 
            else 
            {
                maze.placeSymbol(pos.x, pos.y, hasSword() ? 'a' : 'h');
            }
        }
    }
}