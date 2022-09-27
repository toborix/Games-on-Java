package GUI.Clicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clicker implements ActionListener {

    int count = 0;
    private JLabel label = new JLabel("Number of clicks: 0");

    public Clicker(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

//        кнопка
        JButton button = new JButton("Click me");
        button.addActionListener(this);


//        то что находиться внутри формы: текст и кнопка
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 10, 50));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);


//        форма - выплывающее окно
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("My Clicker");
        frame.pack();
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks: " + count);
    }
    public static void main(String[] args) {
        new Clicker();

    }


}
