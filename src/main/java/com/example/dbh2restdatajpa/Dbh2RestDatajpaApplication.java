package com.example.dbh2restdatajpa;

import com.example.dbh2restdatajpa.controllers.LaptopController;
import com.example.dbh2restdatajpa.entities.Laptop;
import com.example.dbh2restdatajpa.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class Dbh2RestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Dbh2RestDatajpaApplication.class, args);

		LaptopRepository repository = (LaptopRepository)context.getBean(LaptopRepository.class);
		//Como esta vacia la lista voy a generar una lista

/*		Laptop laptop1=new Laptop(null,"Lenovo",4,false);
		Laptop laptop2=new Laptop(null,"Mac Pro 13",4,true);
		repository.save(laptop1);
		repository.save(laptop2);*/

		// 3. RECUPERAR TODOS LAS LAPTOPS
		System.out.println("Numero de Laptop guardados: "+repository.findAll().size());


	}

}
