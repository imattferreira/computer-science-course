import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Angel implements ActionListener {
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Angel was called");
    }
}

class Demon implements ActionListener {
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Demon was called");
    }
}

public class Frame {
    public static void main(String[] args) {
        var frame = new JFrame();

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        var button = new JButton("Click me!");

        frame.getContentPane().add(button);

        var angel = new Angel();
        var demon = new Demon();



        button.addActionListener(angel);
        button.addActionListener(demon);
    }
}
