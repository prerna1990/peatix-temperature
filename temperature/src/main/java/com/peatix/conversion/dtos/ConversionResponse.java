package com.peatix.conversion.dtos;


import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class ConversionResponse {

	private String inputUnit;

	private Double inputValue;

	private Double convertedValue;

	private String convertedUnit;

}
