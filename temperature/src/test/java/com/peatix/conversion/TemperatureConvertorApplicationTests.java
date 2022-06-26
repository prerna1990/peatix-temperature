package com.peatix.conversion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.peatix.conversion.dtos.ConversionResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@AutoConfigureMockMvc
@SpringBootTest(classes = {TemperatureConvertorApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TemperatureConvertorApplicationTests {
	private final RestTemplate restTemplate = new RestTemplate();
	@LocalServerPort
	private int port;

	@Test
	public void testTransformCelsiusToFahrenheit() {
		String baseUrl = getUrl("convert");
		String json = "{\n"
					  + "            \"sourceUnit\": \"CELSIUS\",\n"
					  + "            \"destinationUnit\": \"Fahrenheit\",\n"
					  + "            \"value\": \"100\"\n"
					  + "}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		ResponseEntity<ConversionResponse> response = restTemplate.postForEntity(baseUrl, request, ConversionResponse.class);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getInputUnit(), "CELSIUS");
		assertEquals(response.getBody().getInputValue(), Double.valueOf(100));
		assertEquals(response.getBody().getConvertedUnit(), "FAHRENHEIT");
		assertEquals(response.getBody().getConvertedValue(), Double.valueOf(212));
	}

	@Test
	public void testTransformFahrenheitToCelsius() {
		String baseUrl = getUrl("convert");
		String json = "{\n"
					  + "            \"sourceUnit\": \"Fahrenheit\",\n"
					  + "            \"destinationUnit\": \"CELSIUS\",\n"
					  + "            \"value\": \"100\"\n"
					  + "}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		ResponseEntity<ConversionResponse> response = restTemplate.postForEntity(baseUrl, request, ConversionResponse.class);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getInputUnit(), "FAHRENHEIT");
		assertEquals(response.getBody().getInputValue(), Double.valueOf(100));
		assertEquals(response.getBody().getConvertedUnit(), "CELSIUS");
		assertEquals(response.getBody().getConvertedValue(), Double.valueOf(37.77777777777778));
	}

	@Test
	public void testSameMeasureCelsiusTransformation() {
		String baseUrl = getUrl("convert");
		String json = "{\n"
					  + "            \"sourceUnit\": \"CELSIUS\",\n"
					  + "            \"destinationUnit\": \"CELSIUS\",\n"
					  + "            \"value\": \"100\"\n"
					  + "}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		ResponseEntity<ConversionResponse> response = restTemplate.postForEntity(baseUrl, request, ConversionResponse.class);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getInputUnit(), "CELSIUS");
		assertEquals(response.getBody().getInputValue(), Double.valueOf(100));
		assertEquals(response.getBody().getConvertedUnit(), "CELSIUS");
		assertEquals(response.getBody().getConvertedValue(), Double.valueOf(100));
	}

	@Test
	public void testSameMeasureFahrenheitTransformation() {
		String baseUrl = getUrl("convert");
		String json = "{\n"
					  + "            \"sourceUnit\": \"Fahrenheit\",\n"
					  + "            \"destinationUnit\": \"Fahrenheit\",\n"
					  + "            \"value\": \"100\"\n"
					  + "}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		ResponseEntity<ConversionResponse> response = restTemplate.postForEntity(baseUrl, request, ConversionResponse.class);
		assertNotNull(response.getBody());
		assertEquals(response.getBody().getInputUnit(), "FAHRENHEIT");
		assertEquals(response.getBody().getInputValue(), Double.valueOf(100));
		assertEquals(response.getBody().getConvertedUnit(), "FAHRENHEIT");
		assertEquals(response.getBody().getConvertedValue(), Double.valueOf(100));
	}

	private String getUrl(String path) {
		return "http://localhost:" + port + "/" + "temperature/v1" + "/" + path;
	}
}
