package lpoo.gui;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import lpoo.logic.Direction;
import lpoo.logic.GameState;
import lpoo.logic.Maze;
import lpoo.logic.RandomMaze;

public class GUIInterface extends JFrame 
{    
    private Timer jTimer;
    
    public GUIInterface() 
    {
        try {
            initComponents();
        } catch (Exception ex)
        {
            
        }
        initializeGame();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lpoo/res/FireDragon_icon.png")));
      
    }

    private void initializeGame()
    {
        if (GUIGlobals.userDifficulty != -1)
        {
            GameState.setDifficulty(GUIGlobals.userDifficulty);
        }
        else
        {
            GameState.initialize(new RandomMaze(GUIGlobals.mazeWidth, GUIGlobals.mazeHeight));
            GameState.initializeDragons(GUIGlobals.numberDragons);
            GameState.initializeDarts(GUIGlobals.numberDragons + 1);
        }
        
        GameState.setDragonMovement(GUIGlobals.dragonDifficulty);
        
        pnlPlayfield.initializeMaze(GameState.getMaze());
        pnlPlayfield.setPreferredSize(pnlPlayfield.getWindowSize());
       
        checkState();
        revalidate();
        pack();
    }
    
    protected void resumeGame()
    {
        pnlPlayfield.setPreferredSize(pnlPlayfield.getWindowSize());
        
        checkState();
        revalidate();
        pack();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblStatus = new javax.swing.JLabel();
        pnlPlayfield = new lpoo.gui.AreaDesenho();
        mbDefault = new javax.swing.JMenuBar();
        mnGame = new javax.swing.JMenu();
        btnNew = new javax.swing.JMenuItem();
        btnLoadLevel = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        btnLoadState = new javax.swing.JMenuItem();
        btnSaveState = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnExit = new javax.swing.JMenuItem();
        mnOptions = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        btnControls = new javax.swing.JMenuItem();
        btnPreferences = new javax.swing.JMenuItem();
        mnHelp = new javax.swing.JMenu();
        btnAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze Run");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 13));
        lblStatus.setText("jLabel1");
        getContentPane().add(lblStatus, java.awt.BorderLayout.PAGE_END);

        pnlPlayfield.setPreferredSize(new java.awt.Dimension(640, 480));
        pnlPlayfield.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pnlPlayfieldFocusLost(evt);
            }
        });
        pnlPlayfield.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlPlayfieldMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlPlayfieldMouseExited(evt);
            }
        });
        pnlPlayfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pnlPlayfieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pnlPlayfieldKeyTyped(evt);
            }
        });
        getContentPane().add(pnlPlayfield, java.awt.BorderLayout.CENTER);

        mnGame.setText("Game");

        btnNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        btnNew.setText("New Game");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        mnGame.add(btnNew);

        btnLoadLevel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        btnLoadLevel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/world.png"))); // NOI18N
        btnLoadLevel.setText("Load...");
        btnLoadLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadLevelActionPerformed(evt);
            }
        });
        mnGame.add(btnLoadLevel);
        mnGame.add(jSeparator2);

        btnLoadState.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        btnLoadState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/folder.png"))); // NOI18N
        btnLoadState.setText("Load State");
        btnLoadState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadStateActionPerformed(evt);
            }
        });
        mnGame.add(btnLoadState);

        btnSaveState.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        btnSaveState.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/disk.png"))); // NOI18N
        btnSaveState.setText("Save State");
        btnSaveState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveStateActionPerformed(evt);
            }
        });
        mnGame.add(btnSaveState);
        mnGame.add(jSeparator1);

        btnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/door_open.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        mnGame.add(btnExit);

        mbDefault.add(mnGame);

        mnOptions.setText("Options");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/magnifier.png"))); // NOI18N
        jMenu1.setText("Zoom");

        jMenuItem1.setText("75%");
        buttonGroup1.add(jMenuItem1);
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("100%");
        buttonGroup1.add(jMenuItem2);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("150%");
        buttonGroup1.add(jMenuItem3);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("200%");
        buttonGroup1.add(jMenuItem4);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        mnOptions.add(jMenu1);
        mnOptions.add(jSeparator3);

        btnControls.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/joystick.png"))); // NOI18N
        btnControls.setText("Controls");
        btnControls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlsActionPerformed(evt);
            }
        });
        mnOptions.add(btnControls);

        btnPreferences.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/wrench.png"))); // NOI18N
        btnPreferences.setText("Preferences");
        btnPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreferencesActionPerformed(evt);
            }
        });
        mnOptions.add(btnPreferences);

        mbDefault.add(mnOptions);

        mnHelp.setText("Help");

        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/help.png"))); // NOI18N
        btnAbout.setText("About");
        btnAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAboutActionPerformed(evt);
            }
        });
        mnHelp.add(btnAbout);

        mbDefault.add(mnHelp);

        setJMenuBar(mbDefault);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreferencesActionPerformed
        GUIOptions guiOptions = new GUIOptions(this);
        guiOptions.setVisible(true);
    }//GEN-LAST:event_btnPreferencesActionPerformed

    private void btnControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlsActionPerformed
        GUIControls guiControls = new GUIControls(this);
        guiControls.setVisible(true);
    }//GEN-LAST:event_btnControlsActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        GUIAbout guiAbout = new GUIAbout(this);
        guiAbout.setVisible(true);
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Exit", JOptionPane.YES_NO_OPTION);
        
        if (r == JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void checkState()
    {
        lblStatus.setText("OBJECTIVE: " + GameState.getObjective());

        if (GameState.gameOver()) 
        {            
            if (GameState.playerWon()) 
            {
                lblStatus.setText("CONGRATULATIONS!");
                JOptionPane.showMessageDialog(null, "Congratulations! You have reached the exit safe and sound :)", "Player Wins", JOptionPane.PLAIN_MESSAGE);
                
            } 
            else 
            {
                 lblStatus.setText("GAME OVER");
                JOptionPane.showMessageDialog(null, "You were killed by the dragon :(", "Game Over", JOptionPane.PLAIN_MESSAGE);
               
            }
            
        }
    }
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        if (GameState.gameOver())
        {
            return;
        }
        
        boolean validKey = false;
        int pressedKey = evt.getKeyCode();
        
        if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyUp])
        {
            GameState.update(Direction.UP);
            validKey = true; 
        }
        else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyDown])
        {
            GameState.update(Direction.DOWN);
            validKey = true; 
        }
        else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyLeft])
        {
            GameState.update(Direction.LEFT);
            validKey = true; 
        }
        else if (pressedKey == GUIGlobals.currentKeys[GUIGlobals.keyRight])
        {
            GameState.update(Direction.RIGHT);
            validKey = true; 
        }

        if (validKey)
        {
            repaint();
            checkState();
        }
    }//GEN-LAST:event_formKeyPressed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        int r;
        
        if (GameState.gameOver())
        {
            r = JOptionPane.YES_OPTION;
        }
        else
        {
             r = JOptionPane.showConfirmDialog(this, "Do you want to start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
        }
      
        if (r == JOptionPane.YES_OPTION)
        {
            initializeGame();
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void loadState(ObjectInputStream aInputStream) throws IOException, ClassNotFoundException
    {
        GameState.read(aInputStream);
    }
    
    private void saveState(ObjectOutputStream aOutputStream) throws IOException
    {
        GameState.write(aOutputStream);
    }
    
    private void loadLevel() throws FileNotFoundException, IOException, ClassNotFoundException  
    {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run maze files (*.maze)", "maze"));
        int showOpenDialog = fileChooser.showOpenDialog(this);
        
        FileInputStream fin;
        ObjectInputStream oin;
        
        if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
        {
            return;
        }

        final File buffer = fileChooser.getSelectedFile();
        
        if (buffer == null)
        {
            return;
        }
        
        fin = new FileInputStream(buffer);
        oin = new ObjectInputStream(fin);
  
        Maze newMaze = (Maze) oin.readObject();

        pnlPlayfield.initializeMaze(newMaze);
        GameState.initializeCustom(newMaze);
        
        fin.close();
        oin.close();
    }
    
    private void loadState() throws FileNotFoundException, IOException, ClassNotFoundException
    {
         final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run maze files (*.maze)", "maze"));
        int showOpenDialog = fileChooser.showOpenDialog(this);
        
        FileInputStream fin;
        ObjectInputStream oin;
        
        if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
        {
            return;
        }

        final File buffer = fileChooser.getSelectedFile();
        
        if (buffer == null)
        {
            return;
        }
        
        fin = new FileInputStream(buffer);
        oin = new ObjectInputStream(fin);
  
        GameState.read(oin);
        resumeGame();
        fin.close();
        oin.close();
    }
    
    private void saveState() throws IOException
    {
        FileOutputStream fout;
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Maze Run saved games (*.state)", "state"));
        ObjectOutputStream oout;
                

            int showSaveDialog = fileChooser.showSaveDialog(this);

            if (showSaveDialog != JFileChooser.APPROVE_OPTION) 
            {
                    return;
            }
            
          final File  buffer = fileChooser.getSelectedFile();

        fout = new FileOutputStream(buffer);        
        oout = new ObjectOutputStream(fout);
        
        GameState.write(oout);
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        getParent().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void btnLoadStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadStateActionPerformed
         try {
            loadState();
        } catch (IOException ex) {
            Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoadStateActionPerformed

    private void btnSaveStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveStateActionPerformed
        try {
            saveState();
        } catch (IOException ex) {
            Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveStateActionPerformed

    private void btnLoadLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadLevelActionPerformed
        try 
        {
            loadLevel();
        } catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(GUIInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoadLevelActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 10));
        pnlPlayfield.scaleSprites(24, 24);
        repaint();
        pack();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 15));
        pnlPlayfield.scaleSprites(48, 48);
        repaint();
        pack();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 17));
        pnlPlayfield.scaleSprites(64, 64);
        repaint();
        pack();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        pnlPlayfield.scaleSprites(32, 32);
        lblStatus.setFont(lblStatus.getFont().deriveFont(lblStatus.getFont().getStyle() | java.awt.Font.BOLD, 13));
        repaint();
        pack();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void pnlPlayfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlPlayfieldKeyReleased
      
    }//GEN-LAST:event_pnlPlayfieldKeyReleased

    private void pnlPlayfieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnlPlayfieldKeyTyped
 pnlPlayfield.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlPlayfieldKeyTyped

    private void pnlPlayfieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPlayfieldMouseEntered
  
    }//GEN-LAST:event_pnlPlayfieldMouseEntered

    private void pnlPlayfieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pnlPlayfieldFocusLost
   
    }//GEN-LAST:event_pnlPlayfieldFocusLost

    private void pnlPlayfieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlPlayfieldMouseExited
     
    }//GEN-LAST:event_pnlPlayfieldMouseExited

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
       repaint();
    }//GEN-LAST:event_formKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnAbout;
    private javax.swing.JMenuItem btnControls;
    private javax.swing.JMenuItem btnExit;
    private javax.swing.JMenuItem btnLoadLevel;
    private javax.swing.JMenuItem btnLoadState;
    private javax.swing.JMenuItem btnNew;
    private javax.swing.JMenuItem btnPreferences;
    private javax.swing.JMenuItem btnSaveState;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenuBar mbDefault;
    private javax.swing.JMenu mnGame;
    private javax.swing.JMenu mnHelp;
    private javax.swing.JMenu mnOptions;
    private lpoo.gui.AreaDesenho pnlPlayfield;
    // End of variables declaration//GEN-END:variables
}
