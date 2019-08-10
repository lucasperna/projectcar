package com.lperna.projectcar.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.lperna.projectcar.domain.VehicleType;

public class VehicleTypeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório.")
	private String name;
	private String desc;
	
	public VehicleTypeDTO()
	{}

	public VehicleTypeDTO(VehicleType obj)
	{
		this.id = obj.getId();
		this.name = obj.getName();
		this.desc = obj.getDesc();
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
