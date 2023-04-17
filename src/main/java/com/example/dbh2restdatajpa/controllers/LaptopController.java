package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.services.LaptopService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class LaptopController {


    @Autowired
    private LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    /**http://localhost:8080/api/laptop
     * @Return
     */
    @GetMapping("/api/laptop")
    @ApiOperation(notes = "Busca todas las laptops guardadas", value = "Buscar Laptops")
    public ResponseEntity<List<Laptop>> findAll(){
        return ResponseEntity.ok( laptopService.findAll());

    }

    @GetMapping("/api/laptop/{id}")
    @ApiOperation(notes = "Busca todas las laptops guardadas", value = "Buscar Laptops")
    public ResponseEntity<Optional<Laptop>> finOneById( @PathVariable Long id){
        return ResponseEntity.ok( laptopService.finOneById(id));

    }


    @PostMapping("/api/laptop")
    @ApiOperation(value = "Create Laptop ",notes = "")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        System.out.println("Estoy aqui");

        return ResponseEntity.ok( laptopService.create(laptop));
    }

    @PutMapping("/api/laptop")
    @ApiOperation(value = "Actualizar Laptop ",notes = "Actualizo")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        return ResponseEntity.ok(laptopService.update(laptop));
    }

    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.ok(laptopService.delete(id));

    }

    @DeleteMapping("/api/laptop")
    public ResponseEntity<String> deleteAll(@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok(laptopService.deleteAll());

    }

}
