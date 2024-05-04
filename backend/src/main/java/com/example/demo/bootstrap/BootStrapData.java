package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerInterface;
import com.example.demo.dao.DivisionInterface;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BootStrapData implements CommandLineRunner {

    public final CustomerInterface customerInterface;
    public final DivisionInterface divisionInterface;

    public BootStrapData(CustomerInterface customerInterface, DivisionInterface divisionInterface) {
        this.customerInterface = customerInterface;
        this.divisionInterface = divisionInterface;
    }

    @Override
    public void run(String... args) throws Exception {
        if (customerInterface.count() < 6) {
            Division arizona = divisionInterface.getReferenceById(2L);
            Customer Aaron = new Customer("Our Rd.", "Aaron", "Tandem", "784-217-4368", "47892", arizona);
            customerInterface.save(Aaron);

            Division michigan = divisionInterface.getReferenceById(21L);
            Customer Kate = new Customer("Everett Rd.", "Kate", "Buckson", "342-222-4686", "90824", michigan);
            customerInterface.save(Kate);

            Division washington = divisionInterface.getReferenceById(46L);
            Customer Jack = new Customer("Ranger Ave.", "Jack", "Vonderworth", "983-109-7467", "19872", washington);
            customerInterface.save(Jack);

            Division illinois = divisionInterface.getReferenceById(12L);
            Customer Carley = new Customer("Chicage Ln.", "Carley", "Savage", "463-911-1572", "09437", illinois);
            customerInterface.save(Carley);

            Division california = divisionInterface.getReferenceById(4L);
            Customer Jacob = new Customer("Goleta Blvd.", "Jacob", "Whitmore", "432-145-8352", "88463", california);
            customerInterface.save(Jacob);
        }
    }

}
