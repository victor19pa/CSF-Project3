package com.project3.ecommerce.services.implementations;

import com.project3.ecommerce.models.Guest;
import com.project3.ecommerce.repositories.GuestRepository;
import com.project3.ecommerce.services.GuestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        super();
        this.guestRepository = guestRepository;
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id).get();
    }

    @Override
    public Guest saveGuest(Guest guest) {
        return guestRepository.save(guest);
    }
}
