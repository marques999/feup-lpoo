package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.text.*;

public class GUINewMaze extends JDialog
{
	private static final long serialVersionUID = 1470018550773975078L;
	
	private int mazeWidth;
	private int mazeHeight;

	public GUINewMaze(Frame parent)
	{
		super(parent, true);

		if (mazeWidth == 0)
		{
			mazeWidth = 11;
		}

		if (mazeHeight == 0)
		{
			mazeHeight = 11;
		}

		initComponents();
	}

	private void initComponents()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jFormattedTextField1 = new JFormattedTextField();
		jFormattedTextField2 = new JFormattedTextField();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("New Maze");
		setResizable(false);

		jLabel1.setText("Enter maze size:");
		jLabel2.setText("x");
		jButton1.setText("Create maze");
		jButton1.addActionListener(this::jButton1ActionPerformed);
		jButton2.setText("Cancel");
		jButton2.addActionListener(this::jButton2ActionPerformed);
		jFormattedTextField1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
		jFormattedTextField1.setText(Integer.toString(GUIGlobals.editorWidth));
		jFormattedTextField2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
		jFormattedTextField2.setText(Integer.toString(GUIGlobals.editorHeight));

		GroupLayout layout = new GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(jLabel1)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(jFormattedTextField1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE).addGap(2, 2, 2)
				.addComponent(jFormattedTextField2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jButton1)
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jButton2)
				.addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(jLabel1)
				.addComponent(jLabel2)
				.addComponent(jFormattedTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(jFormattedTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(jButton2)
				.addComponent(jButton1))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		getContentPane().setLayout(layout);
		getRootPane().setDefaultButton(jButton1);
		pack();
		setLocationRelativeTo(getParent());
	}

	private void jButton2ActionPerformed(ActionEvent evt)
	{
		this.dispose();
	}

	private void jButton1ActionPerformed(ActionEvent evt)
	{
		int newWidth = Integer.parseInt(jFormattedTextField1.getText());
		int newHeight = Integer.parseInt(jFormattedTextField2.getText());

		if (newWidth >= 7 && newHeight >= 7)
		{
			if (newWidth % 2 != 0 && newHeight % 2 != 0)
			{
				((GUIMazeEditor) getParent()).newMaze(newWidth, newHeight);
				GUIGlobals.editorWidth = newWidth;
				GUIGlobals.editorHeight = newHeight;
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid dimensions: width and height must be odd!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Invalid dimensions: width and height must be greater than or equal to 7.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JButton jButton1;
	private JButton jButton2;
	private JFormattedTextField jFormattedTextField1;
	private JFormattedTextField jFormattedTextField2;
	private JLabel jLabel1;
	private JLabel jLabel2;
}