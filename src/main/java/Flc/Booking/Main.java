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
public class Main {
    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();
        Scanner sc = new Scanner(System.in);

        // CREATE MEMBERS
        for (int i = 1; i <= 10; i++) {
            system.getMembers().add(new Member(i, "Member" + i));
        }

        // CREATE LESSONS (48)
        String[] types = {"Yoga", "Zumba", "BoxFit", "Aquacise"};
        String[] days = {"Saturday", "Sunday"};
        String[] times = {"Morning", "Afternoon", "Evening"};

        int id = 1;
        for (int w = 1; w <= 8; w++) {
            for (String d : days) {
                for (String t : times) {
                    system.getLessons().add(
                        new Lesson(id, types[(id - 1) % 4], d, t, 10 + ((id - 1) % 4) * 2)
                    );
                    id++;
                }
            }
        }

        // MAIN MENU LOOP
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Book Lesson");
            System.out.println("2. Change Booking");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Attend Lesson");
            System.out.println("5. Monthly Report");
            System.out.println("6. Champion Report");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

           
            // 1. BOOK LESSON
          
            if (choice == 1) {

                System.out.println("1. View Lessons ByDay");
                System.out.println("2. View Lessons ByType");

                int option = sc.nextInt();
                sc.nextLine();

                List<Lesson> filtered = new ArrayList<>();

                // VIEW BY DAY
                if (option == 1) {
                    System.out.print("Enter day (Saturday/Sunday): ");
                    String day = sc.nextLine();

                    for (Lesson l : system.getLessons()) {
                        if (l.getDay().equalsIgnoreCase(day)) {
                            System.out.println(l);
                            filtered.add(l);
                        }
                    }

                    if (filtered.isEmpty()) {
                        System.out.println("No lessons found for this day");
                        continue;
                    }
                }

                // VIEW BY TYPE
                else if (option == 2) {
                    System.out.print("Enter type (Yoga/Zumba/BoxFit/Aquacise): ");
                    String type = sc.nextLine();

                    for (Lesson l : system.getLessons()) {
                        if (l.getType().equalsIgnoreCase(type)) {
                            System.out.println(l);
                            filtered.add(l);
                        }
                    }

                    if (filtered.isEmpty()) {
                        System.out.println("No lessons found for this type");
                        continue;
                    }
                }

                else {
                    System.out.println("Invalid option");
                    continue;
                }

                // SELECT LESSON
                System.out.print("Enter Lesson ID: ");
                int lessonId = sc.nextInt();

                Lesson selected = system.getLessons().stream()
                        .filter(l -> l.getId() == lessonId)
                        .findFirst()
                        .orElse(null);

                if (selected == null) {
                    System.out.println("Lesson not found");
                    continue;
                }

                // BOOK
                Member member = system.getMembers().get(0);
                system.bookLesson(member, selected);
            }

           
            // 2. CHANGE BOOKING
           
            else if (choice == 2) {

                System.out.print("Enter Booking ID: ");
                int bookingId = sc.nextInt();

                System.out.println("Available Lessons:");
                system.getLessons().forEach(System.out::println);

                System.out.print("Enter New Lesson ID: ");
                int newLessonId = sc.nextInt();

                Lesson newLesson = system.getLessons().stream()
                        .filter(l -> l.getId() == newLessonId)
                        .findFirst()
                        .orElse(null);

                system.changeBooking(bookingId, newLesson);
            }

          
            // 3. CANCEL BOOKING
           
            else if (choice == 3) {
                System.out.print("Enter Booking ID: ");
                int bookingId = sc.nextInt();
                system.cancelBooking(bookingId);
            }

           
            // 4. ATTEND LESSON
            
            else if (choice == 4) {
                System.out.print("Enter Booking ID: ");
                int bookingId = sc.nextInt();

                System.out.print("Enter Rating (1-5): ");
                int rating = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Review: ");
                String comment = sc.nextLine();

                system.attendLesson(bookingId, rating, comment);
            }

            
            // 5. REPORT
            
            else if (choice == 5) {
                ReportService.lessonReport(system.getLessons());
            }

            
            // 6. CHAMPION REPORT
            
            else if (choice == 6) {
                ReportService.championReport(system.getLessons());
            }

            // =========================
            // EXIT
            else if (choice == 7) {
                System.out.println("Exiting...");
                break;
            }

            else {
                System.out.println("❌ Invalid choice");
            }
        }

        sc.close();
    }
}