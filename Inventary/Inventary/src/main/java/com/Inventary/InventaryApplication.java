package com.Inventary;

import com.Inventary.Entity.Inventory;
import com.Inventary.Repository.InventoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class InventaryApplication {
  
	public static void main(String[] args) {
		SpringApplication.run(InventaryApplication.class, args);
	}

	//in controller the method check product is in stock for that i need same product in inventory
    @Bean
	public CommandLineRunner loadData(InventoryRepo inventoryRepo){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_14");
			inventory1.setQuantity(0);

			inventoryRepo.save(inventory);
			inventoryRepo.save(inventory1);

		};
	}
}
