package lpoo.gui;

import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import lpoo.logic.Maze;

public class GUIMazeEditor extends JFrame 
{
    private File buffer;

    public GUIMazeEditor() 
    {
        initComponents();
        updateStats();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(24);
        jScrollPane1.getHorizontalScrollBar().setUnitIncrement(24);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/lpoo/res/editor-128x128.png")));
    }

    public void newMaze(int w, int h) 
    {
        areaEdicao1 = new AreaEdicao(w, h);
        jScrollPane1.setViewportView(areaEdicao1);
        buttonGroup1.clearSelection();
    }

    private void updateStats()
    {
        lblNumWidth.setText(Integer.toString(areaEdicao1.mazeWidth));
        lblNumHeight.setText(Integer.toString(areaEdicao1.mazeHeight));
        lblNumDragons.setText(Integer.toString(areaEdicao1.numDragons));
        lblNumDarts.setText(Integer.toString(areaEdicao1.numDarts));
        lblNumDoors.setText(Integer.toString(areaEdicao1.numDoors));
        lblNumPlayers.setText(Integer.toString(areaEdicao1.numPlayers));
        lblNumSwords.setText(Integer.toString(areaEdicao1.numSwords));
        lblNumShields.setText(Integer.toString(areaEdicao1.numShields));
        
        String difficultyText = "None";
        
        switch (areaEdicao1.getDifficulty())
        {
            case 1:
                difficultyText = "Easy";
                break;
            case 2:
                difficultyText = "Medium";
                break;
            case 3:
                difficultyText = "Hard";
                break;
            case 4:
                difficultyText= "Nightmare";
                break;
        }

        lblDifficultyText.setText(difficultyText);
        jPanel1.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        tbDefault = new javax.swing.JToolBar();
        btnWall = new javax.swing.JToggleButton();
        btnPlayer = new javax.swing.JToggleButton();
        btnDragon = new javax.swing.JToggleButton();
        btnDoor = new javax.swing.JToggleButton();
        btnDart = new javax.swing.JToggleButton();
        btnShield = new javax.swing.JToggleButton();
        btnSword = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnErase = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaEdicao1 = new lpoo.gui.AreaEdicao();
        jPanel1 = new javax.swing.JPanel();
        lblDifficulty = new javax.swing.JLabel();
        lblWidth = new javax.swing.JLabel();
        lblPlayers = new javax.swing.JLabel();
        lblNumPlayers = new javax.swing.JLabel();
        lblNumWidth = new javax.swing.JLabel();
        lblDimensions = new javax.swing.JLabel();
        lblHeight = new javax.swing.JLabel();
        lblDragons = new javax.swing.JLabel();
        lblDarts = new javax.swing.JLabel();
        lblNumDragons = new javax.swing.JLabel();
        lblNumSwords = new javax.swing.JLabel();
        lblNumHeight = new javax.swing.JLabel();
        lblDifficultyText = new javax.swing.JLabel();
        lblNumDarts = new javax.swing.JLabel();
        lblNumShields = new javax.swing.JLabel();
        lblSwords = new javax.swing.JLabel();
        lblEntities = new javax.swing.JLabel();
        lblShields = new javax.swing.JLabel();
        lblDoors = new javax.swing.JLabel();
        lblNumDoors = new javax.swing.JLabel();
        mbDefault = new javax.swing.JMenuBar();
        mnFile = new javax.swing.JMenu();
        btnNew = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        btnLoad = new javax.swing.JMenuItem();
        btnSave = new javax.swing.JMenuItem();
        btnSaveAs = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        btnExit = new javax.swing.JMenuItem();
        mnEdit = new javax.swing.JMenu();
        btnUndo = new javax.swing.JMenuItem();
        btnRedo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        btnClear = new javax.swing.JMenuItem();
        btnRandomize = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        btnValidate = new javax.swing.JMenuItem();
        mnHelp = new javax.swing.JMenu();
        btnAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Maze Editor - Untitled");

        tbDefault.setRollover(true);

        btnWall.setBackground(new java.awt.Color(255, 153, 102));
        buttonGroup1.add(btnWall);
        btnWall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/wall-32x32.png"))); // NOI18N
        btnWall.setText("Wall");
        btnWall.setFocusable(false);
        btnWall.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnWall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnWall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnWall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWallActionPerformed(evt);
            }
        });
        tbDefault.add(btnWall);

        buttonGroup1.add(btnPlayer);
        btnPlayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/hero-32x32.png"))); // NOI18N
        btnPlayer.setText("Player");
        btnPlayer.setFocusable(false);
        btnPlayer.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnPlayer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPlayer.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerActionPerformed(evt);
            }
        });
        tbDefault.add(btnPlayer);

        buttonGroup1.add(btnDragon);
        btnDragon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/dragon-32x32.png"))); // NOI18N
        btnDragon.setText("Dragon");
        btnDragon.setFocusable(false);
        btnDragon.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnDragon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDragon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDragon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDragonActionPerformed(evt);
            }
        });
        tbDefault.add(btnDragon);

        buttonGroup1.add(btnDoor);
        btnDoor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/door1-32x32.png"))); // NOI18N
        btnDoor.setText("Door");
        btnDoor.setFocusable(false);
        btnDoor.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnDoor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDoor.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoorActionPerformed(evt);
            }
        });
        tbDefault.add(btnDoor);

        buttonGroup1.add(btnDart);
        btnDart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/dart-32x32.png"))); // NOI18N
        btnDart.setText("Dart");
        btnDart.setFocusable(false);
        btnDart.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnDart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDart.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDartActionPerformed(evt);
            }
        });
        tbDefault.add(btnDart);

        buttonGroup1.add(btnShield);
        btnShield.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/shield-32x32.png"))); // NOI18N
        btnShield.setText("Shield");
        btnShield.setFocusable(false);
        btnShield.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnShield.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnShield.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnShield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShieldActionPerformed(evt);
            }
        });
        tbDefault.add(btnShield);

        buttonGroup1.add(btnSword);
        btnSword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/sword-32x32.png"))); // NOI18N
        btnSword.setText("Sword");
        btnSword.setFocusable(false);
        btnSword.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnSword.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSword.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSwordActionPerformed(evt);
            }
        });
        tbDefault.add(btnSword);
        tbDefault.add(jSeparator4);

        buttonGroup1.add(btnErase);
        btnErase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/eraser-32x32.png"))); // NOI18N
        btnErase.setText("Erase");
        btnErase.setFocusable(false);
        btnErase.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnErase.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnErase.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnErase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEraseActionPerformed(evt);
            }
        });
        tbDefault.add(btnErase);

        getContentPane().add(tbDefault, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jScrollPane1MousePressed(evt);
            }
        });

        areaEdicao1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        areaEdicao1.setMinimumSize(new java.awt.Dimension(64, 64));
        areaEdicao1.setPreferredSize(new java.awt.Dimension(640, 480));
        areaEdicao1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                areaEdicao1MouseDragged(evt);
            }
        });
        areaEdicao1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                areaEdicao1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                areaEdicao1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout areaEdicao1Layout = new javax.swing.GroupLayout(areaEdicao1);
        areaEdicao1.setLayout(areaEdicao1Layout);
        areaEdicao1Layout.setHorizontalGroup(
            areaEdicao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 638, Short.MAX_VALUE)
        );
        areaEdicao1Layout.setVerticalGroup(
            areaEdicao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(areaEdicao1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblDifficulty.setFont(lblDifficulty.getFont().deriveFont(lblDifficulty.getFont().getStyle() | java.awt.Font.BOLD));
        lblDifficulty.setText("Difficulty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 12, 10, 0);
        jPanel1.add(lblDifficulty, gridBagConstraints);

        lblWidth.setText("Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblWidth, gridBagConstraints);

        lblPlayers.setText("Darts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblPlayers, gridBagConstraints);

        lblNumPlayers.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumPlayers, gridBagConstraints);

        lblNumWidth.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumWidth, gridBagConstraints);

        lblDimensions.setFont(lblDimensions.getFont().deriveFont(lblDimensions.getFont().getStyle() | java.awt.Font.BOLD));
        lblDimensions.setText("Dimensions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 10, 32);
        jPanel1.add(lblDimensions, gridBagConstraints);

        lblHeight.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblHeight, gridBagConstraints);

        lblDragons.setText("Swords");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblDragons, gridBagConstraints);

        lblDarts.setText("Players");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblDarts, gridBagConstraints);

        lblNumDragons.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumDragons, gridBagConstraints);

        lblNumSwords.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumSwords, gridBagConstraints);

        lblNumHeight.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumHeight, gridBagConstraints);

        lblDifficultyText.setText("Easy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblDifficultyText, gridBagConstraints);

        lblNumDarts.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumDarts, gridBagConstraints);

        lblNumShields.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumShields, gridBagConstraints);

        lblSwords.setText("Dragons");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblSwords, gridBagConstraints);

        lblEntities.setFont(lblEntities.getFont().deriveFont(lblEntities.getFont().getStyle() | java.awt.Font.BOLD));
        lblEntities.setText("Entities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
        jPanel1.add(lblEntities, gridBagConstraints);

        lblShields.setText("Shields");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblShields, gridBagConstraints);

        lblDoors.setText("Doors");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
        jPanel1.add(lblDoors, gridBagConstraints);

        lblNumDoors.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 12);
        jPanel1.add(lblNumDoors, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_END);

        mnFile.setText("File");

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        mnFile.add(btnNew);
        mnFile.add(jSeparator3);

        btnLoad.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        btnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/folder.png"))); // NOI18N
        btnLoad.setText("Load");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });
        mnFile.add(btnLoad);

        btnSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/disk.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        mnFile.add(btnSave);

        btnSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/disk_multiple.png"))); // NOI18N
        btnSaveAs.setText("Save As...");
        btnSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAsActionPerformed(evt);
            }
        });
        mnFile.add(btnSaveAs);
        mnFile.add(jSeparator2);

        btnExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/door_open.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        mnFile.add(btnExit);

        mbDefault.add(mnFile);

        mnEdit.setText("Edit");

        btnUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        btnUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/arrow_undo.png"))); // NOI18N
        btnUndo.setText("Undo");
        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });
        mnEdit.add(btnUndo);

        btnRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        btnRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/arrow_redo.png"))); // NOI18N
        btnRedo.setText("Redo");
        btnRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedoActionPerformed(evt);
            }
        });
        mnEdit.add(btnRedo);
        mnEdit.add(jSeparator1);

        btnClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/bomb.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        mnEdit.add(btnClear);

        btnRandomize.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        btnRandomize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/hourglass.png"))); // NOI18N
        btnRandomize.setText("Randomize...");
        btnRandomize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomizeActionPerformed(evt);
            }
        });
        mnEdit.add(btnRandomize);
        mnEdit.add(jSeparator6);

        btnValidate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lpoo/res/tick.png"))); // NOI18N
        btnValidate.setText("Validate");
        btnValidate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValidateActionPerformed(evt);
            }
        });
        mnEdit.add(btnValidate);

        mbDefault.add(mnEdit);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayerActionPerformed
        areaEdicao1.setSymbol('h');
    }//GEN-LAST:event_btnPlayerActionPerformed

    private void areaEdicao1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaEdicao1MousePressed
        updateStats();
    }//GEN-LAST:event_areaEdicao1MousePressed

    private void btnDragonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDragonActionPerformed
        areaEdicao1.setSymbol('D');
    }//GEN-LAST:event_btnDragonActionPerformed

    private void btnShieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShieldActionPerformed
        areaEdicao1.setSymbol('V');
    }//GEN-LAST:event_btnShieldActionPerformed

    private void btnDartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDartActionPerformed
        areaEdicao1.setSymbol('*');
    }//GEN-LAST:event_btnDartActionPerformed

    private void btnWallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWallActionPerformed
        areaEdicao1.setSymbol('X');
    }//GEN-LAST:event_btnWallActionPerformed

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        areaEdicao1.undo();
    }//GEN-LAST:event_btnUndoActionPerformed

    private void btnRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedoActionPerformed
        areaEdicao1.redo();
    }//GEN-LAST:event_btnRedoActionPerformed

    private void btnRandomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomizeActionPerformed
        areaEdicao1.erase();
        areaEdicao1.generateMaze();
        updateStats();
    }//GEN-LAST:event_btnRandomizeActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void loadFile() throws FileNotFoundException, IOException, ClassNotFoundException  
    {
        final JFileChooser fileChooser = new JFileChooser();
        int showOpenDialog = fileChooser.showOpenDialog(this);
        
        FileInputStream fin;
        ObjectInputStream oin;
        
        if (showOpenDialog != JFileChooser.APPROVE_OPTION) 
        {
            return;
        }

        buffer = fileChooser.getSelectedFile();
        
        if (buffer == null)
        {
            return;
        }
        
        fin = new FileInputStream(buffer);
        oin = new ObjectInputStream(fin);
  
        setTitle("Maze Builder - " + buffer.getAbsolutePath());
        
        areaEdicao1.initializeMaze((Maze) oin.readObject());
        areaEdicao1.repaint();
        updateStats();
        fin.close();
        oin.close();
    }

    private void saveFile(boolean overwrite) throws FileNotFoundException, IOException 
    {
        FileOutputStream fout;
        ObjectOutputStream oout;
                
        if (buffer == null || overwrite) 
        {
            final JFileChooser fileChooser = new JFileChooser();
            int showSaveDialog = fileChooser.showSaveDialog(this);

            if (showSaveDialog != JFileChooser.APPROVE_OPTION) 
            {
                    return;
            }
            
            buffer = fileChooser.getSelectedFile();
            setTitle("Maze Builder - " + buffer.getAbsolutePath());
        }

        fout = new FileOutputStream(buffer);        
        oout = new ObjectOutputStream(fout);
        
        areaEdicao1.writeMaze(oout);
    }

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        try
        {
            loadFile();
        } 
        catch (IOException | ClassNotFoundException ex) 
        {
            Logger.getLogger(GUIMazeEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try 
        {
            saveFile(false);
        }
        catch (IOException ex) 
        {
             Logger.getLogger(GUIMazeEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAsActionPerformed
        try 
        {
            saveFile(true);
        }
        catch (IOException ex) 
        {
             Logger.getLogger(GUIMazeEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveAsActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        GUINewMaze g_msize;
        g_msize = new GUINewMaze(this);
        g_msize.setVisible(true);
        setTitle("Maze Editor - Untitled");
        buffer = null;
        updateStats();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwordActionPerformed
        areaEdicao1.setSymbol('E');
    }//GEN-LAST:event_btnSwordActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        areaEdicao1.erase();
        updateStats();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnEraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEraseActionPerformed
        areaEdicao1.setSymbol(' ');
    }//GEN-LAST:event_btnEraseActionPerformed

    private void btnAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAboutActionPerformed
        GUIAbout guiAbout = new GUIAbout(this);
        guiAbout.setVisible(true);
    }//GEN-LAST:event_btnAboutActionPerformed

    private void btnDoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoorActionPerformed
        areaEdicao1.setSymbol('S');
    }//GEN-LAST:event_btnDoorActionPerformed

    private void btnValidateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValidateActionPerformed
        areaEdicao1.validateMaze();
    }//GEN-LAST:event_btnValidateActionPerformed

    private void jScrollPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MousePressed

    private void areaEdicao1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaEdicao1MouseDragged
        updateStats();
    }//GEN-LAST:event_areaEdicao1MouseDragged

    private void areaEdicao1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_areaEdicao1MouseReleased
        updateStats();
       
    }//GEN-LAST:event_areaEdicao1MouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lpoo.gui.AreaEdicao areaEdicao1;
    private javax.swing.JMenuItem btnAbout;
    private javax.swing.JMenuItem btnClear;
    private javax.swing.JToggleButton btnDart;
    private javax.swing.JToggleButton btnDoor;
    private javax.swing.JToggleButton btnDragon;
    private javax.swing.JToggleButton btnErase;
    private javax.swing.JMenuItem btnExit;
    private javax.swing.JMenuItem btnLoad;
    private javax.swing.JMenuItem btnNew;
    private javax.swing.JToggleButton btnPlayer;
    private javax.swing.JMenuItem btnRandomize;
    private javax.swing.JMenuItem btnRedo;
    private javax.swing.JMenuItem btnSave;
    private javax.swing.JMenuItem btnSaveAs;
    private javax.swing.JToggleButton btnShield;
    private javax.swing.JToggleButton btnSword;
    private javax.swing.JMenuItem btnUndo;
    private javax.swing.JMenuItem btnValidate;
    private javax.swing.JToggleButton btnWall;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JLabel lblDarts;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblDifficultyText;
    private javax.swing.JLabel lblDimensions;
    private javax.swing.JLabel lblDoors;
    private javax.swing.JLabel lblDragons;
    private javax.swing.JLabel lblEntities;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblNumDarts;
    private javax.swing.JLabel lblNumDoors;
    private javax.swing.JLabel lblNumDragons;
    private javax.swing.JLabel lblNumHeight;
    private javax.swing.JLabel lblNumPlayers;
    private javax.swing.JLabel lblNumShields;
    private javax.swing.JLabel lblNumSwords;
    private javax.swing.JLabel lblNumWidth;
    private javax.swing.JLabel lblPlayers;
    private javax.swing.JLabel lblShields;
    private javax.swing.JLabel lblSwords;
    private javax.swing.JLabel lblWidth;
    private javax.swing.JMenuBar mbDefault;
    private javax.swing.JMenu mnEdit;
    private javax.swing.JMenu mnFile;
    private javax.swing.JMenu mnHelp;
    private javax.swing.JToolBar tbDefault;
    // End of variables declaration//GEN-END:variables
}
