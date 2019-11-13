package com.flow.snow.snow.service.impl;

import com.flow.snow.snow.dao.StationDao;
import com.flow.snow.snow.entity.Station;
import com.flow.snow.snow.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {
    @Autowired
    StationDao stationDao;
    @Override
    public List<Station> findAll() {
        return stationDao.findAll();
    }
}
