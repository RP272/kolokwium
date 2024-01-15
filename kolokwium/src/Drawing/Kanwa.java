package Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kanwa extends JPanel implements ActionListener {
    private List<Square> squares = new ArrayList<>();
    private Random r = new Random();
    Image image;
    Graphics2D device;
    Graphics2D buffer;
    private Timer timer;
    private int delay = 10;
    private int square_size = 30;
    private List<Integer> x_spawning_points = new ArrayList<>();
    public Kanwa(int width, int height){
        setSize(width, height);
        setBackground(Color.YELLOW);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setLayout(null);
        timer = new Timer(delay, this);
    }

    public void initialize(){
        int width = getWidth();
        int height = getHeight();
        image = createImage(width, height);
        buffer = (Graphics2D) image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D) getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int start_x = 0;
        while(start_x < getWidth()){
            x_spawning_points.add(start_x);
            start_x += square_size;
        }

    }

    void addSquare(){
        int random_x = r.nextInt(x_spawning_points.size());
        Square s = new Square(random_x, -square_size, square_size, getHeight(), this.delay);
        squares.add(s);
        timer.addActionListener(s);
        new Thread(s).start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        try{
            for(Square s : squares){{
                s.draw(g2d);
            }}
        } finally {
            g2d.dispose();
        }
    }
}
