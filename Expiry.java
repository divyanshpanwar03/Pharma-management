package expiry_package;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.Instant;
import java.util.Date;

public class Expiry {
    private String status;

    public String checkExpiry(Date expirationDate, Date currentDate) {

 

        // Compare dates
        int comparison = expirationDate.compareTo(currentDate);
        if (comparison < 0) {
            System.out.println("Expired");
            status = "Expired";
            return status;
        } else {
            System.out.println("Not yet expired");
            status = "Not yet expired";
            return status;
        }
    }

    public long daysRemaining(Date expirationDate, Date currentDate) {
    

        // Calculate days remaining
        long differenceInMillis = expirationDate.getTime() - currentDate.getTime();
        long daysBetween = differenceInMillis / (1000 * 60 * 60 * 24);
        return daysBetween;
    }
}
