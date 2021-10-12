package com.project3.ecommerce.services;


import com.project3.ecommerce.models.Guest;

import java.util.List;

public interface GuestService {
    List<Guest> getAllGuests();
    Guest getGuestById(Long id);
    Guest saveGuest(Guest guest);
}
