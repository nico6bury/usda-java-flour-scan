/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import IJM.IJProcess;
import Scan.Scan;
import Utils.Result;
import Utils.Result.ResultType;

/**
 *
 * @author Nicholas.Sixbury
 */
public class MainWindow extends javax.swing.JFrame {

    protected Scan scan = null;
    private JFileChooser selectFilesChooser = new JFileChooser();
    private JFileChooser ijProcFileChooser = new JFileChooser();
    private File lastScannedFile = null;
    private File lastIjProcFile = null;
    private List<File> imageQueue = new ArrayList<File>();

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        // try and figure out if we should use dark mode
        boolean useDarkMode = false;
        // set the application theme / look and feel
        if (useDarkMode) { FlatDarkLaf.setup(); }
        else { FlatLightLaf.setup(); }

        // set up file listeners
        selectFilesChooser.addActionListener(selectFilesListener);
        selectFilesChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectFilesChooser.setMultiSelectionEnabled(true);
        // ijProcFileChooser.addActionListener(ijProcFileListener);

        initComponents();
    }//end MainWindow constructor

    /**
     * Shows a pretty generic message box giving the name and message of supplied error. Uses JOptionPane
     * @param e The exception that was generated.
     */
    protected static void showGenericExceptionMessage(Exception e) {
        JOptionPane.showMessageDialog(null, "While attempting the chosen command, the program encountered an exception of type " + e.getClass().getName() + ".\nThe exception message was " + e.getMessage(), "Unhandled Exception Caught.", JOptionPane.ERROR_MESSAGE);
    }//end genericExceptionMessage(e)

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        uxStatusTxt = new javax.swing.JTextArea();
        uxConnectToScannerBtn = new javax.swing.JButton();
        uxScanBigBtn = new javax.swing.JButton();
        uxScanQueueBtn = new javax.swing.JButton();
        uxAddFilesBtn = new javax.swing.JButton();
        uxProcessAllBtn = new javax.swing.JButton();
        uxEmptyQueueBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        uxSearchTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        uxQueueList = new javax.swing.JList<>();
        uxSearchBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        uxImagePropertiesTxt = new javax.swing.JTextArea();
        uxPrevImageBtn = new javax.swing.JButton();
        uxNextImageBtn = new javax.swing.JButton();
        uxOpenFileBtn = new javax.swing.JButton();
        uxImageLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        uxOutputTable = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        uxInitMenu = new javax.swing.JMenu();
        uxConnectScannerBtn = new javax.swing.JMenuItem();
        uxResetScanner = new javax.swing.JMenuItem();
        uxRunMenu = new javax.swing.JMenu();
        uxScanBtn = new javax.swing.JMenuItem();
        uxIjBtn = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("usda-java-flour-scan");

        jSplitPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jSplitPane1.setDividerLocation(415);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane2.setDividerLocation(230);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxStatusTxt.setEditable(false);
        uxStatusTxt.setColumns(20);
        uxStatusTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxStatusTxt.setRows(5);
        jScrollPane1.setViewportView(uxStatusTxt);

        uxConnectToScannerBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxConnectToScannerBtn.setText("Connect to Scanner");
        uxConnectToScannerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxConnectToScannerBtnActionPerformed(evt);
            }
        });

        uxScanBigBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxScanBigBtn.setText("Scan");
        uxScanBigBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxScanBigBtnActionPerformed(evt);
            }
        });

        uxScanQueueBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxScanQueueBtn.setText("Scan + Add to Queue");
        uxScanQueueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxScanQueueBtnActionPerformed(evt);
            }
        });

        uxAddFilesBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxAddFilesBtn.setText("Add Files to Queue");
        uxAddFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxAddFilesBtnActionPerformed(evt);
            }
        });

        uxProcessAllBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxProcessAllBtn.setText("Process Queue");
        uxProcessAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxProcessAllBtnActionPerformed(evt);
            }
        });

        uxEmptyQueueBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxEmptyQueueBtn.setText("Empty Queue");
        uxEmptyQueueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxEmptyQueueBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(uxConnectToScannerBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxScanBigBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxScanQueueBtn))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(uxAddFilesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxProcessAllBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxEmptyQueueBtn)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uxScanQueueBtn)
                    .addComponent(uxScanBigBtn)
                    .addComponent(uxConnectToScannerBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uxAddFilesBtn)
                    .addComponent(uxProcessAllBtn)
                    .addComponent(uxEmptyQueueBtn))
                .addContainerGap())
        );

        jSplitPane2.setTopComponent(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxSearchTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxQueueList.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxQueueList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                uxQueueListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(uxQueueList);

        uxSearchBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxSearchBtn.setText("Search");
        uxSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxSearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(uxSearchTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxSearchBtn)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uxSearchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uxSearchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSplitPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane3.setDividerLocation(240);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxImagePropertiesTxt.setColumns(1);
        uxImagePropertiesTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxImagePropertiesTxt.setRows(1);
        uxImagePropertiesTxt.setPreferredSize(new java.awt.Dimension(102, 84));
        jScrollPane3.setViewportView(uxImagePropertiesTxt);

        uxPrevImageBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uxPrevImageBtn.setText("Previous Image");
        uxPrevImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxPrevImageBtnActionPerformed(evt);
            }
        });

        uxNextImageBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uxNextImageBtn.setText("Next Image");
        uxNextImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxNextImageBtnActionPerformed(evt);
            }
        });

        uxOpenFileBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        uxOpenFileBtn.setText("Open in File Explorer");
        uxOpenFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxOpenFileBtnActionPerformed(evt);
            }
        });

        uxImageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxImageLabel.setText("please select a processed image");
        uxImageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uxPrevImageBtn)
                    .addComponent(uxNextImageBtn)
                    .addComponent(uxOpenFileBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uxImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uxImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxPrevImageBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxNextImageBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxOpenFileBtn)))
                .addContainerGap())
        );

        jSplitPane3.setTopComponent(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxOutputTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxOutputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Rep", "Slice", "Rotation", "Side", "Count", "Pixels", "%Area", "L* Mean", "L* Stdev"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        uxOutputTable.setShowGrid(true);
        jScrollPane5.setViewportView(uxOutputTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane3.setRightComponent(jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3)
        );

        jSplitPane1.setRightComponent(jPanel4);

        uxInitMenu.setText("Init");
        uxInitMenu.setFocusable(false);
        uxInitMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxConnectScannerBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxConnectScannerBtn.setText("Connect Scanner");
        uxConnectScannerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxConnectScannerBtnActionPerformed(evt);
            }
        });
        uxInitMenu.add(uxConnectScannerBtn);

        uxResetScanner.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxResetScanner.setText("Reset Scanner");
        uxResetScanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxResetScannerActionPerformed(evt);
            }
        });
        uxInitMenu.add(uxResetScanner);

        jMenuBar1.add(uxInitMenu);

        uxRunMenu.setText("Run");
        uxRunMenu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxScanBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxScanBtn.setText("Scan");
        uxScanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxScanBtnActionPerformed(evt);
            }
        });
        uxRunMenu.add(uxScanBtn);

        uxIjBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxIjBtn.setText("ImageJ Process");
        uxIjBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxIjBtnActionPerformed(evt);
            }
        });
        uxRunMenu.add(uxIjBtn);

        jMenuBar1.add(uxRunMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uxScanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxScanBtnActionPerformed
        PerformScan();
    }//GEN-LAST:event_uxScanBtnActionPerformed

    private void uxIjBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxIjBtnActionPerformed
        // if (lastScannedFile == null) {
        //     JOptionPane.showMessageDialog(this, "Please select the image file generated by the scanner.", "No scanned image selected", JOptionPane.ERROR_MESSAGE);
        // }//end if last scanned file is null
        // else if (!lastScannedFile.exists()) {
        //     JOptionPane.showMessageDialog(this, "Please select a scanned image that exists. \nFile " + lastScannedFile.getAbsolutePath() + "\n does not exist.", "Scanned image file does not exist.", JOptionPane.ERROR_MESSAGE);
        // }//end if last scanned file doesn't exist
        // else {
        //     try {
        //         // process the images in imagej
		// 		IJProcess ijProcess = new IJProcess();
        //         Result<String> outputData = ijProcess.runMacro(uxScannedFileTxt.getText());
        //         if (outputData.isErr()) {
        //             outputData.getError().printStackTrace();
        //             showGenericExceptionMessage(outputData.getError());
        //         }//end if we couldn't get output data
        //         // display the output data to the user
		// 	} catch (URISyntaxException e) {
		// 		e.printStackTrace();
        //         showGenericExceptionMessage(e);
		// 	}//end catching URISyntaxException
        // }//end else we should probably be able to process the file
    }//GEN-LAST:event_uxIjBtnActionPerformed

    private ActionListener selectFilesListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand() == "ApproveSelection") {
                File[] selectedFiles = selectFilesChooser.getSelectedFiles();
                // uxScannedFileTxt.setText(lastScannedFile.getPath());
                for (int i = 0; i < selectedFiles.length; i++) {
                    uxStatusTxt.append("selected scanned file \"" + selectedFiles[i].getAbsolutePath() + "\"\n");
                    imageQueue.add(selectedFiles[i]);
                }//end adding each selected file to the queue
            }//end if selection was approved
            else if (e.getActionCommand() == "CancelSelection") {
                uxStatusTxt.append("File selection cancelled.\n");
            }//end else if selection was cancelled
        }//end actionPerformed
    };

    // private ActionListener ijProcFileListener = new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         System.out.println(e.getActionCommand());
    //         if (e.getActionCommand() == "ApproveSelection") {
    //             lastIjProcFile = ijProcFileChooser.getSelectedFile();
    //             uxIJOutputTxt.setText(lastIjProcFile.getPath());
    //             uxStatusTxt.append("Selected ij processed file \"" + lastIjProcFile.getAbsolutePath() + "\"\n");
    //         }//end if selection was approved
    //         else if (e.getActionCommand() == "CancelSelection") {
    //             uxStatusTxt.append("File selection cancelled\n");
    //         }//end else if selection was cancelled
    //     }//end actionPerformed
    // };

    private void uxConnectScannerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxConnectScannerBtnActionPerformed
        // check for scanner already initialized
        if (scan != null) {
            JOptionPane.showMessageDialog(this, "A scanner is already connected. Please disconnect the current scanner before connecting a new one.", "Scanner already connected", JOptionPane.ERROR_MESSAGE);
        }//end if scan isn't null
        
        scan = new Scan();
        // try to access the scanner source
        Result<ResultType> initScannerResult = scan.initScanner();
        if (initScannerResult.isErr()) {
            showGenericExceptionMessage(initScannerResult.getError());
            // reset scanner to null
            scan = null;
        }//end if we encountered an error while detecting the connected scanner
        // try to set scanner settings
        Result<ResultType> setScanSettingResult = scan.setScanSettings();
        if (setScanSettingResult.isErr()) {
            showGenericExceptionMessage(setScanSettingResult.getError());
            // reset scan to null
            scan = null;
        }//end if we encountered an error while setting scan settings
    }//GEN-LAST:event_uxConnectScannerBtnActionPerformed

    private void uxResetScannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxResetScannerActionPerformed
        if (scan == null) {
            JOptionPane.showMessageDialog(this, "The scanner is already disconnected. It can't be reset further.", "Scanner already disconnected", JOptionPane.ERROR_MESSAGE);
        }//end if scan is already null
        else {
            Result<ResultType> closeResult = scan.closeScanner();
            if (closeResult.isErr()) {
                showGenericExceptionMessage(closeResult.getError());
            }//end if closing scanner resulted in error
            scan = null;
        }//end else we need to reset scanner connection
    }//GEN-LAST:event_uxResetScannerActionPerformed

    /**
     * This method performs the operation of scanning an image. It then returns the resulting file if successful, or the exception wrapped in a result if not.
     * @return Returns a Result wrapped File.
     */
    private Result<File> PerformScan() {
        System.out.println("You clicked the \"Scan\" button.");
        // try to scan something with the scanner
        Result<String> scanResult = scan.runScanner();
        if (scanResult.isOk()) {
            String result = scanResult.getValue();
            lastScannedFile = new File(result);
            return new Result<File>(lastScannedFile);
        }//end else if scan result is ok
        else {
            return new Result<File>(scanResult.getError());
        }//end if we have an error to show
    }//end method PerformScan()

    private void uxConnectToScannerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxConnectToScannerBtnActionPerformed
        // just trigger the top menu code
        uxConnectScannerBtnActionPerformed(evt);
    }//GEN-LAST:event_uxConnectToScannerBtnActionPerformed

    private void uxScanBigBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxScanBigBtnActionPerformed
        Result<File> scanResult = PerformScan();
        if (scanResult.isErr()) {showGenericExceptionMessage(scanResult.getError());}
    }//GEN-LAST:event_uxScanBigBtnActionPerformed

    private void uxScanQueueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxScanQueueBtnActionPerformed
        Result<File> scanResult = PerformScan();
        if (scanResult.isErr()) {showGenericExceptionMessage(scanResult.getError());}
        else if (scanResult.isOk()) {
            imageQueue.add(scanResult.getValue());
            UpdateQueueList();
        }//end else if we can add something to the queue
    }//GEN-LAST:event_uxScanQueueBtnActionPerformed

    private void uxAddFilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxAddFilesBtnActionPerformed
        // adding selected files to queue should be handled by selectFIlesListener
        selectFilesChooser.showOpenDialog(this);
        UpdateQueueList();
    }//GEN-LAST:event_uxAddFilesBtnActionPerformed

    /**
     * This method should be called whenever the image queue is updated, in order to show the changes in the list.
     */
    private void UpdateQueueList() {
        uxQueueList.removeAll();
        String[] imageArray = new String[imageQueue.size()];
        for(int i = 0; i < imageArray.length; i++) {
            imageArray[i] = imageQueue.get(i).getName();
        }//end adding each image file to the array
        uxQueueList.setListData(imageArray);
    }//end UpdateQueueList()

    private void uxProcessAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxProcessAllBtnActionPerformed
        if (imageQueue == null || imageQueue.size() == 0) {
            JOptionPane.showMessageDialog(this, "Please select the image file generated by the scanner.", "No scanned image selected", JOptionPane.ERROR_MESSAGE);
        }//end if last scanned file is null
        // else if () {
        //     JOptionPane.showMessageDialog(this, "Please select a scanned image that exists. \nFile " + lastScannedFile.getAbsolutePath() + "\n does not exist.", "Scanned image file does not exist.", JOptionPane.ERROR_MESSAGE);
        // }//end if last scanned file doesn't exist
        else {
            try {
                // process the images in imagej
				IJProcess ijProcess = new IJProcess();
                Result<String> outputData = ijProcess.runMacro(imageQueue);
                if (outputData.isErr()) {
                    outputData.getError().printStackTrace();
                    showGenericExceptionMessage(outputData.getError());
                }//end if we couldn't get output data
                // display the output data to the user
			} catch (URISyntaxException e) {
				e.printStackTrace();
                showGenericExceptionMessage(e);
			}//end catching URISyntaxException
        }//end else we should probably be able to process the file
    }//GEN-LAST:event_uxProcessAllBtnActionPerformed

    private void uxNextImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxNextImageBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uxNextImageBtnActionPerformed

    private void uxPrevImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxPrevImageBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uxPrevImageBtnActionPerformed

    private void uxOpenFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxOpenFileBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uxOpenFileBtnActionPerformed

    private void uxQueueListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_uxQueueListValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_uxQueueListValueChanged

    private void uxEmptyQueueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxEmptyQueueBtnActionPerformed
        imageQueue.clear();
        UpdateQueueList();
    }//GEN-LAST:event_uxEmptyQueueBtnActionPerformed

    private void uxSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxSearchBtnActionPerformed
        // reset the list before we mess with things
        UpdateQueueList();
        String searchString = uxSearchTxt.getText();
        List<String> goodSoFar = new ArrayList<String>();
        for (int i = 0; i < imageQueue.size(); i++) {
            String thisImageName = imageQueue.get(i).getName();
            if (thisImageName.contains(searchString)) {
                goodSoFar.add(thisImageName);
            }//end if we found a match
        }//end finding all matches for search string
        uxQueueList.removeAll();
        // add everything into an array
        String[] a = goodSoFar.toArray(new String[goodSoFar.size()]);
        uxQueueList.setListData(a);
    }//GEN-LAST:event_uxSearchBtnActionPerformed

    /**
     * THIS is the MAIN METHOD that the program should start from.
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton uxAddFilesBtn;
    private javax.swing.JMenuItem uxConnectScannerBtn;
    private javax.swing.JButton uxConnectToScannerBtn;
    private javax.swing.JButton uxEmptyQueueBtn;
    private javax.swing.JMenuItem uxIjBtn;
    private javax.swing.JLabel uxImageLabel;
    private javax.swing.JTextArea uxImagePropertiesTxt;
    private javax.swing.JMenu uxInitMenu;
    private javax.swing.JButton uxNextImageBtn;
    private javax.swing.JButton uxOpenFileBtn;
    private javax.swing.JTable uxOutputTable;
    private javax.swing.JButton uxPrevImageBtn;
    private javax.swing.JButton uxProcessAllBtn;
    private javax.swing.JList<String> uxQueueList;
    private javax.swing.JMenuItem uxResetScanner;
    private javax.swing.JMenu uxRunMenu;
    private javax.swing.JButton uxScanBigBtn;
    private javax.swing.JMenuItem uxScanBtn;
    private javax.swing.JButton uxScanQueueBtn;
    private javax.swing.JButton uxSearchBtn;
    private javax.swing.JTextField uxSearchTxt;
    private javax.swing.JTextArea uxStatusTxt;
    // End of variables declaration//GEN-END:variables
}
