package com.peatix.conversion.service;

import com.peatix.conversion.dtos.ConversionRequest;
import com.peatix.conversion.dtos.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureConvertorService {
	Double celsiusToFahrenheit(Double temperature);

	Double fahrenheitToCelsius(Double temperature);

	ConversionResponse convertResponse(ConversionRequest conversionRequest);
}
