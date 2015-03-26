package lpoo.gui;

import java.awt.*;
import javax.swing.*;

public class GUIStats extends JDialog 
{
    private final int[] values;
    
    public GUIStats(Frame parent, int[] values) 
    {
        super(parent, true);
        this.values = values;
        
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Statistics");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblDifficulty.setFont(lblDifficulty.getFont().deriveFont(lblDifficulty.getFont().getStyle() | java.awt.Font.BOLD));
        lblDifficulty.setText("Difficulty");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel1.add(lblDifficulty, gridBagConstraints);

        lblWidth.setText("Width");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblWidth, gridBagConstraints);

        lblPlayers.setText("Players");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblPlayers, gridBagConstraints);

        lblNumPlayers.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumPlayers, gridBagConstraints);

        lblNumWidth.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumWidth, gridBagConstraints);

        lblDimensions.setFont(lblDimensions.getFont().deriveFont(lblDimensions.getFont().getStyle() | java.awt.Font.BOLD));
        lblDimensions.setText("Maze Dimensions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel1.add(lblDimensions, gridBagConstraints);

        lblHeight.setText("Height");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblHeight, gridBagConstraints);

        lblDragons.setText("Dragons");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblDragons, gridBagConstraints);

        lblDarts.setText("Darts");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblDarts, gridBagConstraints);

        lblNumDragons.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumDragons, gridBagConstraints);

        lblNumSwords.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumSwords, gridBagConstraints);

        lblNumHeight.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumHeight, gridBagConstraints);

        lblDifficultyText.setText("Easy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblDifficultyText, gridBagConstraints);

        lblNumDarts.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumDarts, gridBagConstraints);

        lblNumShields.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(lblNumShields, gridBagConstraints);

        lblSwords.setText("Swords");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSwords, gridBagConstraints);

        lblEntities.setFont(lblEntities.getFont().deriveFont(lblEntities.getFont().getStyle() | java.awt.Font.BOLD));
        lblEntities.setText("Entities");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel1.add(lblEntities, gridBagConstraints);

        lblShields.setText("Shields");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblShields, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(196, 298));
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        lblNumWidth.setText(Integer.toString(values[0]));
        lblNumHeight.setText(Integer.toString(values[1]));
        lblNumPlayers.setText(Integer.toString(values[2]));
        lblNumDragons.setText(Integer.toString(values[3]));
        lblNumSwords.setText(Integer.toString(values[4]));
        lblNumDarts.setText(Integer.toString(values[5]));
        lblNumShields.setText(Integer.toString(values[6]));
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDarts;
    private javax.swing.JLabel lblDifficulty;
    private javax.swing.JLabel lblDifficultyText;
    private javax.swing.JLabel lblDimensions;
    private javax.swing.JLabel lblDragons;
    private javax.swing.JLabel lblEntities;
    private javax.swing.JLabel lblHeight;
    private javax.swing.JLabel lblNumDarts;
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
    // End of variables declaration//GEN-END:variables
}
