package org.softuni.mobilele;

import org.softuni.mobilele.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));
    private final SeedService seedService;

    @Autowired
    public DataInitializer(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Greetings fellow buyer");
        this.seedService.seedRoles();

        while (true) {
            System.out.println("Choose option from:" +
                    "\n0 - Exit" +
                    "\n1 - Seed Users" +
                    "\n2 - Seed Brands" +
                    "\n3 - Seed Models" +
                    "\n4 - Seed Offers"
            );


            String input = bufferedReader.readLine().toLowerCase();

            switch (input) {
                case "1":
                    System.out.println(this.seedService.seedUsers());

                    break;
                case "2":
                    System.out.println(this.seedService.seedBrands());

                    break;
                case "3":
                    System.out.println(this.seedService.seedModels());

                    break;
                case "4":

                    break;
                case "0":
                    return;
            }

            System.out.println("==================================");
        }
    }

}
