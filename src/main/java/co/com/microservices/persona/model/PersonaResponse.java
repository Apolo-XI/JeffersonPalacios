package co.com.microservices.persona.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetEnigmaStepRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-06-15T16:02:12.290-05:00")

public class PersonaResponse {

	@JsonProperty("nombres")
	private String nombres = null;

	@JsonProperty("apellidos")
	private String apellidos = null;

	@JsonProperty("telefono")
	private String telefono = null;

	@JsonProperty("direccion")
	private String direccion = null;

	@JsonProperty("correo")
	private String correo = null;

	@JsonProperty("pass")
	private String pass = null;

	@JsonProperty("rol")
	private String rol = null;

	@JsonProperty("genero")
	private String genero = null;

	@JsonProperty("estado")
	private String estado = null;
	
	@JsonProperty("mensajeResponse")
	private String mensajeResponse = null;


	/**
	 * Get header
	 * 
	 * @return header
	 **/
	@ApiModelProperty(required = true, value = "")
	@NotNull

	@Valid

	public PersonaResponse personaRequest(String nombres, String apellidos, String telefono, String direccion,
			String correo, String pass, String rol, String genero, String estado, String mensajeResponse) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.pass = pass;
		this.rol = rol;
		this.genero = genero;
		this.estado = estado;
		this.mensajeResponse = mensajeResponse;
		return this;
	}

	/**
	 * Get Nombres
	 * 
	 * @return Nombres
	 **/
	@ApiModelProperty(required = true, value = "")

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * Get Apellidos
	 * 
	 * @return Apellidos
	 **/
	@ApiModelProperty(required = true, value = "")

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero the genero to set
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the mensajeResponse
	 */
	public String getMensajeResponse() {
		return mensajeResponse;
	}

	/**
	 * @param mensajeResponse the mensajeResponse to set
	 */
	public void setMensajeResponse(String mensajeResponse) {
		this.mensajeResponse = mensajeResponse;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PersonaResponse persona = (PersonaResponse) o;
		return Objects.equals(this.nombres, persona.nombres)
				&& Objects.equals(this.apellidos, persona.apellidos) && Objects.equals(this.correo, persona.correo)
				&& Objects.equals(this.pass, persona.pass);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombres, apellidos, correo, pass);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PersonaRequest {\n");

		sb.append("    nombres: ").append(toIndentedString(nombres)).append("\n");
		sb.append("    apellidos: ").append(toIndentedString(apellidos)).append("\n");
		sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
		sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
		sb.append("    correo: ").append(toIndentedString(correo)).append("\n");
		sb.append("    pass: ").append(toIndentedString(pass)).append("\n");
		sb.append("    rol: ").append(toIndentedString(rol)).append("\n");
		sb.append("    genero: ").append(toIndentedString(genero)).append("\n");
		sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
		sb.append("    mensajeResponse: ").append(toIndentedString(mensajeResponse)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
