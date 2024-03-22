package asseco.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import asseco.voting.entity.Voter;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Integer> {
   List<Voter> findByNameContainingIgnoreCase(String name);
   boolean existsByCitizenNumber(int citizenNumber);

   @Query("SELECT r FROM Voter r WHERE (:name IS NULL OR upper(r.name) LIKE CONCAT('%%', upper(:name), '%%')) AND (:citizenNumber IS NULL OR r.citizenNumber = :citizenNumber)")
   List<Voter> findByParameters(String name, Integer citizenNumber);
}
