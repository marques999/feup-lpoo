package lpoo.gui;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import lpoo.logic.Direction;
import lpoo.logic.GameState;

public class GUIInterface extends javax.swing.JFrame 
{
    public GUIInterface() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        pnlPlayfield = new lpoo.gui.AreaDesenho();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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

        jMenu1.setText("Game");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("About...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        setSize(new java.awt.Dimension(636, 685));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        GUIAbout guiAbout = new GUIAbout();
        guiAbout.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblStatus;
    private lpoo.gui.AreaDesenho pnlPlayfield;
    // End of variables declaration//GEN-END:variables
}