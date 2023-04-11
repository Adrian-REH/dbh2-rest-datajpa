package com.example.dbh2restdatajpa.services;

import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import org.springframework.stereotype.Service;

@Service
public class LaptioService {
    private LaptopRepository laptopRepository;

    public LaptioService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }



}
