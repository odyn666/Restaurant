package com.github.odyn666.restaurantreservation.service;

import com.github.odyn666.restaurantreservation.model.RestaurantModel;
import com.github.odyn666.restaurantreservation.model.TableModel;
import com.github.odyn666.restaurantreservation.repository.TableRepository;
import com.github.odyn666.restaurantreservation.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository repo;

    //*CREATE
    public TableModel createTable(TableModel table) {
        return repo.save(table);
    }

    //*READ
    public TableModel getTableByIdentifier(String identifier) {
        return repo.findTableModelByIdentifier(identifier).orElseThrow(NotFoundException::new);
    }

    public List<TableModel> getAllTables() {
        return repo.findAll();
    }

    public TableModel getTableByID(Long id) {
        return repo.findTableById(id).orElseThrow(NotFoundException::new);
    }

    public List<TableModel> getAllReservedTables() {
        return repo.findAllByIsReserved(true);

    }

    //*UPDATE
    @Transactional
    public TableModel updateTableByID(Long id, Map<String, Object> tableVariables) {
        TableModel table = getTableByID(id);
        tableVariables.forEach((key, value) -> {
            if (key.equals("id")) return;

            Field field = ReflectionUtils.findField(TableModel.class, key);
            field.setAccessible(true);

            ReflectionUtils.setField(field, table, value);

        });

        return table;
    }
    //*DELETE


    public void deleteTableByID(Long id) {
        TableModel tableByID = getTableByID(id);
        repo.delete(tableByID);
    }
}
