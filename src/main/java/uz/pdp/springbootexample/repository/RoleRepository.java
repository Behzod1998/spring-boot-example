package uz.pdp.springbootexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootexample.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
