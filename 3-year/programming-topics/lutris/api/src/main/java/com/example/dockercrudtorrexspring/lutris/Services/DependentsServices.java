package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class DependentsServices {

    Connection connection;

    public DependentsServices() throws NoSuchAlgorithmException {
        this.connection = DatabaseRepository.getInstance().getConnection();
    }

    public Dependent create(Dependent dependent) throws SQLException {
        String sql = "INSERT INTO dependents (nameDependent, birth, idEmployee) OUTPUT INSERTED.idDependent VALUES (?,?,?);";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, dependent.getName());
        stm.setString(2, dependent.getBirth());
        stm.setInt(3, dependent.getIdEmployee());

        ResultSet resultSet = stm.executeQuery();

        if (resultSet == null) {
            return null;
        }
        resultSet.next();
        System.out.println("Dependent Successfully Inserted");

        int id = resultSet.getInt("idDependent");

        return new Dependent(id, dependent.getName(), dependent.getBirth(), dependent.getIdEmployee());
    }

    public ArrayList<Dependent> getAll() throws SQLException {
        ArrayList<Dependent> dependents = new ArrayList<>();

        Statement statement = this.connection.createStatement();
        String consult = "SELECT * FROM dependents;";
        ResultSet result = statement.executeQuery(consult);

        while (result.next()) {
            int id = result.getInt("idDependent");
            String name = result.getString("nameDependent");
            String birth = result.getString("birth");
            int idEmp = result.getInt("idEmployee");
            dependents.add(new Dependent(id, name, birth, idEmp));
        }
        return dependents;
    }

    public Dependent findOne(int id) throws SQLException {
        String sql = "SELECT * FROM dependents WHERE idDependent = " + id;
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next()) {
            return null;
        }
        int resultId = resultSet.getInt("idDependent");
        String resultName = resultSet.getString("nameDependent");
        String resultBirth = resultSet.getString("birth");
        int resultIdEmp = resultSet.getInt("idEmployee");

        return new Dependent(resultId, resultName, resultBirth, resultIdEmp);
    }

    public void update(Dependent dependent) throws SQLException {
        String sql = "UPDATE dependents SET nameDependent = ?, birth = ?, idEmployee = ? WHERE idDependent = ?";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, dependent.getName());
        stm.setString(2, dependent.getBirth());
        stm.setInt(3, dependent.getIdEmployee());
        stm.setInt(4, dependent.getId());
        stm.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM dependents WHERE idDependent = " + id;
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.executeUpdate();
    }
}
