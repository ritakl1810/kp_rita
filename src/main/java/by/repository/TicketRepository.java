package by.repository;

import by.model.Customer;
import by.model.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
   /* @Query("SELECT max(c.id_destination) FROM Destination c")
    Integer findMaxId();
*/
    @Query("SELECT c FROM Ticket c WHERE c.id_customer=?1")
    List<Ticket> findREisById_customer(Customer id);

    @Query("SELECT c from Ticket c GROUP BY c.destination_city  order by count(c.destination_city) desc ")
    List<Ticket> findBetter(Pageable pageable);

}
