package com.pheonix.user.management.dao;

import com.pheonix.user.management.model.Country;
import com.pheonix.user.management.utils.exception.PheonixException;

import java.util.Optional;

public interface CountryDao {

    Country save(Country country);
    Country findById(Integer Id)throws PheonixException;

}
