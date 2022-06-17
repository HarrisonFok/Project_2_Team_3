package com.Project_2_Location_Status_API.Services;

import com.Project_2_Location_Status_API.DTO.CovidStatsDTO;
import com.Project_2_Location_Status_API.DTO.VaccineDataDTO;
import com.Project_2_Location_Status_API.Entities.StatusReport;
import com.Project_2_Location_Status_API.Repositories.StatusRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StatusService {

    @Setter(onMethod = @__({@Autowired}))
    private StatusRepository statusRepository;

    /**
     * Service method to get status by location
     * @throws null pointer exception if location is not found
     * @param location
     * @return the status of the queried location
     */
    public StatusReport getStatusByLocation(String location) {
        StatusReport op = statusRepository.findByLocation(location);
        if (op != null) {
            return op;
        } else {
            throw new NullPointerException(location + " could not be found");
        }
    }

    /**
     * Creates and saves a new status report
     * @throws null pointer exception if:
     *      -there is no location
     *      -null or negative score
     *      -null creation date
     *      -future date
     * @param statusReport
     */

    public void saveNewStatus(StatusReport statusReport) {
        if (statusReport.getLocation() == null || statusReport.getLocation().isEmpty()) {
            throw new NullPointerException("Can't create a statusReport without a location");
        } else if (statusReport.getScore() == 0 || statusReport.getScore() < 0) {
            throw new NullPointerException("Can't create a statusReport with a null or negative score");
        } else if (statusReport.getCreationDate() == null) {
            throw new NullPointerException("Can't create a statusReport with a null creation date");
        } else if (statusReport.getCreationDate().isAfter(LocalDate.now())) {
            throw new NullPointerException("Can't create a statusReport with a date in the future");
        } else {
            statusRepository.save(statusReport);
        }
    }

    /**
     * Calculation takes covid status data and coverts it to a numerical score
     * @param covidStats
     * @param vaccineStats
     * @return the amount of current vaccinated residents divided by the total population
     * of a country, multiplied by 100 to receive score
     */

    public double calculateScore(CovidStatsDTO covidStats, VaccineDataDTO vaccineStats) {
        if (covidStats == null  || vaccineStats == null)
            throw new NullPointerException("No data found for country provided");
        double totalPopulation = covidStats.getPopulation();
        /*
          vaccineStats returns the total num of doses administered in a country(to-date)
          so assuming a double dose vaccine policy ,the returned num is divided by 2
          therefore, popVaccinated returns an approximate number of people vaccinated(with double dose) in a country
         */
        double popVaccinated = vaccineStats.getTimeline().fields().next().getValue().asDouble() /2;
        return  (popVaccinated/totalPopulation) * 100;
    }


    /**
     * Converts previously created score into a new Status
     * Safe to travel: percent vaccinated is greater than or equal to 80%
     * Proceed with caution: percent vaccinated is greater than or equal to 40% but less than 80%
     * Not safe to travel: percent vaccinated is lower than 40%
     * @param percentVaccinated
     * @return the newly assigned status based on score
     */
    public String calculateStatusBasedOnScore(double percentVaccinated) {
        boolean safe = percentVaccinated >= 80;
        boolean caution = percentVaccinated >= 40 && percentVaccinated < 80;
        return safe ? "Safe to travel" : caution ? "Proceed with caution" : "Not safe to travel";
    }
}