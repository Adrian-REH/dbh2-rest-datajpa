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


    public List<Laptop> findAll(HttpHeaders headers){
        if ((headers.get("Authorization").get(0).contentEquals("laptop-value-45xx23"))){
            return laptopRepository.findAll();
        }
        return null;
    }

    public Optional<Laptop> finOneById(Long id, HttpHeaders headers){
        if (headers.get("Authorization").get(0).contentEquals("laptop-value-45xx23")){
            if (id !=null){
                if (laptopRepository.existsById(id)) {
                    return laptopRepository.findById(id);
                }
            }
        }

        return Optional.empty();
    }

    public Laptop create(Laptop laptop, HttpHeaders headers)  {
        if (headers.get("Authorization").get(0).contentEquals("laptop-value-45xx23")){
            if (laptop.getId() !=null){
                if (!laptopRepository.existsById(laptop.getId())){
                    return laptopRepository.save(laptop);
                }
                return null;

            }else {
                return laptopRepository.save(laptop);

            }
        }

        return null;
    }

    public Laptop update(Laptop laptop, HttpHeaders headers){
        if (isAuthorizationAndExistLaptop(laptop.getId(),headers)){

            return laptopRepository.save(laptop);

        }

        return null;
    }

    public String delete(Long id, HttpHeaders headers){
        if (isAuthorizationAndExistLaptop(id,headers)){
            laptopRepository.deleteById(id);
            return"Se borro correctamente";

        }

        return null;
    }

    private Boolean isAuthorizationAndExistLaptop(Long id,HttpHeaders headers){
        if (headers.get("Authorization").get(0).contentEquals("laptop-value-45xx23")){
            if (id!=null){
                if (laptopRepository.existsById(id)){
                    return true;
                }
            }
        }
        return false;
    }

    public String deleteAll( HttpHeaders headers){
        if (headers.get("Authorization").get(0).contentEquals("laptop-value-45xx23")){
            if (laptopRepository.findAll().size() > 0){
                laptopRepository.deleteAll();
                return"Se borraron correctamente";
            }
        }

        return null;
    }

}
