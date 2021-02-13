package swingpaint.viev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingPaint extends JFrame implements MouseMotionListener, ActionListener {
    private int x = 0;
    private int y = 0;
    private Color color = Color.BLACK;
    private int size = 5;

    public SwingPaint() {
        setTitle("Paint");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        container.addMouseMotionListener(this);
        container.setLayout(new BorderLayout());


        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new FlowLayout());
        toolPanel.setVisible(true);

        JButton red = new JButton("red");
        red.setBackground(Color.RED);
        JButton blue = new JButton("blue");
        blue.setBackground(Color.BLUE);
        JButton black = new JButton("black");
        black.setBackground(Color.BLACK);
        JButton green = new JButton("green");
        green.setBackground(Color.GREEN);

        JButton firstSize = new JButton("1px");
        JButton secondSize = new JButton("5px");
        JButton thirdSize = new JButton("10px");

        toolPanel.add(firstSize);
        firstSize.addActionListener(this);
        toolPanel.add(secondSize);
        secondSize.addActionListener(this);
        toolPanel.add(thirdSize);
        thirdSize.addActionListener(this);

        toolPanel.add(red);
        toolPanel.add(black);
        toolPanel.add(green);
        toolPanel.add(blue);
        red.setBounds(20, 20, 20, 20);

        red.addActionListener(this);
        black.addActionListener(this);
        blue.addActionListener(this);
        green.addActionListener(this);
        add(toolPanel, BorderLayout.EAST);

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
        g.fillOval(x + 10, y + 30, size, size);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String act = e.getActionCommand();
        switch (act) {
            case "blue":
                color = new Color(51, 51, 204);
                break;
            case "red":
                color = new Color(0x8B0606);
                break;
            case "green":
                color = new Color(0x04EC04);
                break;
            case "black":
                color = new Color(0x000000);
                break;
            case "1px":
                size = 1;
                break;
            case "5px":
                size = 5;
                break;
            case "10px":
                size = 10;
                break;
        }
    }

}
