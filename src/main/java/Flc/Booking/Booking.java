/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Flc.Booking;

/**
 *
 * @author Admin
 */

public class Booking {
    private int id;
    private Member member;
    private Lesson lesson;
    private BookingStatus status;
    private Review review;

    public Booking(int id, Member member, Lesson lesson) {
        this.id = id;
        this.member = member;
        this.lesson = lesson;
        this.status = BookingStatus.BOOKED;
    }

    public void attend(int rating, String comment) {
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating!");
            return;
        }

        if (status == BookingStatus.ATTENDED) {
            System.out.println("Already attended!");
            return;
        }

        status = BookingStatus.ATTENDED;
        review = new Review(rating, comment);
    }

    public void cancel() {
        status = BookingStatus.CANCELLED;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getId() { return id; }
    public Member getMember() { return member; }
    public Lesson getLesson() { return lesson; }
    public BookingStatus getStatus() { return status; }
    public Review getReview() { return review; }
}
