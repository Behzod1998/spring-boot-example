package uz.pdp.springbootexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootexample.entity.User;
import uz.pdp.springbootexample.projection.UserListProjection;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select  e.id  ,\n" +
            "        e.full_name as fullName,\n" +
            "        p.id as positionId,\n" +
            "        p.name as positionName,\n" +
            "        e.salary,\n" +
            "        e.profile_image_id as profileImageId\n" +
            "from Users e  join positions p on e.position_id = p.id",nativeQuery = true)

    Page<UserListProjection> getAllUsers(Pageable pageable);




}



