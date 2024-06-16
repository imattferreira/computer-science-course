package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Sector;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class SectorsServices {

    Connection connection;

    public SectorsServices() throws NoSuchAlgorithmException {
        connection = DatabaseRepository.getInstance().getConnection();

    }

    public Sector create(Sector sector) throws SQLException {
        String sql = "INSERT INTO sectors (nameSector, launchDate) OUTPUT INSERTED.idSector, INSERTED.launchDate VALUES (?, GETDATE());";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, sector.getName());

        ResultSet resultSet = stm.executeQuery();

        if (resultSet == null) {
            return null;
        }
        System.out.println("Sector inserted successfully...");
        resultSet.next();

        int id = resultSet.getInt("idSector");
        String date = resultSet.getString("launchDate");

        return new Sector(id, sector.getName(), date);

    }

    public ArrayList<Sector> getAll() throws SQLException {
        ArrayList<Sector> sectors = new ArrayList<>();

        String sql = "SELECT * FROM sectors";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("idSector");
            String name = resultSet.getString("nameSector");
            String launchDate = resultSet.getString("launchDate");

            sectors.add(new Sector(id, name, launchDate));
        }

        return sectors;
    }

    public Sector findOne(int id) throws SQLException {
        String sql = "SELECT * FROM sectors WHERE idSector =" + id;
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next()) {
            return null;
        }
        int idU = resultSet.getInt("idSector");
        String name = resultSet.getString("nameSector");
        String launchDate = resultSet.getString("launchDate");
        return new Sector(idU, name, launchDate);

    }

    public void update(Sector sector) throws SQLException {
        String sql = "UPDATE sectors SET nameSector = " + " '" + sector.getName() + "' " + " WHERE idSector = " + sector.getId();
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM sectors WHERE idSector = " + id;
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.executeUpdate();
    }

}