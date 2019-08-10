package com.lperna.projectcar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lperna.projectcar.domain.Vehicle;
import com.lperna.projectcar.dto.VehicleDTO;
import com.lperna.projectcar.repositories.VehicleRepository;
import com.lperna.projectcar.services.exceptions.DataIntegretyException;
import com.lperna.projectcar.services.exceptions.ObjectNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repo;
	
	/*@Autowired
	private TypeRepository typeRepository;*/
	
	public Vehicle find(Integer id)
	{
		Optional<Vehicle> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Vehicle.class.getName()));
	}
	
	/*public Page<Vehicle> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		List<Type> types = typeRepository.findAllById(ids);
		return repo.search(nome, types, pageRequest);
		
	}*/
	
	public Vehicle insert (Vehicle obj)
	{
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}
	
	public Vehicle update (Vehicle obj)
	{
		Vehicle newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id)
	{
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e)
		{
			throw new DataIntegretyException("Não é possível excluir Veículo que possui tipo(s)!");
		}
	}
	
	public List<Vehicle> findAll()
	{

		return repo.findAll();
	}
	
	public Page<Vehicle> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Vehicle fromDTO(VehicleDTO objDTO)
	{
		return new Vehicle(objDTO.getId(), objDTO.getName(), objDTO.getPlate());
	}
	
	private void updateData(Vehicle newObj, Vehicle obj)
	{
		newObj.setName(obj.getName());
		newObj.setPlate(obj.getPlate());
	}
}
