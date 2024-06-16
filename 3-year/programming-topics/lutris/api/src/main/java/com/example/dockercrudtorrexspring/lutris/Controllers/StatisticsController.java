package com.example.dockercrudtorrexspring.lutris.Controllers;


import com.example.dockercrudtorrexspring.lutris.Entities.Statistic;
import com.example.dockercrudtorrexspring.lutris.Services.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    StatisticsService statisticService;

    public StatisticsController() throws NoSuchAlgorithmException {
        this.statisticService = new StatisticsService();

    }

    @GetMapping(path = "/count", produces = "application/json")
    public ResponseEntity<Statistic> countAll() throws SQLException {
        try {
            var result = this.statisticService.countAllData();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
