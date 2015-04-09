package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUINewMaze extends JDialog
{
	private static final long serialVersionUID = 1470018550773975078L;
	private static final String messageTitle = "Error";
	private static final String messageEvenSize = "Invalid dimensions: width and height must be odd!";
	private static final String messageInvalidSize = "Invalid dimensions: width and height must be greater than or equal to 7.";

	public GUINewMaze(Frame parent)
	{
		super(parent, true);
		initComponents();
	}

	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		jPanel1 = new JPanel();
		jButton1 = new JButton();
		jButton2 = new JButton();
		jPanel2 = new JPanel();
		jLabel1 = new JLabel();
		jFormattedTextField1 = new JFormattedTextField();
		jLabel2 = new JLabel();
		jFormattedTextField2 = new JFormattedTextField();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("New maze");
		setResizable(false);
		getContentPane().setLayout(new GridBagLayout());

		jPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 8));
		jButton1.setText("Create");
		jButton1.addActionListener(this::jButton1ActionPerformed);
		jPanel1.add(jButton1);
		jButton2.setText("Cancel");
		jButton2.addActionListener(this::jButton2ActionPerformed);
		jPanel1.add(jButton2);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(4, 8, 1, 8);
		getContentPane().add(jPanel1, gridBagConstraints);

		jLabel1.setText("Enter maze size: ");
		jPanel2.add(jLabel1);
		jFormattedTextField1.setText(Integer.toString(GUIGlobals.editorWidth));
		jFormattedTextField1.setPreferredSize(new Dimension(32, 20));
		jPanel2.add(jFormattedTextField1);
		jLabel2.setText("x");
		jPanel2.add(jLabel2);
		jFormattedTextField2.setText(Integer.toString(GUIGlobals.editorHeight));
		jFormattedTextField2.setPreferredSize(new Dimension(32, 20));
		jPanel2.add(jFormattedTextField2);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(4, 8, 0, 8);
		getContentPane().add(jPanel2, gridBagConstraints);
		setSize(new Dimension(210, 120));
		setLocationRelativeTo(getParent());
	}

	private void jButton1ActionPerformed(ActionEvent evt)
	{
		int newWidth = Integer.parseInt(jFormattedTextField1.getText());
		int newHeight = Integer.parseInt(jFormattedTextField2.getText());

		if (newWidth >= 7 && newHeight >= 7)
		{
			if (newWidth % 2 != 0 && newHeight % 2 != 0)
			{
				GUIGlobals.editorWidth = newWidth;
				GUIGlobals.editorHeight = newHeight;
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, messageEvenSize, messageTitle, JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(this, messageInvalidSize, messageTitle, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void jButton2ActionPerformed(ActionEvent evt)
	{
		dispose();
	}

	private JButton jButton1;
	private JButton jButton2;
	private JFormattedTextField jFormattedTextField1;
	private JFormattedTextField jFormattedTextField2;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel1;
	private JPanel jPanel2;
}