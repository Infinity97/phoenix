package com.pheonix.user.management.dao.impl;

import com.pheonix.user.management.dto.ApiResponseStatus;
import com.pheonix.user.management.model.Country;
import com.pheonix.user.management.dao.repository.CountryRepository;
import com.pheonix.user.management.dao.CountryDao;
import com.pheonix.user.management.utils.exception.PheonixException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao {

    private final CountryRepository countryRepository;

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Integer Id)throws PheonixException{
        return countryRepository.findById(Id).orElseThrow(() -> new PheonixException(ApiResponseStatus.COUNTRY_NOT_PRESENT, HttpStatus.BAD_REQUEST));
    }
}
