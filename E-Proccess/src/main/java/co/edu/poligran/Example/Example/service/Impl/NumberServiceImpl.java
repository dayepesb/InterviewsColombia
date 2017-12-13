package co.edu.poligran.Example.Example.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poligran.Example.Example.Entity.NumberEntity;
import co.edu.poligran.Example.Example.Repository.NumberRepository;
import co.edu.poligran.Example.Example.service.ServiceNumber;

@Service
public class NumberServiceImpl implements ServiceNumber{

	@Autowired
	private NumberRepository nr;
	@Override
	public NumberEntity findById(Integer id) {
		return nr.findOne(id);
	}

	@Override
	public NumberEntity findByNumber(String number) {
		return nr.findByNumber(number);
	}

	@Override
	public NumberEntity saveNumber(NumberEntity ne) {
		return nr.save(ne);
	}

}
