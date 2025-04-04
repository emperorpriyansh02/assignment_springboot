package com.priyansh.assignment_springboot.Interface;

import com.priyansh.assignment_springboot.Model.Load;

import java.util.List;
import java.util.UUID;

public interface LoadService {
    Load createLoad(Load load);

    List<Load> getAllLoads(String shipperId, String truckType);

    Load getLoadById(UUID loadId);

    Load updateLoad(UUID loadId, Load updatedLoad);

    void deleteLoad(UUID loadId);
}
