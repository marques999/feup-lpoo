package lpoo.gui;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIAbout extends javax.swing.JFrame 
{
    public GUIAbout() 
    {
        initComponents();
    }

    private void initComponents() 
    {
        java.awt.GridBagConstraints gridBagConstraints;

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
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("About");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/FireDragon_icon.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(16, 21, 16, 21);
        getContentPane().add(lblIcon, gridBagConstraints);

        pnlText.setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(lblTitle.getFont().deriveFont(lblTitle.getFont().getStyle() | java.awt.Font.BOLD, lblTitle.getFont().getSize()+3));
        lblTitle.setText("Maze Run");
        pnlText.add(lblTitle, new java.awt.GridBagConstraints());

        lblVersion.setText("Version 0.01");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        pnlText.add(lblVersion, gridBagConstraints);

        lblAuthors.setFont(lblAuthors.getFont().deriveFont(lblAuthors.getFont().getStyle() | java.awt.Font.BOLD));
        lblAuthors.setText("Authors:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlText.add(lblAuthors, gridBagConstraints);

        lblDM.setText("Diogo Marques");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 0);
        pnlText.add(lblDM, gridBagConstraints);

        lblPM.setText("Pedro Melo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 4, 0);
        pnlText.add(lblPM, gridBagConstraints);

        lblEmailDM.setText("up201305642@fe.up.pt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        pnlText.add(lblEmailDM, gridBagConstraints);

        lblEmailPM.setText("up201305618@fe.up.pt");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        pnlText.add(lblEmailPM, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(pnlText, gridBagConstraints);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        pnlButton.add(btnOK);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 8, 8);
        getContentPane().add(pnlButton, gridBagConstraints);

        setSize(new java.awt.Dimension(316, 408));
        setLocationRelativeTo(null);
    }

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) 
    {
        setVisible(false);
        dispose();
    }

    public static void main(String args[]) 
    {
        try 
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(GUIAbout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new GUIAbout().setVisible(true);
        });
    }

    private JButton btnOK;
    private JLabel lblAuthors;
    private JLabel lblDM;
    private javax.swing.JLabel lblEmailDM;
    private javax.swing.JLabel lblEmailPM;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPM;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlText;
}