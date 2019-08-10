package com.lperna.projectcar.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lperna.projectcar.domain.Vehicle;
import com.lperna.projectcar.dto.VehicleDTO;
import com.lperna.projectcar.services.VehicleService;
import com.lperna.projectcar.services.VehicleTypeService;

@RestController
@RequestMapping(value="vehicles")
public class VehicleResources {
	
	@Autowired
	VehicleService service;
	
	@Autowired
	VehicleTypeService serviceType;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Vehicle> find(@PathVariable Integer id) //Path para o id da URL e variavel
	{
		Vehicle obj = service.find(id);
		return ResponseEntity.ok().body(obj); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Vehicle obj) {
		
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update (@Valid @RequestBody VehicleDTO objDTO, @PathVariable Integer id)
	{
		Vehicle obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id)
	{
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<VehicleDTO>> findAll()
	{
		List<Vehicle> list = service.findAll();
		List<VehicleDTO> listDTO = list.stream().map(obj -> new VehicleDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO); 
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<VehicleDTO>> findPage(@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction)
	{
		Page<Vehicle> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<VehicleDTO> listDTO = list.map(obj -> new VehicleDTO(obj));
		return ResponseEntity.ok().body(listDTO); 
	}
}
