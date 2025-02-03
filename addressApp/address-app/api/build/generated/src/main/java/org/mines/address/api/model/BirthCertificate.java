package org.mines.address.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * BirthCertificate
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-03T10:03:17.438581522+01:00[Europe/Paris]", comments = "Generator version: 7.4.0")
public class BirthCertificate {

  private String id;

  private String personId;

  private String birthPlace;

  public BirthCertificate() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BirthCertificate(String personId, String birthPlace) {
    this.personId = personId;
    this.birthPlace = birthPlace;
  }

  public BirthCertificate id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public BirthCertificate personId(String personId) {
    this.personId = personId;
    return this;
  }

  /**
   * Get personId
   * @return personId
  */
  @NotNull 
  @Schema(name = "personId", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("personId")
  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public BirthCertificate birthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
    return this;
  }

  /**
   * Get birthPlace
   * @return birthPlace
  */
  @NotNull 
  @Schema(name = "birthPlace", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("birthPlace")
  public String getBirthPlace() {
    return birthPlace;
  }

  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BirthCertificate birthCertificate = (BirthCertificate) o;
    return Objects.equals(this.id, birthCertificate.id) &&
        Objects.equals(this.personId, birthCertificate.personId) &&
        Objects.equals(this.birthPlace, birthCertificate.birthPlace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personId, birthPlace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BirthCertificate {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    personId: ").append(toIndentedString(personId)).append("\n");
    sb.append("    birthPlace: ").append(toIndentedString(birthPlace)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

