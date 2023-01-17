package Main;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class GUI implements ActionListener {
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    private static String startArg;

    public static int currentUserId = 0;
    public static String currentUserRole = "";

    public void GUI(String startArg){
        this.startArg = startArg;
        JFrame frame = new JFrame();
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100,20,165,25);
        panel.add(userText);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10,80,80,25);
        button.addActionListener(new GUI());
        panel.add(button);

        button = new JButton("Register");
        button.setBounds(140,80,100,25);
        button.addActionListener(new GUI());
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10,110,300,25);
        panel.add(success);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        String buttonText=  "";
        Object o = e.getSource();
        JButton b = null;
        if(o instanceof JButton){
            b= (JButton) o;
        }
        if(b != null){
            buttonText = b.getText();
        }

        if(buttonText.equals("Login")){
            userText.setText("");
            passwordText.setText("");
           if(validateLogin(user,password) == true){
               loadConfigFromDB(user);
               getIDfromUser(user);
               getRolefromUser(user);
               Main.startGame(startArg,currentUserRole);

           }
        }
        else if(buttonText.equals("Register")){
            userText.setText("");
            passwordText.setText("");
            insertToDb(user,password);
        }

    }
    public Connection connectToDB(){
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }
    public boolean checkIfUserAlreadyExists(String user){
        boolean canInsert = true;
        String[] existingUsers = new String[100];
        int i =0;
        try{
        Connection connection = connectToDB();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT userName from `users` WHERE 1");
        while(rs.next()){
            existingUsers[i] = rs.getString(1);
            i++;
        }
        for(int j = 0; j<i;j++){
            if(existingUsers[j].equals(user)){
                canInsert = false;
                throw new customExceptions.userAlreadyExisting();
            }
        }

        }catch (Exception e){
            System.out.println(e);
        }
        return canInsert;
    }
    public void insertToDb(String user, String passwordUser){
        String url = "jdbc:mysql://localhost:3306/jdbcdemo";
        String username = "root";
        String password = "";
        boolean possibleInsert = true;
        if(user.equals("") || passwordUser.equals("")){
            possibleInsert = false;
        }
        possibleInsert = checkIfUserAlreadyExists(user);
        String userToInsert ="'"+user+"'";
        String passwordToInsert = "'" + passwordUser + "'";
        String role = "'user'";
        try{
            if(user.equals("") || passwordUser.equals("|")){
                throw new customExceptions.userOrPassEmpty();
            }
            if(user.length() < 4){
                possibleInsert = false;
                throw new customExceptions.tooShortUser();
            }
            if(password.length() < 4){
                possibleInsert = false;
                throw new customExceptions.tooShortPass();
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            String query1 = "INSERT INTO `users` " + "VALUES (null," +userToInsert+","+passwordToInsert+","+role+");";
            String query2 = "INSERT INTO `config`" + "VALUES (null,3,3);";
            if(possibleInsert == true) {
                PreparedStatement stmt = connection.prepareStatement(query1);
                stmt.executeUpdate(query1);
                PreparedStatement stmt2 = connection.prepareStatement(query2);
                stmt2.executeUpdate(query2);
                connection.close();
            }
            else{
                System.out.println("Not a valid user!");
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public boolean validateLogin(String user,String password){
        String extractedPass = "";
        boolean canStartGame = false;
        try{
            Connection connection = connectToDB();
            Statement stmt = connection.createStatement();
            String query = "SELECT password FROM `users` WHERE userName='"+user+"';";
            ResultSet rs = stmt.executeQuery(query);
            if(user.equals("") || password.equals("")){
                throw new customExceptions.userOrPassEmpty();
            }
            while (rs.next()){
                extractedPass = rs.getString(1);
            }
            if(password.equals(extractedPass)){
                canStartGame = true;
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return canStartGame;
    }

    public void getIDfromUser(String user){
        try{
            Connection connection = connectToDB();
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM `users` WHERE userName='"+user+"';";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                currentUserId = Integer.parseInt(rs.getString(1));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void getRolefromUser(String user){
        try{
            Connection connection = connectToDB();
            Statement stmt = connection.createStatement();
            String query = "SELECT role FROM `users` WHERE userName='"+user+"';";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                currentUserRole = rs.getString(1);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void loadConfigFromDB(String user){
        String extractedID = "";
        String musicVolume = "",sfxVolume = "";
        int i = 0;
        try{
            Connection connection = connectToDB();
            Statement stmt = connection.createStatement();
            String query = "SELECT id FROM `users` WHERE userName='"+user+"';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                extractedID = rs.getString(1);
            }
            String query1 = "SELECT * FROM `config` WHERE id='"+extractedID+"';";
            Statement stmt1 = connection.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
            while (rs1.next()){
                musicVolume = rs1.getString(2);
                sfxVolume = rs1.getString(3);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));
            bw.write(String.valueOf(musicVolume));
            bw.newLine();
            bw.write(String.valueOf(sfxVolume));

            bw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void saveConfigToDB(int volume,int sfx){
        String volume1 = "'"+String.valueOf(volume)+"'";
        String sfx1 = "'"+String.valueOf(sfx)+"'";
        System.out.println(volume1 + "," +sfx1);
        System.out.println("Current user ID:"+currentUserId);
        try{
            Connection connection = connectToDB();
            Statement stmt = connection.createStatement();
            String query = "UPDATE `config` SET `musicVolume`="+volume1+",`sfxVolume`="+sfx1+"WHERE `config`.`id`="+currentUserId+";";
            stmt.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
