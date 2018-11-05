package co.com.microservices.persona.api;

import co.com.microservices.persona.model.ErrorDetail;
import co.com.microservices.persona.model.GeneralRequest;
import co.com.microservices.persona.model.JsonApiBodyResponseSuccess;
import co.com.microservices.persona.model.Persona;
import co.com.microservices.persona.model.PersonaResponse;
import co.com.microservices.persona.repository.PersonaRepository;
import co.com.microservices.persona.utils.Cifrado;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-31T10:15:12.025-05:00")

@Controller
public class PersonaApiController implements PersonaApi {

	private static final Logger log = LoggerFactory.getLogger(PersonaApiController.class);
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	@Autowired
	PersonaRepository personaRepository;

	final private static Map mapRoles = new HashMap();
	private Cifrado oCifrado = new Cifrado();
	static {
		mapRoles.put("1", "SuperAdmin");
		mapRoles.put("2", "Admin");
		mapRoles.put("3", "Usuario");
	}

	@org.springframework.beans.factory.annotation.Autowired
	public PersonaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<?> addPersona(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody Persona persona) {
		String accept = request.getHeader("Accept");
		ErrorDetail oError = new ErrorDetail();

		if (accept != null && accept.contains("application/json")) {
			if (persona.getId() != null && personaRepository.exists(persona.getId())) {
				oError.setCode("204");
				oError.detail("Ya existe una persona con ID: " + persona.getId());
				oError.setStatus("No content");
				oError.setTitle("Error");
				oError.setSource("/addPersona");

				return returnError(oError,HttpStatus.METHOD_FAILURE);
			} else {
				if ("1".equalsIgnoreCase(persona.getRol())) {
					oError.setCode("204");
					oError.detail(
							"Ya existe un usuario SuperAdministrador, por lo tanto no se puede crear uno adicional.");
					oError.setTitle("Error");
					oError.setStatus("No content");
					oError.setSource("/addPersona");

					return returnError(oError,HttpStatus.METHOD_FAILURE);
				} else {

					if (!mapRoles.containsKey(persona.getRol())) {
						oError.setCode("204");
						oError.detail("No existe el Rol asociado al codigo: [" + persona.getRol() + "].");
						oError.setStatus("No content");
						oError.setTitle("Error");
						oError.setSource("/addPersona");

						return returnError(oError,HttpStatus.METHOD_FAILURE);

					} else if (persona.getEmail() != "") {

						List<Persona> lista = new ArrayList<>();
						personaRepository.findByEmail(persona.getEmail()).forEach(lista::add);
						boolean existeConEmail = false;
						Persona oPersonaValidate = new Persona();
						for (int i = 0; i < lista.size(); i++) {
							oPersonaValidate = (Persona) lista.get(i);
							existeConEmail = oPersonaValidate.getEmail().equals(persona.getEmail());
							if (existeConEmail)
								break;
						}

						if (existeConEmail) {

							if (oPersonaValidate.getEstado().equalsIgnoreCase("I")) {
								oPersonaValidate.setEstado("A");
								personaRepository.save(oPersonaValidate);
								return new ResponseEntity<Persona>(oPersonaValidate, HttpStatus.OK);
							}

							oError.setCode("204");
							oError.detail(
									"Ya existe un usuario registrado con el Email: [" + persona.getEmail() + "]");
							oError.setStatus("No content");
							oError.setTitle("Error");
							oError.setSource("/addPersona");

							return returnError(oError,HttpStatus.METHOD_FAILURE);
						}
					}

					persona.setEstado(
							persona.getEstado() == null || persona.getEstado() == "" ? "A" : persona.getEstado());
					// Cifrado de contraseña
					persona.setPass(oCifrado.Encriptar(persona.getPass()));
					Persona oPersonaResponse = personaRepository.save(persona);
					return new ResponseEntity<Persona>(oPersonaResponse, HttpStatus.OK);
				}
			}
		}

		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> deletePerson(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody GeneralRequest body) {
		String accept = request.getHeader("Accept");

		if (accept != null && accept.contains("application/json")) {
			if (body.getParametro() == null) {
				body.response("Debe diligenciar un ID para realizar un Delete.");

				return new ResponseEntity<GeneralRequest>(body, HttpStatus.NOT_FOUND);
			} else if (!personaRepository.exists(body.getParametro())) {
				body.response("No existe persona con ID: " + body.getParametro() + " para ser eliminada.");

				return new ResponseEntity<GeneralRequest>(body, HttpStatus.NOT_FOUND);
			} else {

				Persona oPersona = personaRepository.findOne(body.getParametro());
				if ("1".equalsIgnoreCase(oPersona.getRol())) {
					body.response("No se puede eliminar el usuario SuperAdministrador.");

					return new ResponseEntity<GeneralRequest>(body, HttpStatus.NOT_FOUND);
				} else {

					oPersona.setEstado("I");
					personaRepository.save(oPersona);
					return new ResponseEntity<Persona>(oPersona, HttpStatus.OK);
				}
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	public ResponseEntity<?> findAll() {
		String accept = request.getHeader("Content-Type");
		if (accept != null && accept.contains("application/json")) {

			JsonApiBodyResponseSuccess oResponse = new JsonApiBodyResponseSuccess();
			List<Persona> lista = (List<Persona>) personaRepository.findAll();
			oResponse.setData(lista);

			return new ResponseEntity<JsonApiBodyResponseSuccess>(oResponse, HttpStatus.OK);

		} else {

			ErrorDetail oError = new ErrorDetail();

			oError.setCode("404");
			oError.detail("No hay información disponible");
			oError.setStatus("No content");
			oError.setTitle("Error");
			oError.setSource("/findAll");

			return returnError(oError,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> findPersonByCorreo(
			@ApiParam(value = "Correo de la persona", required = true) @RequestParam("correo") String correo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {

			JsonApiBodyResponseSuccess oResponse = new JsonApiBodyResponseSuccess();
			List<Persona> lista = new ArrayList<>();
			personaRepository.findByEmail(correo).forEach(lista::add);
			oResponse.setData(lista);
			
			if(lista.size() > 0) {
				return new ResponseEntity<JsonApiBodyResponseSuccess>(oResponse, HttpStatus.OK);
			}else {
				return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> findPersonById(
			@ApiParam(value = "Id de la persona", required = true) @RequestParam("id") String id) {
		String accept = request.getHeader("Content-Type");
		if (accept != null && accept.contains("application/json")) {
			return new ResponseEntity<Persona>(personaRepository.findOne(id), HttpStatus.OK);
		} else {

			ErrorDetail oError = new ErrorDetail();

			oError.setCode("204");
			oError.detail("No hay información disponible");
			oError.setStatus("No content");
			oError.setTitle("Error");
			oError.setSource("/findPersonById");

			return returnError(oError,HttpStatus.METHOD_FAILURE);

		}
	}

	public ResponseEntity<?> validateLogin(
			@ApiParam(value = "Email de la persona", required = true) @RequestParam("email") String email,
			@ApiParam(value = "Pass de la persona", required = true) @RequestParam("pass") String pass) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {

			List<Persona> lista = new ArrayList<>();
			ErrorDetail oError = new ErrorDetail();
			try {
				pass = oCifrado.Encriptar(pass);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(email == "" || pass == "") {
				oError.setCode(HttpStatus.BAD_REQUEST.toString());
				oError.detail("El correo y/o contraseña no pueden ser null");
				oError.setStatus("Bad request");
				oError.setTitle("Error");
				oError.setSource("/validateLogin");
				return returnError(oError,HttpStatus.BAD_REQUEST);
			} 
			
			personaRepository.findByEmailAndPass(email, pass).forEach(lista::add);

			if (lista.isEmpty()) {
				oError.setCode("400");
				oError.detail("El correo y/o contraseña son incorrectos");
				oError.setStatus("No content");
				oError.setTitle("Error");
				oError.setSource("/validateLogin");

				return returnError(oError,HttpStatus.BAD_REQUEST);
			} else if (lista.size() > 0) {
				return new ResponseEntity<Persona>(lista.get(0), HttpStatus.OK);
			}

		} 
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> updatePerson(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody Persona persona) {
		String accept = request.getHeader("Accept");
		ErrorDetail oError = new ErrorDetail();

		if (accept != null && accept.contains("application/json")) {
			if (persona.getId() == null) {

				oError.setCode("404");
				oError.detail("Debe diligenciar un ID para realizar un Update.");
				oError.setStatus("No found");
				oError.setTitle("Error");
				oError.setSource("/updatePerson");

				return returnError(oError,HttpStatus.NOT_FOUND);
			} else if (!personaRepository.exists(persona.getId())) {
				
				oError.setCode("404");
				oError.detail("No existe la Persona con ID: " + persona.getId());
				oError.setStatus("No found");
				oError.setTitle("Error");
				oError.setSource("/updatePerson");

				return returnError(oError,HttpStatus.NOT_FOUND);
			} else {

				Persona oPersonaValidate = personaRepository.findOne(persona.getId());
				if (oPersonaValidate.getEstado().equalsIgnoreCase("I")) {

					oError.setCode("404");
					oError.detail("No esta permitido modificar Personas inactivas");
					oError.setStatus("No found");
					oError.setTitle("Error");
					oError.setSource("/updatePerson");

					return returnError(oError,HttpStatus.NOT_FOUND);
				} else if ("1".equalsIgnoreCase(persona.getRol())) {
					oError.setCode("204");
					oError.detail(
							"Ya existe un usuario SuperAdministrador, por lo tanto no se puede actualizar uno adicional a este mismo rol.");
					oError.setTitle("Error");
					oError.setStatus("No content");
					oError.setSource("/updatePerson");

					return returnError(oError,HttpStatus.METHOD_FAILURE);
				}

				// Cifrado de contraseña
				persona.setPass(oCifrado.Encriptar(persona.getPass()));
				personaRepository.save(persona);
				return new ResponseEntity<Persona>(persona, HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	
	private ResponseEntity<?> returnError(ErrorDetail oError, HttpStatus status){
		List listaErrores = new ArrayList();
		listaErrores.add(oError);
		return new ResponseEntity<List>(listaErrores, status);
	}
	
}
