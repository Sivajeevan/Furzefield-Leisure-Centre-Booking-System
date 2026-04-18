/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flc.Booking;
import java.util.*;
/**
 *
 * @author Admin
 */

public class ReportService {

  
    // MONTHLY LESSON REPORT
   
    public static void lessonReport(List<Lesson> lessons) {

        for (Lesson l : lessons) {

            int attended = 0;
            int totalRating = 0;

            for (Booking b : l.getBookings()) {

                if (b.getStatus() == BookingStatus.ATTENDED) {
                    attended++;
                    totalRating += b.getReview().getRating();
                }
            }

            double avg = (attended == 0) ? 0 : (double) totalRating / attended;

            System.out.printf(
                "%-10s | Attended: %d | Avg Rating: %.2f\n",
                l.getType(), attended, avg
            );
        }
    }

   
    // CHAMPION REPORT
  
    public static void championReport(List<Lesson> lessons) {

        Map<String, Double> incomeMap = new HashMap<>();

        for (Lesson l : lessons) {

            double income = l.getBookings().size() * l.getPrice();

            incomeMap.put(
                l.getType(),
                incomeMap.getOrDefault(l.getType(), 0.0) + income
            );
        }

        String topExercise = "";
        double maxIncome = 0;

        for (String type : incomeMap.keySet()) {

            System.out.println(type + " Income: £" + incomeMap.get(type));

            if (incomeMap.get(type) > maxIncome) {
                maxIncome = incomeMap.get(type);
                topExercise = type;
            }
        }

        System.out.println("Champion Exercise: " + topExercise);
    }
}