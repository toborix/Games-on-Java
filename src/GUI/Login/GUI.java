package GUI.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    private static JTextField userText;
    private static JPasswordField passwordText;
    private static JLabel success;

    public static void main(String[] args) {
        JFrame frame = new JFrame(); // выплывающее окно
        JPanel panel = new JPanel(); // пространство внтури окна
        frame.setSize(270,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        /*
            Лэйбл для блока "User"
         */
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        /*
            Инпут для ввода текста
         */
        userText = new JTextField(20 );
        userText.setBounds(100, 20, 165, 25 );
        panel.add(userText);

        /*
            Лэйбл для  блока "Password"
         */
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100,50, 165, 25);
        panel.add(passwordText);

        JButton button = new JButton("Login");
        button.setBounds(10,80, 80, 25);
        button.addActionListener(new GUI());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10,110,330,25);
        panel.add(success);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
//        System.out.println(user + ", " + password);

        if (user.equals("") && password.equals("")){ // Добавь любой пароль и любой логин для проверки
            success.setText("Login successful");
        }
    }
}
