package uz.pdp.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootexample.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
