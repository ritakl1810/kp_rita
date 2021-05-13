package by.repository;

import by.model.Reis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReisRepository extends JpaRepository<Reis, Integer> {
    @Query("SELECT c FROM Reis c WHERE c.name LIKE %?1%")
    List<Reis> findAllByName(String name);

    @Query("SELECT c FROM Reis c WHERE c.cost BETWEEN ?1 AND ?2")
    List<Reis> findAllFiltered(String min, String max);

    @Query("SELECT c FROM Reis c WHERE c.name = ?1")
    Reis findReisIdByName(String name);
}
