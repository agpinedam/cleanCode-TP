package org.mines.address.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
import java.util.Map;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Statistics
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-02-03T10:03:17.438581522+01:00[Europe/Paris]", comments = "Generator version: 7.4.0")
public class Statistics {

  private Integer totalPersons;

  private Integer totalBirthCertificates;

  private Float averageAge;

  @Valid
  private Map<String, Integer> personsByAgeGroup = new HashMap<>();

  public Statistics totalPersons(Integer totalPersons) {
    this.totalPersons = totalPersons;
    return this;
  }

  /**
   * Total number of persons in the registry
   * @return totalPersons
  */
  
  @Schema(name = "totalPersons", description = "Total number of persons in the registry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPersons")
  public Integer getTotalPersons() {
    return totalPersons;
  }

  public void setTotalPersons(Integer totalPersons) {
    this.totalPersons = totalPersons;
  }

  public Statistics totalBirthCertificates(Integer totalBirthCertificates) {
    this.totalBirthCertificates = totalBirthCertificates;
    return this;
  }

  /**
   * Total number of birth certificates issued
   * @return totalBirthCertificates
  */
  
  @Schema(name = "totalBirthCertificates", description = "Total number of birth certificates issued", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalBirthCertificates")
  public Integer getTotalBirthCertificates() {
    return totalBirthCertificates;
  }

  public void setTotalBirthCertificates(Integer totalBirthCertificates) {
    this.totalBirthCertificates = totalBirthCertificates;
  }

  public Statistics averageAge(Float averageAge) {
    this.averageAge = averageAge;
    return this;
  }

  /**
   * Average age of persons in the registry
   * @return averageAge
  */
  
  @Schema(name = "averageAge", description = "Average age of persons in the registry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("averageAge")
  public Float getAverageAge() {
    return averageAge;
  }

  public void setAverageAge(Float averageAge) {
    this.averageAge = averageAge;
  }

  public Statistics personsByAgeGroup(Map<String, Integer> personsByAgeGroup) {
    this.personsByAgeGroup = personsByAgeGroup;
    return this;
  }

  public Statistics putPersonsByAgeGroupItem(String key, Integer personsByAgeGroupItem) {
    if (this.personsByAgeGroup == null) {
      this.personsByAgeGroup = new HashMap<>();
    }
    this.personsByAgeGroup.put(key, personsByAgeGroupItem);
    return this;
  }

  /**
   * Number of persons categorized by age groups
   * @return personsByAgeGroup
  */
  
  @Schema(name = "personsByAgeGroup", description = "Number of persons categorized by age groups", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("personsByAgeGroup")
  public Map<String, Integer> getPersonsByAgeGroup() {
    return personsByAgeGroup;
  }

  public void setPersonsByAgeGroup(Map<String, Integer> personsByAgeGroup) {
    this.personsByAgeGroup = personsByAgeGroup;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Statistics statistics = (Statistics) o;
    return Objects.equals(this.totalPersons, statistics.totalPersons) &&
        Objects.equals(this.totalBirthCertificates, statistics.totalBirthCertificates) &&
        Objects.equals(this.averageAge, statistics.averageAge) &&
        Objects.equals(this.personsByAgeGroup, statistics.personsByAgeGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPersons, totalBirthCertificates, averageAge, personsByAgeGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Statistics {\n");
    sb.append("    totalPersons: ").append(toIndentedString(totalPersons)).append("\n");
    sb.append("    totalBirthCertificates: ").append(toIndentedString(totalBirthCertificates)).append("\n");
    sb.append("    averageAge: ").append(toIndentedString(averageAge)).append("\n");
    sb.append("    personsByAgeGroup: ").append(toIndentedString(personsByAgeGroup)).append("\n");
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

