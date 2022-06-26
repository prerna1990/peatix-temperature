package com.peatix.temperature.dtos;

import com.peatix.temperature.service.TemperatureConvertorService;
import com.peatix.temperature.service.TemperatureConvertorServiceImpl;

public enum ConversionStrategy {

	CELSIUS_TO_FAHRENHEIT  {
		@Override
		public
		Double convert(Double temperature) {
			TemperatureConvertorService temperatureConvertorService = new TemperatureConvertorServiceImpl();
			return temperatureConvertorService.celsiusToFahrenheit(temperature);
		}
	},

	FAHRENHEIT_TO_CELSIUS {
		@Override
		public
		Double convert(Double temperature) {
			TemperatureConvertorService temperatureConvertorService = new TemperatureConvertorServiceImpl();
			return temperatureConvertorService.fahrenheitToCelsius(temperature);
		}
	};

	public abstract Double convert(Double value);

}