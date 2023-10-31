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
    private JFileChooser scannedFileChooser = new JFileChooser();
    private JFileChooser ijProcFileChooser = new JFileChooser();
    private File lastScannedFile = null;
    private File lastIjProcFile = null;

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
        scannedFileChooser.addActionListener(scannedFileListener);
        ijProcFileChooser.addActionListener(ijProcFileListener);

        initComponents();
    }//end MainWindow constructor

    /**
     * Shows a pretty generic message box giving the name and message of supplied error. Uses JOptionPane
     * @param e The exception that was generated.
     */
    protected static void showGenericExceptionMessage(Exception e) {
        JOptionPane.showMessageDialog(null, "While attempting to find the scanner, the program encountered an exception of type " + e.getClass().getName() + ".\nThe exception message was " + e.getMessage(), "Error while initializing scan source.", JOptionPane.ERROR_MESSAGE);
    }//end genericExceptionMessage(e)

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uxScannedFileBtn = new javax.swing.JButton();
        uxScannedFileTxt = new javax.swing.JTextField();
        uxIJOutputBtn = new javax.swing.JButton();
        uxIJOutputTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        uxStatusTxt = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        uxInitMenu = new javax.swing.JMenu();
        uxConnectScannerBtn = new javax.swing.JMenuItem();
        uxResetScanner = new javax.swing.JMenuItem();
        uxRunMenu = new javax.swing.JMenu();
        uxScanBtn = new javax.swing.JMenuItem();
        uxIjBtn = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("usda-java-flour-scan");

        uxScannedFileBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxScannedFileBtn.setText("Scanned File");
        uxScannedFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxScannedFileBtnActionPerformed(evt);
            }
        });

        uxScannedFileTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxIJOutputBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxIJOutputBtn.setText("IJ Output File");
        uxIJOutputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxIJOutputBtnActionPerformed(evt);
            }
        });

        uxIJOutputTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxStatusTxt.setEditable(false);
        uxStatusTxt.setColumns(20);
        uxStatusTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxStatusTxt.setRows(5);
        jScrollPane1.setViewportView(uxStatusTxt);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uxIJOutputBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(uxScannedFileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uxIJOutputTxt)
                            .addComponent(uxScannedFileTxt))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uxScannedFileBtn)
                    .addComponent(uxScannedFileTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uxIJOutputBtn)
                    .addComponent(uxIJOutputTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uxScanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxScanBtnActionPerformed
        System.out.println("You clicked the \"Scan\" button.");
        // try to scan something with the scanner
        Result<String> scanResult = scan.runScanner();
        if (scanResult.isErr()) {
            showGenericExceptionMessage(scanResult.getError());
        }//end if we have an error to show
        else if (scanResult.isOk()) {
            String result = scanResult.getValue();
            lastScannedFile = new File(result);
            uxScannedFileTxt.setText(lastScannedFile.getPath());
        }//end else if scan result is ok
    }//GEN-LAST:event_uxScanBtnActionPerformed

    private void uxIjBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxIjBtnActionPerformed
        if (lastScannedFile == null) {
            JOptionPane.showMessageDialog(this, "Please select the image file generated by the scanner.", "No scanned image selected", JOptionPane.ERROR_MESSAGE);
        }//end if last scanned file is null
        else if (!lastScannedFile.exists()) {
            JOptionPane.showMessageDialog(this, "Please select a scanned image that exists. \nFile " + lastScannedFile.getAbsolutePath() + "\n does not exist.", "Scanned image file does not exist.", JOptionPane.ERROR_MESSAGE);
        }//end if last scanned file doesn't exist
        else {
            try {
                // TODO: add code for processing images in imagej
				IJProcess ijProcess = new IJProcess();
                Result<String> macroResult = ijProcess.runMacro(uxScannedFileTxt.getText());
                if (macroResult.isErr()) {
                    macroResult.getError().printStackTrace();
                    showGenericExceptionMessage(macroResult.getError());
                }
			} catch (URISyntaxException e) {
				e.printStackTrace();
                showGenericExceptionMessage(e);
			}//end catching URISyntaxException
        }//end else we should probably be able to process the file
    }//GEN-LAST:event_uxIjBtnActionPerformed

    private ActionListener scannedFileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand() == "ApproveSelection") {
                lastScannedFile = scannedFileChooser.getSelectedFile();
                uxScannedFileTxt.setText(lastScannedFile.getPath());
                uxStatusTxt.append("Selected scanned file \"" + lastScannedFile.getAbsolutePath() + "\"\n");
            }//end if selection was approved
            else if (e.getActionCommand() == "CancelSelection") {
                uxStatusTxt.append("File selection cancelled.\n");
            }//end else if selection was cancelled
        }//end actionPerformed
    };

    private ActionListener ijProcFileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            if (e.getActionCommand() == "ApproveSelection") {
                lastIjProcFile = ijProcFileChooser.getSelectedFile();
                uxIJOutputTxt.setText(lastIjProcFile.getPath());
                uxStatusTxt.append("Selected ij processed file \"" + lastIjProcFile.getAbsolutePath() + "\"\n");
            }//end if selection was approved
            else if (e.getActionCommand() == "CancelSelection") {
                uxStatusTxt.append("File selection cancelled\n");
            }//end else if selection was cancelled
        }//end actionPerformed
    };

    private void uxScannedFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxScannedFileBtnActionPerformed
        scannedFileChooser.showOpenDialog(this);
    }//GEN-LAST:event_uxScannedFileBtnActionPerformed

    private void uxIJOutputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxIJOutputBtnActionPerformed
        ijProcFileChooser.showOpenDialog(this);
    }//GEN-LAST:event_uxIJOutputBtnActionPerformed

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem uxConnectScannerBtn;
    private javax.swing.JButton uxIJOutputBtn;
    private javax.swing.JTextField uxIJOutputTxt;
    private javax.swing.JMenuItem uxIjBtn;
    private javax.swing.JMenu uxInitMenu;
    private javax.swing.JMenuItem uxResetScanner;
    private javax.swing.JMenu uxRunMenu;
    private javax.swing.JMenuItem uxScanBtn;
    private javax.swing.JButton uxScannedFileBtn;
    private javax.swing.JTextField uxScannedFileTxt;
    private javax.swing.JTextArea uxStatusTxt;
    // End of variables declaration//GEN-END:variables
}
