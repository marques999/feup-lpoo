package lpoo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIMain extends JFrame
{
	public GUIMain()
	{
		initComponents();
	}

	private void initComponents()
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
		setSize(new java.awt.Dimension(189, 137));
		setLocationRelativeTo(null);
	}

	private void jButton1ActionPerformed(ActionEvent evt)
	{
		GUIInterface guiInterface = new GUIInterface();
		guiInterface.setVisible(true);
		setVisible(false);
	}

	private void jButton2ActionPerformed(ActionEvent evt)
	{
		GUIMazeEditor guiMazeEditor = new GUIMazeEditor();
		guiMazeEditor.setVisible(true);
		setVisible(false);
	}

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
		{
		}

		EventQueue.invokeLater(() -> {
            new GUIMain().setVisible(true);
        });
	}

	private JButton jButton1;
	private JButton jButton2;
}