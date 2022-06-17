package com.Project_2_Location_Search_API.repositories;

import com.Project_2_Location_Search_API.entities.LocationQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationQueryRepository extends JpaRepository<LocationQuery, Integer> {

    @Query("FROM LocationQuery WHERE location_name = :location_name")
    List<LocationQuery> findAllByLocationName(@Param("location_name") String location_name);

    @Query("FROM LocationQuery WHERE status = :status")
    List<LocationQuery> findAllByStatus(@Param("status") String status);

    @Query("FROM LocationQuery WHERE population >= :population")
    List<LocationQuery> findAllByPopulation(@Param("population") int population);

    @Query("FROM LocationQuery WHERE vaccinated >= :vaccinated")
    List<LocationQuery> findAllByVaccinatedPopulation(@Param("vaccinated") int vaccinated);

    @Query("FROM LocationQuery WHERE total_infections >= :total_infections")
    List<LocationQuery> findAllByTotalInfections(@Param("total_infections") int total_infections);

    @Query("FROM LocationQuery WHERE total_deaths >= :total_deaths")
    List<LocationQuery> findAllByTotalDeaths(@Param("total_deaths") int total_deaths);

    @Query("FROM LocationQuery WHERE total_recovered >= :total_recovered")
    List<LocationQuery> findAllByTotalRecovered(@Param("total_recovered") int total_recovered);
}
