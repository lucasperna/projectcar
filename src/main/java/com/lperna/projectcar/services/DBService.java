package com.lperna.projectcar.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lperna.projectcar.domain.VehicleType;
import com.lperna.projectcar.domain.Vehicle;
import com.lperna.projectcar.repositories.VehicleTypeRepository;
import com.lperna.projectcar.repositories.VehicleRepository;


@Service
public class DBService {

	@Autowired
	private VehicleTypeRepository typeRepository;
	@Autowired
	private VehicleRepository vehicleRepository;
	
	
	public void instateateTestDatabase () throws ParseException
	{
		VehicleType t1 = new VehicleType(null, "Automovel","H");
		VehicleType t2 = new VehicleType(null, "Caminhao","C");
		VehicleType t3 = new VehicleType(null, "Saveiro", "M");
		
		Vehicle v1 = new Vehicle(null, "HB20", "AAA1452"); 
		Vehicle v2 = new Vehicle(null, "Corola", "BBB5214"); 
		Vehicle v3 = new Vehicle(null, "Toro", "CCC6985");

		t1.getVehicles().addAll(Arrays.asList(v1,v2));
		t2.getVehicles().addAll(Arrays.asList(v2));
		t3.getVehicles().addAll(Arrays.asList(v3));
		
		v1.getVehicletypes().addAll(Arrays.asList(t1));
		v2.getVehicletypes().addAll(Arrays.asList(t1, t2));
		v3.getVehicletypes().addAll(Arrays.asList(t3));
		
		typeRepository.saveAll(Arrays.asList(t1,t2,t3));
		vehicleRepository.saveAll(Arrays.asList(v1,v2,v3));
		
	}
	
}
