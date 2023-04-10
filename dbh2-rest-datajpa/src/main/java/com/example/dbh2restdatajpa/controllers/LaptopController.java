package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    /**
     * @Return
     */
    @GetMapping("/api/laptop")
    public ResponseEntity findAll(@RequestHeader HttpHeaders headers){

        if (Objects.requireNonNull(headers.get("Authorization")).get(0).contentEquals("laptop-value-45xx23") ){
            return ResponseEntity.ok(laptopRepository.findAll());

        }
        return ResponseEntity.notFound().build();

    }


    @PostMapping("/api/laptop")
    public ResponseEntity create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){

        if (Objects.requireNonNull(headers.get("Authorization")).get(0).contentEquals("laptop-value-45xx23") ){
            if (laptop.getId() != null){
                Optional<Laptop> response = laptopRepository.findById(laptop.getId());
                if (response.isEmpty()) {
                    return ResponseEntity.ok(laptopRepository.save(laptop));
                }
            }else {
                laptopRepository.save(laptop);
                return ResponseEntity.ok(laptop);

            }
        }


        return ResponseEntity.notFound().build();
    }



}
