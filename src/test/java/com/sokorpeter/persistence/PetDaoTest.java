package com.sokorpeter.persistence;


import org.junit.Test;
import petstore.Pet;

import static org.junit.Assert.assertEquals;

/**
 * The type Pet dao test.
 */
public class PetDaoTest {
    /**
     * Test get pet by id.
     */
    @Test
    public void testGetPetById() {
        PetDao petDao = new PetDao();
        Pet pet = petDao.getPetById(1);
        assertEquals("doggie", pet.getName());
    }
}