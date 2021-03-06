package com.avenue.com.avenue.product.resource.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.stereotype.Component;

@Component
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException>{

	@Override
	public Response toResponse(IllegalArgumentException exception) {
		return Response.serverError().entity(exception.getMessage()).build();
	}

}
