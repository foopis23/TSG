package foopis.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Display extends JTextArea implements KeyListener
{
    private JFrame frame;
    private JPanel panel;
    private JTextField inputField;
    private JScrollPane scroll;
    private TSG tsg;
    private int history;

    Display(TSG tsg)
    {
        this.tsg = tsg;
        this.setText(null);
        initInput();
        initDisplay();
        initPanel();
        initFrame();
        inputField.requestFocus();
        this.setFont(new Font("Georgia",Font.TRUETYPE_FONT,12));
    }

    private  void initInput()
    {
        inputField = new JTextField();
        history = 0;
        inputField.addKeyListener(this);
        inputField.setMinimumSize(new Dimension(675,25));
        inputField.setPreferredSize(new Dimension(675,25));
        inputField.setMaximumSize(new Dimension(675,25));
    }

    private void initDisplay()
    {
        this.setLineWrap(false);
        this.setEditable(false);
        this.setVisible(true);

        scroll  = new JScrollPane (this);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setMinimumSize(new Dimension(675,325));
        scroll.setPreferredSize(new Dimension(675,325));
        scroll.setMaximumSize(new Dimension(675,325));
    }

    private void initPanel()
    {
        panel = new JPanel();
        panel.add(scroll);
        panel.add(inputField);
    }

    private void initFrame()
    {
        frame = new JFrame(TSG.TITLE+" "+TSG.VERSION);
        frame.setSize(700,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void changeFont(Font f)
    {
        frame.setFont(f);
        this.setFont(f);
        inputField.setFont(f);
    }

    @Override
    public void keyPressed(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            tsg.runAction(inputField.getText());
            inputField.setText(null);
            history=0;
        }

        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            history++;
            if(history>tsg.getHistorySize())
            {
                history = tsg.getHistorySize();
            }
            String s = tsg.getCommandHistory(history);
            inputField.setText(s);
        }

        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
            history--;
            if(history<0)
            {
                history=0;
            }
            String s = tsg.getCommandHistory(history);
            inputField.setText(s);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
