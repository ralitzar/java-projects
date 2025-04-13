package com.ralitzaraynova.transit.domain.service;

import com.ralitzaraynova.transit.domain.exception.DomainException;
import com.ralitzaraynova.transit.domain.model.Owner;
import com.ralitzaraynova.transit.domain.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OwnerRegistrationService {

    private final OwnerRepository ownerRepository;

    public Owner search(Long ownerId){
         return ownerRepository.findById(ownerId)
                .orElseThrow(() -> new DomainException("Owner not found!"));
    }

    @Transactional
    public Owner save(Owner owner){
        Boolean existingEmail = ownerRepository.findByEmail(owner.getEmail())
                .filter(p -> !p.equals(owner))
                .isPresent();
        if(existingEmail){
            throw new DomainException("There is already a registered owner with this email address.");
        }
        return ownerRepository.save(owner);
    }

    @Transactional
    public void delete(Long ownerId){
        ownerRepository.deleteById(ownerId);
    }
}
