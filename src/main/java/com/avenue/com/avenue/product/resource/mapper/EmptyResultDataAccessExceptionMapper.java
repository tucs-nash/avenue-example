package com.avenue.com.avenue.product.resource.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class EmptyResultDataAccessExceptionMapper implements ExceptionMapper<EmptyResultDataAccessException> {

	@Override
	public Response toResponse(EmptyResultDataAccessException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}
}
