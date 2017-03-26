/*
 * StringToDemandConverter.java
 * 
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.DemandRepository;
import domain.Demand;

@Component
@Transactional
public class StringToDemandConverter implements Converter<String, Demand> {

	@Autowired
	DemandRepository demandRepository;


	@Override
	public Demand convert(String text) {
		Demand result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = demandRepository.findOne(id);
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
