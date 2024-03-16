package com.github.odyn666.restaurantreservation.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.github.odyn666.restaurantreservation.model.TableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<TableModel,Long> {
    Optional<TableModel> findTableModelByIdentifier(String identifier);

    List<TableModel> findAllByIsReserved(boolean b);

    Optional<TableModel> findTableById(Long id);
}
