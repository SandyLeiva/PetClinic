package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository
    	extends CrudRepository<Owner, Long> {

        List<Owner> findByFirstName(String firstName);


        List<Owner> findByLastName(String lastName);


        List<Owner> findByAddress(String address);

        List<Owner> findByCity(String city);

        List<Owner> findByTelephone(String telephone);

}
