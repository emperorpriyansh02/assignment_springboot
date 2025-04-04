package com.priyansh.assignment_springboot.Interface;

import com.priyansh.assignment_springboot.Model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking createBooking(Booking booking);
    List<Booking> getBookings(String shipperId, String transporterId);
    Booking getBookingById(UUID bookingId);
    Booking updateBooking(UUID bookingId, Booking updatebooking);
    void deleteBooking(UUID bookingId);
}
