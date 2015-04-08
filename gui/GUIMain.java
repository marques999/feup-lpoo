package lpoo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import lpoo.cli.CLIInterface;

public class GUIMain extends JFrame
{
	private static final long serialVersionUID = 3529558414915895966L;

	public GUIMain()
	{
		initComponents();
	}

	private void initComponents()
	{
		btnStartGUI = new JButton();
		btnStartEditor = new JButton();
		btnStartCLI = new JButton();
		btnStartGUI.setText("Play Game");
		btnStartGUI.addActionListener(this::btnStartGUIActionPerformed);
		btnStartCLI.setText("CLI Interface");
		btnStartCLI.addActionListener(this::btnStartCLIActionPerformed);
		btnStartEditor.setText("Maze Editor");
		btnStartEditor.addActionListener(this::btnStartEditorActionPerformed);

		setTitle("Maze Run");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(btnStartGUI, BorderLayout.NORTH);
		getContentPane().add(btnStartCLI, BorderLayout.CENTER);
		getContentPane().add(btnStartEditor, BorderLayout.PAGE_END);
		setSize(new Dimension(190, 140));
		setLocationRelativeTo(null);
	}

	private void btnStartGUIActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		GUIInterface guiInterface = new GUIInterface();
		guiInterface.setVisible(true);
	}

	private void btnStartEditorActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		GUIMazeEditor guiMazeEditor = new GUIMazeEditor();
		guiMazeEditor.setVisible(true);
	}

	private void btnStartCLIActionPerformed(ActionEvent evt)
	{
		try
		{
			setVisible(false);
			CLIInterface.main(null);
		}
		catch (IOException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
		{
			GUIGlobals.abort(ex, null);
		}

		FileInputStream fin;
		ObjectInputStream oin;

		final File buffer = new File("User Settings");

		if (buffer.exists())
		{
			try
			{
				fin = new FileInputStream(buffer);
				oin = new ObjectInputStream(fin);
				GUIGlobals.read(oin);
				oin.close();
				fin.close();
			}
			catch (IOException | ClassNotFoundException ex)
			{
				GUIGlobals.abort(ex, null);
			}
		}

		EventQueue.invokeLater(() -> {
			new GUIMain().setVisible(true);
		});
	}

	private JButton btnStartGUI;
	private JButton btnStartEditor;
	private JButton btnStartCLI;
}