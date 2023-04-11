package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
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

    /**http://localhost:8080/api/laptop
     * @Return
     */
    @GetMapping("/api/laptop")
    public ResponseEntity findAll(@RequestHeader HttpHeaders headers){

        if (Objects.requireNonNull(headers.get("Authorization")).get(0).contentEquals("laptop-value-45xx23") ){
            return ResponseEntity.ok(laptopRepository.findAll());

        }
        return ResponseEntity.notFound().build();

    }


    /**http://localhost:8080/api/laptop
     *
     * @param laptop
     * @param headers
     * @return
     */
    @PostMapping("/api/laptop")
    public ResponseEntity create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){

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
