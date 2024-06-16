package com.example.dockercrudtorrexspring.lutris.Services;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Repositories.DatabaseRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

public class EmployeesServices {

    Connection connection;

    public EmployeesServices() throws NoSuchAlgorithmException {
        this.connection = DatabaseRepository.getInstance().getConnection();

    }


    public Employee create(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (nameEmployee, birth, idSector, idUnit) OUTPUT INSERTED.idEmployee VALUES (?,?,?,?);";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setString(1, employee.getName());
        stm.setString(2, employee.getDate());
        stm.setInt(3, employee.getIdSector());
        stm.setInt(4, employee.getIdUnit());

        ResultSet resultSet = stm.executeQuery();

        if (resultSet == null) {
            return null;
        }
        System.out.println("Employee successfully inserted...");
        resultSet.next();

        int id = resultSet.getInt("idEmployee");

        return new Employee(id, employee.getName(), employee.getDate(),
                employee.getIdSector(), employee.getIdUnit());

    }

    public ArrayList<Employee> getAll() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees";
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int idE = resultSet.getInt("idEmployee");
            String name = resultSet.getString("nameEmployee");
            String birth = resultSet.getString("birth");
            int idSector = resultSet.getInt("idSector");
            int idUnit = resultSet.getInt("idUnit");
            int idImage = resultSet.getInt("idImage");

            employees.add(new Employee(idE, name, birth, idSector, idUnit, idImage));
        }

        return employees;
    }

    public Employee findOne(int id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE idEmployee = " + id;
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (!resultSet.next()) {
            return null;
        }
        int idE = resultSet.getInt("idEmployee");
        String name = resultSet.getString("nameEmployee");
        String birth = resultSet.getString("birth");
        int idSector = resultSet.getInt("idSector");
        int idUnit = resultSet.getInt("idUnit");
        int idImage = resultSet.getInt("idImage");

        return new Employee(idE, name, birth, idSector, idUnit, idImage);
    }

    public void update(Employee employee) throws SQLException {
        String sql = " UPDATE employees SET nameEmployee = " + "'" + employee.getName() + "'" + ", idUnit = ?, idSector = ? WHERE idEmployee = ? ";
        PreparedStatement stm = this.connection.prepareStatement(sql);
        stm.setInt(1, employee.getIdSector());
        stm.setInt(2, employee.getIdUnit());
        stm.setInt(3, employee.getId());

        stm.executeUpdate();

    }

    public void delete(int id) throws SQLException, IOException {
        String sql = "DELETE FROM employees WHERE idEmployee = " + id;
        PreparedStatement stm = this.connection.prepareStatement(sql);
        deleteImage(String.valueOf(id) + ".jpeg");

        stm.executeUpdate();
    }

    public void saveImage(MultipartFile image, String idEmp) throws IOException, SQLException {
        String uniqueFileName = idEmp + ".jpeg";
        Path uploadPath = Path.of("src/main/resources/Images");
        Path filePath = uploadPath.resolve(uniqueFileName);

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        String sql = "UPDATE employees SET idImage = " + idEmp + "WHERE idEmployee =" + idEmp;
        PreparedStatement stm = this.connection.prepareStatement(sql);

        stm.executeUpdate();
    }

    public void deleteImage(String imageName) throws IOException {

        Path imagePath = Path.of("src/main/resources/Images", imageName);

        if(Files.exists(imagePath)) {
            Files.delete(imagePath);
        }

    }
}
