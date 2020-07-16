package com.centime.modal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@JsonProperty("Name")
	@NotBlank(message = "Name cannot be blank")
	private String name;

	@JsonProperty("Surname")
	@NotBlank(message = "Surname cannot be blank")
	private String surName;

	@Override
	public String toString() {
		return "User [name=" + name + ", surName=" + surName + "]";
	}
	

}
