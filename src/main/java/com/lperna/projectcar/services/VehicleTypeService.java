package com.lperna.projectcar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lperna.projectcar.domain.VehicleType;
import com.lperna.projectcar.dto.VehicleTypeDTO;
import com.lperna.projectcar.repositories.VehicleTypeRepository;
import com.lperna.projectcar.services.exceptions.DataIntegretyException;
import com.lperna.projectcar.services.exceptions.ObjectNotFoundException;

@Service
public class VehicleTypeService {

	@Autowired
	private VehicleTypeRepository repo;
	
	public VehicleType find(Integer id)
	{
		Optional<VehicleType> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + VehicleType.class.getName()));
	}
	
	public VehicleType insert (VehicleType obj)
	{
		obj.setId(null);
		return repo.save(obj);
	}
	
	public VehicleType update (VehicleType obj)
	{
		VehicleType newObj = find(obj.getId());
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
			throw new DataIntegretyException("Não é possível excluir Tipo que possui veículo(s)!");
		}
	}
	
	public List<VehicleType> findAll()
	{

		return repo.findAll();
	}
	
	public Page<VehicleType> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public VehicleType fromDTO(VehicleTypeDTO objDTO)
	{
		return new VehicleType(objDTO.getId(), objDTO.getName(), objDTO.getDesc());
	}
	
	private void updateData(VehicleType newObj, VehicleType obj)
	{
		newObj.setName(obj.getName());
		newObj.setDesc(obj.getDesc());
	}
}
