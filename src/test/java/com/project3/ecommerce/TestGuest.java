package com.project3.ecommerce;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.Guest;
import com.project3.ecommerce.repositories.CategoryRepository;
import com.project3.ecommerce.repositories.GuestRepository;
import com.project3.ecommerce.services.implementations.CategoryServiceImpl;
import com.project3.ecommerce.services.implementations.GuestServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class TestGuest {
    @Autowired
    private GuestRepository repo;
    private GuestServiceImpl serviceImpl;
    @Test
    public void testSaveCategory() {
        Guest guest = new Guest();

        guest.setName("test");
        guest.setAddress("address test");
        guest.setEmail("test@email.com");

        Guest savedGuest = repo.save(guest);
        //Category savedCategory = serviceImpl.saveCategory(category);

        Assertions.assertThat(savedGuest).isNotNull();
        Assertions.assertThat(savedGuest.getId()).isGreaterThan(0);
    }
    @Test
    public void testListCategory(){
        Iterable<Guest> guests = repo.findAll();
        Assertions.assertThat(guests).hasSizeGreaterThan(0);
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }
}
