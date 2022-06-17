package com.Project_2_Location_Status_API.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class VaccineDataDTO {
    private String country;
    private JsonNode timeline; //contains num of vaccination up to current date-1 i.e. yesterday
}
