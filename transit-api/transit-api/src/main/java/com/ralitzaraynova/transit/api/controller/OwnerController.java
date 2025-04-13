package com.ralitzaraynova.transit.api.controller;

import com.ralitzaraynova.transit.domain.exception.DomainException;
import com.ralitzaraynova.transit.domain.model.Owner;
import com.ralitzaraynova.transit.domain.repository.OwnerRepository;
import com.ralitzaraynova.transit.domain.service.OwnerRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRegistrationService ownerRegistrationService;
    private final OwnerRepository ownerRepository;

    @GetMapping
    public List<Owner> list(){
        return ownerRepository.findAll();
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> search(@PathVariable Long ownerId) {
        return ownerRepository.findById(ownerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner add(@Valid @RequestBody Owner owner){
        return ownerRegistrationService.save(owner);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<Owner> update(@Valid @PathVariable Long ownerId,
                                        @RequestBody Owner owner){
        if(!ownerRepository.existsById(ownerId)){
            return ResponseEntity.notFound().build();
        }
            owner.setId(ownerId);
            Owner ownerUpdated = ownerRegistrationService.save(owner);

            return ResponseEntity.ok(ownerUpdated);

    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Object> remove(@PathVariable Long ownerId){
        if(!ownerRepository.existsById(ownerId)){
            return ResponseEntity.notFound().build();
        }
        ownerRegistrationService.delete(ownerId);
        return ResponseEntity.noContent().build();
    }

}
