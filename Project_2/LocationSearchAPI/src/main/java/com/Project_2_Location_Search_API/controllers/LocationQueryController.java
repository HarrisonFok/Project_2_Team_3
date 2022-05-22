package com.Project_2_Location_Search_API.controllers;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.entities.StatusReport;
import com.Project_2_Location_Search_API.service.LocationQueryService;
import com.Project_2_Location_Search_API.service.MapService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/search")
public class LocationQueryController {

    @Setter(onMethod =@__({@Autowired}))
    private LocationQueryService locationQueryService;
    @Setter(onMethod =@__({@Autowired}))
    private MapService mapService;

    @PostMapping("/")
    public void addLocationQuery(@RequestBody LocationQuery locationQuery) {
        LocationQuery locationQueryAdded = locationQueryService.saveSearch(locationQuery);
        if (locationQueryAdded == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error happened when adding a search object");
        }
    }

    @GetMapping("/")
    public List<LocationQuery> getAllLocationQueries() {
        return locationQueryService.getAllSearches();
    }

    @GetMapping("")
    public List<LocationQuery> getAllByFilter(@RequestParam String filter, @RequestParam String filterStr, @RequestParam int minNum) {
        List<LocationQuery> retList = locationQueryService.getAllByFilter(filter, filterStr, minNum);
        return retList;
    }

    @GetMapping("{country}")
    public String getCountryMap(@PathVariable String country){
        StatusReport statusReport =locationQueryService.requestStatusReportByCountry(country);
        return statusReport.getStatus();
    }
/*
    @GetMapping("{country}")
    public ResponseEntity getCountryStatusMap(@PathVariable String country){


        StatusReport statusReport =locationQueryService.requestStatusReportByCountry(country);
        String status = statusReport.getStatus();
        System.out.println(status);

        ResponseEntity img = (mapService.getLocationMap(country,"json"));
        System.out.println(img);



        MapWithStatus mapWithStatus = new MapWithStatus((byte[]) img.getBody(),status);
        return ResponseEntity.ok(mapWithStatus);

        //Add call to map api to show interface
    }
*/
}
