package com.proycc.base;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Item;
import com.proycc.base.service.CotizacionService;
import com.proycc.base.service.ItemService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;


@SpringBootApplication
public class BaseApplication implements CommandLineRunner {
    
    @Autowired
    ItemService customerRepository;
    @Autowired
    CotizacionService cotizacionService;
    
    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }
    
    public static void main(String[] args)  {
        SpringApplication.run(BaseApplication.class, args);
        System.out.println("se ejecutara?");
     }
 
    //para probar cosas
   
    @Override
    public void run(String... args) throws Exception {
        
        Item item = new Item("clavos", 10.0F, 1000);
        item = customerRepository.saveOrUpdate(item);

        item.setName("clavos acero");

        customerRepository.saveOrUpdate(item);

        System.out.println(customerRepository.findByName("clavos acero"));

        System.out.println(customerRepository.findByNameAndQuantity("clavos acero", 1000));

        customerRepository.delete(item);

        System.out.println("Done!");
        
        System.out.println("------Prueba de cotizacion---------------");
        Cotizacion c1 = new Cotizacion(LocalDateTime.now(), "USD", "EMPRES", "AGENCIA", Float.POSITIVE_INFINITY, Float.NaN, Float.MIN_NORMAL, Float.MIN_NORMAL, "Billetes", "compra", "$$");
        System.out.println("cotizacion " + c1.toString());
        cotizacionService.saveOrUpdate(c1);
        c1.setComisionVta(new Float(120.22));
        System.out.println(c1);
        cotizacionService.saveOrUpdate(c1);
        System.out.println("todo " + cotizacionService.getAll());
        
        //exit(0);
    }
}
