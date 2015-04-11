package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import lpoo.cli.CLIInterface;

public class GUIMain extends JFrame
{
	private static final long serialVersionUID = -3154907517184624651L;

	public GUIMain()
	{
		initComponents();
	}

	private void initComponents()
	{
		btnCLI = new JButton();
		btnPlay = new JButton();
		btnEditor = new JButton();
		btnQuit = new JButton();
		lblTitle = new JLabel();
		lblKnight = new JLabel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		setResizable(false);

		btnCLI.setText("CLI");
		btnCLI.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCLI.addActionListener(this::btnCLIActionPerformed);
		btnPlay.setText("Play Game");
		btnPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPlay.setRequestFocusEnabled(false);
		btnPlay.addActionListener(this::btnPlayActionPerformed);
		btnEditor.setText("Maze Editor");
		btnEditor.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnEditor.addActionListener(this::btnEditorActionPerformed);
		btnQuit.setText("Quit");
		btnQuit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnQuit.addActionListener(this::btnQuitActionPerformed);
		lblTitle.setFont(lblTitle.getFont().deriveFont(lblTitle.getFont().getStyle() | Font.BOLD, lblTitle.getFont().getSize() + 13));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText("Maze Run");
		lblKnight.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/Knight-concept-art.png")));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addGap(32, 32, 32)
				.addComponent(lblKnight)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
				.addComponent(btnPlay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup()
				.addGap(10, 10, 10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
				.addComponent(btnEditor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnQuit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btnCLI, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
				.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
				.addGap(128, 128, 128)));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addGap(24, 24, 24)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(lblKnight).addContainerGap(24, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
				.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(37, 37, 37)
				.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(btnCLI, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(btnEditor, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
				.addGap(55, 55, 55)))));

		pack();
		setLocationRelativeTo(null);
	}

	private void btnPlayActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		GUIInterface guiInterface = new GUIInterface();
		guiInterface.setVisible(true);
	}

	private void btnCLIActionPerformed(ActionEvent evt)
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

	private void btnEditorActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		GUIMazeEditor guiMazeEditor = new GUIMazeEditor();
		guiMazeEditor.setVisible(true);
	}

	private void btnQuitActionPerformed(ActionEvent evt)
	{
		dispose();
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

	private JButton btnCLI;
	private JButton btnEditor;
	private JButton btnPlay;
	private JButton btnQuit;
	private JLabel lblKnight;
	private JLabel lblTitle;
}