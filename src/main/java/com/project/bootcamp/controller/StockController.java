package com.project.bootcamp.controller;

import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<StockDTO> save(@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(stockService.save(dto));
    }

    @PutMapping
    public ResponseEntity<StockDTO> update(@Valid @RequestBody StockDTO dto) {
        return ResponseEntity.ok(stockService.update(dto));
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> findAll() {
        return ResponseEntity.ok(stockService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.findById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StockDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(stockService.delete(id));
    }

    @GetMapping("/today")
    public ResponseEntity<List<StockDTO>> findByToday() {
        return ResponseEntity.ok(stockService.findByToday());
    }

}
