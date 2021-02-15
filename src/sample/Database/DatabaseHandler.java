package sample.Database;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName
                + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return connection;
    }

    public void signUpUser(User user) {

        String insert = "INSERT INTO " + Const.USER_TABLE + "("
                + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + ","
                + Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")"
                + "VALUES (?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getUserName());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getLocation());
            preparedStatement.setString(6,user.getGender());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
}
