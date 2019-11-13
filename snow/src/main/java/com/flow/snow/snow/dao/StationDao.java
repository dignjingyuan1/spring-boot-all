package com.flow.snow.snow.dao;

import com.flow.snow.snow.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationDao extends JpaRepository<Station, Long> {
    List<Station> findAll();
}
