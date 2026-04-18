/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Flc.Booking.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Flc.Booking.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Admin
 */

public class BookingSystemTest {

    // TEST 1: Booking success
    @Test
    public void testBookingSuccess() {
        BookingSystem system = new BookingSystem();

        Member m = new Member(1, "Test");
        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        Booking b = system.bookLesson(m, l);

        assertNotNull(b);
    }

    // TEST 2: Duplicate booking
    @Test
    public void testDuplicateBooking() {
        BookingSystem system = new BookingSystem();

        Member m = new Member(1, "Test");
        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        system.bookLesson(m, l);
        Booking second = system.bookLesson(m, l);

        assertNull(second);
    }

    // TEST 3: Capacity limit
    @Test
    public void testCapacityLimit() {
        BookingSystem system = new BookingSystem();

        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        for (int i = 1; i <= 4; i++) {
            system.bookLesson(new Member(i, "M" + i), l);
        }

        Booking extra = system.bookLesson(new Member(5, "Extra"), l);

        assertNull(extra);
    }

    // TEST 4: Cancel booking
    @Test
    public void testCancelBooking() {
        BookingSystem system = new BookingSystem();

        Member m = new Member(1, "Test");
        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        Booking b = system.bookLesson(m, l);
        system.cancelBooking(b.getId());

        assertEquals(BookingStatus.CANCELLED, b.getStatus());
    }

    // TEST 5: Attend lesson
    @Test
    public void testAttendLesson() {
        BookingSystem system = new BookingSystem();

        Member m = new Member(1, "Test");
        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        Booking b = system.bookLesson(m, l);
        system.attendLesson(b.getId(), 5, "Good");

        assertEquals(BookingStatus.ATTENDED, b.getStatus());
    }

    // TEST 6: Cannot attend cancelled booking
    @Test
    public void testAttendCancelledBooking() {
        BookingSystem system = new BookingSystem();

        Member m = new Member(1, "Test");
        Lesson l = new Lesson(1, "Yoga", "Saturday", "Morning", 10);

        Booking b = system.bookLesson(m, l);
        system.cancelBooking(b.getId());

        system.attendLesson(b.getId(), 5, "Good");

        assertEquals(BookingStatus.CANCELLED, b.getStatus());
    }
}