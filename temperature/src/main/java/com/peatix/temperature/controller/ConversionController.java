package com.peatix.temperature.controller;

import com.peatix.temperature.dtos.ConversionRequest;
import com.peatix.temperature.dtos.ConversionResponse;
import com.peatix.temperature.dtos.ConversionStrategy;
import com.peatix.temperature.service.ApplicationStateService;
import com.peatix.temperature.service.TemperatureConvertorService;
import javax.annotation.PostConstruct;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/temperature/v1")
@Slf4j
public class ConversionController {
	@Autowired
	TemperatureConvertorService temperatureConvertorService;

	@Autowired
	ApplicationStateService applicationStateService;


	@PostMapping("/convert")
	public ConversionResponse convertAny(@RequestBody ConversionRequest conversionRequest) {
		if (!applicationStateService.isReady()) {
			throw new ServiceNotReadyException();
		}
		try {
			log.info("Raw request body: " + conversionRequest.toString());
			return temperatureConvertorService.convertResponse(conversionRequest);
		} catch (Exception e) {
			log.error("EXCEPTION: " + ExceptionUtils.getStackTrace(e));
		}
		throw new InvalidRequestException();
	}

	private ConversionResponse convertResponse(ConversionRequest conversionRequest, Double result) {
		if (conversionRequest.getDestinationUnit().equalsIgnoreCase(conversionRequest.getSourceUnit()))
			return new ConversionResponse(conversionRequest.getSourceUnit().toUpperCase(), Double.parseDouble(conversionRequest.getValue()), Double.parseDouble(conversionRequest.getValue()), conversionRequest.getDestinationUnit().toUpperCase());
		return new ConversionResponse(conversionRequest.getSourceUnit().toUpperCase(), Double.parseDouble(conversionRequest.getValue()), result, conversionRequest.getDestinationUnit().toUpperCase());
	}

	@PostConstruct
	public void postConstruct() {
		try {
			applicationStateService.prepareReadyState();
		} catch (InterruptedException e) {
			log.error("EXCEPTION: ", e);
		}
	}

	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "The service is not in a ready state")
	@Generated
	public class ServiceNotReadyException extends RuntimeException {}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The conversion request is not yet supported")
	@Generated
	public class InvalidRequestException extends RuntimeException {}
}
