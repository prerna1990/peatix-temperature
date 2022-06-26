package com.peatix.temperature.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Generated;


@ApiModel(value = "Conversion request body")
@Data
@Generated
public class ConversionRequest {

	@ApiModelProperty(value = "A source unit", required = true)
	@JsonProperty(value = "sourceUnit")
	private String sourceUnit;

	@ApiModelProperty(value = "A destination unit", required = true)
	@JsonProperty(value = "destinationUnit")
	private String destinationUnit;

	@ApiModelProperty(value = "The value to be converted from the source unit to the destination unit", required = true)
	@JsonProperty(value = "value")
	private String value;

}

