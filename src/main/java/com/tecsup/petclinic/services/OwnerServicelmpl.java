package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;
import com.tecsup.petclinic.repositories.OwnerRepository;
import com.tecsup.petclinic.repositories.PetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServicelmpl implements OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerServicelmpl.class);

    @Autowired
    OwnerRepository ownerRepository;

    /**
     *
     * @param owner
     * @return
     */
    @Override
    public Owner create(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     *
     * @param owner
     * @return
     */

    @Override
    public Owner update(Owner owner) {
        return ownerRepository.save(owner);
    }

    /**
     *
     * @param id
     * @throws OwnerNotFoundException
     */

    @Override
    public void delete(Long id) throws OwnerNotFoundException {

        Owner owner = findById(id);
        ownerRepository.delete(owner);
    }


    /**
     *
     * @param id
     * @return
     */

    @Override
    public Owner findById(long id) throws OwnerNotFoundException {

        Optional<Owner> owner = ownerRepository.findById(id);

        if (!owner.isPresent())
            throw new OwnerNotFoundException("Record not found...!");

        return owner.get();
    }


    /**
     *
     * @param firstName
     * @return
     */
    @Override
    public List<Owner> findByFirstName(String firstName) {

        List<Owner> owners = ownerRepository.findByFirstName(firstName);

        owners.stream().forEach(owner -> logger.info("" + owner));

        return owners;
    }

    /**
     *
     * @param lastName
     * @return
     */
    @Override
    public List<Owner> findByLastName(String lastName) {

        List<Owner> owners = ownerRepository.findByLastName(lastName);

        owners.stream().forEach(owner -> logger.info("" + owner));

        return owners;
    }
    /**
     *
     * @param address
     * @return
     */
    @Override
    public List<Owner> findByAddress(String address) {
        List<Owner> owners = ownerRepository.findByAddress(address);

        owners.stream().forEach(owner -> logger.info("" + owner));

        return owners;
    }

    /**
     *
     * @param city
     * @return
     */
    @Override
    public List<Owner> findByCity(String city) {
        List<Owner> owners = ownerRepository.findByCity(city);

        owners.stream().forEach(owner -> logger.info("" + owner));

        return owners;
    }

    /**
     *
     * @param telephone
     * @return
     */
    @Override
    public List<Owner> findByTelephone(String telephone) {
        List<Owner> owners = ownerRepository.findByTelephone(telephone);

        owners.stream().forEach(owner -> logger.info("" + owner));

        return owners;
    }

    /**
     *
     * @return
     */
    @Override
    public Iterable<Owner> findAll() {

        // TODO Auto-generated
        return ownerRepository.findAll();
    }
}
