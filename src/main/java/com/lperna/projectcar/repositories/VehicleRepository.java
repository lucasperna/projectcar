package com.lperna.projectcar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lperna.projectcar.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>
{
	
	/*@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Vehicle obj INNER JOIN obj.types cat WHERE obj.name LIKE %:name% AND cat IN :types")
	Page<Vehicle> search(@Param("name") String nome, @Param("types") List<Type> types, Pageable pageRequest);*/
	

}