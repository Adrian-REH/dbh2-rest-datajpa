package com.example.dbh2restdatajpa.controllers;

import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

class LaptopControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private LaptopRepository laptopRepository;

    @InjectMocks
    private LaptopController laptopController;


    private static final Laptop LAPTOP_1 = new Laptop(1L,"Lenovo",3,false);
    private static final Laptop LAPTOP_2 = new Laptop(2L,"MacOs",4,true);
    private static final Laptop LAPTOP_NULL = new Laptop(null,null,null,null);


    protected Laptop laptop;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(laptopController).build();

     
    }
    
    
    @Test
    void findAll() throws Exception {
        List<Laptop> records= new  ArrayList<Laptop>(Arrays.asList(LAPTOP_1, LAPTOP_2));
        Mockito.when(laptopRepository.findAll()).thenReturn( records);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "laptop-value-45xx23");
        assertNotNull(laptopController.findAll(headers));
    }


    @Test
    void create() {
        Mockito.when(laptopRepository.save(LAPTOP_1)).thenReturn(LAPTOP_1);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "laptop-value-45xx23");

        //VERIFICO QUE CONTIENE LO CORRECTO
        assertNull(laptopRepository.save(LAPTOP_NULL));
        ResponseEntity<Laptop> laptop = laptopController.create(LAPTOP_1,headers);
        assertEquals(LAPTOP_1.getId(), laptop.getBody().getId());

        //VERIFICO QUE DEVUELVE LO CORRECTO
        Mockito.when(laptopController.create(LAPTOP_1,headers)).thenReturn(ResponseEntity.ok(LAPTOP_1));
    }
    @Test
    void create_Null() {

        Mockito.when(laptopRepository.save(LAPTOP_NULL)).thenReturn(null);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "laptop-value-45xx23");
        Mockito.when(laptopController.create(LAPTOP_1,headers)).thenReturn(ResponseEntity.ok(LAPTOP_1));

    }

    @Test
    void create_Auth_Null() {
        Mockito.when(laptopRepository.save(LAPTOP_NULL)).thenReturn(null);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "");
        assertNull(laptopRepository.save(LAPTOP_NULL));
        Mockito.when(laptopController.create(LAPTOP_1,headers)).thenReturn(ResponseEntity.notFound().build());


    }




}