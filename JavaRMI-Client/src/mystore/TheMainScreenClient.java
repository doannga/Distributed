/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mystore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author NgaPC and DemTran
 */
public class TheMainScreenClient extends javax.swing.JFrame {

    /**
     * Creates new form TheMainScreenClient
     */
    private final boolean IS_DONE = false;
    private String currentPath;
    public String syncState;
    private InputStream is = null;
    private OutputStream os = null;
    private ClientInterface client;
    private ServerInterface server;
    private Thread syncThread;
    private Synchronization sync;

    public TheMainScreenClient(String rootFilePath1, ClientInterface client, ServerInterface server) {
        this.client = client;
        this.server = server;

        initComponents();

        tAre_show.append("Connected...\n");
        loadFile(tb_file, rootFilePath1);
        loadFolder(tb_folder, rootFilePath1);
        tAre_show.append(rootFilePath1 + "\n");

        currentPath = rootFilePath1;
        lb_currentPath.setText("Current Path: " + currentPath);

        syncThread = null;
        sync = null;

        tAre_show.update(tAre_show.getGraphics());
        DefaultCaret caret = (DefaultCaret) tAre_show.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startSync();
                    stopSync();
                } catch (RemoteException ex) {
                    tAre_show.append("Error auto synchronize!\n" + ex.getMessage());
                }
            }
        })).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lb_currentPath = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_folder = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_file = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        bt_newFolder = new javax.swing.JButton();
        bt_newFile = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tAre_show = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        bt_Syn = new javax.swing.JButton();
        bt_delete = new javax.swing.JButton();
        btn_Disconnect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));
        jPanel1.setForeground(new java.awt.Color(153, 255, 153));

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        lb_currentPath.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        lb_currentPath.setForeground(new java.awt.Color(255, 0, 0));
        lb_currentPath.setText("Current Path: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lb_currentPath, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_currentPath, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        tb_folder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tb_folder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tb_folder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name Folder", "Path"
            }
        ));
        tb_folder.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tb_folderInputMethodTextChanged(evt);
            }
        });
        jScrollPane1.setViewportView(tb_folder);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Folder:");

        tb_file.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Name File", "Length", "Created Date", "Path"
            }
        ));
        jScrollPane2.setViewportView(tb_file);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("File:");

        bt_newFolder.setBackground(new java.awt.Color(102, 255, 204));
        bt_newFolder.setText("New Folder");
        bt_newFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_newFolderActionPerformed(evt);
            }
        });

        bt_newFile.setBackground(new java.awt.Color(102, 255, 204));
        bt_newFile.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        bt_newFile.setText("COPPY FILE");
        bt_newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_newFileActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 255, 204));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton1.setText("NEW FILE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 255, 204));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 14)); // NOI18N
        jButton2.setText("LOADING..");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tAre_show.setColumns(20);
        tAre_show.setRows(5);
        jScrollPane4.setViewportView(tAre_show);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_newFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(bt_newFile, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_newFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_newFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        jPanel4.setBackground(new java.awt.Color(255, 204, 204));

        bt_Syn.setBackground(new java.awt.Color(102, 255, 204));
        bt_Syn.setText("Start");
        bt_Syn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_SynActionPerformed(evt);
            }
        });

        bt_delete.setBackground(new java.awt.Color(102, 255, 204));
        bt_delete.setText("Delete");
        bt_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_deleteActionPerformed(evt);
            }
        });

        btn_Disconnect.setBackground(new java.awt.Color(102, 255, 204));
        btn_Disconnect.setText("Disconnect");
        btn_Disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DisconnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_Syn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(bt_delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Disconnect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_Syn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(bt_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_Disconnect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_newFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newFolderActionPerformed
        String folderName = JOptionPane.showInputDialog(null, "Folder name: ");
        File f;
        tAre_show.append(folderName + "\n");
        f = new File(currentPath + "/" + folderName);
        if (f.mkdir()) {
            loadFolder(tb_folder, currentPath);
            loadFile(tb_file, currentPath);
        } else {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra vui lòng thử lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bt_newFolderActionPerformed

    private void bt_newFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_newFileActionPerformed
        JFileChooser x = new JFileChooser();
        while (true) {
            int returnvalue = x.showOpenDialog(null);
            if (returnvalue == JFileChooser.APPROVE_OPTION) {
                try {
                    String fileName = x.getSelectedFile().getName();
                    String filePath = x.getSelectedFile().getPath();
                    File f1 = new File(filePath);
                    File f2 = new File(currentPath, fileName);
                    tAre_show.append("Current Path: " + currentPath + '\n');
                    // xác định đầu ra và đầu vào  để còn coppy
                    is = new FileInputStream(f1);
                    os = new FileOutputStream(f2);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                    is.close();
                    os.close();
                    tAre_show.append("Copy successfully\n");
                    loadFolder(tb_folder, currentPath);
                    loadFile(tb_file, currentPath);
                    break;

                } catch (Exception e) {
                    tAre_show.append("Copy failed\n");
                }
            } else {
                tAre_show.append("Copy failed\n");
                break;
            }
        }
    }//GEN-LAST:event_bt_newFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String folderName = JOptionPane.showInputDialog(null, "Folder name: ");
        File f;
        tAre_show.append(folderName + "\n");
        f = new File(currentPath + "/" + folderName);
        try {
            if (f.createNewFile()) {
                loadFolder(tb_folder, currentPath);
                loadFile(tb_file, currentPath);
            } else {

            }        // TODO add your handling code here:
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "có lỗi sảy ra vui lòng thử lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void bt_SynActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_SynActionPerformed
        // TODO add your handling code here:
        if (bt_Syn.getText().equals("Start")) {
            try {
                tAre_show.append("Start sync...\n");
                startSync();
                loadFile(tb_file, currentPath);
                loadFolder(tb_folder, currentPath);
            } catch (RemoteException ex) {

            }
            bt_Syn.setText("Stop");
        } else if (bt_Syn.getText().equals("Stop")) {
            try {
                tAre_show.append("Stop sync\n");
                stopSync();
            } catch (RemoteException ex) {
                Logger.getLogger(TheMainScreenClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            bt_Syn.setText("Start");
        }
    }//GEN-LAST:event_bt_SynActionPerformed

    private void bt_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_deleteActionPerformed
        // TODO add your handling code here:
        int rowfolder = tb_folder.getSelectedRow();
        int rowfile = tb_file.getSelectedRow();
        if (rowfolder < 0 && rowfile < 0) {
            JOptionPane.showMessageDialog(null,
                    "You must choose a file or folder", "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (rowfile >= 0) {
                String pathDelFile = (String) tb_file.getValueAt(rowfile, 3);
                File delFile = new File(pathDelFile);
                delFile.delete();
                loadFile(tb_file, currentPath);
                loadFolder(tb_folder, currentPath);

            }
            if (rowfolder >= 0) {
                String pathDelFoder = (String) tb_folder.getValueAt(rowfolder, 1);
                File delFoder = new File(pathDelFoder);
                deleteFolder(delFoder);
                delFoder.delete();
                loadFile(tb_file, currentPath);
                loadFolder(tb_folder, currentPath);
            }
        }
    }//GEN-LAST:event_bt_deleteActionPerformed

    private void tb_folderInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tb_folderInputMethodTextChanged

    }//GEN-LAST:event_tb_folderInputMethodTextChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadFolder(tb_folder, currentPath);
        loadFile(tb_file, currentPath);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btn_DisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DisconnectActionPerformed
        try {
            server.disConnect(client);
        } catch (RemoteException ex) {
            Logger.getLogger(TheMainScreenClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_DisconnectActionPerformed

    public void loadFolder(JTable tb, String ROOT_FILE_PATH) {
        String[] title1 = new String[2];
        title1[0] = "Folder";
        title1[1] = "Path";
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(title1);// dong nay co the bo luon

        String[] link = new String[100];
        String[] linkName = new String[100];
        String[] array = new String[100];
        int i = 0;
        File f = new File(ROOT_FILE_PATH);
        File[] allsubFiles = f.listFiles();
        for (File file : allsubFiles) {
            if (file.isDirectory()) {
                link[i] = file.getAbsolutePath();
                linkName[i] = file.getName();
                array[0] = linkName[i];
                array[1] = link[i];
                i++;
                model.addRow(array);
            }
        }
        tb.setModel(model);
    }

    public void loadFile(JTable tb, String ROOT_FILE_PATH) {
        String[] title1 = new String[4];
        title1[0] = "File name";
        title1[1] = "Length";
        title1[2] = "Date Modified";
        title1[3] = "Path";
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(title1);
        String[] array = new String[100];
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        File f = new File(ROOT_FILE_PATH);
        File[] allSubFiles = f.listFiles();
        for (File file : allSubFiles) {
            if (!file.isDirectory()) {
                Long length = file.length();
                array[0] = file.getName();
                array[1] = length.toString();
                array[2] = sdf.format(file.lastModified());
                array[3] = file.getPath();
                model.addRow(array);
            }
        }
        tb.setModel(model);
    }

    private void stopSync() throws RemoteException {
        this.bt_Syn.setEnabled(false);
        sync.stopSync();
        if (syncThread.isAlive()) {
            while (syncThread.isAlive()) {
            }
            try {
                syncThread.join();
            } catch (InterruptedException ex) {
                tAre_show.append(("Error stopping syncThread\n"));
            }
        }
        this.bt_Syn.setEnabled(true);

    }

    private void startSync() throws RemoteException {
            File clientFile = new File(currentPath);
            File serverFile = server.getServerFile();
            this.sync = new Synchronization(IS_DONE, clientFile, serverFile, client, server);

            syncThread = new Thread(sync);
            syncThread.start();

    }

    public String getParentFolder(String currentPath) {

        int beginIndex = 0;
        for (int i = currentPath.length() - 1; i >= 0; i--) {
            if ((int) currentPath.charAt(i) == 92) {
                beginIndex = i;
                break;
            }
        }
        String parentFolder = currentPath.substring(beginIndex + 1);
        return parentFolder;
    }

    // delete folder xóa hết file trước rồi xóa phần còn lại
    public boolean deleteFolder(File delFolder) {
        if (delFolder.isDirectory()) {
            if (delFolder.list().length == 0) {
                delFolder.delete();
                return true;
            } else {
                File[] allSubFiles = delFolder.listFiles();
                for (File file : allSubFiles) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
        } else {
            delFolder.delete();
            return true;
        }
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TheMainScreenClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheMainScreenClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheMainScreenClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheMainScreenClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Syn;
    private javax.swing.JButton bt_delete;
    private javax.swing.JButton bt_newFile;
    private javax.swing.JButton bt_newFolder;
    private javax.swing.JButton btn_Disconnect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lb_currentPath;
    public static javax.swing.JTextArea tAre_show;
    private javax.swing.JTable tb_file;
    private javax.swing.JTable tb_folder;
    // End of variables declaration//GEN-END:variables

    public JTextArea getTAre_show() {
        return tAre_show;
    }

    public void setTAre_show(JTextArea tAre_show) {
        this.tAre_show = tAre_show;
    }

    public JTable getTb_file() {
        return tb_file;
    }

    public void setTb_file(JTable tb_file) {
        this.tb_file = tb_file;
    }

    public JTable getTb_folder() {
        return tb_folder;
    }

    public void setTb_folder(JTable tb_folder) {
        this.tb_folder = tb_folder;
    }
}
