package com.github.odyn666.restaurantreservation.controller;

import com.github.odyn666.restaurantreservation.model.TableModel;
import com.github.odyn666.restaurantreservation.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService service;

    @GetMapping("/get/all")
    public ResponseEntity<List<TableModel>> getAllTables() {
        return ResponseEntity.ok(service.getAllTables());
    }

    @GetMapping("/get/identifier")
    public ResponseEntity<TableModel> getTableByIdentifier(@RequestParam String identifier) {
        return ResponseEntity.ok(service.getTableByIdentifier(identifier));
    }

    @GetMapping("/get/reserved")
    public ResponseEntity<List<TableModel>> getAllReservedTables() {
        return ResponseEntity.ok(service.getAllReservedTables());
    }

    @PostMapping("/add")
    public ResponseEntity<TableModel> createTable(@RequestBody TableModel tableModel)
    {
        return ResponseEntity.ok(service.createTable(tableModel));
    }
}
