package foopis.main;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends javax.swing.JPanel implements KeyListener {

    private javax.swing.JLabel mapLabel;
    private mapPanel panel;
    private javax.swing.JScrollPane displayScroll;
    private javax.swing.JScrollPane statScroll;
    private javax.swing.JTextArea displayArea;
    private javax.swing.JTextArea statsArea;
    private javax.swing.JTextField input;
    private javax.swing.JFrame frame;
    private TSG tsg;

    public Display(TSG tsg) {
        this.tsg = tsg;
        initComponents();
        frame = new JFrame(TSG.TITLE+TSG.VERSION);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void append(String s)
    {
        displayArea.append(s);
    }

    public void clearDisplay()
    {
        displayArea.setText(null);
    }

    public void changeFont(Font f)
    {
        displayArea.setFont(f);
        input.setFont(f);
        statsArea.setFont(f);
        frame.setFont(f);
        mapLabel.setFont(f);
    }

    public void displayStats(String stats, String inventory)
    {
        statsArea.setText(null);
        statsArea.append(stats);
        statsArea.append("\n");
        statsArea.append(inventory);
    }

    public void redrawMap()
    {
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        displayScroll = new javax.swing.JScrollPane();
        displayArea = new javax.swing.JTextArea();
        input = new javax.swing.JTextField();
        input.addKeyListener(this);
        panel = new mapPanel();
        mapLabel = new javax.swing.JLabel();
        statScroll = new javax.swing.JScrollPane();
        statsArea = new javax.swing.JTextArea();

        displayArea.setEditable(false);
        displayArea.setColumns(20);
        displayArea.setRows(5);
        displayScroll.setViewportView(displayArea);

        DefaultCaret caret = (DefaultCaret)displayArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        panel.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 240, Short.MAX_VALUE)
        );

        mapLabel.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        mapLabel.setText("Map");

        statsArea.setEditable(false);
        statsArea.setColumns(20);
        statsArea.setRows(5);
        statScroll.setViewportView(statsArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(displayScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                                        .addComponent(input))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(mapLabel)
                                                .addGap(117, 117, 117))
                                        .addComponent(statScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(displayScroll)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(mapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(statScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))
                                .addContainerGap())
        );
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int k=e.getKeyCode();

        if(k==KeyEvent.VK_ENTER)
        {
            tsg.runAction(input.getText());
            input.setText(null);
        }
    }

    class mapPanel extends JPanel
    {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Draw Graphics Here/////////////////////////
            g.setColor(Color.WHITE);
            g.fillRect(20,20,20,20);
            g.drawString("OOF",50,50);
            ////////////////////////////////////////////
        }
    }
}
