package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class LaptopController {

    @Autowired
    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    @GetMapping("/api/laptop")
    @ApiOperation(notes = "Busca todas las laptops guardadas", value = "Buscar Laptops")
    public ResponseEntity findAll(@ApiParam("Header Authorization") @RequestHeader HttpHeaders headers){

        if (Objects.requireNonNull(headers.get("Authorization")).get(0).contentEquals("laptop-value-45xx23") ){
            return ResponseEntity.ok(laptopRepository.findAll());
        }

        return ResponseEntity.notFound().build();

    }



    @PostMapping("/api/laptop")
    @ApiOperation(value = "Create Laptop ",notes = "Consideracion\n" +
            "1. ¿Esta Autorizado?\n" +
            "2. ¿El id es null?\n" +
            "3. ¿Existe en la BD?\n" +
            "4. Resolver\n" +
            "No: Null, Si: Guardo")
    public ResponseEntity create(@RequestBody Laptop laptop,@RequestHeader HttpHeaders headers){

        if (Objects.requireNonNull(headers.get("Authorization")).get(0).contentEquals("laptop-value-45xx23") ){
            if (laptop.getId() != null){
                Optional<Laptop> response = laptopRepository.findById(laptop.getId());
                if (response.isEmpty()) {
                    return ResponseEntity.ok(laptopRepository.save(laptop));
                }
            }else {
                return ResponseEntity.ok(laptopRepository.save(laptop));

            }
        }


        return ResponseEntity.notFound().build();
    }



}
