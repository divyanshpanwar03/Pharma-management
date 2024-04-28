/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expiry_package;

import dao.ConnectionProvider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author welcome
 */
public class UpdateExpiry extends Expiry {
    public void change_expiry(Date date) {
    try (Connection con = ConnectionProvider.getCon();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery("SELECT barcode, expiration_date FROM drugs")) {

        while (rs.next()) {
            String barcode = rs.getString("barcode");
            Date expiration_date = rs.getDate("expiration_date");
            
            String expiryCheck = checkExpiry(expiration_date, date);
            long days = daysRemaining(expiration_date, date);
            System.out.println(days);
            java.sql.Date sqlExpirationDate = new java.sql.Date(expiration_date.getTime());

            if ("Not yet expired".equals(expiryCheck)) {
                // Update the expiry date if the row is not expired
                try (PreparedStatement ps = con.prepareStatement("UPDATE drugs SET expiry = ? WHERE barcode = ?")) {
                    // Correct the parameter indices
                    ps.setDate(1, sqlExpirationDate); // Setting expiry date as sql date
                    ps.setString(2, barcode); // Setting barcode
                    ps.executeUpdate();
                }
            } else if ("Expired".equals(expiryCheck)) {
                // Delete the row if expired
                try (PreparedStatement ps = con.prepareStatement("DELETE FROM drugs WHERE barcode = ?")) {
                    ps.setString(1, barcode); // Setting barcode for deletion
                    ps.executeUpdate();
                }
            }
        }
    } catch (Exception e) {
         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw);

    // Print the stack trace to the StringWriter
    e.printStackTrace(pw);

    // Convert the stack trace to a string
    String stackTrace = sw.toString();

    // Display the stack trace in a dialog box
    JOptionPane.showMessageDialog(null, stackTrace, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
