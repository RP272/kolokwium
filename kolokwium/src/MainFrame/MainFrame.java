package MainFrame;

import Drawing.Kanwa;

import javax.swing.*;

public class MainFrame extends JFrame {
    private int WIDTH = 810;
    private int HEIGHT = 600;
    public MainFrame(){
        super("Opadające kwadraty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        JPanel mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        Kanwa kanwa = new Kanwa(WIDTH, HEIGHT);
        kanwa.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.add(kanwa);
    }
}
