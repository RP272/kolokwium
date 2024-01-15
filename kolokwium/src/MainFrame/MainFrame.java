package MainFrame;

import Drawing.Kanwa;
import Drawing.Square;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        Kanwa kanwa = new Kanwa();
        kanwa.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.add(kanwa);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                if(e.getKeyCode() == 39){
                    Square s = kanwa.getActive_square();
                    if(s == null) return;
                    kanwa.getActive_square().setX( s.getX() + s.getSize() );
                }
                if(e.getKeyCode() == 37){
                    Square s = kanwa.getActive_square();
                    if(s == null) return;
                    kanwa.getActive_square().setX( s.getX() - s.getSize() );
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                kanwa.initialize();
            }
        });
        kanwa.animate();
    }
}
