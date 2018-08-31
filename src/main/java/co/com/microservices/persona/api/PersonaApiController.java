package co.com.microservices.persona.api;

import co.com.microservices.persona.model.ErrorDetail;
import co.com.microservices.persona.model.GeneralRequest;
import co.com.microservices.persona.model.JsonApiBodyResponseSuccess;
import co.com.microservices.persona.model.Persona;
import co.com.microservices.persona.model.PersonaResponse;
import co.com.microservices.persona.repository.PersonaRepository;

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
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-31T10:15:12.025-05:00")

@Controller
public class PersonaApiController implements PersonaApi {

	private static final Logger log = LoggerFactory.getLogger(PersonaApiController.class);
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	@Autowired
	PersonaRepository personaRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public PersonaApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Void> addPersona(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody Persona body) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<Void> deletePerson(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody GeneralRequest body) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> findPersonByCorreo(
			@ApiParam(value = "Correo de la persona", required = true) @RequestParam("correo") String correo) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json")) {

			JsonApiBodyResponseSuccess oResponse = new JsonApiBodyResponseSuccess();
			List<Persona> lista = new ArrayList<>();
			personaRepository.findByCorreo(correo).forEach(lista::add);
			oResponse.setData(lista);

			return new ResponseEntity<JsonApiBodyResponseSuccess>(oResponse, HttpStatus.OK);
		}

		return new ResponseEntity<JsonApiBodyResponseSuccess>(HttpStatus.NOT_IMPLEMENTED);
	}

	public ResponseEntity<?> findPersonById(
			@ApiParam(value = "Id de la persona", required = true) @RequestParam("id") String id) {
		String accept = request.getHeader("Content-Type");
		if (accept != null && accept.contains("application/json")) {

			JsonApiBodyResponseSuccess oResponse = new JsonApiBodyResponseSuccess();
			List<Persona> lista = new ArrayList<>();
			personaRepository.findById(id).forEach(lista::add);
			oResponse.setData(lista);

			return new ResponseEntity<JsonApiBodyResponseSuccess>(oResponse, HttpStatus.OK);

		} else {

			ErrorDetail oError = new ErrorDetail();
			List listaErrores = new ArrayList();

			oError.setCode("204");
			oError.detail("No hay información disponible");
			oError.setStatus("No content");

			listaErrores.add(oError);

			return (ResponseEntity<List>) listaErrores;
		}
	}

	public ResponseEntity<Void> updatePerson(
			@ApiParam(value = "Person object that needs to be added to the store", required = true) @Valid @RequestBody Persona body) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}
