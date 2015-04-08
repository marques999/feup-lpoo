package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import lpoo.logic.*;

public class GUIInterface extends JFrame
{
	private static final long serialVersionUID = 7110803925049276531L;

	private boolean attackModifier;
	private Maze initialState;

	public GUIInterface()
	{
		initComponents();
		initializeGame();
		applyZoom(GUIGlobals.interfaceZoom);
		attackModifier = false;
	}

	private void initializeGame()
	{
		if (GUIGlobals.userDifficulty != -1)
		{
			GameState.setDifficulty(GUIGlobals.userDifficulty);
		}
		else
		{
			GameState.initialize(new RandomMaze(GUIGlobals.mazeWidth, GUIGlobals.mazeHeight));
			GameState.initializeDragons(GUIGlobals.numberDragons);
			GameState.initializeDarts(GUIGlobals.numberDragons + 1);
		}

		GameState.setDragonMovement(GUIGlobals.dragonDifficulty);
		pnlPlayfield.initializeMaze(GameState.getMaze());
		resumeGame();
	}

	protected void resumeGame()
	{
		cloneMaze();
		pnlPlayfield.setPreferredSize(pnlPlayfield.getWindowSize());
		pnlPlayfield.repaint();
		checkState();
		revalidate();
		pack();
	}

	private void restartGame()
	{
		pnlPlayfield.initializeMaze(initialState);
		GameState.initializeCustom(initialState);
		resumeGame();
	}
	
	private void cloneMaze()
	{
		try
		{
			initialState = GameState.getMaze().clone();
		}
		catch (CloneNotSupportedException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	private void initComponents()
	{
		buttonGroup1 = new ButtonGroup();
		lblStatus = new JLabel();
		pnlPlayfield = new AreaDesenho();
		mbDefault = new JMenuBar();
		mnGame = new JMenu();
		btnNew = new JMenuItem();
		btnLoadLevel = new JMenuItem();
		jSeparator2 = new JPopupMenu.Separator();
		btnLoadState = new JMenuItem();
		btnSaveState = new JMenuItem();
		jSeparator1 = new JPopupMenu.Separator();
		btnExit = new JMenuItem();
		mnOptions = new JMenu();
		mnZoom = new JMenu();
		mnZoom75 = new JMenuItem();
		mnZoom100 = new JMenuItem();
		mnZoom150 = new JMenuItem();
		mnZoom200 = new JMenuItem();
		jSeparator3 = new JPopupMenu.Separator();
		btnControls = new JMenuItem();
		btnPreferences = new JMenuItem();
		mnHelp = new JMenu();
		btnAbout = new JMenuItem();
		btnRestart = new JMenuItem();

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lpoo/res/FireDragon_icon.png")));
		setLocationRelativeTo(null);
		setTitle("Maze Run");
		setResizable(false);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent evt)
			{
				formWindowClosing(evt);
			}
		});

		addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				formKeyPressed(evt);
			}

			@Override
			public void keyReleased(KeyEvent evt)
			{
				formKeyReleased(evt);
			}
		});

		lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | Font.BOLD, 13));
		lblStatus.setText("jLabel1");

		getContentPane().add(lblStatus, BorderLayout.PAGE_END);

		pnlPlayfield.setPreferredSize(new Dimension(640, 480));

		getContentPane().add(pnlPlayfield, BorderLayout.CENTER);

		mnGame.setText("Game");
		btnNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		btnNew.setText("New Game");
		btnNew.addActionListener(this::btnNewActionPerformed);
		mnGame.add(btnNew);
		btnRestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		btnRestart.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/arrow_rotate.png")));
		btnRestart.setText("Restart");
		btnRestart.addActionListener(this::btnRestartActionPerformed);
		mnGame.add(btnRestart);
		btnLoadLevel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		btnLoadLevel.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/world.png")));
		btnLoadLevel.setText("Load maze...");
		btnLoadLevel.addActionListener(this::btnLoadLevelActionPerformed);
		mnGame.add(btnLoadLevel);
		mnGame.add(jSeparator2);
		btnLoadState.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		btnLoadState.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/folder.png")));
		btnLoadState.setText("Load State");
		btnLoadState.addActionListener(this::btnLoadStateActionPerformed);
		mnGame.add(btnLoadState);
		btnSaveState.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		btnSaveState.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/disk.png")));
		btnSaveState.setText("Save State");
		btnSaveState.addActionListener(this::btnSaveStateActionPerformed);
		mnGame.add(btnSaveState);
		mnGame.add(jSeparator1);
		btnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		btnExit.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/door_open.png")));
		btnExit.setText("Exit");
		btnExit.addActionListener(this::btnExitActionPerformed);
		mnGame.add(btnExit);
		mbDefault.add(mnGame);
		mnOptions.setText("Options");
		mnZoom.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/magnifier.png")));
		mnZoom.setText("Zoom");
		mnZoom75.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mnZoom75.setText("75%");
		buttonGroup1.add(mnZoom75);
		mnZoom75.addActionListener(this::mnZoom75ActionPerformed);
		mnZoom.add(mnZoom75);
		mnZoom100.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		mnZoom100.setText("100%");
		buttonGroup1.add(mnZoom100);
		mnZoom100.addActionListener(this::mnZoom100ActionPerformed);
		mnZoom.add(mnZoom100);
		mnZoom150.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK));
		mnZoom150.setText("150%");
		buttonGroup1.add(mnZoom150);
		mnZoom150.addActionListener(this::mnZoom150ActionPerformed);
		mnZoom.add(mnZoom150);
		mnZoom200.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.CTRL_MASK));
		mnZoom200.setText("200%");
		buttonGroup1.add(mnZoom200);
		mnZoom200.addActionListener(this::mnZoom200ActionPerformed);
		mnZoom.add(mnZoom200);
		mnOptions.add(mnZoom);
		mnOptions.add(jSeparator3);
		btnControls.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/joystick.png")));
		btnControls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		btnControls.setText("Controls");
		btnControls.addActionListener(this::btnControlsActionPerformed);
		mnOptions.add(btnControls);
		btnPreferences.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/wrench.png")));
		btnPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		btnPreferences.setText("Preferences");
		btnPreferences.addActionListener(this::btnPreferencesActionPerformed);
		mnOptions.add(btnPreferences);
		mbDefault.add(mnOptions);
		mnHelp.setText("Help");
		btnAbout.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/help.png")));
		btnAbout.setText("About");
		btnAbout.addActionListener(this::btnAboutActionPerformed);
		mnHelp.add(btnAbout);
		mbDefault.add(mnHelp);

		setJMenuBar(mbDefault);
		pack();
		setLocationRelativeTo(null);
	}

	private void btnPreferencesActionPerformed(ActionEvent evt)
	{
		GUIOptions guiOptions = new GUIOptions(this);
		guiOptions.setVisible(true);
	}

	private void btnControlsActionPerformed(ActionEvent evt)
	{
		GUIControls guiControls = new GUIControls(this);
		guiControls.setVisible(true);
	}

	private void confirmExit()
	{
		if (!GameState.gameOver())
		{
			String confirmationMessage = "Are you sure you want to quit your current game?";

			if (JOptionPane.showConfirmDialog(this, confirmationMessage, "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			{
				saveSettings();
				System.exit(0);
			}
		}
		else
		{
			System.exit(0);
		}
	}

	private void saveSettings()
	{
		FileOutputStream fout;
		ObjectOutputStream oout;

		try
		{
			fout = new FileOutputStream("User Settings");
			oout = new ObjectOutputStream(fout);
			GUIGlobals.write(oout);
			oout.close();
			fout.close();
		}
		catch (IOException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	private void btnAboutActionPerformed(ActionEvent evt)
	{
		GUIAbout guiAbout = new GUIAbout(this);
		guiAbout.setVisible(true);
	}

	private void btnExitActionPerformed(ActionEvent evt)
	{
		confirmExit();
	}

	private void btnRestartActionPerformed(ActionEvent evt)
	{
		restartGame();
	}

	private String printDarts()
	{
		return String.format("Darts: %02d | Dragons: %02d | ", GameState.getNumberDarts(), GameState.getNumberDragons());
	}
	
	private void checkState()
	{
		if (attackModifier)
		{
			lblStatus.setText(printDarts() + "Please select a direction (Up, Down, Left, Right, ESC to cancel):");
		}
		else
		{
			lblStatus.setText(printDarts() + "OBJECTIVE: " + GameState.getObjective());
		}

		if (GameState.gameOver())
		{
			if (GameState.playerWon())
			{
				lblStatus.setText("CONGRATULATIONS!");
				JOptionPane.showMessageDialog(this, "Congratulations! You have reached the exit safe and sound :)", "Player Wins", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				lblStatus.setText("GAME OVER");
				JOptionPane.showMessageDialog(this, "You were killed by the dragon :(", "Game Over", JOptionPane.WARNING_MESSAGE);
			}

		}
	}

	private void formKeyPressed(KeyEvent evt)
	{
		if (GameState.gameOver())
		{
			return;
		}

		boolean validKey = false;
		boolean dartsSuccess = false;

		int pressedKey = evt.getKeyCode();
		if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyAction])
		{
			if (GameState.getPlayer().hasDarts())
			{
				attackModifier = true;
				validKey = true;
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You don't have enough darts :(", "Maze Run", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if (pressedKey == KeyEvent.VK_ESCAPE)
		{
			attackModifier = false;
			validKey = true;
		}
		else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyUp])
		{
			if (attackModifier)
			{
				dartsSuccess = GameState.attackDarts(Direction.UP);
				attackModifier = false;
			}
			else
			{
				GameState.update(Direction.UP);
			}

			validKey = true;
		}
		else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyDown])
		{
			if (attackModifier)
			{
				dartsSuccess = GameState.attackDarts(Direction.DOWN);
				attackModifier = false;
			}
			else
			{
				GameState.update(Direction.DOWN);
			}

			validKey = true;
		}
		else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyLeft])
		{
			if (attackModifier)
			{
				dartsSuccess = GameState.attackDarts(Direction.LEFT);
				attackModifier = false;
			}
			else
			{
				GameState.update(Direction.LEFT);
			}

			validKey = true;
		}
		else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyRight])
		{
			if (attackModifier)
			{
				dartsSuccess = GameState.attackDarts(Direction.RIGHT);
				attackModifier = false;
			}
			else
			{
				GameState.update(Direction.RIGHT);
			}

			validKey = true;
		}

		if (dartsSuccess)
		{
			lblStatus.setText("Attack successful!");
		}

		if (validKey)
		{
			repaint();
			checkState();
		}
	}

	private void btnNewActionPerformed(ActionEvent evt)
	{
		if (!GameState.gameOver())
		{
			if (JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game?", "New Game", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
			{
				return;
			}
		}

		initializeGame();
	}

	private void loadLevel()
	{
		FileInputStream fin;
		ObjectInputStream oin;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run maze files (*.maze)", "maze"));

		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		try
		{
			fin = new FileInputStream(fileChooser.getSelectedFile());
			oin = new ObjectInputStream(fin);
			Maze newMaze = (Maze) oin.readObject();
			pnlPlayfield.initializeMaze(newMaze);
			GameState.initializeCustom(newMaze);
			GameState.setDragonMovement(GUIGlobals.dragonDifficulty);
			resumeGame();
			fin.close();
			oin.close();
		}
		catch (IOException | ClassNotFoundException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	private void loadState()
	{
		FileInputStream fin;
		ObjectInputStream oin;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run saved games (*.state)", "state"));

		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		try
		{
			fin = new FileInputStream(fileChooser.getSelectedFile());
			oin = new ObjectInputStream(fin);
			GameState.read(oin);
			pnlPlayfield.initializeMaze(GameState.getMaze());
			resumeGame();
			oin.close();
			fin.close();
		}
		catch (IOException | ClassNotFoundException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	private void saveState()
	{
		FileOutputStream fout;
		ObjectOutputStream oout;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run saved games (*.state)", "state"));

		if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		try
		{
			fout = new FileOutputStream(fileChooser.getSelectedFile() + ".state");
			oout = new ObjectOutputStream(fout);
			GameState.write(oout);
			oout.close();
			fout.close();
		}
		catch (IOException ex)
		{
			GUIGlobals.abort(ex, this);
		}
	}

	private void btnLoadStateActionPerformed(ActionEvent evt)
	{
		loadState();
	}

	private void btnSaveStateActionPerformed(ActionEvent evt)
	{
		saveState();
	}

	private void btnLoadLevelActionPerformed(ActionEvent evt)
	{
		loadLevel();
	}

	private void applyZoom(int zoom)
	{
		switch (zoom)
		{
		case -1: // 24x24
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | Font.BOLD, 9));
			pnlPlayfield.scaleSprites(24, 24);
			break;
		case 0: // 32x32
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | Font.BOLD, 12));
			pnlPlayfield.scaleSprites(32, 32);
			break;
		case 1: // 48x48
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | Font.BOLD, 14));
			pnlPlayfield.scaleSprites(48, 48);
			break;
		case 2: // 64x64
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | Font.BOLD, 16));
			pnlPlayfield.scaleSprites(64, 64);
			break;
		}

		GUIGlobals.interfaceZoom = zoom;
		repaint();
		pack();
	}

	private void mnZoom75ActionPerformed(ActionEvent evt)
	{
		applyZoom(-1);
	}

	private void mnZoom100ActionPerformed(ActionEvent evt)
	{
		applyZoom(0);
	}

	private void mnZoom150ActionPerformed(ActionEvent evt)
	{
		applyZoom(1);
	}

	private void mnZoom200ActionPerformed(ActionEvent evt)
	{
		applyZoom(2);
	}

	private void formKeyReleased(KeyEvent evt)
	{
		repaint();
	}

	private void formWindowClosing(WindowEvent evt)
	{
		confirmExit();
	}

	private JMenuItem btnAbout;
	private JMenuItem btnControls;
	private JMenuItem btnExit;
	private JMenuItem btnLoadLevel;
	private JMenuItem btnLoadState;
	private JMenuItem btnNew;
	private JMenuItem btnPreferences;
	private JMenuItem btnRestart;
	private JMenuItem btnSaveState;
	private ButtonGroup buttonGroup1;
	private JPopupMenu.Separator jSeparator1;
	private JPopupMenu.Separator jSeparator2;
	private JPopupMenu.Separator jSeparator3;
	private JLabel lblStatus;
	private JMenuBar mbDefault;
	private JMenu mnGame;
	private JMenu mnHelp;
	private JMenu mnOptions;
	private JMenu mnZoom;
	private JMenuItem mnZoom100;
	private JMenuItem mnZoom150;
	private JMenuItem mnZoom200;
	private JMenuItem mnZoom75;
	private AreaDesenho pnlPlayfield;
}