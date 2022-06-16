package com.Project_2_Location_Status_API.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor


public class CovidStatsDTO {

    private String country;
    private Object countryInfo; //contains abbreviated tags for country's(i.e. USA) and url for a flag img, among other info
    private Integer cases;
    private Integer recovered;
    private Integer deaths;
    private Integer population;


}
