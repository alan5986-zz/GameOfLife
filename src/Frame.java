import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Frame extends JFrame {

    public Frame(int generations, int size) throws InterruptedException {

        Grid grid = new Grid(size);
        this.setSize((size*10)+20,(size*10)+40);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(grid);
        this.setVisible(true);

        for(int i = 0; i < generations; i++){
            //System.out.println("Epoch: " + i);
            TimeUnit.MILLISECONDS.sleep(100);
            grid.repaint();
        }




    }
}
