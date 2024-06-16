package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Statistic;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsService {
    DatabaseRepository databaseRepository;

    public StatisticsService() throws NoSuchAlgorithmException {
        this.databaseRepository = DatabaseRepository.getInstance();
    }

    public Statistic countAllData() throws SQLException {
        String sql = "SELECT (SELECT COUNT(*) FROM dependents) AS dependentCount, " +
                "(SELECT COUNT(*)FROM units) AS unitCount, " +
                "(SELECT COUNT(*) FROM sectors) AS sectorCount, " +
                "(SELECT COUNT(*) FROM employees) AS employeeCount;";
        Statement statement = databaseRepository.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next()) {
            return null;
        }

        int empCount = resultSet.getInt("employeeCount");
        int depenCount = resultSet.getInt("dependentCount");
        int unitCount = resultSet.getInt("unitCount");
        int sectorCount = resultSet.getInt("sectorCount");

        return new Statistic(empCount, depenCount, unitCount, sectorCount);

    }
}
