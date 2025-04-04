package com.priyansh.assignment_springboot.Service;

import com.priyansh.assignment_springboot.Interface.LoadService;
import com.priyansh.assignment_springboot.Model.Load;
import com.priyansh.assignment_springboot.Repository.LoadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.priyansh.assignment_springboot.Model.LoadStatus.POSTED;

@Service
public class LoadServiceImpl implements LoadService {

    private LoadRepository loadRepository;

    public LoadServiceImpl(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    @Override
    public Load createLoad(Load load) {
        load.setStatus(POSTED);
        return loadRepository.save(load);
    }

    @Override
    public List<Load> getAllLoads(String shipperId, String truckType) {
        if(shipperId != null && truckType != null) {
            return loadRepository.findByShipperIdAndTruckType(shipperId, truckType);
        } else if (shipperId != null) {
            return loadRepository.findByShipperId(shipperId);
        } else {
            return loadRepository.findAll();
        }
    }

    @Override
    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId).orElseThrow(() -> new RuntimeException("Load not found"));
    }

    @Override
    public Load updateLoad(UUID loadId, Load updatedLoad) {
        Load existingLoad = getLoadById(loadId);
        existingLoad.setProductType(updatedLoad.getProductType());
        existingLoad.setTruckType(updatedLoad.getTruckType());
        existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        existingLoad.setWeight(updatedLoad.getWeight());
        existingLoad.setComment(updatedLoad.getComment());
        return loadRepository.save(existingLoad);
    }

    @Override
    public void deleteLoad(UUID loadId) {
        Load load = getLoadById(loadId);
        loadRepository.delete(load);
    }
}
