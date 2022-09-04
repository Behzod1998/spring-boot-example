package uz.pdp.springbootexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.springbootexample.entity.User;
import uz.pdp.springbootexample.projection.UserListProjection;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select  u.id  ,\n" +
            "        u.full_name as fullName,\n" +
            "        u.username," +
            "        p.id as positionId,\n" +
            "        p.name as positionName,\n" +
            "        u.salary,\n" +
            "        u.profile_image_id as profileImageId\n" +
            "from users u  join positions p on u.position_id = p.id",nativeQuery = true)

    Page<UserListProjection>getAllUsers(Pageable pageable);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

//
//    @Query(value = "select  u.id  ,\n" +
//            "        u.full_name as fullName,\n" +
//            "        p.id as positionId,\n" +
//            "        p.name as positionName,\n" +
//            "        u.salary,\n" +
//            "        u.profile_image_id as profileImageId\n" +
//            "from users u    join positions p on u.position_id = p.id ",nativeQuery = true)

    Page<UserListProjection> findUsersByUsernameContains(Pageable pageable, String userName);
}







