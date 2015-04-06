package lpoo.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIAbout extends JDialog
{
	private static final long serialVersionUID = -2510033787356166251L;

	public GUIAbout(Frame parent)
	{
		super(parent, true);

		initComponents();
	}

	private void initComponents()
	{
		GridBagConstraints gridBagConstraints;

		lblIcon = new JLabel();
		pnlText = new JPanel();
		lblTitle = new JLabel();
		lblVersion = new JLabel();
		lblAuthors = new JLabel();
		lblDM = new JLabel();
		lblPM = new JLabel();
		lblEmailDM = new JLabel();
		lblEmailPM = new JLabel();
		pnlButton = new JPanel();
		btnOK = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("About");
		setResizable(false);
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(new GridBagLayout());

		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(new ImageIcon(getClass().getResource("/lpoo/res/FireDragon_icon.png")));

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(16, 21, 16, 21);
		getContentPane().add(lblIcon, gridBagConstraints);

		pnlText.setLayout(new GridBagLayout());
		lblTitle.setFont(lblTitle.getFont().deriveFont(lblTitle.getFont().getStyle() | Font.BOLD, lblTitle.getFont().getSize() + 3));
		lblTitle.setText("WOW !! 7/4 IS BIRTHDAY !!");
		pnlText.add(lblTitle, new GridBagConstraints());
		lblVersion.setText("CONGURATURATIONS !! PLEASE GOOD JOB.");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.insets = new Insets(10, 0, 10, 0);

		pnlText.add(lblVersion, gridBagConstraints);
		lblAuthors.setFont(lblAuthors.getFont().deriveFont(lblAuthors.getFont().getStyle() | Font.BOLD));
		lblAuthors.setText("Authors:");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
		gridBagConstraints.insets = new Insets(8, 0, 8, 0);

		pnlText.add(lblAuthors, gridBagConstraints);
		lblDM.setText("Diogo Marques");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new Insets(4, 0, 4, 0);

		pnlText.add(lblDM, gridBagConstraints);
		lblPM.setText("Pedro Melo");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.insets = new Insets(10, 0, 4, 0);

		pnlText.add(lblPM, gridBagConstraints);
		lblEmailDM.setText("up201305642@fe.up.pt");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;

		pnlText.add(lblEmailDM, gridBagConstraints);
		lblEmailPM.setText("up201305618@fe.up.pt");

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;

		pnlText.add(lblEmailPM, gridBagConstraints);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(pnlText, gridBagConstraints);

		btnOK.setText("OK");
		btnOK.addActionListener(this::btnOKActionPerformed);
		pnlButton.add(btnOK);

		gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(pnlButton, gridBagConstraints);
		setSize(new Dimension(316, 408));
		setLocationRelativeTo(getParent());
	}

	private void btnOKActionPerformed(ActionEvent evt)
	{
		setVisible(false);
		dispose();
	}

	private JButton btnOK;
	private JLabel lblAuthors;
	private JLabel lblDM;
	private JLabel lblEmailDM;
	private JLabel lblEmailPM;
	private JLabel lblIcon;
	private JLabel lblPM;
	private JLabel lblTitle;
	private JLabel lblVersion;
	private JPanel pnlButton;
	private JPanel pnlText;
}