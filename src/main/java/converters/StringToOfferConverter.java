/*
 * StringToOfferConverter.java
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

import repositories.OfferRepository;
import domain.Offer;

@Component
@Transactional
public class StringToOfferConverter implements Converter<String, Offer> {

	@Autowired
	OfferRepository offerRepository;


	@Override
	public Offer convert(String text) {
		Offer result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = offerRepository.findOne(id);
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
