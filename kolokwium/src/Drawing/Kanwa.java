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

    public Square getActive_square() {
        return active_square;
    }

    private Square active_square = null;
    private Random r = new Random();
    Image image;
    Graphics2D device;
    Graphics2D buffer;
    private Timer timer;
    private Timer square_timer;
    private int delay = 10;
    private int square_size = 30;
    private List<Integer> x_spawning_points = new ArrayList<>();

    public Kanwa(){
        super();
        setBackground(Color.YELLOW);
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        timer = new Timer(delay, this);
        square_timer = new Timer(6000, null);
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
        addSquare();
        square_timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSquare();
            }
        });
        square_timer.start();
    }

    public void animate(){
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    void addSquare(){
        int random_x = r.nextInt(x_spawning_points.size());
        Square s = new Square(this.buffer, x_spawning_points.get(random_x), -square_size, square_size, getHeight(), this.delay, this.squares);
        active_square = s;
        squares.add(s);
        timer.addActionListener(s);
        new Thread(s).start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(active_square != null && active_square.isGrounded()){
            active_square = null;
        }
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
    }
}
