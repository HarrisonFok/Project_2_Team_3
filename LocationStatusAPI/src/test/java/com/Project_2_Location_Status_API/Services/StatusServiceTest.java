package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.StatusReport;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StatusServiceTest {
    private StatusService statusService;
    private StatusRepository mockStatusRepository;

    @BeforeEach
    public void initBefore() {
        mockStatusRepository = mock(StatusRepository.class);
        statusService = new StatusService();
        statusService.setStatusRepository(mockStatusRepository);
    }

    @Test
    public void shouldSaveNewSearchSuccessfully(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        Assertions.assertDoesNotThrow(() -> {
            statusService.saveNewStatus(statusReport);
        });
    }

    @Test
    public void shouldNotSaveNewStatusWithNullScore(){
        StatusReport statusReport = new StatusReport(1, 0, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null or negative score", ex.getMessage(), "Null score prevented statusReport creation");
    }

    @Test
    public void shouldNotSaveNewStatusWithNegativeScore(){
        StatusReport statusReport = new StatusReport(1, -25, "Spain",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null or negative score", ex.getMessage(), "Negative score prevented statusReport creation");
    }

    @Test
    public void shouldNotSaveNewStatusWithEmptyStringLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "",
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport without a location", ex.getMessage(), "No provided location prevented statusReport creation");
    }

    @Test
    public void shouldNotSaveNewStatusWithNullLocation(){
        StatusReport statusReport = new StatusReport(1, 54, null,
                LocalDate.parse("2020-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport without a location", ex.getMessage(), "Null location prevented statusReport creation");
    }

    @Test
    public void shouldNotSaveNewStatusWithNullDate(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                null,"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a null creation date", ex.getMessage(), "Null creation date prevented statusReport creation");
    }

    @Test
    public void shouldNotSaveNewStatusWithFutureDate(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2028-01-01"),"Safe to Travel");
        when(mockStatusRepository.save(statusReport)).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.saveNewStatus(statusReport));
        Assertions.assertEquals("Can't create a statusReport with a date in the future", ex.getMessage(), "StatusReport creation cannot use a creation date in the future");
    }

    @Test
    public void shouldGetStatusByLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2022-01-01"),"Safe to Travel");
        when(mockStatusRepository.findByLocation(statusReport.getLocation())).thenReturn(statusReport);

        Assertions.assertEquals(statusReport, statusService.getStatusByLocation(statusReport.getLocation()), "StatusReport was successfully found by location");
    }

    @Test
    public void shouldNotGetStatusByInvalidLocation(){
        StatusReport statusReport = new StatusReport(1, 54, "Spain",
                LocalDate.parse("2022-01-01"),"Safe to Travel");
        when(mockStatusRepository.findByLocation(statusReport.getLocation())).thenReturn(statusReport);

        NullPointerException ex = Assertions.assertThrows(NullPointerException.class, () -> statusService.getStatusByLocation("InvalidLocation"));
        Assertions.assertEquals("InvalidLocation could not be found", ex.getMessage(), "StatusReport cannot be found by invalid location");
    }

    @Test
    public void shouldCalculateScoreSuccessfully() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode timelineNode = mapper.createObjectNode();
        ((ObjectNode) timelineNode).put("5/21/22", 84952660);

        CovidStatsDTO covidStatsDTO = new CovidStatsDTO("Canada", new Object(), 3844725, 3513673, 40664, 38363883);
        VaccineDataDTO vaccineDataDTO = new VaccineDataDTO("Canada", timelineNode);

        double score = statusService.calculateScore(covidStatsDTO, vaccineDataDTO);

        Assertions.assertEquals(110.71957966298666, score);
    }

    @Test
    public void shouldNotCalculateScorewithNullDTO() {
        CovidStatsDTO covidStatsDTO = new CovidStatsDTO("Canada", new Object(), 3844725, 3513673, 40664, 38363883);

        NullPointerException uex = Assertions.assertThrows(NullPointerException.class, () -> {
            statusService.calculateScore(covidStatsDTO, null);
        });

        Assertions.assertEquals("No data found for country provided", uex.getMessage(), "Input null covidStatsDTO");
    }

    @Test
    public void shouldCalculateStatusBasedOnScore(){
        String statusSafe = statusService.calculateStatusBasedOnScore(80);
        String statusCaution = statusService.calculateStatusBasedOnScore(40);
        String statusNotSafe = statusService.calculateStatusBasedOnScore(20);

        Assertions.assertEquals("Safe to travel", statusSafe);
        Assertions.assertEquals("Proceed with caution", statusCaution);
        Assertions.assertEquals("Not safe to travel", statusNotSafe);
    }

}