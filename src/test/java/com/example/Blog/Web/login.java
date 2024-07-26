import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog{
    private JTextField txtEmail;
    private JPasswordField txtpassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel LoginPanel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                String password = String.valueOf(txtpassword.getPassword());

                user = getAuthenticateUser(email, password);
                if(user != null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this, "Email or Password Invalid", "Try Again", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public static Users user;
    private Users getAuthenticateUser(String email, String password){
        Users user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/myaccount?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD ="";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email =? AND password =?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                user = new Users();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.password = resultSet.getString("password");
                user.address = resultSet.getString("address");
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return user;
    }

    public static void main(String[] args){
        LoginForm frame = new LoginForm(null);
        Users user = LoginForm.user;
        if (user != null){
            System.out.println("Login Successful"+user.name);
            System.out.println("Hieeus ngu");
        }
    }
}
