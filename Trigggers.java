
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Trigggers
{
    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL database
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Modify as per your database configuration
        String user = "your_username";
        String password = "your_password";

        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection(url, user, password);

            // Close the PreparedStatement
            insertStatement.close();

            // Create a PreparedStatement to execute the trigger logic
            String triggerQuery = "UPDATE drugs SET quantity = quantity - ? WHERE barcode = ? AND dose = ? AND name = ? AND type = ?";
            PreparedStatement triggerStatement = connection.prepareStatement(triggerQuery);
            triggerStatement.setInt(1, /* value for new.amount */);
            triggerStatement.setString(2, /* value for new.barcode */);
            triggerStatement.setString(3, /* value for new.dose */);
            triggerStatement.setString(4, /* value for new.name */);
            triggerStatement.setString(5, /* value for new.type */);
              triggerStatement.executeUpdate();

            // Close the PreparedStatement
            triggerStatement.close();

            // Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
