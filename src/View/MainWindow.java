/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import IJM.IJProcess;
import IJM.SumResult;
import IJM.SumResult.LeftOrRight;
import Scan.Scan;
import Utils.Constants;
import Utils.Result;
import Utils.Result.ResultType;
import ij.IJ;

/**
 *
 * @author Nicholas.Sixbury
 */
public class MainWindow extends javax.swing.JFrame {

    protected Scan scan = null;
    private JFileChooser selectFilesChooser = new JFileChooser();
    private File lastScannedFile = null;
    private List<File> imageQueue = new ArrayList<File>();
    private List<File> allImages = new ArrayList<File>();
    private IJProcess ijProcess = new IJProcess();
    // where displayed image was last selected from
    private LastSelectedFrom lastSelectedFrom = LastSelectedFrom.NoSelection;
    // dialog boxes we can re-use
    private AreaFlagDialog areaFlagDialog = new AreaFlagDialog(this, true);
    private ThresholdDialog thresholdDialog = new ThresholdDialog(this, true);
    // progress bar for imagej processing
    ProgressMonitor progressMonitor;
    // task for background work
    IJTask ijTask = new IJTask(imageQueue, ijProcess);

    /**
     * enum added for use in keeping track of whether displayed image was selected from QueueList or OutputTable
     */
    private enum LastSelectedFrom {
        QueueList,
        OutputTable,
        NoSelection
    }

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

        // build title block
        StringBuilder tb = new StringBuilder();
        tb.append(Constants.LOCATION);
        tb.append("\t");
        tb.append(Constants.PROGRAM_NAME);
        tb.append("\n");
        tb.append(Constants.DATE());
        tb.append("\t");
        tb.append(Constants.VERSION);
        tb.append("\t");
        tb.append(Constants.PEOPLE);
        tb.append("\n");
        tb.append("To interface with EPSON V600 Scanner\n");
        tb.append("To collect reflective image of flour sample in a 100mm diameter petri dish\n");
        tb.append("Process image to estimate %contamination and L* color from CIELAB color space");
        uxTitleBlockTxt.setText(tb.toString());

        // set columns in output text
        // uxOutputTxt.setText(String.format("%-16s  %-3s  %-7s  %-7s  %-4s  %-7s  %-7s  %-4s  %-6s\n","FileID","TH","L_Count", "L_%Area","L_L*","R_Count","R_%Area","R_L*","Avg_%Area"));

        // configure the table model
        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int idx = uxOutputTable.getSelectedRow();
                    String selected_filename = uxOutputTable.getModel().getValueAt(idx, 0).toString();
                    // call method to update image label
                    updateImageDisplay(selected_filename);
                    // update properties display
                    uxImagePropertiesTxt.setText("Image: " + selected_filename + "\nSelected From: OutputTable[" + idx + "]");
                    // update flags and such
                    lastSelectedFrom = LastSelectedFrom.OutputTable;
                }//end if the value is done adjusting
            }//end valueChanged(e)
        };
        uxOutputTable.getSelectionModel().addListSelectionListener(lsl);
        // Object[] columnNames = {""};
        // Object[][] rowData = new Object[][] {
        //     {"","","","","","","","",""}
        // };
        // dtm = new DefaultTableModel(rowData, columnNames);
        // uxOutputTable.setModel(dtm);
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
        jMenuItem1 = new javax.swing.JMenuItem();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        uxTitleBlockTxt = new javax.swing.JTextArea();
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
        uxClearOutputBtn = new javax.swing.JButton();
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
        jMenu1 = new javax.swing.JMenu();
        uxSetThresholdMenuBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        uxSetAreaFlagMenuBtn = new javax.swing.JMenuItem();

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

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("USDA-ARS-FlourScan-Java");

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerLocation(525);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSplitPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane2.setDividerLocation(325);
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

        uxTitleBlockTxt.setEditable(false);
        uxTitleBlockTxt.setColumns(20);
        uxTitleBlockTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxTitleBlockTxt.setRows(5);
        jScrollPane6.setViewportView(uxTitleBlockTxt);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
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
            .addComponent(jScrollPane6)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
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
        uxQueueList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
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
        jSplitPane3.setDividerLocation(325);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxImagePropertiesTxt.setEditable(false);
        uxImagePropertiesTxt.setColumns(1);
        uxImagePropertiesTxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxImagePropertiesTxt.setRows(1);
        uxImagePropertiesTxt.setEnabled(false);
        uxImagePropertiesTxt.setPreferredSize(new java.awt.Dimension(102, 84));
        jScrollPane3.setViewportView(uxImagePropertiesTxt);

        uxPrevImageBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxPrevImageBtn.setText("Previous Image");
        uxPrevImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxPrevImageBtnActionPerformed(evt);
            }
        });

        uxNextImageBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxNextImageBtn.setText("Next Image");
        uxNextImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxNextImageBtnActionPerformed(evt);
            }
        });

        uxOpenFileBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxOpenFileBtn.setText("Open in File Explorer");
        uxOpenFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxOpenFileBtnActionPerformed(evt);
            }
        });

        uxImageLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxImageLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxClearOutputBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxClearOutputBtn.setText("Clear Output Table");
        uxClearOutputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxClearOutputBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(uxPrevImageBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(uxNextImageBtn))
                        .addComponent(uxOpenFileBtn)
                        .addComponent(jScrollPane3))
                    .addComponent(uxClearOutputBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uxImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uxImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uxPrevImageBtn)
                            .addComponent(uxNextImageBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxOpenFileBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(uxClearOutputBtn)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );

        jSplitPane3.setTopComponent(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        uxOutputTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FileID", "TH", "left cnt", "rght cnt", "left L*", "rght L*", "Avg L*", "left %A", "rght %A", "Avg %A", "Flag"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        uxOutputTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        uxOutputTable.setColumnSelectionAllowed(true);
        uxOutputTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        uxOutputTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        uxOutputTable.setShowGrid(true);
        jScrollPane5.setViewportView(uxOutputTable);
        uxOutputTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (uxOutputTable.getColumnModel().getColumnCount() > 0) {
            uxOutputTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            uxOutputTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
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

        jMenu1.setText("Threshold");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxSetThresholdMenuBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxSetThresholdMenuBtn.setText("Set Threshold");
        uxSetThresholdMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxSetThresholdMenuBtnActionPerformed(evt);
            }
        });
        jMenu1.add(uxSetThresholdMenuBtn);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Area Flag");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        uxSetAreaFlagMenuBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        uxSetAreaFlagMenuBtn.setText("Set %Area Flags");
        uxSetAreaFlagMenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uxSetAreaFlagMenuBtnActionPerformed(evt);
            }
        });
        jMenu2.add(uxSetAreaFlagMenuBtn);

        jMenuBar1.add(jMenu2);

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
            .addGroup(layout.createSequentialGroup()
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
                    uxStatusTxt.append("\"" + selectedFiles[i].getAbsolutePath() + "\"\n");
                    imageQueue.add(selectedFiles[i]);
                    allImages.add(selectedFiles[i]);
                }//end adding each selected file to the queue
            }//end if selection was approved
            else if (e.getActionCommand() == "CancelSelection") {
                uxStatusTxt.append("File selection cancelled.\n");
            }//end else if selection was cancelled
        }//end actionPerformed
    };

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
            allImages.add(scanResult.getValue());
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
                // tell user we're about to do processing
                // JOptionPane.showMessageDialog(this, "Please wait. Your images will now be processed.");
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                ijProcess.th01 = thresholdDialog.thresholdToReturn;
                // set up progress bar
                progressMonitor = new ProgressMonitor(this, "Progress!", "", 0, 5);
                progressMonitor.setProgress(3);
                progressMonitor.setMillisToDecideToPopup(0);
                progressMonitor.setMillisToPopup(0);
                // actually run the imagej stuff

                // roll over area flag stuff to the processing
                IJProcess.lower_flag_thresh = areaFlagDialog.firstFlag;
                IJProcess.upper_flag_thresh = areaFlagDialog.secondFlag;

                Result<String> outputData = ijTask.doInBackground();
                if (ijTask.isDone()) {
                    setCursor(Cursor.getDefaultCursor());
                }//end if the task is done
                // SwingUtilities.invokeLater(
                //     () -> JOptionPane.showMessageDialog(this, "Your images have finsihed processing.")
                // );
                // progressDialog.setVisible(false);
                if (outputData.isErr()) {
                    outputData.getError().printStackTrace();
                    showGenericExceptionMessage(outputData.getError());
                }//end if we couldn't get output data
                // group together SumResults which came from the same file path
                List<List<SumResult>> groupedResults = SumResult.groupResultsByFile(ijProcess.lastProcResult);
                // process sumResults into string columns
                updateOutputTable(groupedResults);
                // clear queue now that it's been processed
                imageQueue.clear();
                UpdateQueueList();
                // make sure cursor is updated
                setCursor(Cursor.getDefaultCursor());
			} catch (Exception e) {
				e.printStackTrace();
                showGenericExceptionMessage(e);
			}//end catching URISyntaxException
        }//end else we should probably be able to process the file
    }//GEN-LAST:event_uxProcessAllBtnActionPerformed

    private void uxNextImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxNextImageBtnActionPerformed
        if (lastSelectedFrom == LastSelectedFrom.QueueList) {
            int curIdx = uxQueueList.getSelectedIndex();
            if (curIdx + 1 < uxQueueList.getModel().getSize() && curIdx + 1 >= 0) {
                uxQueueList.setSelectedIndex(curIdx + 1);
            }//end if there's another index to go to
        }//end if last selection was from queue list
        else if (lastSelectedFrom == LastSelectedFrom.OutputTable) {
            int curIdx = uxOutputTable.getSelectedRow();
            if (curIdx + 1 < uxOutputTable.getRowCount() && curIdx + 1 >= 0) {
                uxOutputTable.changeSelection(curIdx + 1, 0, false, false);
            }//end if there's another index to go to
        }//end else if last selection was from output table
    }//GEN-LAST:event_uxNextImageBtnActionPerformed

    private void uxPrevImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxPrevImageBtnActionPerformed
        if (lastSelectedFrom == LastSelectedFrom.QueueList) {
            int curIdx = uxQueueList.getSelectedIndex();
            if (curIdx - 1 >= 0) {
                uxQueueList.setSelectedIndex(curIdx - 1);
            }//end if there's another index to go to
        }//end if last selection was from queue list
        else if (lastSelectedFrom == LastSelectedFrom.OutputTable) {
            int curIdx = uxOutputTable.getSelectedRow();
            if (curIdx - 1 >= 0) {
                uxOutputTable.changeSelection(curIdx - 1, 0, false, false);
            }//end if there's another index to go to
        }//end else if last selection was from output table
    }//GEN-LAST:event_uxPrevImageBtnActionPerformed

    private void uxOpenFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxOpenFileBtnActionPerformed
        // open the file in file explorer
        try {
            String selectedValue = "";
            if (lastSelectedFrom == LastSelectedFrom.QueueList) {selectedValue = uxQueueList.getSelectedValue();}
            else if (lastSelectedFrom == LastSelectedFrom.OutputTable) {selectedValue = uxOutputTable.getModel().getValueAt(uxOutputTable.getSelectedRow(), 0).toString();}
            File imageMatch = getSelectedFileFromAll(selectedValue);
            if (imageMatch == null) {JOptionPane.showMessageDialog(this, "Could not find file that matches selected image, or no image selected.");}
            Runtime.getRuntime().exec("explorer.exe /select," + imageMatch.getAbsolutePath());
        }//end trying to open file explorer
        catch(Exception e) {System.out.println("Couldn't open file explorer");}
    }//GEN-LAST:event_uxOpenFileBtnActionPerformed

    /**
     * Displays the newly selected file image and results to the right. 
     * Due to how all the architecture is currently set up, this is incredibly awkward.
     * @param evt The list selection events
     */
    private void uxQueueListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_uxQueueListValueChanged
        if (!evt.getValueIsAdjusting() && uxQueueList.getSelectedIndex() != -1) {
            // update the displayed image
            updateImageDisplay(uxQueueList.getSelectedValue());
            // update image properties text
            uxImagePropertiesTxt.setText("Image: " + uxQueueList.getSelectedValue() + "\nSelected From: QueueList[" + uxQueueList.getSelectedIndex() + "]");
            // update some flags and such
            lastSelectedFrom = LastSelectedFrom.QueueList;
        }//end if we're not in a series of adjustments?
    }//GEN-LAST:event_uxQueueListValueChanged

    /**
     * This method updates the image label with the image which has the specified filename (as from File.getName())
     * @param filename
     */
    private void updateImageDisplay(String filename) {
        // pick throug the list of files that have been loaded into queue to find the one that matches the selected file name
        File imageMatch = getSelectedFileFromAll(filename);
        // edge case validation
        if (imageMatch == null) {JOptionPane.showMessageDialog(this, "Could not find matching file for selection."); return;}
        // display the image in the label
        ImageIcon icon = scaleImageToIcon(imageMatch);
        if (icon == null) {JOptionPane.showMessageDialog(this, "Could not read selected image to buffer."); return;}
        uxImageLabel.setIcon(icon);
        // // ensure that some files have been processed, at least
        // if (ijProcess.lastProcResult == null) {System.out.println("Please process files before displaying results."); return;}
        // // try and build a list of SumResults that match the file path
        // List<SumResult> matchingResults = new ArrayList<SumResult>();
        // for (int i = 0; i < ijProcess.lastProcResult.size(); i++) {
        //     if (ijProcess.lastProcResult.get(i).file.getAbsolutePath().equals(imageMatch.getAbsolutePath())) {
        //         matchingResults.add(ijProcess.lastProcResult.get(i));
        //     }//end if we found a matching result
        // }//end looking for SumResults matching imageMatch
        // // check that we found some results
        // if (matchingResults.size() == 0) {JOptionPane.showMessageDialog(this, "Could not locate results for selected file."); return;}
        // // display the results that we found in the img info and the table
        // uxImagePropertiesTxt.setText("Image: " + imageMatch.getName());
    }//end updateImageDisplay(filename)

    /**
     * A helper method written for uxQueueListValueChange(). This method loops through all 
     * the files in the queue until it finds one whose name matches the file name selected in uxQueueList.
     * @return This method returns the File that matches if found, or null if it couldn't find a match.
     */
    private File getSelectedFileFromAll(String filename) {
        // search through imageQueue for File which matches
        File imageMatch = null;
        for (File this_image : allImages) {
            if (this_image.getName().equals(filename)) {
                imageMatch = this_image;
                break;
            }//end if we found a match
        }//end looping over all images
        return imageMatch;
    }//end getSelectedFileFromQueue()

    /**
     * This method was written as a helper method for uxQueueListValueChanged(). This method reads an image File into memory as
     * a BufferedImage, and then converts that image into an Icon which has been scaled down to fit in the window. 
     * @param imageFile The File representing an image file to be opened and displayed.
     * @return Returns an ImageIcon if the file is found. Otherwise, returns null if we can't open the image.
     */
    private ImageIcon scaleImageToIcon(File imageFile) {
        BufferedImage buf_img = IJ.openImage(imageFile.getAbsolutePath()).getBufferedImage();
        if (buf_img == null) {return null;}
        // It would maybe be good to improve image scaling at some point
        /*
         * It would maybe be good to improve image scaling at some point, as currently, 
         * in order to resize the image, you have to select a different file, which is pretty jank.
         * 
         * The reason we scale the image to less than the size of the label container is that if you set the iamge to the same
         * width and height as that of the container, then the image will be slightly larger than the label, so every time a new
         * image is selected, the size of the label will just continually grow in size. But, if you set the size to 95% or 99%
         * the size of the label, then that doesn't happen for some reason.
         */
        int imgWidth = buf_img.getWidth();
        int imgHeight = buf_img.getHeight();
        if (imgWidth > uxImageLabel.getWidth()) {
            int newImgWidth = (int)((double)uxImageLabel.getWidth() * 0.95);
            int newImgHeight = newImgWidth * imgHeight / imgWidth;
            imgWidth = newImgWidth;
            imgHeight = newImgHeight;
        }//end if we need to scale down because of width
        if (imgHeight > uxImageLabel.getHeight()) {
            int newImgHeight = (int)((double)uxImageLabel.getHeight() * 0.95);
            int newImgWidth = imgWidth * newImgHeight / imgHeight;
            imgHeight = newImgHeight;
            imgWidth = newImgWidth;
        }//end if we need to scale down because of height
        return new ImageIcon(new ImageIcon(buf_img).getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_DEFAULT));
    }//end scaleImageToIcon(imageFile)

    private void updateOutputTable(List<List<SumResult>> groupedResults) {
        DefaultTableModel this_table_model = (DefaultTableModel)uxOutputTable.getModel();
        for (List<SumResult> resultGroup : groupedResults) {
            // check that we have valid left and right results
            SumResult left = null;
            SumResult right = null;
            for (SumResult tempResult : resultGroup) {
                if (tempResult.leftOrRight == LeftOrRight.Left) {left = tempResult;}
                else if (tempResult.leftOrRight == LeftOrRight.Right) {right = tempResult;}
            }//end categorizing all of result group
            if (left != null && right != null) {
                double this_avg_l = (left.l_mean + right.l_mean) / 2.0;
                double this_avg_area = (left.percent_area + right.percent_area) / 2.0;
                Object[] this_row = new Object[11];
                this_row[0] = left.file.getName();
                this_row[1] = left.threshold;
                this_row[2] = left.count;
                this_row[3] = right.count;
                this_row[4] = String.format("%3.1f", left.l_mean);
                this_row[5] = String.format("%3.1f", right.l_mean);
                this_row[6] = String.format("%3.1f", this_avg_l);
                this_row[7] = String.format("%3.2f", left.percent_area);
                this_row[8] = String.format("%3.2f", right.percent_area);
                this_row[9] = String.format("%3.2f", this_avg_area);
                if (this_avg_area > areaFlagDialog.firstFlag) {this_row[10] = "x";}
                if (this_avg_area > areaFlagDialog.secondFlag) {this_row[10] = "xx";}
                this_table_model.addRow(this_row);
            }//end if we have proper left and right result
            else {
                // TODO: Handle exception case
            }//end else we need to figure out what to do
        }//end looping over each result group
    }//end updateOutputTable(groupedResults)

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

    private void uxSetAreaFlagMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxSetAreaFlagMenuBtnActionPerformed
        areaFlagDialog.setVisible(true);
    }//GEN-LAST:event_uxSetAreaFlagMenuBtnActionPerformed

    private void uxSetThresholdMenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxSetThresholdMenuBtnActionPerformed
        thresholdDialog.setVisible(true);
    }//GEN-LAST:event_uxSetThresholdMenuBtnActionPerformed

    private void uxClearOutputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uxClearOutputBtnActionPerformed
        DefaultTableModel this_table_model = (DefaultTableModel)uxOutputTable.getModel();
        this_table_model.setRowCount(0);
    }//GEN-LAST:event_uxClearOutputBtnActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton uxAddFilesBtn;
    private javax.swing.JButton uxClearOutputBtn;
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
    private javax.swing.JMenuItem uxSetAreaFlagMenuBtn;
    private javax.swing.JMenuItem uxSetThresholdMenuBtn;
    private javax.swing.JTextArea uxStatusTxt;
    private javax.swing.JTextArea uxTitleBlockTxt;
    // End of variables declaration//GEN-END:variables
}
