package com.priyansh.assignment_springboot.Service;

import com.priyansh.assignment_springboot.Interface.BookingService;
import com.priyansh.assignment_springboot.Model.Booking;
import com.priyansh.assignment_springboot.Model.BookingStatus;
import com.priyansh.assignment_springboot.Model.Load;
import com.priyansh.assignment_springboot.Model.LoadStatus;
import com.priyansh.assignment_springboot.Repository.BookingRepository;
import com.priyansh.assignment_springboot.Repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;

    @Override
    public Booking createBooking(Booking booking) {
        Load load = loadRepository.findById(booking.getLoadId())
                .orElseThrow(() -> new IllegalArgumentException("Load not found"));

        if(load.getStatus() == LoadStatus.CANCELLED){
            throw new IllegalStateException("Cannot book a CANCELLED Load");
        }
        booking.setStatus(BookingStatus.PENDING);
        Booking savedBooking = bookingRepository.save(booking);

        load.setStatus(LoadStatus.BOOKED);
        loadRepository.save(load);
        return savedBooking;

    }

    @Override
    public List<Booking> getBookings(String shipperId, String transporterId) {
        if (shipperId != null && transporterId != null ) {
            return bookingRepository.findByShipperIdAndTransporterId(shipperId);
        } else if (shipperId != null) {
            return bookingRepository.findByShipperId(transporterId);
        }  else if (transporterId != null) {
            return bookingRepository.findByTransporterId(transporterId);
        }
        return bookingRepository.findAll();

    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found")) ;
    }

    @Override
    public Booking updateBooking(UUID bookingId, Booking updatebooking) {
        Booking existingBooking = getBookingById(bookingId);
        existingBooking.setStatus(updatebooking.getStatus());

        if (updatebooking.getStatus() == BookingStatus.ACCEPTED){
            Load load = loadRepository.findById(existingBooking.getLoadId())
                    .orElseThrow(() -> new RuntimeException("Load not found"));
            load.setStatus(LoadStatus.BOOKED);
            loadRepository.save(load);
        }

        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        Booking booking = getBookingById(bookingId);
        UUID loadID = booking.getLoadId();
        bookingRepository.delete(booking);

        Load load = loadRepository.findById(loadID)
                .orElseThrow(() -> new RuntimeException("Load not found"));

        load.setStatus(LoadStatus.CANCELLED);
        loadRepository.save(load);
    }
}
