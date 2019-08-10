package com.lperna.projectcar.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.lperna.projectcar.domain.Vehicle;

public class VehicleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório.")
	private String name;
	@Length(min=7,max=7,message="O tamanho deve ter 7 caracteres.")
	private String plate;
	
	public VehicleDTO()
	{}

	public VehicleDTO(Vehicle obj)
	{
		this.id = obj.getId();
		this.name = obj.getName();
		this.plate = obj.getPlate();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
}
