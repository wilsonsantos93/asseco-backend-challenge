package asseco.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import asseco.voting.entity.Station;

public interface StationRepository extends JpaRepository<Station, Integer> {
    boolean existsByFreguesiaAndLocation(String freguesia, String location);

    /* @Query(value="SELECT station.*, COUNT(Voter.id) as totalVoters FROM Station LEFT JOIN Voter ON Station.id = Voter.station_id GROUP BY Station.id", nativeQuery=true)
    List<Object[]> findAllWithVotersCount(); */

    @Query("SELECT r FROM Station r WHERE (:distrito IS NULL OR upper(r.distrito) LIKE CONCAT('%%', upper(:distrito), '%%')) AND (:freguesia IS NULL OR upper(r.freguesia) LIKE CONCAT('%%', upper(:freguesia), '%%')) AND (:location IS NULL OR upper(r.location) LIKE CONCAT('%%', upper(:location), '%%'))")
    List<Station> findByParameters(String distrito, String freguesia, String location);
}

