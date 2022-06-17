package com.Project_2_Location_Search_API.service;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import com.Project_2_Location_Search_API.entities.StatusReport;
import com.Project_2_Location_Search_API.repositories.LocationQueryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LocationQueryServiceTest {

    private LocationQueryRepository mockLocationQueryRepository;

    @InjectMocks
    private LocationQueryService locationQueryService = new LocationQueryService();

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void initBefore() {
        mockLocationQueryRepository = mock(LocationQueryRepository.class);
//        locationQueryService = new LocationQueryService();
        locationQueryService.setLocationQueryRepository(mockLocationQueryRepository);
    }

    @Test
    public void shouldCreateNewSearch(){
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, 20, 100);

        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);
        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.saveSearch(locationQuery);
        });
    }

    @Test
    public void shouldFailToCreateNewSearchLocation() {
        LocationQuery locationQuery = new LocationQuery(1, null, "unsafe",
                500, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Location name can't be null", ex.getMessage(), "Wrong exception thrown when given a null location");
    }

    @Test
    public void shouldFailToCreateNewSearchStatus() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", null,
                500, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Status can't be null", ex.getMessage(), "Wrong exception thrown when given a null status");
    }

    @Test
    public void shouldFailToCreateNewSearchPopulation() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                null, 300, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Population can't be null", ex.getMessage(), "Wrong exception thrown when given a null population");
    }

    @Test
    public void shouldFailToCreateNewSearchVaccinated() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, null, 50, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Vaccinated can't be null", ex.getMessage(), "Wrong exception thrown when given a null vaccination");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalInfections() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, null, 20, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Total infections can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Infections");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalDeaths() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, null, 100);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Total deaths can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Deaths");
    }

    @Test
    public void shouldFailToCreateNewSearchTotalRecovered() {
        LocationQuery locationQuery = new LocationQuery(1, "spain", "unsafe",
                500, 300, 50, 20, null);
        when(mockLocationQueryRepository.save(locationQuery)).thenReturn(locationQuery);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> locationQueryService.saveSearch(locationQuery));
        Assertions.assertEquals("Total recovered can't be null", ex.getMessage(), "Wrong exception thrown when given a null Total Recovered");
    }

    @Test
    public void shouldGetAllSearches() {
        List<LocationQuery> locationQueries = new ArrayList<>();
        when(mockLocationQueryRepository.findAll()).thenReturn(locationQueries);

        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.getAllSearches();
        });
    }

    @Test
    public void shouldGetAllByFilter() {
        List<LocationQuery> locationQueries = new ArrayList<>();
        when(mockLocationQueryRepository.findAll()).thenReturn(locationQueries);

        Assertions.assertDoesNotThrow(() -> {
            locationQueryService.getAllByFilter("location_name",    "test", 0);
            locationQueryService.getAllByFilter("status",           "test", 0);
            locationQueryService.getAllByFilter("population",       "test", 0);
            locationQueryService.getAllByFilter("vaccinated",       "test", 0);
            locationQueryService.getAllByFilter("total_infections", "test", 0);
            locationQueryService.getAllByFilter("total_deaths",     "test", 0);
            locationQueryService.getAllByFilter("total_recovered",  "test", 0);
        });
    }

    @Test
    public void shouldGetRequestStatusReportByCountry(){

        StatusReport statusReport = new StatusReport(1, 80.00, "Canada", LocalDate.parse("2022-05-21"), "Safe to travel");

//        doReturn(statusReport).when(restTemplate).getForObject(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.<Class<StatusReport>>any());

        when(restTemplate.getForObject(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.<Class<StatusReport>>any()))
                .thenReturn(statusReport);

        Assertions.assertEquals(statusReport, locationQueryService.requestStatusReportByCountry("Canada"));
    }
}
