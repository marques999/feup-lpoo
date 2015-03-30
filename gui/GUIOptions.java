package lpoo.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUIOptions extends JDialog
{
	private int userDifficulty = 1;
	private int numberDragons = 1;
	private int dragonMovement = 2;

	private final ImageIcon spriteHero;
	private final ImageIcon spriteDragon;

	public GUIOptions(Frame parent)
	{
		super(parent, true);
		spriteDragon = new ImageIcon(getClass().getResource("/lpoo/res/dragon-64x64.png"));
		spriteHero = new ImageIcon(getClass().getResource("/lpoo/res/Knight-concept-art.png"));
		initComponents();
		setLocationRelativeTo(getParent());
		loadDefaults();
	}

	private void loadDefaults()
	{
		userDifficulty = GUIGlobals.userDifficulty;

		switch (userDifficulty)
		{
		case -1:
			rbGameCustom.setSelected(true);
			numberDragons = GUIGlobals.numberDragons;
			break;
		case 1:
			rbGameBeginner.setSelected(true);
			numberDragons = 1;
			break;
		case 2:
			rbGameEasy.setSelected(true);
			numberDragons = 2;
			break;
		case 3:
			rbGameMedium.setSelected(true);
			numberDragons = 3;
			break;
		case 4:
			rbGameHard.setSelected(true);
			numberDragons = 6;
			break;
		case 5:
			rbGameNightmare.setSelected(true);
			numberDragons = 12;
			break;
		}

		spnWidth.setValue((Integer) GUIGlobals.mazeWidth);
		spnHeight.setValue((Integer) GUIGlobals.mazeHeight);
		cmbNumber.setSelectedIndex(GUIGlobals.numberDragons - 1);

		if (userDifficulty == -1)
		{
			spnWidth.setEnabled(true);
			spnHeight.setEnabled(true);
			cmbNumber.setEnabled(true);
		}

		dragonMovement = GUIGlobals.dragonDifficulty;
		cmbDifficulty.setSelectedIndex(dragonMovement - 1);
		visibleDragons(numberDragons);
	}

	private void visibleDragons(int d)
	{
		lblNumDragons.setText("Number of dragons: " + d);

		for (int i = 0; i < lblDragons.size(); i++)
		{
			lblDragons.get(i).setVisible(i < d);
		}
	}

	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		buttonGroup1 = new ButtonGroup();
		jSplitPane1 = new JSplitPane();
		pnlDragons = new JPanel();

		for (int i = 0; i < 12; i++)
		{
			lblDragons.add(new JLabel());
		}

		lblNumDragons = new JLabel();
		pnlSelect = new JPanel();
		lblMaze = new JLabel();
		pnlMaze = new JPanel();
		rbGameBeginner = new JRadioButton();
		rbGameEasy = new JRadioButton();
		rbGameMedium = new JRadioButton();
		rbGameHard = new JRadioButton();
		rbGameNightmare = new JRadioButton();
		pnlCustom = new JPanel();
		rbGameCustom = new JRadioButton();
		lblX = new JLabel();
		spnWidth = new javax.swing.JSpinner();
		spnHeight = new javax.swing.JSpinner();
		pnlButtons = new javax.swing.JPanel();
		btnStart = new JButton();
		btnCancel = new JButton();
		lblDifficulty = new JLabel();
		lblNumber = new JLabel();
		cmbNumber = new JComboBox();
		cmbDifficulty = new JComboBox();
		pnlHero = new JPanel();
		lblHero = new JLabel();

		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setTitle("Preferences");
		setAutoRequestFocus(false);
		setBackground(Color.WHITE);
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlDragons.setLayout(new GridLayout(4, 3));

		for (JLabel lblDragon : lblDragons)
		{
			lblDragon.setIcon(spriteDragon);
			pnlDragons.add(lblDragon);
		}

		jSplitPane1.setTopComponent(pnlDragons);
		lblNumDragons.setText("Number of dragons: 1");
		jSplitPane1.setRightComponent(lblNumDragons);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 16, 0, 16);
		getContentPane().add(jSplitPane1, gridBagConstraints);
		GridBagLayout pnlSelectLayout = new GridBagLayout();
		pnlSelectLayout.columnWidths = new int[] { 0, 5, 0 };
		pnlSelectLayout.rowHeights = new int[] { 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0 };
		pnlSelectLayout.rowWeights = new double[] { 0.0 };

		pnlSelect.setLayout(pnlSelectLayout);
		lblMaze.setFont(lblMaze.getFont().deriveFont(lblMaze.getFont().getStyle() | Font.BOLD));
		lblMaze.setText("Select maze difficulty:");
		lblMaze.setVerticalAlignment(SwingConstants.BOTTOM);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(8, 0, 8, 0);

		pnlSelect.add(lblMaze, gridBagConstraints);
		pnlMaze.setLayout(new GridLayout(6, 0, 0, 2));
		buttonGroup1.add(rbGameBeginner);
		rbGameBeginner.setText("Beginner (static maze, 11x11)");
		rbGameBeginner.addActionListener(this::rbGameBeginnerActionPerformed);
		pnlMaze.add(rbGameBeginner);
		buttonGroup1.add(rbGameEasy);
		rbGameEasy.setText("Easy (random maze, 11x11)");
		rbGameEasy.addActionListener(this::rbGameEasyActionPerformed);
		pnlMaze.add(rbGameEasy);
		buttonGroup1.add(rbGameMedium);
		rbGameMedium.setText("Medium (random maze, 15x15)");
		rbGameMedium.addActionListener(this::rbGameMediumActionPerformed);
		pnlMaze.add(rbGameMedium);
		buttonGroup1.add(rbGameHard);
		rbGameHard.setText("Hard (random maze, 23x23)");
		rbGameHard.addActionListener(this::rbGameHardActionPerformed);
		pnlMaze.add(rbGameHard);
		buttonGroup1.add(rbGameNightmare);
		rbGameNightmare.setText("Nightmare (random maze, 31x31)");
		rbGameNightmare.addActionListener(this::rbGameNightmareActionPerformed);
		pnlMaze.add(rbGameNightmare);
		pnlCustom.setLayout(new GridBagLayout());
		buttonGroup1.add(rbGameCustom);
		rbGameCustom.setText("Custom");
		rbGameCustom.addActionListener(this::rbGameCustomActionPerformed);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		gridBagConstraints.insets = new Insets(0, 0, 0, 16);

		pnlCustom.add(rbGameCustom, gridBagConstraints);
		lblX.setText("x");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(0, 4, 0, 4);

		pnlCustom.add(lblX, gridBagConstraints);
		spnWidth.setModel(new SpinnerNumberModel(7, 7, 31, 2));
		spnWidth.setEnabled(false);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0, 4, 0, 4);

		pnlCustom.add(spnWidth, gridBagConstraints);
		spnHeight.setModel(new SpinnerNumberModel(7, 7, 31, 2));
		spnHeight.setEnabled(false);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.insets = new Insets(0, 4, 0, 4);

		pnlCustom.add(spnHeight, gridBagConstraints);
		pnlMaze.add(pnlCustom);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;

		pnlSelect.add(pnlMaze, gridBagConstraints);
		btnStart.setText("Apply");
		btnStart.addActionListener(this::btnStartActionPerformed);
		pnlButtons.add(btnStart);
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(this::jButton2ActionPerformed);
		pnlButtons.add(btnCancel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 12;
		gridBagConstraints.ipady = 1;
		gridBagConstraints.insets = new java.awt.Insets(16, 0, 16, 0);

		pnlSelect.add(pnlButtons, gridBagConstraints);
		lblDifficulty.setFont(lblDifficulty.getFont().deriveFont(lblDifficulty.getFont().getStyle() | java.awt.Font.BOLD));
		lblDifficulty.setText("Select dragon difficulty:");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(8, 0, 8, 0);

		pnlSelect.add(lblDifficulty, gridBagConstraints);
		lblNumber.setFont(lblNumber.getFont().deriveFont(lblNumber.getFont().getStyle() | Font.BOLD));
		lblNumber.setText("Select number of dragons:");
		lblNumber.setHorizontalTextPosition(SwingConstants.RIGHT);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.anchor = GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new Insets(8, 0, 8, 0);

		pnlSelect.add(lblNumber, gridBagConstraints);
		cmbNumber.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		cmbNumber.setEnabled(false);
		cmbNumber.addActionListener(this::cmbNumberActionPerformed);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 10;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;

		pnlSelect.add(cmbNumber, gridBagConstraints);
		cmbDifficulty.setModel(new DefaultComboBoxModel(new String[] { "Beginner (static, random sleep)", "Easy (static, doesn't sleep)",
				"Medium (random, random sleep)", "Hard (random, doesn't sleep)" }));
		cmbDifficulty.addActionListener(this::cmbDifficultyActionPerformed);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		pnlSelect.add(cmbDifficulty, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.PAGE_START;
		gridBagConstraints.insets = new Insets(6, 8, 6, 8);
		getContentPane().add(pnlSelect, gridBagConstraints);

		lblHero.setIcon(spriteHero);
		pnlHero.add(lblHero);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
		getContentPane().add(pnlHero, gridBagConstraints);
		pack();
	}

	private void rbGameBeginnerActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(false);
		spnWidth.setEnabled(false);
		spnHeight.setEnabled(false);
		userDifficulty = 1;
		visibleDragons(1);
	}

	private void rbGameEasyActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(false);
		spnWidth.setEnabled(false);
		spnHeight.setEnabled(false);
		userDifficulty = 2;
		visibleDragons(2);
	}

	private void rbGameMediumActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(false);
		spnWidth.setEnabled(false);
		spnHeight.setEnabled(false);
		userDifficulty = 3;
		visibleDragons(3);
	}

	private void rbGameHardActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(false);
		spnWidth.setEnabled(false);
		spnHeight.setEnabled(false);
		userDifficulty = 4;
		visibleDragons(6);
	}

	private void rbGameNightmareActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(false);
		spnWidth.setEnabled(false);
		spnHeight.setEnabled(false);
		userDifficulty = 5;
		visibleDragons(12);
	}

	private void btnStartActionPerformed(java.awt.event.ActionEvent evt)
	{
		GUIGlobals.userDifficulty = userDifficulty;
		GUIGlobals.dragonDifficulty = dragonMovement;

		if (userDifficulty == -1)
		{
			GUIGlobals.numberDragons = numberDragons;
			GUIGlobals.mazeWidth = (Integer) spnWidth.getValue();
			GUIGlobals.mazeHeight = (Integer) spnHeight.getValue();
		}

		setVisible(false);
		dispose();
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)
	{
		setVisible(false);
		dispose();
	}

	private void cmbNumberActionPerformed(java.awt.event.ActionEvent evt)
	{
		numberDragons = cmbNumber.getSelectedIndex() + 1;
		visibleDragons(numberDragons);
	}

	private void rbGameCustomActionPerformed(java.awt.event.ActionEvent evt)
	{
		cmbNumber.setEnabled(true);
		spnWidth.setEnabled(true);
		spnHeight.setEnabled(true);
		userDifficulty = -1;
		numberDragons = cmbNumber.getSelectedIndex() + 1;
		visibleDragons(numberDragons);
	}

	private void cmbDifficultyActionPerformed(java.awt.event.ActionEvent evt)
	{
		dragonMovement = cmbDifficulty.getSelectedIndex() + 1;
	}

	private final ArrayList<JLabel> lblDragons = new ArrayList<>();
	private ButtonGroup buttonGroup1;
	private JButton btnStart;
	private JButton btnCancel;
	private JComboBox cmbDifficulty;
	private JComboBox cmbNumber;
	private JSplitPane jSplitPane1;
	private JLabel lblDifficulty;
	private JLabel lblHero;
	private JLabel lblMaze;
	private JLabel lblNumDragons;
	private JLabel lblNumber;
	private JLabel lblX;
	private JPanel pnlButtons;
	private JPanel pnlCustom;
	private JPanel pnlDragons;
	private JPanel pnlHero;
	private JPanel pnlMaze;
	private JPanel pnlSelect;
	private JRadioButton rbGameBeginner;
	private JRadioButton rbGameCustom;
	private JRadioButton rbGameEasy;
	private JRadioButton rbGameHard;
	private JRadioButton rbGameMedium;
	private JRadioButton rbGameNightmare;
	private JSpinner spnHeight;
	private JSpinner spnWidth;
}