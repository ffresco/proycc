package com.proycc.base;

import com.proycc.base.domain.Cotizacion;
import com.proycc.base.domain.Item;
import com.proycc.base.service.CotizacionService;
import com.proycc.base.service.ItemService;
import static java.lang.System.exit;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BaseApplication implements CommandLineRunner {
    
    @Autowired
    ItemService customerRepository;
    @Autowired
    CotizacionService cotizacionService;
    
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
        Cotizacion c1 = new Cotizacion(new Date(), "USD", "EMPRES", "AGENCIA", Float.POSITIVE_INFINITY, Float.NaN, Float.MIN_NORMAL, Float.MIN_NORMAL, "Billetes", "compra", "$$");
        System.out.println("cotizacion " + c1.toString());
        cotizacionService.saveOrUpdate(c1);
        c1.setComisionVta(new Float(120.22));
        System.out.println(c1);
        cotizacionService.saveOrUpdate(c1);
        System.out.println("todo " + cotizacionService.getAll());
        

        //exit(0);
    }
}
