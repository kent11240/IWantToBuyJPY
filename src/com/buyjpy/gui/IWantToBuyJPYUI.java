package com.buyjpy.gui;

import com.buyjpy.service.BankService;
import com.buyjpy.util.FormatUtil;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class IWantToBuyJPYUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private static final HashMap<String, String> rateTable = new HashMap<>();
    private static final BankService bankService = new BankService();
    public static int time = 10000;

    private Timer timer = null;
    private final DefaultTableModel model;

    static {
        rateTable.put("nowSellRate", "");
        rateTable.put("previousSellRate", "");
        rateTable.put("nowBuyRate", "");
        rateTable.put("previousBuyRate", "");
        rateTable.put("nowBank", "");
        rateTable.put("previousBank", "");
        rateTable.put("nowCcd", "");
        rateTable.put("previousCcd", "");
    }

    public IWantToBuyJPYUI() {
        initComponents();
        model = (DefaultTableModel) dataTable.getModel();
        super.setLocationRelativeTo(null); //視窗起始位置置中
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bankGroup = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        startButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sinopacBtn = new javax.swing.JRadioButton();
        fubonBtn = new javax.swing.JRadioButton();
        esunBtn = new javax.swing.JRadioButton();
        ctcbBtn = new javax.swing.JRadioButton();
        botBtn = new javax.swing.JRadioButton();
        timeSpinner = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lightLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        msgTextPane = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        ccdComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("我要買日幣 v3.0");
        setMinimumSize(new java.awt.Dimension(553, 308));
        setResizable(false);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "時間", "即期買入", "即期賣出"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataTable.setGridColor(new java.awt.Color(150, 150, 150));
        jScrollPane2.setViewportView(dataTable);
        if (dataTable.getColumnModel().getColumnCount() > 0) {
            dataTable.getColumnModel().getColumn(0).setMinWidth(140);
            dataTable.getColumnModel().getColumn(0).setMaxWidth(140);
            dataTable.getColumnModel().getColumn(1).setMinWidth(65);
            dataTable.getColumnModel().getColumn(1).setMaxWidth(65);
            dataTable.getColumnModel().getColumn(2).setMinWidth(65);
            dataTable.getColumnModel().getColumn(2).setMaxWidth(65);
        }

        startButton.setText("啟動");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        cleanButton.setText("清除");
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        stopButton.setText("停止");
        stopButton.setEnabled(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("執行狀態：");

        statusLabel.setText("已停止");

        jLabel2.setText("選擇銀行：");

        bankGroup.add(sinopacBtn);
        sinopacBtn.setSelected(true);
        sinopacBtn.setText("永豐銀行");
        sinopacBtn.setActionCommand("Sinopac");

        bankGroup.add(fubonBtn);
        fubonBtn.setText("富邦銀行");
        fubonBtn.setActionCommand("Fubon");

        bankGroup.add(esunBtn);
        esunBtn.setText("玉山銀行");
        esunBtn.setActionCommand("Esun");

        bankGroup.add(ctcbBtn);
        ctcbBtn.setText("中國信託(已失效)");
        ctcbBtn.setActionCommand("Ctbc");
        ctcbBtn.setEnabled(false);

        bankGroup.add(botBtn);
        botBtn.setText("臺灣銀行");
        botBtn.setActionCommand("Bot");

        timeSpinner.setModel(new javax.swing.SpinnerNumberModel(10, 5, 60, 1));

        jLabel3.setText("時間間隔：");

        jLabel4.setText("秒(5-60)");

        lightLabel.setForeground(Color.RED);
        lightLabel.setText("●");

        jLabel5.setText("事件紀錄：");

        msgTextPane.setEditable(false);
        jScrollPane3.setViewportView(msgTextPane);

        jLabel6.setText("選擇幣別：");

        ccdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "JPY", "USD", "AUD", "SEK" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(stopButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cleanButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(esunBtn)
                                    .addComponent(ctcbBtn)
                                    .addComponent(botBtn))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(sinopacBtn)
                            .addComponent(fubonBtn))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lightLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(statusLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ccdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cleanButton, startButton, stopButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(ccdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(timeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(statusLabel)
                            .addComponent(lightLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sinopacBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fubonBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(esunBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ctcbBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cleanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cleanButton, startButton, stopButton});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        try {
            initSetting();
            setStartStatus();

            timer = new Timer(time, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getRate();
                    model.addRow(new Object[]{FormatUtil.formatDateTime(new Date()), rateTable.get("nowBuyRate"), rateTable.get("nowSellRate")});
                    dataTable.changeSelection(dataTable.getRowCount() - 1, 0, false, false);
                    isRateChanged();
                }
            });

            timer.setInitialDelay(0);
            timer.start();
        } catch (Exception ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        model.setRowCount(0);
        msgTextPane.setText("");
    }//GEN-LAST:event_cleanButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if (timer != null) {
            timer.stop();
        }
        setStopStatus();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void setStartStatus() {
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
        sinopacBtn.setEnabled(false);
        fubonBtn.setEnabled(false);
        esunBtn.setEnabled(false);
        ctcbBtn.setEnabled(false);
        botBtn.setEnabled(false);
        ccdComboBox.setEnabled(false);
        timeSpinner.setEnabled(false);
        statusLabel.setText("執行中");
        lightLabel.setForeground(Color.decode("#3bd23d"));
    }

    private void setStopStatus() {
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        sinopacBtn.setEnabled(true);
        fubonBtn.setEnabled(true);
        esunBtn.setEnabled(true);
        ctcbBtn.setEnabled(false);
        botBtn.setEnabled(true);
        ccdComboBox.setEnabled(true);
        timeSpinner.setEnabled(true);
        statusLabel.setText("已停止");
        lightLabel.setForeground(Color.RED);
    }

    private void initSetting() {
        time = (int) timeSpinner.getValue() * 1000;
    }

    private void isRateChanged() {
        //判斷是否切換銀行
//        System.out.println(rateTable.get("previousBank") + "+" + rateTable.get("nowBank"));
        if (!rateTable.get("previousBank").equals("")) {
            if (!rateTable.get("previousBank").equals(rateTable.get("nowBank"))) {
                return;
            }
        }
//        System.out.println(rateTable.get("previousCcd") + "+" + rateTable.get("nowCcd"));
        //判斷是否切換幣別
        if (!rateTable.get("previousCcd").equals("")) {
            if (!rateTable.get("previousCcd").equals(rateTable.get("nowCcd"))) {
                return;
            }
        }
        //判斷是否匯率變動
//        System.out.println(rateTable.get("previousSellRate") + "+" + rateTable.get("nowSellRate"));
        if (!rateTable.get("previousSellRate").equals("")) {
            double nowSellRate = Double.parseDouble(rateTable.get("nowSellRate"));
            double previousSellRate = Double.parseDouble(rateTable.get("previousSellRate"));
            StringBuilder msg = new StringBuilder();
            msg.append("[").append(FormatUtil.formatTime(new Date())).append("]").append(" 即期賣出:\n").append(rateTable.get("previousSellRate")).append(" --> ").append(rateTable.get("nowSellRate")).append("\n");
            if (nowSellRate > previousSellRate) {
                appendToPane(msgTextPane, msg.toString(), Color.red);
                toFront();
            } else if (nowSellRate < previousSellRate) {
                appendToPane(msgTextPane, msg.toString(), Color.decode("#3bd23d"));
                toFront();
            }
        }
//        System.out.println(rateTable.get("previousBuyRate") + "+" + rateTable.get("nowBuyRate"));
        if (!rateTable.get("previousBuyRate").equals("")) {
            double nowSellRate = Double.parseDouble(rateTable.get("nowBuyRate"));
            double previousSellRate = Double.parseDouble(rateTable.get("previousBuyRate"));
            StringBuilder msg = new StringBuilder();
            msg.append("[").append(FormatUtil.formatTime(new Date())).append("]").append(" 即期買入:\n").append(rateTable.get("previousBuyRate")).append(" --> ").append(rateTable.get("nowBuyRate")).append("\n");
            if (nowSellRate > previousSellRate) {
                appendToPane(msgTextPane, msg.toString(), Color.red);
                toFront();
            } else if (nowSellRate < previousSellRate) {
                appendToPane(msgTextPane, msg.toString(), Color.decode("#3bd23d"));
                toFront();
            }
        }
    }

    private void appendToPane(JTextPane tp, String msg, Color c) {
        tp.setEditable(true);

        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Consolas");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);

        tp.setEditable(false);
    }

    private void getRate() {
        String bank = bankGroup.getSelection().getActionCommand();
        
        rateTable.put("previousBank", rateTable.get("nowBank"));
        rateTable.put("nowBank", bank);
        rateTable.put("previousCcd", rateTable.get("nowCcd"));
        rateTable.put("nowCcd", ccdComboBox.getSelectedItem().toString());
        
        try {
            Method m = BankService.class.getDeclaredMethod("do" + bank, HashMap.class);
            m.invoke(bankService, rateTable);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IWantToBuyJPYUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new IWantToBuyJPYUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bankGroup;
    private javax.swing.JRadioButton botBtn;
    private javax.swing.JComboBox<String> ccdComboBox;
    private javax.swing.JButton cleanButton;
    private javax.swing.JRadioButton ctcbBtn;
    private javax.swing.JTable dataTable;
    private javax.swing.JRadioButton esunBtn;
    private javax.swing.JRadioButton fubonBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lightLabel;
    private javax.swing.JTextPane msgTextPane;
    private javax.swing.JRadioButton sinopacBtn;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton stopButton;
    private javax.swing.JSpinner timeSpinner;
    // End of variables declaration//GEN-END:variables
}
