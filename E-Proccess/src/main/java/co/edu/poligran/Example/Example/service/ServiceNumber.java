package co.edu.poligran.Example.Example.service;

import co.edu.poligran.Example.Example.Entity.NumberEntity;

public interface ServiceNumber {

	NumberEntity findById(Integer id);

	NumberEntity findByNumber(String number);

	NumberEntity saveNumber(NumberEntity ne);
}
