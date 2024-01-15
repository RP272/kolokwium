package Drawing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Square implements Runnable, ActionListener {
    private Graphics2D buffer;

    public int getX() {
        return x;
    }

    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private int y;

    public int getSize() {
        return size;
    }

    private int size;
    private int height_bound;
    private int delay;
    private int y_speed = 2;
    private Color color;
    private List<Square> neighbors = new ArrayList<>();
    private Random r = new Random();

    public boolean isGrounded() {
        return grounded;
    }

    private boolean grounded = false;
    public Square(Graphics2D buff, int x, int y, int size, int height_bound, int delay, List<Square> neighbors) {
        this.buffer = buff;
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        this.height_bound = height_bound;
        this.delay = delay;
        this.neighbors = neighbors;
    }

    @Override
    public void run() {
        while(this.y + this.y_speed + this.size < height_bound - this.size){
            for(Square s: neighbors){
                if(s != this){
                    if(this.getX() == s.getX() && this.y + this.size + this.y_speed >= s.getY()){
                        grounded = true;
                        return;
                    }
                }
            }
            this.y += this.y_speed;
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }
        grounded = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        buffer.setColor(this.color);
        buffer.fillRect(this.x, this.y, this.size, this.size);
    }
}
