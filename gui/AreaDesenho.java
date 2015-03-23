package lpoo.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import lpoo.logic.GameState;
import lpoo.logic.Maze;

public final class AreaDesenho extends JPanel
{
    private ImageIcon s_hero;
    private ImageIcon s_dragon;
    private ImageIcon s_dart;
    private ImageIcon s_shield;

    private Image s_hero_resized;
    private Image s_dragon_resized;
    private Image s_dart_resized;
    private Image s_shield_resized;

    private Maze g_maze;
    private int m_size;
    private int g_size;

    public AreaDesenho() 
    {
        this.s_hero = new ImageIcon(getClass().getResource("/lpoo/res/unarmedhero.png"));
        this.s_shield = new ImageIcon(getClass().getResource("/lpoo/res/shield-64x64.png"));
        this.s_dart = new ImageIcon(getClass().getResource("/lpoo/res/dart-64x64.png"));
        this.s_dragon = new ImageIcon(getClass().getResource("/lpoo/res/FireDragon_tiny.png"));
        this.g_maze = GameState.getMaze();
        this.m_size = g_maze.getSize();
        this.g_size = 640 / m_size;

        Image t_img = s_hero.getImage();

        this.s_hero_resized = t_img;/*t_img.getScaledInstance(g_size, g_size, java.awt.Image.SCALE_SMOOTH);*/

        this.s_dragon_resized = s_dragon.getImage();
        this.s_dart_resized = s_dart.getImage();
        this.s_shield_resized = s_shield.getImage();
    }

    public void paintComponent(Graphics g) 
    {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, 640, 640);
        g.setColor(new Color(200, 100, 50));

        int y = 0;

        for (int i = 0; i < m_size; i++) 
        {
            int x = 0;

            for (int j = 0; j < m_size; j++) 
            {
                switch (g_maze.symbolAt(j, i)) 
                {
                    case 'h': case 'a':
                    case 'H': case 'A':
                        g.drawImage(s_hero_resized, x, y, null);
                        break;
                    case '*':
                        g.drawImage(s_dart_resized, x, y, null);
                        break;
                    case 'V':
                        g.drawImage(s_shield_resized, x, y, null);
                        break;
                    case 'D':
                    case 'd':
                        g.drawImage(s_dragon_resized, x, y, null);
                        break;
                    case 'X':
                        g.fillRect(x, y, g_size, g_size);
                        break;
                }

                x += g_size;
            }

            y += g_size;
        }
    }
}