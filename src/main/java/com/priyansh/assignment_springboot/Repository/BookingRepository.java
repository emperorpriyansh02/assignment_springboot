package com.priyansh.assignment_springboot.Repository;

import com.priyansh.assignment_springboot.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    List<Booking> findByLoadId(UUID loadId);

    List<Booking> findByTransporterId(String transporterId);

    List<Booking> findByShipperId(String shipperId);
    List<Booking> findByShipperIdAndTransporterId(String customerId);
}
