package lpoo.gui;

import javax.swing.ImageIcon;
import javax.swing.*;
import lpoo.logic.GameState;

public class GUINewGame extends JDialog
{
    private int userDifficulty;
    private int dragonMovement;

    private final ImageIcon spDragon;
    private final ImageIcon spPlayer;

    public GUINewGame() 
    {
        this.spDragon = new ImageIcon(getClass().getResource("/lpoo/res/FireDragon_tiny.png"));
        this.spPlayer = new ImageIcon(getClass().getResource("/lpoo/res/Knight-concept-art.png"));

        initComponents();

        lblDragon4.setVisible(false);
        lblDragon5.setVisible(false);
        lblDragon6.setVisible(false);
        lblDragon7.setVisible(false);
        lblDragon8.setVisible(false);
        lblDragon9.setVisible(false);
        lblDragon10.setVisible(false);
        lblDragon11.setVisible(false);
        lblDragon12.setVisible(false);

        userDifficulty = 1;
        dragonMovement = 2;
    }

    private void initComponents() 
    {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pnlSelect = new javax.swing.JPanel();
        lblGame = new javax.swing.JLabel();
        pnlGame = new javax.swing.JPanel();
        rbGameBeginner = new javax.swing.JRadioButton();
        rbGameEasy = new javax.swing.JRadioButton();
        rbGameMedium = new javax.swing.JRadioButton();
        rbGameHard = new javax.swing.JRadioButton();
        rbGameHard1 = new javax.swing.JRadioButton();
        lblDragon = new javax.swing.JLabel();
        pnlDragon = new javax.swing.JPanel();
        rbDragonBeginner = new javax.swing.JRadioButton();
        rbDragonEasy = new javax.swing.JRadioButton();
        rbDragonMedium = new javax.swing.JRadioButton();
        rbDragonHard = new javax.swing.JRadioButton();
        pnlButtons = new javax.swing.JPanel();
        btnStart = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        pnlHero = new javax.swing.JPanel();
        lblHero = new javax.swing.JLabel();
        pnlSplit = new javax.swing.JSplitPane();
        pnlDragons = new javax.swing.JPanel();
        lblDragon1 = new javax.swing.JLabel();
        lblDragon2 = new javax.swing.JLabel();
        lblDragon3 = new javax.swing.JLabel();
        lblDragon4 = new javax.swing.JLabel();
        lblDragon5 = new javax.swing.JLabel();
        lblDragon6 = new javax.swing.JLabel();
        lblDragon7 = new javax.swing.JLabel();
        lblDragon8 = new javax.swing.JLabel();
        lblDragon9 = new javax.swing.JLabel();
        lblDragon10 = new javax.swing.JLabel();
        lblDragon11 = new javax.swing.JLabel();
        lblDragon12 = new javax.swing.JLabel();
        lblNumDragons = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Preferences");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0};
        jPanel3Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel3Layout.rowWeights = new double[] {0.0};
        pnlSelect.setLayout(jPanel3Layout);

        lblGame.setFont(lblGame.getFont().deriveFont(lblGame.getFont().getStyle() | java.awt.Font.BOLD));
        lblGame.setText("Select game difficulty:");
        lblGame.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlSelect.add(lblGame, gridBagConstraints);

        pnlGame.setLayout(new java.awt.GridLayout(5, 0));

        buttonGroup1.add(rbGameBeginner);
        rbGameBeginner.setSelected(true);
        rbGameBeginner.setText("Beginner (static maze, 11x11)");
        rbGameBeginner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbGameBeginnerActionPerformed(evt);
            }
        });
        pnlGame.add(rbGameBeginner);

        buttonGroup1.add(rbGameEasy);
        rbGameEasy.setText("Easy (random maze, 11x11)");
        rbGameEasy.addActionListener(this::rbGameEasyActionPerformed);
        pnlGame.add(rbGameEasy);

        buttonGroup1.add(rbGameMedium);
        rbGameMedium.setText("Medium (random maze, 15x15)");
        rbGameMedium.addActionListener(this::rbGameMediumActionPerformed);
        pnlGame.add(rbGameMedium);

        buttonGroup1.add(rbGameHard);
        rbGameHard.setText("Hard (random maze, 23x23)");
        rbGameHard.addActionListener(this::rbGameHardActionPerformed);
        pnlGame.add(rbGameHard);

        buttonGroup1.add(rbGameHard1);
        rbGameHard1.setText("Nightmare (random maze, 31x31)");
        rbGameHard1.addActionListener(this::rbGameHard1ActionPerformed);
        pnlGame.add(rbGameHard1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        pnlSelect.add(pnlGame, gridBagConstraints);

        lblDragon.setFont(lblDragon.getFont().deriveFont(lblDragon.getFont().getStyle() | java.awt.Font.BOLD));
        lblDragon.setText("Select dragon difficulty:");
        lblDragon.setAlignmentX(0.5F);
        lblDragon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(8, 0, 8, 0);
        pnlSelect.add(lblDragon, gridBagConstraints);

        pnlDragon.setLayout(new java.awt.GridLayout(4, 0));

        buttonGroup2.add(rbDragonBeginner);
        rbDragonBeginner.setText("Beginner (static, random sleep)");
        rbDragonBeginner.addActionListener(this::rbDragonBeginnerActionPerformed);
        pnlDragon.add(rbDragonBeginner);

        buttonGroup2.add(rbDragonEasy);
        rbDragonEasy.setSelected(true);
        rbDragonEasy.setText("Easy (static, doesn't sleep)");
        rbDragonEasy.addActionListener(this::rbDragonEasyActionPerformed);
        pnlDragon.add(rbDragonEasy);

        buttonGroup2.add(rbDragonMedium);
        rbDragonMedium.setText("Medium (random, random sleep)");
        rbDragonMedium.addActionListener(this::rbDragonMediumActionPerformed);
        pnlDragon.add(rbDragonMedium);

        buttonGroup2.add(rbDragonHard);
        rbDragonHard.setText("Hard (random, doesn't sleep)");
        rbDragonHard.addActionListener(this::rbDragonHardActionPerformed);
        pnlDragon.add(rbDragonHard);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        pnlSelect.add(pnlDragon, gridBagConstraints);

        btnStart.setText("Save");
        btnStart.addActionListener(this::btnStartActionPerformed);
        pnlButtons.add(btnStart);

        jButton2.setText("Exit");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        pnlButtons.add(jButton2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(16, 0, 16, 0);
        pnlSelect.add(pnlButtons, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        getContentPane().add(pnlSelect, gridBagConstraints);

        lblHero.setIcon(spPlayer);
        pnlHero.add(lblHero);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        getContentPane().add(pnlHero, gridBagConstraints);

        pnlSplit.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        pnlDragons.setLayout(new java.awt.GridLayout(4, 3));

        lblDragon1.setIcon(spDragon);
        pnlDragons.add(lblDragon1);

        lblDragon2.setIcon(spDragon);
        pnlDragons.add(lblDragon2);

        lblDragon3.setIcon(spDragon);
        pnlDragons.add(lblDragon3);

        lblDragon4.setIcon(spDragon);
        pnlDragons.add(lblDragon4);

        lblDragon5.setIcon(spDragon);
        pnlDragons.add(lblDragon5);

        lblDragon6.setIcon(spDragon);
        pnlDragons.add(lblDragon6);

        lblDragon7.setIcon(spDragon);
        pnlDragons.add(lblDragon7);

        lblDragon8.setIcon(spDragon);
        pnlDragons.add(lblDragon8);

        lblDragon9.setIcon(spDragon);
        pnlDragons.add(lblDragon9);

        lblDragon10.setIcon(spDragon);
        pnlDragons.add(lblDragon10);

        lblDragon11.setIcon(spDragon);
        pnlDragons.add(lblDragon11);

        lblDragon12.setIcon(spDragon);
        pnlDragons.add(lblDragon12);

        pnlSplit.setLeftComponent(pnlDragons);

        lblNumDragons.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumDragons.setText("Number of dragons: 1");
        lblNumDragons.setToolTipText("");
        pnlSplit.setRightComponent(lblNumDragons);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        getContentPane().add(pnlSplit, gridBagConstraints);

        setSize(new java.awt.Dimension(710, 390));
        setLocationRelativeTo(null);
    }               

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) 
    {
        System.exit(0);
    }

    private void rbDragonBeginnerActionPerformed(java.awt.event.ActionEvent evt)
    {
        dragonMovement = 1;
    }

    private void rbDragonEasyActionPerformed(java.awt.event.ActionEvent evt) 
    {
        dragonMovement = 2;
    }

    private void rbDragonMediumActionPerformed(java.awt.event.ActionEvent evt) 
    {
        dragonMovement = 3;
    }

    private void rbDragonHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDragonHardActionPerformed
        dragonMovement = 4;
    }//GEN-LAST:event_rbDragonHardActionPerformed

    private void rbGameBeginnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGameBeginnerActionPerformed
        lblDragon1.setVisible(true);
        lblDragon2.setVisible(false);
        lblDragon3.setVisible(false);
        lblDragon4.setVisible(false);
        lblDragon5.setVisible(false);
        lblDragon6.setVisible(false);
        lblDragon7.setVisible(false);
        lblDragon8.setVisible(false);
        lblDragon9.setVisible(false);
        lblDragon10.setVisible(false);
        lblDragon11.setVisible(false);
        lblDragon12.setVisible(false);
        userDifficulty = 1;
        lblNumDragons.setText("Number of dragons: 1");
    }//GEN-LAST:event_rbGameBeginnerActionPerformed

    private void rbGameEasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGameEasyActionPerformed
        lblDragon1.setVisible(true);
        lblDragon2.setVisible(true);
        lblDragon3.setVisible(false);
        lblDragon4.setVisible(false);
        lblDragon5.setVisible(false);
        lblDragon6.setVisible(false);
        lblDragon7.setVisible(false);
        lblDragon8.setVisible(false);
        lblDragon9.setVisible(false);
        lblDragon10.setVisible(false);
        lblDragon11.setVisible(false);
        lblDragon12.setVisible(false);
        userDifficulty = 2;
        lblNumDragons.setText("Number of dragons: 2");
    }//GEN-LAST:event_rbGameEasyActionPerformed

    private void rbGameMediumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGameMediumActionPerformed
        lblDragon1.setVisible(true);
        lblDragon2.setVisible(true);
        lblDragon3.setVisible(true);
        lblDragon4.setVisible(false);
        lblDragon5.setVisible(false);
        lblDragon6.setVisible(false);
        lblDragon7.setVisible(false);
        lblDragon8.setVisible(false);
        lblDragon9.setVisible(false);
        lblDragon10.setVisible(false);
        lblDragon11.setVisible(false);
        lblDragon12.setVisible(false);
        userDifficulty = 3;
        lblNumDragons.setText("Number of dragons: 3");
    }//GEN-LAST:event_rbGameMediumActionPerformed

    private void rbGameHardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGameHardActionPerformed
        lblDragon1.setVisible(true);
        lblDragon2.setVisible(true);
        lblDragon3.setVisible(true);
        lblDragon4.setVisible(true);
        lblDragon5.setVisible(true);
        lblDragon6.setVisible(true);
        lblDragon7.setVisible(false);
        lblDragon8.setVisible(false);
        lblDragon9.setVisible(false);
        lblDragon10.setVisible(false);
        lblDragon11.setVisible(false);
        lblDragon12.setVisible(false);
        userDifficulty = 4;
        lblNumDragons.setText("Number of dragons: 6");
    }//GEN-LAST:event_rbGameHardActionPerformed

    private void rbGameHard1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbGameHard1ActionPerformed
        lblDragon1.setVisible(true);
        lblDragon2.setVisible(true);
        lblDragon3.setVisible(true);
        lblDragon4.setVisible(true);
        lblDragon5.setVisible(true);
        lblDragon6.setVisible(true);
        lblDragon7.setVisible(true);
        lblDragon8.setVisible(true);
        lblDragon9.setVisible(true);
        lblDragon10.setVisible(true);
        lblDragon11.setVisible(true);
        lblDragon12.setVisible(true);
        userDifficulty = 5;
        lblNumDragons.setText("Number of dragons: 12");
    }//GEN-LAST:event_rbGameHard1ActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

        GameState.setDifficulty(userDifficulty);
        GameState.setDragonMovement(dragonMovement);

        GUIInterface guiInterface;
        guiInterface = new GUIInterface();
        setVisible(false);
        guiInterface.setVisible(true);

    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * @param args the command line arguments
     */


    private ImageIcon imgDragon;
    private ImageIcon heroDragon;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnStart;
    javax.swing.ButtonGroup buttonGroup1;
    javax.swing.ButtonGroup buttonGroup2;
    javax.swing.JButton jButton2;
    javax.swing.JLabel lblDragon;
    javax.swing.JLabel lblDragon1;
    javax.swing.JLabel lblDragon10;
    javax.swing.JLabel lblDragon11;
    javax.swing.JLabel lblDragon12;
    javax.swing.JLabel lblDragon2;
    javax.swing.JLabel lblDragon3;
    javax.swing.JLabel lblDragon4;
    javax.swing.JLabel lblDragon5;
    javax.swing.JLabel lblDragon6;
    javax.swing.JLabel lblDragon7;
    javax.swing.JLabel lblDragon8;
    javax.swing.JLabel lblDragon9;
    javax.swing.JLabel lblGame;
    javax.swing.JLabel lblHero;
    javax.swing.JLabel lblNumDragons;
    javax.swing.JPanel pnlButtons;
    javax.swing.JPanel pnlDragon;
    javax.swing.JPanel pnlDragons;
    javax.swing.JPanel pnlGame;
    javax.swing.JPanel pnlHero;
    javax.swing.JPanel pnlSelect;
    javax.swing.JSplitPane pnlSplit;
    javax.swing.JRadioButton rbDragonBeginner;
    javax.swing.JRadioButton rbDragonEasy;
    javax.swing.JRadioButton rbDragonHard;
    javax.swing.JRadioButton rbDragonMedium;
    javax.swing.JRadioButton rbGameBeginner;
    javax.swing.JRadioButton rbGameEasy;
    javax.swing.JRadioButton rbGameHard;
    javax.swing.JRadioButton rbGameHard1;
    javax.swing.JRadioButton rbGameMedium;
    // End of variables declaration//GEN-END:variables
}