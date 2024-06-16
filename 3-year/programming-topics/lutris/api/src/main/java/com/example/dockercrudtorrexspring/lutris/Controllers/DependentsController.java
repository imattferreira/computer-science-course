package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Dependent;
import com.example.dockercrudtorrexspring.lutris.Services.DependentsServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dependents")
public class DependentsController {

    DependentsServices dependentsServices;

    public DependentsController() throws NoSuchAlgorithmException {
        this.dependentsServices = new DependentsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Dependent>> getAll() {
        try {
            ArrayList<Dependent> dependents = this.dependentsServices.getAll();
            return new ResponseEntity<>(dependents, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Dependent> findOne(@PathVariable("id") int id) {
        try {
            var result = this.dependentsServices.findOne(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Dependent> create(@RequestBody Dependent dependent) {
        try {
            var result = this.dependentsServices.create(dependent);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Dependent dependent) {

        try {
            var updateDependent = this.dependentsServices.findOne(id);

            if (updateDependent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            updateDependent.setName(dependent.getName());
            updateDependent.setBirth(dependent.getBirth());
            updateDependent.setIdEmployee(dependent.getIdEmployee());

            dependentsServices.update(updateDependent);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws SQLException {

        try {
            var existingDependent = this.dependentsServices.findOne(id);
            if (existingDependent == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this.dependentsServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
