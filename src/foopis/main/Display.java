package foopis.main;

import foopis.main.rooms.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;

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
    private LinkedList<Room> shownRooms;
    private Room currentRoom;
    private String TITLE = "TSG";
    private static final String VERSION = "v4.0 Alpha TEST";

    public Display(TSG tsg) {
        this.tsg = tsg;
        initComponents();
        frame = new JFrame(TITLE+VERSION);
        URL iconURL = getClass().getResource("/resources/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        frame.setIconImage(icon.getImage());
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

    private void getShownRooms()
    {
        LinkedList<Room> allRooms = tsg.getRooms();
        shownRooms  = new LinkedList<>();
        for(Room room: allRooms)
        {
            if(room.hasEntered())
            {
                shownRooms.add(room);
            }
        }

        currentRoom = tsg.getCurrentRoom();
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
        statScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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
        int padding = 5;
        Color mainColor = new Color(140, 140, 140);
        Color highlight = new Color(180, 180, 180);
        Color ladderColor = new Color(240, 240, 240);
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(tsg.running) {
                getShownRooms();


                int height = this.getHeight() - padding * 2;
                int width = this.getWidth() - padding * 2;
                int maxX = 0;
                int maxY = 0;
                int minX = 0;
                int minY = 0;
                for (Room room : shownRooms) {
                    int x = room.getX();
                    int y = room.getY();
                    maxX = Math.max(x, maxX);
                    maxY = Math.max(y, maxY);
                    minX = Math.min(x, minX);
                    minY = Math.min(y, minY);
                }

                int horizDist = maxX - minX + 1;
                int vertDist = maxY - minY + 1;

                int roomSize = Math.min(width / horizDist, height / vertDist);

                for (Room room : shownRooms) {
                    g.setColor(mainColor);

                    int x = room.getX();
                    int y = room.getY();
                    float centerX = maxX - minX / 2;
                    float centerY = maxY - minY / 2;

                    int roomX = (x - minX) * roomSize + width / 2 - roomSize * horizDist / 2 + padding;
                    int roomY = (y - minY) * roomSize + height / 2 - roomSize * vertDist / 2 + padding;

                    g.fillRect(roomX + roomSize / 4, roomY + roomSize / 4, roomSize / 2 + 1, roomSize / 2 + 1);

                    int[][] doorRects = {
                            {roomSize * 3 / 7, 0, roomSize / 7 + 1, roomSize / 4 + 1},
                            {roomSize * 3 / 4, roomSize * 3 / 7, roomSize / 4 + 1, roomSize / 7 + 1},
                            {roomSize * 3 / 7, roomSize * 3 / 4, roomSize / 7 + 1, roomSize / 4 + 1},
                            {0, roomSize * 3 / 7, roomSize / 4 + 1, roomSize / 7 + 1}
                    };

                    for (int i = 0; i < 4; i++) {
                        int[] rect = doorRects[i];
                        if (room.isExit(i)) {
                            g.fillRect(roomX + rect[0], roomY + rect[1], rect[2], rect[3]);
                        }
                    }

                    if (room == currentRoom) {
                        g.setColor(highlight);
                        g.fillRect(roomX + roomSize * 3 / 8, roomY + roomSize * 3 / 8, roomSize / 4 + 1, roomSize / 4 + 1);
                    }

                    if (room instanceof RoomLadder) {
                        g.setColor(ladderColor);
                        g.fillRect(roomX + roomSize * 7 / 16, roomY + roomSize * 7 / 16, roomSize / 8 + 1, roomSize / 8 + 1);
                    }
                }
                g.setColor(Color.WHITE);
                g.drawString(Integer.toString(tsg.player.getFloors()),1,this.getHeight()-1);
            }
        }
    }
}
