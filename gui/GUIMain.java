package lpoo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import lpoo.logic.GameState;

public
        class GUIMain extends JFrame
{

    public
            GUIMain()
    {
        initComponents();
    }

    private
            void initComponents()
    {
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton1.setText("Play Game");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.setText("Maze Editor");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        setTitle("Maze Run");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(jButton1, BorderLayout.CENTER);
        getContentPane().add(jButton2, BorderLayout.PAGE_END);
        setSize(new Dimension(190, 140));
        setLocationRelativeTo(null);
    }

    private
            void jButton1ActionPerformed(ActionEvent evt)
    {
        GUIInterface guiInterface = new GUIInterface();
        guiInterface.setVisible(true);
        setVisible(false);
    }

    private
            void jButton2ActionPerformed(ActionEvent evt)
    {
        GUIMazeEditor guiMazeEditor = new GUIMazeEditor();
        guiMazeEditor.setVisible(true);
        setVisible(false);
    }

    public static
            void main(String args[]) throws IOException, ClassNotFoundException
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
        }

        FileInputStream fin;
        FileOutputStream fout;
        ObjectInputStream oin;
        ObjectOutputStream oout;
        
        final File buffer = new File("User Settings");

        if (buffer.exists())
        {
            fin = new FileInputStream(buffer);
            oin = new ObjectInputStream(fin);

            GUIGlobals.read(oin);

            oin.close();
            fin.close();

        }
        
        EventQueue.invokeLater(() ->
        {
            new GUIMain().setVisible(true);
        });
        
      
    }

    private
            JButton jButton1;
    private
            JButton jButton2;
}
