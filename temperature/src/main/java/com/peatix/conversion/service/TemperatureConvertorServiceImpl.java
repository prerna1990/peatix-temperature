package com.peatix.conversion.service;

import com.peatix.conversion.dtos.ConversionRequest;
import com.peatix.conversion.dtos.ConversionResponse;
import com.peatix.conversion.dtos.ConversionStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TemperatureConvertorServiceImpl implements TemperatureConvertorService {

	@Override
	public Double celsiusToFahrenheit(Double temperature) {
		return (temperature * 9 / 5) + 32;
	}

	@Override
	public Double fahrenheitToCelsius(Double temperature) {
		return (temperature - 32) * 5 / 9;
	}

	@Override
	public ConversionResponse convertResponse(ConversionRequest conversionRequest) {
		String requestedStrategy = conversionRequest.getSourceUnit().toUpperCase() + "_TO_" + conversionRequest.getDestinationUnit().toUpperCase();
		Double result = Double.valueOf(conversionRequest.getValue());
		if (!conversionRequest.getDestinationUnit().equalsIgnoreCase(conversionRequest.getSourceUnit())) {
			ConversionStrategy conversionStrategy = ConversionStrategy.valueOf(requestedStrategy);
			result = conversionStrategy.convert(Double.parseDouble(conversionRequest.getValue()));
		}
		log.info(conversionRequest.getValue() + " " + conversionRequest.getSourceUnit().toLowerCase() + " is " + result + " in " + conversionRequest.getDestinationUnit().toLowerCase());
		return new ConversionResponse(conversionRequest.getSourceUnit().toUpperCase(), Double.parseDouble(conversionRequest.getValue()), result, conversionRequest.getDestinationUnit().toUpperCase());
	}
}

