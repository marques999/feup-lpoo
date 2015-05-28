package lpoo.proj2.logic;

public interface CollisionDetector 
{
    public boolean collides(Paddle paddle);
    public boolean collides(Puck puck);
    public boolean collides(Wall wall);
    public boolean collides(Goal goal); 
}