package lpoo.logic;

public final class Dragon extends Entity 
{
    private boolean sleeping;

    /**
     * @brief constructor with parameters for class 'Dragon'
     * @param x initial X coordinate for dragon position
     * @param y initial Y coordinate for dragon position
     */
    protected Dragon(int x, int y) 
    {
        this(new Point(x, y));
    }

    /**
     * @brief constructor with parameters for class 'Dragon'
     * @param pos initial dragon position
     */
    protected Dragon(Point pos) 
    {
        super(pos, 100);

        this.sleeping = false;
    }

    /**
     * @return returns 'true' is dragon is asleep; 'false' otherwise
     */
    public final boolean isSleeping() 
    {
        return this.sleeping;
    }

    /**
     * @brief toggles dragon sleep status (puts him to sleep/wakes him up)
     */
    protected void toggleSleep() 
    {
        this.sleeping = !sleeping;
    }

    /**
     * @return 
     * @param maze
     * @param direction the direction to move
     */
    protected final boolean validMove(Maze maze, Direction direction) 
    {
        if (this.getHealth() <= 0) 
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

        boolean isNotWall = maze.symbolAt(newPosition.x, newPosition.y) != 'X';
        boolean isNotExit = maze.symbolAt(newPosition.x, newPosition.y) != 'S';

        return (isNotWall && isNotExit);
    }

    /**
     * @param maze
     * @brief move the dragon
     * @param direction the direction to move
     */
    @Override
    protected void move(Maze maze, Direction direction) 
    {
        if (direction != Direction.NONE) 
        {
            maze.clearSymbol(pos.x, pos.y);

            if (direction == Direction.UP) 
            {
                pos.y--;
            } 
            else if (direction == Direction.DOWN) 
            {
                pos.y++;
            } 
            else if (direction == Direction.LEFT) 
            {
                pos.x--;
            } 
            else if (direction == Direction.RIGHT) 
            {
                pos.x++;
            }
        }
    }

    private boolean isActive() 
    {
        return getHealth() > 0 && !sleeping;
    }

    /**
     * @brief attacks the 'Hero' from close range
     * @param player the player entity
     */
    private boolean canAttack(Hero player) 
    {
        if (!isActive() || player.getHealth() <= 0 || player.hasSword()) 
        {
            return false;
        }

        Point hero = player.getPosition();

        if (pos.x <= hero.x + 1 && pos.x >= hero.x - 1 && pos.y == hero.y) 
        {
            return true;
        }

        return pos.y <= hero.y + 1 && pos.y >= hero.y - 1 && pos.x == hero.x;
    }

    protected final boolean canAttackFire(Maze maze, Hero player) 
    {
        if (canAttack(player)) 
        {
            return false;
        }

        if (!isActive() || player.getHealth() <= 0 || player.hasShield()) 
        {
            return false;
        }

        int playerX = player.getX();
        int playerY = player.getY();
        int dragonX = pos.x;
        int dragonY = pos.y;
        int incrementX = 0;
        int incrementY = 0;

        if (playerY == pos.y && Math.abs(playerX - pos.x) <= 3) {
            incrementX = (playerX < pos.x) ? -1 : 1;
        } 
        else if (playerX == pos.x && Math.abs(playerY - pos.y) <= 3) {
            incrementY = (playerY < pos.y) ? -1 : 1;
        } 
        else {
            return false;
        }

        for (int i = 0; i < 4 && maze.symbolAt(dragonX, dragonY) != 'X';
                i++, dragonX += incrementX, dragonY += incrementY) {
            if (playerX == dragonX && playerY == dragonY) {
                return true;
            }
        }

        return false;
    }

    protected final void attack(Maze maze, Hero player) 
    {
        if (canAttack(player)) 
        {
            maze.clearSymbol(player.getX(), player.getY());
            player.setHealth(0);
        }
    }

    protected final void attackFire(Maze maze, Hero player) 
    {
        if (canAttackFire(maze, player)) 
        {
            maze.placeSymbol(player.getX(), player.getY(), 'O');
            player.setHealth(0);
        }
    }

    /**
     * @brief draws the dragon on the game board
     */
    @Override
    protected void draw(Maze maze) 
    {
        if (this.getHealth() > 0) 
        {
            if (sleeping) 
            {
                maze.placeSymbol(pos.x, pos.y, 'd');
            } 
            else 
            {
                maze.placeSymbol(pos.x, pos.y, 'D');
            }
        }
    }
}