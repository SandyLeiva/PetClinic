package com.tecsup.petclinic.controllers;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.OwnerDTO;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.PetDTO;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.services.OwnerService;
import com.tecsup.petclinic.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {
    @Autowired
    private OwnerService service;

    @GetMapping("/owners")
    public Iterable<Owner> getOwners(){
        return service.findAll();
    }

    @RequestMapping(value="/owners/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Owner> findOne(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (OwnerNotFoundException e) {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/owners")
    @ResponseStatus(HttpStatus.CREATED)
    Owner create(@RequestBody OwnerDTO newOwner){
        Owner owner = new Owner();
        owner.setFirstName(newOwner.getFirstName());
        owner.setLastName(newOwner.getLastName());
        owner.setAddress(newOwner.getAddress());
        owner.setCity(newOwner.getCity());
        owner.setTelephone(newOwner.getTelephone());
        return service.create(owner);
    }

    @DeleteMapping("/owners/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        try {
            service.delete(id);
            return  new ResponseEntity<>(""+id,HttpStatus.OK);
        }catch (OwnerNotFoundException e) {
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
