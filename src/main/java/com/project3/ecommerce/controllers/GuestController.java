package com.project3.ecommerce.controllers;

import com.project3.ecommerce.models.Category;
import com.project3.ecommerce.models.Guest;
import com.project3.ecommerce.models.Invoice;
import com.project3.ecommerce.services.implementations.GuestServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guest")
public class GuestController {
    private GuestServiceImpl guestServiceImpl;

    public GuestController(GuestServiceImpl guestServiceImpl) {
        super();
        this.guestServiceImpl = guestServiceImpl;
    }

    @GetMapping("/show")
    public List<Guest> showAllGuest(){
        return guestServiceImpl.getAllGuests();
    }

    @PostMapping("/save")
    @ResponseBody
    public Guest saveGuest(@ModelAttribute Guest guest){
        return guestServiceImpl.saveGuest(guest);
    }

}
