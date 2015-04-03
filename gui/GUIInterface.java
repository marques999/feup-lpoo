package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import lpoo.logic.*;

public class GUIInterface extends JFrame
{
	private static final long serialVersionUID = 7110803925049276531L;
	
	private boolean attackModifier;
	private File customLevel;

	public GUIInterface()
	{
		try
		{
			initComponents();
		}
		catch (Exception ex)
		{

		}
                
		initializeGame();
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lpoo/res/FireDragon_icon.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		pnlPlayfield.setPreferredSize(pnlPlayfield.getWindowSize());
		pnlPlayfield.repaint();

		checkState();
		revalidate();
		pack();
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

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

		lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 13));
		lblStatus.setText("jLabel1");
		
		getContentPane().add(lblStatus, BorderLayout.PAGE_END);

		pnlPlayfield.setPreferredSize(new Dimension(640, 480));

		getContentPane().add(pnlPlayfield, BorderLayout.CENTER);

		mnGame.setText("Game");
		btnNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		btnNew.setText("New Game");
		btnNew.addActionListener(this::btnNewActionPerformed);
		mnGame.add(btnNew);
		btnLoadLevel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		btnLoadLevel.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/world.png")));
		btnLoadLevel.setText("Load...");
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
		mnZoom75.setText("75%");
		buttonGroup1.add(mnZoom75);
		mnZoom75.addActionListener(this::mnZoom75ActionPerformed);
		mnZoom.add(mnZoom75);
		mnZoom100.setText("100%");
		buttonGroup1.add(mnZoom100);
		mnZoom100.addActionListener(this::mnZoom100ActionPerformed);
		mnZoom.add(mnZoom100);
		mnZoom150.setText("150%");
		buttonGroup1.add(mnZoom150);
		mnZoom150.addActionListener(this::mnZoom150ActionPerformed);
		mnZoom.add(mnZoom150);
		mnZoom200.setText("200%");
		buttonGroup1.add(mnZoom200);
		mnZoom200.addActionListener(this::mnZoom200ActionPerformed);
		mnZoom.add(mnZoom200);
		mnOptions.add(mnZoom);
		mnOptions.add(jSeparator3);
		btnControls.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/joystick.png")));
		btnControls.setText("Controls");
		btnControls.addActionListener(this::btnControlsActionPerformed);
		mnOptions.add(btnControls);
		btnPreferences.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/wrench.png")));
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
		int r = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit without saving?", "Exit", JOptionPane.YES_NO_OPTION);

		if (r != JOptionPane.OK_OPTION)
		{
			return;
		}

		File buffer = new File("User Settings");
		FileOutputStream fout = null;
		ObjectOutputStream oout = null;

		try
		{
			fout = new FileOutputStream(buffer);
			oout = new ObjectOutputStream(fout);
			GUIGlobals.write(oout);
		}
		catch (IOException ex)
		{
			Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				oout.close();
				fout.close();
			}
			catch (IOException ex)
			{
				Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		System.exit(0);
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

	private void checkState()
	{
		if (attackModifier)
		{
			lblStatus.setText("Please select a direction (ESC to cancel):");
		}
		else
		{
			lblStatus.setText("OBJECTIVE: " + GameState.getObjective());
		}

		if (GameState.gameOver())
		{
			if (GameState.playerWon())
			{
				lblStatus.setText("CONGRATULATIONS!");
				JOptionPane.showMessageDialog(null, "Congratulations! You have reached the exit safe and sound :)", "Player Wins", JOptionPane.PLAIN_MESSAGE);
			}
			else
			{
				lblStatus.setText("GAME OVER");
				JOptionPane.showMessageDialog(null, "You were killed by the dragon :(", "Game Over", JOptionPane.WARNING_MESSAGE);
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
			int r = JOptionPane.showConfirmDialog(this, "Are you sure you want to start a new game?", "New Game", JOptionPane.YES_NO_OPTION);

			if (r == JOptionPane.YES_OPTION)
			{
				initializeGame();
			}
		}
	}

	private void loadLevel() throws IOException, ClassNotFoundException
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run maze files (*.maze)", "maze"));

		int showOpenDialog = fileChooser.showOpenDialog(this);

		FileInputStream fin;
		ObjectInputStream oin;

		if (showOpenDialog != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		final File buffer = fileChooser.getSelectedFile();

		if (buffer == null)
		{
			return;
		}

		fin = new FileInputStream(buffer);
		oin = new ObjectInputStream(fin);
		Maze newMaze = (Maze) oin.readObject();

		pnlPlayfield.initializeMaze(newMaze);
		GameState.initializeCustom(newMaze);
		GameState.setDragonMovement(GUIGlobals.dragonDifficulty);
		resumeGame();
		fin.close();
		oin.close();
	}

	private void loadState() throws IOException, ClassNotFoundException
	{
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run maze files (*.maze)", "maze"));
		int showOpenDialog = fileChooser.showOpenDialog(this);

		FileInputStream fin;
		ObjectInputStream oin;

		if (showOpenDialog != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		final File buffer = fileChooser.getSelectedFile();

		if (buffer == null)
		{
			return;
		}

		fin = new FileInputStream(buffer);
		oin = new ObjectInputStream(fin);

		GameState.read(oin);
		resumeGame();
		oin.close();
		fin.close();
	}

	private void saveState() throws IOException
	{
		FileOutputStream fout;
		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run saved games (*.state)", "state"));
		ObjectOutputStream oout;

		int showSaveDialog = fileChooser.showSaveDialog(this);

		if (showSaveDialog != JFileChooser.APPROVE_OPTION)
		{
			return;
		}

		final File buffer = fileChooser.getSelectedFile();

		fout = new FileOutputStream(buffer);
		oout = new ObjectOutputStream(fout);
		GameState.write(oout);
		oout.close();
		fout.close();
	}

	private void btnLoadStateActionPerformed(ActionEvent evt)
	{
		try
		{
			loadState();
		}
		catch (IOException | ClassNotFoundException ex)
		{
			Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void btnSaveStateActionPerformed(ActionEvent evt)
	{
		try
		{
			saveState();
		}
		catch (IOException ex)
		{
			Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void btnLoadLevelActionPerformed(ActionEvent evt)
	{
		try
		{
			loadLevel();
		}
		catch (IOException | ClassNotFoundException ex)
		{
			Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void applyZoom(int zoom)
	{
		switch (zoom)
		{
		case -1: // 24x24
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 10));
			pnlPlayfield.scaleSprites(24, 24);
			break;
		case 0: // 32x32
			pnlPlayfield.scaleSprites(32, 32);
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 13));
			break;
		case 1: // 48x48
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 15));
			pnlPlayfield.scaleSprites(48, 48);
			break;
		case 2: // 64x64
			lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 17));
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