package com.lperna.projectcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lperna.projectcar.domain.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer>
{
	

}