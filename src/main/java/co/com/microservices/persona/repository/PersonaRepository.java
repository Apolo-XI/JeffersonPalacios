package co.com.microservices.persona.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import co.com.microservices.persona.model.JsonApiBodyResponseSuccess;
import co.com.microservices.persona.model.Persona;

@EnableScan
public interface PersonaRepository extends CrudRepository<Persona, String>{
	public List<Persona> findByCorreo(String correo);
	public List<Persona> findById(String id);
}
