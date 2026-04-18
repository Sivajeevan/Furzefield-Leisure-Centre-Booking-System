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
public class Lesson {
    private int id;
    private String type;
    private String day;
    private String time;
    private double price;
    private int capacity = 4;
    private List<Booking> bookings = new ArrayList<>();

    public Lesson(int id, String type, String day, String time, double price) {
        this.id = id;
        this.type = type;
        this.day = day;
        this.time = time;
        this.price = price;
    }

    public boolean isFull() {
        return bookings.size() >= capacity;
    }

    public void addBooking(Booking b) {
        bookings.add(b);
    }

    public void removeBooking(Booking b) {
        bookings.remove(b);
    }

    public List<Booking> getBookings() { return bookings; }
    public String getType() { return type; }
    public String getDay() { return day; }
    public double getPrice() { return price; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return id + " | " + type + " | " + day + " | " + time + " | £" + price;
    }
}
