package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import com.example.dbh2restdatajpa.services.LaptopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@VisibleForTesting
class LaptopControllerTest {

    private MockMvc mockMvc;
    private final HttpHeaders headers = new HttpHeaders();
    private static final Laptop LAPTOP_1 = new Laptop(1L,"Lenovo",3,false);
    private static final Laptop LAPTOP_2 = new Laptop(2L,"MacOs",4,true);
    private static final Laptop LAPTOP_NULL = new Laptop(null,null,null,null);
    private final List<Laptop> records= new  ArrayList<Laptop>(Arrays.asList(LAPTOP_1, LAPTOP_2));


    @Mock
    private LaptopService laptopService;

    @InjectMocks
    private LaptopController laptopController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(laptopController).build();
        headers.add("Authorization", "laptop-value-45xx23");


    }



    
    @Test
    void findAll() throws Exception {
        //Lleno el repositorio
        Mockito.when(laptopService.findAll(headers)).thenReturn( records);

        //Creo un Headers
        ResponseEntity<List<Laptop>> laptop = laptopController.findAll(headers);


        assertEquals(laptop.getBody().size(),2);
        assertEquals(laptop.getBody().get(0).getId(),1L);
    }

    @Test
    void findOneById(){
        Optional<Laptop> as= Optional.of(LAPTOP_1);
        Mockito.when(laptopService.finOneById(LAPTOP_1.getId(),headers)).thenReturn( as);

        ResponseEntity<Optional<Laptop>> laptop = laptopController.finOneById(LAPTOP_1.getId(),headers);

        assertEquals(laptop.getBody().get(), LAPTOP_1);

    }

    @Test
    void create() {
        Mockito.when(laptopService.create(LAPTOP_1,headers)).thenReturn(LAPTOP_1);

        //VERIFICO QUE CONTIENE LO CORRECTO
        ResponseEntity<Laptop> laptop = laptopController.create(LAPTOP_1,headers);
        assertEquals(LAPTOP_1.getId(), laptop.getBody().getId());

        //VERIFICO QUE DEVUELVE LO CORRECTO
        Mockito.when(laptopController.create(LAPTOP_1,headers).getBody()).thenReturn(LAPTOP_1);

    }



    @Test
    void update() {
        Mockito.when(laptopService.update(LAPTOP_1,headers)).thenReturn( LAPTOP_1);

        ResponseEntity<Laptop> laptop = laptopController.update(LAPTOP_1,headers);

        assertEquals(laptop.getBody(), LAPTOP_1);

    }
    @Test
    void delete() {
        Mockito.when(laptopService.delete(LAPTOP_1.getId(),headers)).thenReturn( "Se borro correctamente");

        ResponseEntity<String> message = laptopController.delete(LAPTOP_1.getId(),headers);

        assertEquals(message.getBody(), "Se borro correctamente");

    }
    @Test
    void  deleteAll() {
        Mockito.when(laptopService.deleteAll(headers)).thenReturn( "Se borraron correctamente");

        ResponseEntity<String> message = laptopController.deleteAll(headers);

        assertEquals(message.getBody(), "Se borraron correctamente");


    }


}