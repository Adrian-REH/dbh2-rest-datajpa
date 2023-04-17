package com.example.dbh2restdatajpa.services;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.exceptions.LaptopNotAuthorizedException;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LaptopService {
    private LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    public List<Laptop> findAll(){
        return laptopRepository.findAll();

    }

    public Optional<Laptop> finOneById(Long id){
        if (id !=null){
            if (laptopRepository.existsById(id)) {
                return laptopRepository.findById(id);
            }
        }

        return Optional.empty();
    }

    public Laptop create(Laptop laptop)  {
        if (laptop.getId() !=null){
            if (!laptopRepository.existsById(laptop.getId())){
                return laptopRepository.save(laptop);
            }
            return null;

        }else {
            System.out.println("Estoy aqui");
            return laptopRepository.save(laptop);

        }

    }

    public Laptop update(Laptop laptop){
        if (isAuthorizationAndExistLaptop(laptop.getId())){

            return laptopRepository.save(laptop);

        }

        return null;
    }

    public String delete(Long id){
        if (isAuthorizationAndExistLaptop(id)){
            laptopRepository.deleteById(id);
            return"Se borro correctamente";

        }

        return null;
    }

    private Boolean isAuthorizationAndExistLaptop(Long id){
        if (id!=null){
            if (laptopRepository.existsById(id)){
                return true;
            }
        }
        return false;
    }

    public String deleteAll(){
        if (laptopRepository.findAll().size() > 0){
            laptopRepository.deleteAll();
            return"Se borraron correctamente";
        }

        return null;
    }

}
