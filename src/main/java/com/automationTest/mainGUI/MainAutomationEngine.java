package com.automationTest.mainGUI;

import com.automationTest.utilites.globaldata.MyConfig;
import com.automationTest.mainThread.MainEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainAutomationEngine extends JFrame {
    private JPanel pnlMain;
    public Thread thrMainEngine;
    public static JTextArea txtOutput;
    public static JButton btnStart, btnStop;

    public MainAutomationEngine(String strTitle) {
        super(strTitle);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        pnlMain = new JPanel();
        pnlMain.setLayout(null);

        JLabel jLabel1 = new JLabel("Automation Engine", SwingConstants.CENTER);
        jLabel1.setFont (jLabel1.getFont ().deriveFont (32.0f));
        JLabel jLabel3 = new JLabel("Output : ");


        txtOutput = new JTextArea();
        JScrollPane scrOutput = new JScrollPane(txtOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        txtOutput.setEditable(false);

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        jLabel1.setBounds(200, 10, 400, 50);
        jLabel3.setBounds(15, 100, 100, 10);

        scrOutput.setBounds(15, 120, 750, 430);


        btnStart.setBounds(115, 75, 210, 30);
        btnStop.setBounds(335, 75, 210, 30);

        pnlMain.add(jLabel1);
        pnlMain.add(jLabel3);

        pnlMain.add(scrOutput);

        pnlMain.add(btnStart);
        pnlMain.add(btnStop);

        this.setContentPane(pnlMain);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainAutomationEngine.txtOutput.append("Report Folder : " + MyConfig.strPathFolderResultTesting + "\n");
                txtOutput.setText("");
                btnStart.setEnabled(false);

                thrMainEngine = new Thread(new MainEngine());
                thrMainEngine.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thrMainEngine.interrupt();
                thrMainEngine=null;

                btnStart.setEnabled(true);
            }
        });


        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    throw new RuntimeException("Exit manually.");
                } catch (RuntimeException ex) {
                    ex.printStackTrace();
                    System.exit(1);

                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame jfrMainFrame = new MainAutomationEngine("Automation Enggine (©Kevin Hertanto 2022)" );
        jfrMainFrame.setVisible(true);
        btnStart.doClick();

    }


}
