package uz.pdp.springbootexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.projection.EmployeeListProjection;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "select  e.id  ,\n" +
            "        e.full_name as fullName,\n" +
            "        p.id as positionId,\n" +
            "        p.name as positionName,\n" +
            "        e.salary,\n" +
            "        e.profile_image_id as profileImageId\n" +
            "from employees e  join positions p on e.position_id = p.id",nativeQuery = true)

    Page<EmployeeListProjection> getAllEmployees(Pageable pageable);




}



