package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Unit;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class UnitsServices {

    Connection connection;

    public UnitsServices() throws NoSuchAlgorithmException {
        this.connection = DatabaseRepository.getInstance().getConnection();
    }

    public Unit create(Unit unit) throws SQLException {
        String sql = "insert into units (city, launchDate) OUTPUT INSERTED.idUnit, INSERTED.launchDate values (?, GETDATE());";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, unit.getName());

        ResultSet resultSet = stm.executeQuery();

        if(resultSet == null) {
            return unit;
        }
        resultSet.next();
        int id = resultSet.getInt("idUnit");
        String date = resultSet.getString("launchDate");

        return new Unit(id, unit.getName(), date);
    }

    public ArrayList<Unit> getAll() throws SQLException {
        ArrayList<Unit> units = new ArrayList<>();

        String sql = "SELECT * FROM units";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("idUnit");
            String name = resultSet.getString("city");
            String launchDate = resultSet.getString("launchDate");

            units.add(new Unit(id, name, launchDate));
        }

        return units;
    }

    public Unit findOne(int id) throws SQLException {
        String sql = "SELECT * FROM units WHERE idUnit = " + id;
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if(!resultSet.next()) {
            return null;
        }
        int idR = resultSet.getInt("idUnit");
        String name = resultSet.getString("city");
        String launchDate = resultSet.getString("launchDate");
        return new Unit(idR, name, launchDate);
    }


    public void update(Unit unit) throws SQLException{
        String sql = "UPDATE units SET city = " + " ' " + unit.getName() +" ' " + "  WHERE idUnit = " + unit.getId() ;
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM units WHERE idUnit = " + id;
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.executeUpdate();
    }
}
