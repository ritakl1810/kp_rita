package by.repository;

import by.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarkRepository extends JpaRepository<Rate, Integer> {
    @Query("SELECT avg(c.rate) FROM Rate c ")
    Float findAverageRate();

    @Query("SELECT count(c.rate) FROM Rate c GROUP BY c.rate order by c.rate desc")
    List<Integer> findMarksCount();
}
