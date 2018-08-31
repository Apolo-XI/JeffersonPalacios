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
 * GeneralRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-08-31T10:15:12.025-05:00")

public class GeneralRequest   {
  @JsonProperty("parametro")
  private String parametro = null;

  @JsonProperty("parametro2")
  private String parametro2 = null;

  public GeneralRequest parametro(String parametro) {
    this.parametro = parametro;
    return this;
  }

  /**
   * Get parametro
   * @return parametro
  **/
  @ApiModelProperty(value = "")


  public String getParametro() {
    return parametro;
  }

  public void setParametro(String parametro) {
    this.parametro = parametro;
  }

  public GeneralRequest parametro2(String parametro2) {
    this.parametro2 = parametro2;
    return this;
  }

  /**
   * Get parametro2
   * @return parametro2
  **/
  @ApiModelProperty(value = "")


  public String getParametro2() {
    return parametro2;
  }

  public void setParametro2(String parametro2) {
    this.parametro2 = parametro2;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeneralRequest generalRequest = (GeneralRequest) o;
    return Objects.equals(this.parametro, generalRequest.parametro) &&
        Objects.equals(this.parametro2, generalRequest.parametro2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parametro, parametro2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GeneralRequest {\n");
    
    sb.append("    parametro: ").append(toIndentedString(parametro)).append("\n");
    sb.append("    parametro2: ").append(toIndentedString(parametro2)).append("\n");
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

