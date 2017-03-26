/*
 * StringToCommentConverter.java
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

import repositories.CommentRepository;
import domain.Comment;

@Component
@Transactional
public class StringToCommentConverter implements Converter<String, Comment> {

	@Autowired
	CommentRepository commentRepository;


	@Override
	public Comment convert(String text) {
		Comment result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = commentRepository.findOne(id);
		} catch (Exception oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
