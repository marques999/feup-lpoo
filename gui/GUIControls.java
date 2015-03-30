package lpoo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIControls extends JDialog
{
	public GUIControls(Frame parent)
	{
		super(parent, true);

		initComponents();
	}

	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		jPanel1 = new JPanel();
		btnUp = new JButton();
		btnDown = new JButton();
		btnLeft = new JButton();
		btnAction = new JButton();
		lblUp = new JLabel();
		lblDown = new JLabel();
		lblAction = new JLabel();
		lblLeft = new JLabel();
		lblRight = new JLabel();
		btnRight = new JButton();
		jPanel2 = new JPanel();
		btnApply = new JButton();
		btnCancel = new JButton();
		btnReset = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Controls");

		jPanel1.setLayout(new GridBagLayout());
		btnUp.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyUp]));
		btnUp.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				btnUpKeyPressed(evt);
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.anchor = GridBagConstraints.WEST;

		jPanel1.add(btnUp, gridBagConstraints);
		btnDown.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyDown]));
		btnDown.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				btnDownKeyPressed(evt);
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		jPanel1.add(btnDown, gridBagConstraints);
		btnLeft.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyLeft]));
		btnLeft.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				btnLeftKeyPressed(evt);
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		jPanel1.add(btnLeft, gridBagConstraints);
		btnAction.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyAction]));
		btnAction.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				btnActionKeyPressed(evt);
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		jPanel1.add(btnAction, gridBagConstraints);
		lblUp.setText("Up");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;

		jPanel1.add(lblUp, gridBagConstraints);
		lblDown.setText("Down");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(0, 32, 0, 32);

		jPanel1.add(lblDown, gridBagConstraints);
		lblAction.setText("Special");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;

		jPanel1.add(lblAction, gridBagConstraints);
		lblLeft.setText("Left");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new Insets(0, 32, 0, 32);

		jPanel1.add(lblLeft, gridBagConstraints);
		lblRight.setText("Right");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(0, 32, 0, 32);

		jPanel1.add(lblRight, gridBagConstraints);
		btnRight.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyRight]));
		btnRight.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent evt)
			{
				btnRightKeyPressed(evt);
			}
		});

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

		jPanel1.add(btnRight, gridBagConstraints);

		getContentPane().add(jPanel1, BorderLayout.CENTER);

		jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 24));
		btnApply.setText("Apply");
		btnApply.addActionListener(this::btnApplyActionPerformed);
		jPanel2.add(btnApply);
		btnReset.setText("Reset");
		btnReset.addActionListener(this::btnResetActionPerformed);
		jPanel2.add(btnReset);
		btnCancel.setText("Cancel");
		btnCancel.addActionListener(this::btnCancelActionPerformed);
		jPanel2.add(btnCancel);

		getContentPane().add(jPanel2, BorderLayout.PAGE_END);
		setResizable(false);
		setSize(new Dimension(244, 257));
		setLocationRelativeTo(getParent());
	}

	private boolean checkRepeated(int newKey)
	{
		for (int keyCode : defaultKeys)
		{
			if (newKey == keyCode)
			{
				return true;
			}
		}

		return false;
	}

	private void btnUpKeyPressed(KeyEvent evt)
	{
		if (!checkRepeated(evt.getKeyCode()))
		{
			defaultKeys[GUIGlobals.keyUp] = evt.getKeyCode();
			btnUp.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyUp]));
		}
	}

	private void btnDownKeyPressed(KeyEvent evt)
	{
		if (!checkRepeated(evt.getKeyCode()))
		{
			defaultKeys[GUIGlobals.keyDown] = evt.getKeyCode();
			btnDown.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyDown]));
		}
	}

	private void btnLeftKeyPressed(KeyEvent evt)
	{
		if (!checkRepeated(evt.getKeyCode()))
		{
			defaultKeys[GUIGlobals.keyLeft] = evt.getKeyCode();
			btnLeft.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyLeft]));
		}
	}

	private void btnRightKeyPressed(KeyEvent evt)
	{
		if (!checkRepeated(evt.getKeyCode()))
		{
			defaultKeys[GUIGlobals.keyRight] = evt.getKeyCode();
			btnRight.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyRight]));
		}
	}

	private void btnActionKeyPressed(KeyEvent evt)
	{
		if (!checkRepeated(evt.getKeyCode()))
		{
			defaultKeys[GUIGlobals.keyAction] = evt.getKeyCode();
			btnAction.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyAction]));
		}
	}

	private void btnApplyActionPerformed(ActionEvent evt)
	{
		GUIGlobals.currentKeys = defaultKeys;
		setVisible(false);
		dispose();
	}

	private void btnCancelActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		dispose();
	}

	private void btnResetActionPerformed(ActionEvent evt)
	{
		defaultKeys = GUIGlobals.defaultKeys.clone();
		btnUp.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyUp]));
		btnDown.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyDown]));
		btnLeft.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyLeft]));
		btnRight.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyRight]));
		btnAction.setText(KeyEvent.getKeyText(defaultKeys[GUIGlobals.keyAction]));
	}

	private int[] defaultKeys = GUIGlobals.currentKeys.clone();

	private JButton btnAction;
	private JButton btnApply;
	private JButton btnCancel;
	private JButton btnDown;
	private JButton btnLeft;
	private JButton btnReset;
	private JButton btnRight;
	private JButton btnUp;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JLabel lblUp;
	private JLabel lblDown;
	private JLabel lblLeft;
	private JLabel lblRight;
	private JLabel lblAction;
}