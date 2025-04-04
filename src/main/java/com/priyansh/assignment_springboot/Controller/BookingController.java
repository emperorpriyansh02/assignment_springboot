package com.priyansh.assignment_springboot.Controller;

import com.priyansh.assignment_springboot.Interface.BookingService;
import com.priyansh.assignment_springboot.Model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        Booking newBooking = bookingService.createBooking(booking);
        return ResponseEntity.ok(newBooking);
    }
    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(
            @RequestParam String shipperId,
            @RequestParam String transporterId) {
        List<Booking> bookings = bookingService.getBookings(shipperId, transporterId);
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable UUID bookingId,
            @RequestBody Booking updateBooking) {
        Booking booking = bookingService.updateBooking(bookingId, updateBooking);
        return ResponseEntity.ok(booking);

    }
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}