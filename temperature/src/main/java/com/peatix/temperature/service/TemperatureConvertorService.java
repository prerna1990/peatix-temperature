package com.peatix.temperature.service;

import com.peatix.temperature.dtos.ConversionRequest;
import com.peatix.temperature.dtos.ConversionResponse;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureConvertorService {
	Double celsiusToFahrenheit(Double temperature);

	Double fahrenheitToCelsius(Double temperature);

	ConversionResponse convertResponse(ConversionRequest conversionRequest);
}
