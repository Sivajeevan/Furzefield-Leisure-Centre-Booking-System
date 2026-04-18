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

public class BookingSystem {

    private List<Member> members = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int bookingId = 1;

    public List<Member> getMembers() { return members; }
    public List<Lesson> getLessons() { return lessons; }
    public List<Booking> getBookings() { return bookings; }


    // BOOK LESSON
    
    public Booking bookLesson(Member m, Lesson l) {

        if (l == null) return null;

        if (l.isFull()) {
            System.out.println("Lesson full!");
            return null;
        }

        for (Booking b : bookings) {
            
                System.out.println("Duplicate booking!");
                return null;
            }
        }

        Booking booking = new Booking(bookingId++, m, l);
        bookings.add(booking);
        l.addBooking(booking);

        System.out.println("Booking successful! ID: " + booking.getId());
        return booking;
    }

    // CANCEL BOOKING
    
    public void cancelBooking(int id) {

        for (Booking b : bookings) {
            if (b.getId() == id) {
                b.cancel();
                b.getLesson().removeBooking(b);
                System.out.println("Cancelled");
                return;
            }
        }

        System.out.println("Booking not found");
    }

    // ATTEND LESSON + REVIEW
 
    public void attendLesson(int id, int rating, String comment) {

        for (Booking b : bookings) {
            if (b.getId() == id) {

                if (b.getStatus() == BookingStatus.CANCELLED) {
                    System.out.println("Cannot attend cancelled booking");
                    return;
                }

                if (b.getStatus() == BookingStatus.ATTENDED) {
                    System.out.println("Already attended!");
                    return;
                }

                b.attend(rating, comment);
                System.out.println("Attended + Review added");
                return;
            }
        }

        System.out.println("Booking not found");
    }

    // CHANGE BOOKING

    public void changeBooking(int bookingId, Lesson newLesson) {

        for (Booking b : bookings) {
            if (b.getId() == bookingId) {

                if (newLesson == null) {
                    System.out.println("Invalid lesson");
                    return;
                }

                if (newLesson.isFull()) {
                    System.out.println("New lesson is full!");
                    return;
                }

                b.getLesson().removeBooking(b);
                newLesson.addBooking(b);
                b.setLesson(newLesson);

                System.out.println("Booking changed successfully");
                return;
            }
        }

        System.out.println("Booking not found");
    }

    // VIEW BY DAY
    
    public void viewByDay(String day) {

        boolean found = false;

        for (Lesson l : lessons) {
            if (l.getDay().equalsIgnoreCase(day)) {
                System.out.println(l);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No lessons found for this day");
        }
    }

    // VIEW BY TYPE

    public void viewByType(String type) {

        boolean found = false;

        for (Lesson l : lessons) {
            if (l.getType().equalsIgnoreCase(type)) {
                System.out.println(l);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No lessons found for this type");
        }
    }


    // VIEW REVIEWS
    
    public void viewReviews() {

        boolean found = false;

        for (Booking b : bookings) {

            if (b.getStatus() == BookingStatus.ATTENDED && b.getReview() != null) {

                System.out.println("-----------------------------");
                System.out.println("Lesson: " + b.getLesson().getType());
                System.out.println("Day: " + b.getLesson().getDay());
                System.out.println("Rating: " + b.getReview().getRating());
                System.out.println("Review: " + b.getReview().getComment());
                System.out.println("-----------------------------");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No reviews available");
        }
    }
}


