package com.priyansh.assignment_springboot.Repository;

import com.priyansh.assignment_springboot.Model.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {
    List<Load> findByShipperId(String shipperId);

    List<Load> findByShipperIdAndTruckType(String shipperId, String truckType);
}
