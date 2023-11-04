package com.sokorpeter.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petstore.Pet;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Pet dao.
 */
public class PetDao {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets pet by id.
     *
     * @param petId the pet id
     * @return the pet by id
     */
    public Pet getPetById(long petId) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("/Users/mac/EntJavaRepos/WEEK8/petstoreApi/src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            logger.error("Error loading config.properties", e);
            throw new RuntimeException(e);
        }

        // get the url address from properties file
        String apiUrl = properties.getProperty("petstore.api.url");

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(apiUrl + petId);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Pet pet = null;
        try {
            pet = mapper.readValue(response, Pet.class);
        } catch (Exception e) {
            logger.error("can not read file with JSON format");
        }
        return pet;
    }
}
