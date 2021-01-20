package swingpaint.viev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingPaint extends JFrame implements MouseMotionListener, ActionListener {
    private int x = 0;
    private int y = 0;
    private Color color = Color.BLACK;

    public SwingPaint() {
        setTitle("Paint");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.addMouseMotionListener(this);
        container.setLayout(new BorderLayout());

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(4, 1));

        JButton red = new JButton("red");
        red.setBackground(Color.RED);
        JButton blue = new JButton("blue");
        blue.setBackground(Color.BLUE);
        JButton black = new JButton("black");
        black.setBackground(Color.BLACK);
        JButton green = new JButton("green");
        green.setBackground(Color.GREEN);

        colorPanel.add(red);
        colorPanel.add(black);
        colorPanel.add(green);
        colorPanel.add(blue);

        red.addActionListener(this);
        black.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        add(colorPanel, BorderLayout.EAST);

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        repaint();
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(x + 10, y + 30, 4, 4);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        if (act.equals("blue")) {
            color = new Color(51, 51, 204);
        } else if (act.equals("red")) {
            color = new Color(0x8B0606);
        } else if (act.equals("green")) {
            color = new Color(0x04EC04);
        } else if (act.equals("black")) {
            color = new Color(0x000000);
        }
    }

}
