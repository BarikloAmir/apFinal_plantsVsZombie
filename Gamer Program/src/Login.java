package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * login is a class that send your name to server or for change your name you use this class
 * this class has a login frame that you enter your name and send to server
 */
public class Login {

    private JFrame loginForm;
    private JButton loginOld;
    private JButton loginNew;
    private JTextField unameField;
    private JTextField newGamer;
    //result of check
    String result;

    //name
    String name;


    /**
     * constructor
     */
    public Login() {

        result = new String("false");


        //name of gamer
        name = "guest001";

        loginForm = new JFrame("LOGIN");
        loginForm.setLocationRelativeTo(null);
        //loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = loginForm.getX();
        int y = loginForm.getY();

        loginForm.setLocation(x-150,y-50);

        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        loginForm.setContentPane(panel);

        JLabel label = new JLabel(" Please Enter Your Information ");
        label.setBackground(Color.ORANGE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        label.setBorder(border);

        int labelWidth = label.getPreferredSize().width;
        int labelHeight = label.getPreferredSize().height + 10;
        label.setPreferredSize(new Dimension(labelWidth, labelHeight));

        //ButtonHandler handler = new ButtonHandler();

        JLabel changeLabel = new JLabel("change gamer name : ");
        unameField = new JTextField();

        //unameField.addActionListener(handler);
        //unameField.addFocusListener(handler);

        JLabel newGamerLabel = new JLabel("new gamer name : ");
        newGamer = new JTextField();

        loginOld = new JButton("Request to server");
        loginNew = new JButton("Request to server");

        JPanel fieldsPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        fieldsPanel.add(newGamerLabel);
        fieldsPanel.add(newGamer);
        fieldsPanel.add(loginNew);

        fieldsPanel.add(changeLabel);
        fieldsPanel.add(unameField);
        fieldsPanel.add(loginOld);

        loginForm.add(fieldsPanel);

        loginNew.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder requestNew = new StringBuilder();
                requestNew.append("new,");
                requestNew.append(newGamer.getText());
                name = newGamer.getText();
                System.out.println(name);
                String request = String.valueOf(requestNew);
                requestToServer(request);
            }
        });

        loginOld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder requestNew = new StringBuilder();
                requestNew.append("change,");
                System.out.println(name);
                requestNew.append(unameField.getText()+",");
                System.out.println("jkldsjlkfj"+name);
                requestNew.append(name);
                String request = String.valueOf(requestNew);
                requestToServer(request);
            }
        });
    }

    /**
     * show loginform
     */
        public void showGUI() {
        loginForm.pack();
        loginForm.setVisible(true);
    }

    /**
     * set loginform unvisible
     */
    public void UnShow(){
        loginForm.setVisible(false);
    }

    /**
     * send request to server
     * @param request
     */
    public void requestToServer(String request){
        try (Socket client = new Socket("127.0.0.1", 7660)) {
            System.out.println("Connected to server.");
            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();
            byte[] buffer = new byte[2048];
            while (true) {
                out.write(request.getBytes());
                System.out.println("SENT: " + request);
                int read = in.read(buffer);
                System.out.println("RECV: " + new String(buffer, 0, read));
                result = new String(buffer,0,read);
                System.out.println(result);
                UnShow();
                if(request.split(",")[0].equals("change") && !result.equals("sorry!"))
                    name = unameField.getText();
                JFrame errorForm = new JFrame();
                 if(request.split(",")[0].equals("change") && result.equals("sorry!"))
                     JOptionPane.showMessageDialog(errorForm, "wrong name.sorry!", "Error", JOptionPane.ERROR_MESSAGE);

                break;
            }

        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("done.");
    }



}
