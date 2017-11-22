package com.avenue.com.avenue.product.resource;

import static org.mockito.BDDMockito.given;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.AbstractHttpMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.avenue.com.avenue.product.Application;
import com.avenue.com.avenue.product.entity.Image;
import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ProductDTO;
import com.avenue.com.avenue.product.service.ImageService;
import com.avenue.com.avenue.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class RestClientTest {

	private static final String SERVER_URL_PRODUCTS = "http://localhost:8080/products";

	private static final String SERVER_URL_IMAGES = "http://localhost:8080/products/1/images";

	@MockBean
	private ImageService imageService;

	@MockBean
	private ProductService productService;

	@Test
	public void testGetProductsClient() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS);

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testGetProductsIncludingRelationshipsClient() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/complete");

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testGetByProductClient() throws ClientProtocolException, IOException {

		given(productService.getByProduct(1L)).willReturn(new ProductDTO());

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/1");

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testGetByProductIncludingRelationshipsClient() throws ClientProtocolException, IOException {

		given(productService.getByProductIncludingRelationships(1L)).willReturn(new ProductDTO());

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/1/complete");

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testGetByProductClientError() throws ClientProtocolException, IOException {

		given(productService.getByProduct(5L)).willThrow(new IllegalArgumentException(""));

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/5");

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	@Test
	public void testGetByProductIncludingRelationshipsClientError() throws ClientProtocolException, IOException {

		given(productService.getByProductIncludingRelationships(5L)).willThrow(new IllegalArgumentException(""));

		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/5/complete");

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void testGetChildrenByProduct() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet(SERVER_URL_PRODUCTS + "/1/children");
		
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test
	public void testCreateProduct() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(SERVER_URL_PRODUCTS);
		setHttpHeader(httpPost);
	    httpPost.setEntity(new StringEntity(productJson()));
		
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test
	public void testUpdateProduct() throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(SERVER_URL_PRODUCTS+"/1");
		setHttpHeader(httpPut);
		httpPut.setEntity(new StringEntity(productJson()));
		
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPut);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test
	public void testDeleteProduct() throws ClientProtocolException, IOException {
		HttpDelete httpDelete = new HttpDelete(SERVER_URL_PRODUCTS+"/1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpDelete);
		
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	@Test
	public void testGetImagesClient() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(SERVER_URL_IMAGES);

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testCreateImage() throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(SERVER_URL_IMAGES);
		setHttpHeader(httpPost);
		httpPost.setEntity(new StringEntity(imageJson()));

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testUpdateImage() throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut(SERVER_URL_IMAGES + "/1");
		setHttpHeader(httpPut);
		httpPut.setEntity(new StringEntity(imageJson()));

		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPut);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	public void testDeleteImage() throws ClientProtocolException, IOException {
		HttpDelete httpDelete = new HttpDelete(SERVER_URL_IMAGES + "/1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpDelete);

		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
	}
	
	private String imageJson() throws JsonProcessingException {
		Image image = new Image();
		image.setType("Test");
		
		return new ObjectMapper().writeValueAsString(image);
	}	
	
	
	private String productJson() throws JsonProcessingException {
		Product product = new Product();
		product.setName("Test");
		product.setDescription("Test Desc");
		
		return new ObjectMapper().writeValueAsString(product);
	}
	
	private void setHttpHeader(AbstractHttpMessage httpMethod) {
		httpMethod.setHeader("Accept", "application/json");
		httpMethod.setHeader("Content-type", "application/json");
	}
}
