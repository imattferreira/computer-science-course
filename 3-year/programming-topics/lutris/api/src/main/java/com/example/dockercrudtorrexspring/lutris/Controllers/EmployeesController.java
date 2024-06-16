package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Employee;
import com.example.dockercrudtorrexspring.lutris.Services.EmployeesServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    EmployeesServices employeesServices;

    public EmployeesController() throws NoSuchAlgorithmException {
        this.employeesServices = new EmployeesServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Employee>> getAll() {
        try {
            ArrayList<Employee> employees = this.employeesServices.getAll();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> findOne(@PathVariable("id") int id) {
        try {
            var result = this.employeesServices.findOne(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        try {
            var result = this.employeesServices.create(employee);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Employee employee) {
        try {
            var result = this.employeesServices.findOne(id);
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            result.setName(employee.getName());
            result.setDate(employee.getDate());
            result.setIdSector(employee.getIdSector());
            result.setIdUnit(employee.getIdUnit());

            this.employeesServices.update(result);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            var result = this.employeesServices.findOne(id);
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            this.employeesServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


    }

    @PutMapping(path = "/{id}/images", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> addImage
            (
            @PathVariable("id") int id,
            @RequestPart MultipartFile file
            )
    {

        try{
            var result = employeesServices.findOne(id);
            
            if(result == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            if(file.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            employeesServices.saveImage(file, String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.OK);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
