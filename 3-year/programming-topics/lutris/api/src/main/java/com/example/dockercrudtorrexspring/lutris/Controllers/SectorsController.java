package com.example.dockercrudtorrexspring.lutris.Controllers;

import com.example.dockercrudtorrexspring.lutris.Entities.Sector;
import com.example.dockercrudtorrexspring.lutris.Services.SectorsServices;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sectors")
public class SectorsController {

    SectorsServices sectorsServices;

    public SectorsController() throws NoSuchAlgorithmException {
        this.sectorsServices = new SectorsServices();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Sector>> getAll() {
        try {
            ArrayList<Sector> sectors = this.sectorsServices.getAll();

            return new ResponseEntity<>(sectors, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<Sector> findOne(@PathVariable("id") int id) {
        try {
            var result = this.sectorsServices.findOne(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Sector> create(@RequestBody Sector sector) {
        try {
            var result = this.sectorsServices.create(sector);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Sector sector) {
        try {
            var result = this.sectorsServices.findOne(id);

            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            result.setName(sector.getName());

            this.sectorsServices.update(result);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        try {
            var result = this.sectorsServices.findOne(id);

            if (result == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            this.sectorsServices.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
