package lpoo.gui;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lpoo.logic.Direction;
import lpoo.logic.GameState;

public class GUIInterface extends javax.swing.JFrame 
{
    private int gDragonMovement;
    private int gDifficulty;
    
    public GUIInterface() 
    {
        gDragonMovement = -1;
        gDifficulty = 1;
        
        GameState.setDifficulty(gDifficulty);
        GameState.setDragonMovement(gDragonMovement);  

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        pnlPlayfield = new lpoo.gui.AreaDesenho();
        mMenu = new javax.swing.JMenuBar();
        mGame = new javax.swing.JMenu();
        mGameNew = new javax.swing.JMenuItem();
        mGameRestart = new javax.swing.JMenuItem();
        mGameSeparator1 = new javax.swing.JPopupMenu.Separator();
        mGameLoad = new javax.swing.JMenuItem();
        mGameSave = new javax.swing.JMenuItem();
        mGameSeparator2 = new javax.swing.JPopupMenu.Separator();
        mGameExit = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        mHelp = new javax.swing.JMenu();
        mHelpAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(620, 520));
        setMinimumSize(new java.awt.Dimension(620, 520));
        setPreferredSize(new java.awt.Dimension(620, 520));
        setResizable(false);
        setSize(new java.awt.Dimension(620, 520));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(0, 6));

        lblStatus.setText("jLabel2");
        getContentPane().add(lblStatus, java.awt.BorderLayout.PAGE_END);
        getContentPane().add(pnlPlayfield, java.awt.BorderLayout.CENTER);

        mGame.setText("Game");

        mGameNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mGameNew.setMnemonic('n');
        mGameNew.setText("New Game");
        mGame.add(mGameNew);

        mGameRestart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mGameRestart.setMnemonic('r');
        mGameRestart.setText("Restart");
        mGame.add(mGameRestart);
        mGame.add(mGameSeparator1);

        mGameLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mGameLoad.setMnemonic('l');
        mGameLoad.setText("Load...");
        mGame.add(mGameLoad);

        mGameSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mGameSave.setText("Save Game");
        mGameSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGameSaveActionPerformed(evt);
            }
        });
        mGame.add(mGameSave);
        mGame.add(mGameSeparator2);

        mGameExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mGameExit.setMnemonic('e');
        mGameExit.setText("Exit");
        mGameExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mGameExitActionPerformed(evt);
            }
        });
        mGame.add(mGameExit);

        mMenu.add(mGame);

        jMenu1.setText("Options");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Preferences");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        mMenu.add(jMenu1);

        mHelp.setText("Help");

        mHelpAbout.setText("About...");
        mHelpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mHelpAboutActionPerformed(evt);
            }
        });
        mHelp.add(mHelpAbout);

        mMenu.add(mHelp);

        setJMenuBar(mMenu);

        setSize(new java.awt.Dimension(636, 685));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mHelpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mHelpAboutActionPerformed
        GUIAbout guiAbout = new GUIAbout();
        guiAbout.setVisible(true);
    }//GEN-LAST:event_mHelpAboutActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

        boolean validKey = false;
        
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_W:
                validKey = true;
                GameState.update(Direction.UP);
                break;
            case KeyEvent.VK_S:
                validKey = true;
                GameState.update(Direction.DOWN);
                break;
            case KeyEvent.VK_A:
                 validKey = true;
                GameState.update(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
                validKey = true;                
                GameState.update(Direction.RIGHT);
                break;
        }

        if (!validKey)
        {
            return;
        }
        
        lblStatus.setText("OBJECTIVE: " + GameState.getObjective());

        if (GameState.gameOver()) 
        {
            if (GameState.playerWon()) 
            {
                JOptionPane.showMessageDialog(null, "You were killed by the dragon :(", "Game Over", JOptionPane.PLAIN_MESSAGE);
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "You were killed by the dragon :(", "Game Over", JOptionPane.PLAIN_MESSAGE);
            }
        }

      pnlPlayfield.revalidate();
      pnlPlayfield.repaint();
    }//GEN-LAST:event_formKeyPressed

    private void mGameSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGameSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mGameSaveActionPerformed

    private void mGameExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mGameExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mGameExitActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        GUINewGame newGame = new GUINewGame();
        newGame.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
        public static void main(String args[]) 
    {
        try 
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
            {
                if ("Nimbus".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) 
        {
            java.util.logging.Logger.getLogger(GUINewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new GUIInterface().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenu mGame;
    private javax.swing.JMenuItem mGameExit;
    private javax.swing.JMenuItem mGameLoad;
    private javax.swing.JMenuItem mGameNew;
    private javax.swing.JMenuItem mGameRestart;
    private javax.swing.JMenuItem mGameSave;
    private javax.swing.JPopupMenu.Separator mGameSeparator1;
    private javax.swing.JPopupMenu.Separator mGameSeparator2;
    private javax.swing.JMenu mHelp;
    private javax.swing.JMenuItem mHelpAbout;
    private javax.swing.JMenuBar mMenu;
    private lpoo.gui.AreaDesenho pnlPlayfield;
    // End of variables declaration//GEN-END:variables
}