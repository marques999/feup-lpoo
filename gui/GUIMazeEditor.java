package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import lpoo.logic.Maze;

public class GUIMazeEditor extends JFrame
{
	private static final long serialVersionUID = -8875055031584070730L;
	
	private File buffer;

	public GUIMazeEditor()
	{
		initComponents();
		updateStats();
	}

	public void newMaze(int w, int h)
	{
		areaEdicao1 = new AreaEdicao(w, h);
		pnlEditor.setViewportView(areaEdicao1);
		buttonGroup1.clearSelection();
	}

	private void updateStats()
	{
		lblNumWidth.setText(Integer.toString(areaEdicao1.mazeWidth));
		lblNumHeight.setText(Integer.toString(areaEdicao1.mazeHeight));
		lblNumDragons.setText(Integer.toString(areaEdicao1.numDragons));
		lblNumDarts.setText(Integer.toString(areaEdicao1.numDarts));
		lblNumDoors.setText(Integer.toString(areaEdicao1.numDoors));
		lblNumPlayers.setText(Integer.toString(areaEdicao1.numPlayers));
		lblNumSwords.setText(Integer.toString(areaEdicao1.numSwords));
		lblNumShields.setText(Integer.toString(areaEdicao1.numShields));

		String difficultyText = "None";

		switch (areaEdicao1.getDifficulty())
		{
		case 1:
			difficultyText = "Easy";
			break;
		case 2:
			difficultyText = "Medium";
			break;
		case 3:
			difficultyText = "Hard";
			break;
		case 4:
			difficultyText = "Nightmare";
			break;
		}

		lblDifficultyText.setText(difficultyText);
		pnlStats.repaint();
	}

	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		buttonGroup1 = new ButtonGroup();
		tbDefault = new JToolBar();
		btnWall = new JToggleButton();
		btnPlayer = new JToggleButton();
		btnDragon = new JToggleButton();
		btnDoor = new JToggleButton();
		btnDart = new JToggleButton();
		btnShield = new JToggleButton();
		btnSword = new JToggleButton();
		jSeparator4 = new JToolBar.Separator();
		btnErase = new JToggleButton();
		pnlEditor = new JScrollPane();
		areaEdicao1 = new AreaEdicao();
		pnlStats = new JPanel();
		lblDifficulty = new JLabel();
		lblWidth = new JLabel();
		lblPlayers = new JLabel();
		lblNumPlayers = new JLabel();
		lblNumWidth = new JLabel();
		lblDimensions = new JLabel();
		lblHeight = new JLabel();
		lblDragons = new JLabel();
		lblDarts = new JLabel();
		lblNumDragons = new JLabel();
		lblNumSwords = new JLabel();
		lblNumHeight = new JLabel();
		lblDifficultyText = new JLabel();
		lblNumDarts = new JLabel();
		lblNumShields = new JLabel();
		lblSwords = new JLabel();
		lblEntities = new JLabel();
		lblShields = new JLabel();
		lblDoors = new JLabel();
		lblNumDoors = new JLabel();
		mbDefault = new JMenuBar();
		mnFile = new JMenu();
		btnNew = new JMenuItem();
		jSeparator3 = new JPopupMenu.Separator();
		btnLoad = new JMenuItem();
		btnSave = new JMenuItem();
		btnSaveAs = new JMenuItem();
		jSeparator2 = new JPopupMenu.Separator();
		btnExit = new JMenuItem();
		mnEdit = new JMenu();
		btnUndo = new JMenuItem();
		btnRedo = new JMenuItem();
		jSeparator1 = new JPopupMenu.Separator();
		btnClear = new JMenuItem();
		btnRandomize = new JMenuItem();
		jSeparator6 = new JPopupMenu.Separator();
		btnValidate = new JMenuItem();
		mnHelp = new JMenu();
		btnAbout = new JMenuItem();

		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lpoo/res/editor-128x128.png")));
		setTitle("Maze Editor - Untitled");

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent evt)
			{
				formWindowClosing(evt);
			}
		});

		tbDefault.setRollover(true);
		btnWall.setBackground(new Color(255, 150, 100));
		buttonGroup1.add(btnWall);
		btnWall.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/wall-32x32.png")));
		btnWall.setText("Wall");
		btnWall.setFocusable(false);
		btnWall.setHorizontalAlignment(SwingConstants.TRAILING);
		btnWall.setHorizontalTextPosition(SwingConstants.CENTER);
		btnWall.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnWall.addActionListener(this::btnWallActionPerformed);
		tbDefault.add(btnWall);
		buttonGroup1.add(btnPlayer);
		btnPlayer.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/hero-32x32.png")));
		btnPlayer.setText("Player");
		btnPlayer.setFocusable(false);
		btnPlayer.setHorizontalAlignment(SwingConstants.TRAILING);
		btnPlayer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPlayer.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPlayer.addActionListener(this::btnPlayerActionPerformed);
		tbDefault.add(btnPlayer);
		buttonGroup1.add(btnDragon);
		btnDragon.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/dragon-32x32.png")));
		btnDragon.setText("Dragon");
		btnDragon.setFocusable(false);
		btnDragon.setHorizontalAlignment(SwingConstants.TRAILING);
		btnDragon.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDragon.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDragon.addActionListener(this::btnDragonActionPerformed);
		tbDefault.add(btnDragon);
		buttonGroup1.add(btnDoor);
		btnDoor.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/door1-32x32.png")));
		btnDoor.setText("Door");
		btnDoor.setFocusable(false);
		btnDoor.setHorizontalAlignment(SwingConstants.TRAILING);
		btnDoor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDoor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDoor.addActionListener(this::btnDoorActionPerformed);
		tbDefault.add(btnDoor);
		buttonGroup1.add(btnDart);
		btnDart.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/dart-32x32.png")));
		btnDart.setText("Dart");
		btnDart.setFocusable(false);
		btnDart.setHorizontalAlignment(SwingConstants.TRAILING);
		btnDart.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDart.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDart.addActionListener(this::btnDartActionPerformed);
		tbDefault.add(btnDart);
		buttonGroup1.add(btnShield);
		btnShield.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/shield-32x32.png")));
		btnShield.setText("Shield");
		btnShield.setFocusable(false);
		btnShield.setHorizontalAlignment(SwingConstants.TRAILING);
		btnShield.setHorizontalTextPosition(SwingConstants.CENTER);
		btnShield.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnShield.addActionListener(this::btnShieldActionPerformed);
		tbDefault.add(btnShield);
		buttonGroup1.add(btnSword);
		btnSword.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/sword-32x32.png")));
		btnSword.setText("Sword");
		btnSword.setFocusable(false);
		btnSword.setHorizontalAlignment(SwingConstants.TRAILING);
		btnSword.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSword.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSword.addActionListener(this::btnSwordActionPerformed);
		tbDefault.add(btnSword);
		tbDefault.add(jSeparator4);
		buttonGroup1.add(btnErase);
		btnErase.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/eraser-32x32.png")));
		btnErase.setText("Erase");
		btnErase.setFocusable(false);
		btnErase.setHorizontalAlignment(SwingConstants.TRAILING);
		btnErase.setHorizontalTextPosition(SwingConstants.CENTER);
		btnErase.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnErase.addActionListener(this::btnEraseActionPerformed);
		tbDefault.add(btnErase);

		getContentPane().add(tbDefault, BorderLayout.PAGE_START);

		pnlEditor.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent evt)
			{
				pnlEditorMousePressed(evt);
			}
		});

		areaEdicao1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		areaEdicao1.setMinimumSize(new Dimension(64, 64));
		areaEdicao1.setPreferredSize(new Dimension(640, 480));

		areaEdicao1.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent evt)
			{
				areaEdicao1MouseDragged(evt);
			}

			@Override
			public void mouseMoved(MouseEvent evt)
			{
				areaEdicao1MouseMoved(evt);
			}
		});

		areaEdicao1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent evt)
			{
				areaEdicao1MousePressed(evt);
			}

			@Override
			public void mouseReleased(MouseEvent evt)
			{
				areaEdicao1MouseReleased(evt);
			}
		});

		GroupLayout areaEdicao1Layout = new GroupLayout(areaEdicao1);

		areaEdicao1.setLayout(areaEdicao1Layout);

		areaEdicao1Layout.setHorizontalGroup(areaEdicao1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 638, Short.MAX_VALUE));
		areaEdicao1Layout.setVerticalGroup(areaEdicao1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 544, Short.MAX_VALUE));

		pnlEditor.setViewportView(areaEdicao1);
                pnlEditor.getVerticalScrollBar().setUnitIncrement(24);
		pnlEditor.getHorizontalScrollBar().setUnitIncrement(24);
                
		getContentPane().add(pnlEditor, BorderLayout.CENTER);

		pnlStats.setDoubleBuffered(false);
		pnlStats.setFocusable(false);
		pnlStats.setRequestFocusEnabled(false);
		pnlStats.setVerifyInputWhenFocusTarget(false);
		pnlStats.setLayout(new GridBagLayout());
		lblDifficulty.setFont(lblDifficulty.getFont().deriveFont(lblDifficulty.getFont().getStyle() | Font.BOLD));
		lblDifficulty.setText("Difficulty");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 10;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(10, 12, 10, 0);

		pnlStats.add(lblDifficulty, gridBagConstraints);
		lblWidth.setText("Width");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblWidth, gridBagConstraints);
		lblPlayers.setText("Darts");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblPlayers, gridBagConstraints);
		lblNumPlayers.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumPlayers, gridBagConstraints);
		lblNumWidth.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumWidth, gridBagConstraints);
		lblDimensions.setFont(lblDimensions.getFont().deriveFont(lblDimensions.getFont().getStyle() | Font.BOLD));
		lblDimensions.setText("Dimensions");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(0, 12, 10, 32);

		pnlStats.add(lblDimensions, gridBagConstraints);
		lblHeight.setText("Height");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblHeight, gridBagConstraints);
		lblDragons.setText("Swords");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblDragons, gridBagConstraints);
		lblDarts.setText("Players");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblDarts, gridBagConstraints);
		lblNumDragons.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumDragons, gridBagConstraints);
		lblNumSwords.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumSwords, gridBagConstraints);
		lblNumHeight.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumHeight, gridBagConstraints);
		lblDifficultyText.setText("Easy");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 11;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);
                
		pnlStats.add(lblDifficultyText, gridBagConstraints);
		lblNumDarts.setText("0");
                
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 7;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);
                
		pnlStats.add(lblNumDarts, gridBagConstraints);
		lblNumShields.setText("0");
                
		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumShields, gridBagConstraints);
		lblSwords.setText("Dragons");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblSwords, gridBagConstraints);
		lblEntities.setFont(lblEntities.getFont().deriveFont(lblEntities.getFont().getStyle() | Font.BOLD));
		lblEntities.setText("Entities");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(10, 10, 10, 0);

		pnlStats.add(lblEntities, gridBagConstraints);
		lblShields.setText("Shields");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 9;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblShields, gridBagConstraints);
		lblDoors.setText("Doors");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(0, 12, 0, 0);

		pnlStats.add(lblDoors, gridBagConstraints);
		lblNumDoors.setText("0");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.anchor = GridBagConstraints.LINE_END;
		gridBagConstraints.insets = new Insets(0, 0, 0, 12);

		pnlStats.add(lblNumDoors, gridBagConstraints);

		getContentPane().add(pnlStats, BorderLayout.LINE_END);

		mnFile.setText("File");
		btnNew.setText("New");
		btnNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		btnNew.addActionListener(this::btnNewActionPerformed);
		mnFile.add(btnNew);
		mnFile.add(jSeparator3);
		btnLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		btnLoad.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/folder.png")));
		btnLoad.setText("Load");
		btnLoad.addActionListener(this::btnLoadActionPerformed);
		mnFile.add(btnLoad);
		btnSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		btnSave.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/disk.png")));
		btnSave.setText("Save");
		btnSave.addActionListener(this::btnSaveActionPerformed);
		mnFile.add(btnSave);
		btnSaveAs.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/disk_multiple.png")));
		btnSaveAs.setText("Save As...");
		btnSaveAs.addActionListener(this::btnSaveAsActionPerformed);
		mnFile.add(btnSaveAs);
		mnFile.add(jSeparator2);
		btnExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		btnExit.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/door_open.png")));
		btnExit.setText("Exit");
		btnExit.addActionListener(this::btnExitActionPerformed);
		mnFile.add(btnExit);
		mbDefault.add(mnFile);
		mnEdit.setText("Edit");
		btnUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		btnUndo.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/arrow_undo.png")));
		btnUndo.setText("Undo");
		btnUndo.addActionListener(this::btnUndoActionPerformed);
		mnEdit.add(btnUndo);
		btnRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		btnRedo.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/arrow_redo.png")));
		btnRedo.setText("Redo");
		btnRedo.addActionListener(this::btnRedoActionPerformed);
		mnEdit.add(btnRedo);
		mnEdit.add(jSeparator1);
		btnClear.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		btnClear.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/bomb.png")));
		btnClear.setText("Clear");
		btnClear.addActionListener(this::btnClearActionPerformed);
		mnEdit.add(btnClear);
		btnRandomize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		btnRandomize.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/hourglass.png")));
		btnRandomize.setText("Randomize...");
		btnRandomize.addActionListener(this::btnRandomizeActionPerformed);
		mnEdit.add(btnRandomize);
		mnEdit.add(jSeparator6);
		btnValidate.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/tick.png")));
		btnValidate.setText("Validate");
		btnValidate.addActionListener(this::btnValidateActionPerformed);
		mnEdit.add(btnValidate);
		mbDefault.add(mnEdit);
		mnHelp.setText("Help");
		btnAbout.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/help.png")));
		btnAbout.setText("About");
		btnAbout.addActionListener(this::btnAboutActionPerformed);
		mnHelp.add(btnAbout);
		mbDefault.add(mnHelp);

		setJMenuBar(mbDefault);
		pack();
	}

	private void areaEdicao1MousePressed(MouseEvent evt)
	{
		updateStats();
	}

	private void btnPlayerActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('h');
	}

	private void btnDragonActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('D');
	}

	private void btnShieldActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('V');
	}

	private void btnDartActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('*');
	}

	private void btnWallActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('X');
	}

	private void btnSwordActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('E');
	}

	private void btnEraseActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol(' ');
	}

	private void btnUndoActionPerformed(ActionEvent evt)
	{
		areaEdicao1.undo();
	}

	private void btnRedoActionPerformed(ActionEvent evt)
	{
		areaEdicao1.redo();
	}

	private void btnRandomizeActionPerformed(ActionEvent evt)
	{
		areaEdicao1.erase();
		areaEdicao1.generateMaze();
		updateStats();
	}

	private void btnExitActionPerformed(ActionEvent evt)
	{
		confirmExit();
	}

	private boolean loadFile()
	{
                FileInputStream fin;
                ObjectInputStream oin;
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION)
		{
			return false;
		}

		buffer = fileChooser.getSelectedFile();

		if (buffer == null)
		{
			return false;
		}

		try
		{
			fin = new FileInputStream(buffer);
			oin = new ObjectInputStream(fin);
			setTitle("Maze Builder - " + buffer.getAbsolutePath());
			areaEdicao1.initializeMaze((Maze) oin.readObject());
			areaEdicao1.repaint();
			updateStats();
			fin.close();
			oin.close();
		}
		catch (IOException | ClassNotFoundException ex)
		{
                        GUIGlobals.abort(ex, this);
		}

		return true;
	}

	private boolean saveFile(boolean overwrite)
	{
		FileOutputStream fout;
		ObjectOutputStream oout;

		if (buffer == null || overwrite)
		{
			JFileChooser fileChooser = new JFileChooser();

			if (fileChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION)
			{
				return false;
			}

			buffer = fileChooser.getSelectedFile();
			setTitle("Maze Builder - " + buffer.getAbsolutePath());
		}

		try
		{
			fout = new FileOutputStream(buffer);
			oout = new ObjectOutputStream(fout);
			areaEdicao1.writeMaze(oout);
			fout.close();
			oout.close();
		}
		catch (IOException ex)
		{
                        GUIGlobals.abort(ex, this);
		}

		return true;
	}

	private void btnLoadActionPerformed(ActionEvent evt)
	{
		loadFile();
	}

	private void btnSaveActionPerformed(ActionEvent evt)
	{
		saveFile(false);
	}

	private void btnSaveAsActionPerformed(ActionEvent evt)
	{
		saveFile(true);
	}

	private void btnNewActionPerformed(ActionEvent evt)
	{
		GUINewMaze g_msize;
		g_msize = new GUINewMaze(this);
		g_msize.setVisible(true);
                newMaze(GUIGlobals.editorWidth, GUIGlobals.editorHeight);
		setTitle("Maze Editor - Untitled");
		buffer = null;
		updateStats();
	}

	private void btnClearActionPerformed(ActionEvent evt)
	{
		areaEdicao1.erase();
		updateStats();
	}

	private void btnAboutActionPerformed(ActionEvent evt)
	{
		GUIAbout guiAbout = new GUIAbout(this);
		guiAbout.setVisible(true);
	}

	private void btnDoorActionPerformed(ActionEvent evt)
	{
		areaEdicao1.setSymbol('S');
	}

	private void btnValidateActionPerformed(ActionEvent evt)
	{
		areaEdicao1.validateMaze();
	}

	private void pnlEditorMousePressed(MouseEvent evt)
	{

	}

	private void areaEdicao1MouseDragged(MouseEvent evt)
	{

	}

	private void areaEdicao1MouseReleased(MouseEvent evt)
	{

	}

	private void areaEdicao1MouseMoved(MouseEvent evt)
	{

	}

	private void confirmExit()
	{
		int r = JOptionPane.showConfirmDialog(this, "Do you want to save your changes before exiting?", "Exit", JOptionPane.YES_NO_OPTION);

		if (r == JOptionPane.YES_OPTION)
		{
			if (!saveFile(false))
			{
				return;
			}
		}

		System.exit(0);
	}

	private void formWindowClosing(WindowEvent evt)
	{
		confirmExit();
	}

	private AreaEdicao areaEdicao1;
	private JMenuItem btnAbout;
	private JMenuItem btnClear;
	private JToggleButton btnDart;
	private JToggleButton btnDoor;
	private JToggleButton btnDragon;
	private JToggleButton btnErase;
	private JMenuItem btnExit;
	private JMenuItem btnLoad;
	private JMenuItem btnNew;
	private JToggleButton btnPlayer;
	private JMenuItem btnRandomize;
	private JMenuItem btnRedo;
	private JMenuItem btnSave;
	private JMenuItem btnSaveAs;
	private JToggleButton btnShield;
	private JToggleButton btnSword;
	private JMenuItem btnUndo;
	private JMenuItem btnValidate;
	private JToggleButton btnWall;
	private ButtonGroup buttonGroup1;
	private JPopupMenu.Separator jSeparator1;
	private JPopupMenu.Separator jSeparator2;
	private JPopupMenu.Separator jSeparator3;
	private JToolBar.Separator jSeparator4;
	private JPopupMenu.Separator jSeparator6;
	private JLabel lblDarts;
	private JLabel lblDifficulty;
	private JLabel lblDifficultyText;
	private JLabel lblDimensions;
	private JLabel lblDoors;
	private JLabel lblDragons;
	private JLabel lblEntities;
	private JLabel lblHeight;
	private JLabel lblNumDarts;
	private JLabel lblNumDoors;
	private JLabel lblNumDragons;
	private JLabel lblNumHeight;
	private JLabel lblNumPlayers;
	private JLabel lblNumShields;
	private JLabel lblNumSwords;
	private JLabel lblNumWidth;
	private JLabel lblPlayers;
	private JLabel lblShields;
	private JLabel lblSwords;
	private JLabel lblWidth;
	private JMenuBar mbDefault;
	private JMenu mnEdit;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JScrollPane pnlEditor;
	private JPanel pnlStats;
	private JToolBar tbDefault;
}