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

    @Value("${app.varexample}")
    String meesage;
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
    public ResponseEntity<List<Laptop>> findAll(@ApiParam("Header Authorization") @RequestHeader HttpHeaders headers){
        System.out.println(meesage);
        return ResponseEntity.ok( laptopService.findAll(headers));

    }

    @GetMapping("/api/laptop/{id}")
    @ApiOperation(notes = "Busca todas las laptops guardadas", value = "Buscar Laptops")
    public ResponseEntity<Optional<Laptop>> finOneById( @PathVariable Long id,@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok( laptopService.finOneById(id,headers));

    }


    @PostMapping("/api/laptop")
    @ApiOperation(value = "Create Laptop ",notes = "")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop,@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok( laptopService.create(laptop,headers));
    }

    @PutMapping("/api/laptop")
    @ApiOperation(value = "Actualizar Laptop ",notes = "Actualizo")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop,@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok(laptopService.update(laptop,headers));
    }

    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok(laptopService.delete(id,headers));

    }

    @DeleteMapping("/api/laptop")
    public ResponseEntity<String> deleteAll(@RequestHeader HttpHeaders headers){
        return ResponseEntity.ok(laptopService.deleteAll(headers));

    }

}
