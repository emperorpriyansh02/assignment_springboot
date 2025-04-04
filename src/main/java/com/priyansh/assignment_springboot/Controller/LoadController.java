package com.priyansh.assignment_springboot.Controller;

import com.priyansh.assignment_springboot.Interface.LoadService;
import com.priyansh.assignment_springboot.Model.Load;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/load")
@RequiredArgsConstructor
public class LoadController {
    private final LoadService loadService;

    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load){
        return ResponseEntity.ok(loadService.createLoad(load));
    }

    @GetMapping
    public ResponseEntity<List<Load>> getAllLoads(
            @RequestParam(required = false) String shipperId,
            @RequestParam(required = false) String truckType){
        return ResponseEntity.ok(loadService.getAllLoads(shipperId, truckType));
    }

    @GetMapping("/{loadId}")
    public ResponseEntity<Load> getLoadById(@PathVariable UUID loadId){
        return ResponseEntity.ok(loadService.getLoadById(loadId));

    }

    @PutMapping("/{loadId}")
    public ResponseEntity<Load> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoad){
        return ResponseEntity.ok(loadService.updateLoad(loadId, updatedLoad));
    }

    @DeleteMapping("/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId){
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }

}
