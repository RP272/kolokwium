package Drawing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Square implements Runnable, ActionListener {
    private Graphics2D buffer;
    private int x, y, size, height_bound, delay;
    private Color color;
    private Random r = new Random();
    public Square(Graphics2D buff, int x, int y, int size, int height_bound, int delay) {
        this.buffer = buff;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        this.height_bound = height_bound;
        this.delay = delay;
    }

    @Override
    public void run() {
        while(this.y + this.size < height_bound){
            this.y += this.size;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buffer.setColor(this.color);
        buffer.fillRect(this.x, this.y, this.size, this.size);
    }
}
