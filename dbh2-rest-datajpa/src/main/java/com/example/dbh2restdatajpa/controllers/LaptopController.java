package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }


    @PostMapping("/api/laptop")
    public ResponseEntity create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        if (laptop.getId() != null){
            Optional<Laptop> response = laptopRepository.findById(laptop.getId());
            if (response.isEmpty()) {
                return ResponseEntity.ok(laptopRepository.save(laptop));
            }
        }else {
            laptopRepository.save(laptop);
            return ResponseEntity.ok(laptop);

        }

        return ResponseEntity.notFound().build();
    }



}
