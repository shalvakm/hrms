package com.newhrms.userservice.Models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class AddressConverter<Address extends Object> implements DynamoDBTypeConverter<String, Address> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(Address object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to parse JSON");
    }

    @Override
    public Address unconvert(String object) {
        try {
            Address unconvertedObject = objectMapper.readValue(object,
                    new TypeReference<Address>() {
                    });
            return unconvertedObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
