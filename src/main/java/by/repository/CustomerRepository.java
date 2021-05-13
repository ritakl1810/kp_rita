package by.repository;

import by.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByEmailAndAccess(String email, String access);
    List<Customer> findByEmail(String email);
    Customer findTopByEmail(String email);
}
