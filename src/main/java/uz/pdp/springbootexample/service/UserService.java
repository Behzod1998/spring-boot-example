package uz.pdp.springbootexample.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.springbootexample.entity.*;
import uz.pdp.springbootexample.projection.UserListProjection;
import uz.pdp.springbootexample.repository.PositionRepository;
import uz.pdp.springbootexample.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final UserRepository userRepository;
    final PositionRepository positionRepository;


    public void saveUser(UserDto userDto) {

        Integer positionId = userDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {

            throw new IllegalStateException("Position not found ");

        }


        User user = User
                .builder()
                .fullName(userDto.getFullName())
                .position(optionalPosition.get())
                .salary(userDto.getSalary())
                .build();

        userRepository.save(user);

    }

    public Page<UserListProjection> getAllUsers(Integer page) {
        if (page < 1) {
            throw  new IllegalStateException("Bad req");
        }

        Pageable pageable = PageRequest.of(page-1,5, Sort.Direction.DESC,"update_at");
        Page<UserListProjection> all = userRepository.getAllUsers(pageable);


        return all;
    }


    public User findById(Integer id){ return userRepository.getOne(id);
    }


    public void updateUser(UserDto UserDto) {


        Integer positionId = UserDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {


            throw new IllegalStateException("Position not found ");

        }


        User user = User
                .builder()
                .id(UserDto.getId())
                .fullName(UserDto.getFullName())
                .position(optionalPosition.get())
                .salary(UserDto.getSalary())
                .build();

        userRepository.save(user);


    }

    public void deleteUser(User User) {

        userRepository.delete(User);

    }


    public Page<UserListProjection> findByUsername(String userName , Integer page) {
        if (page < 1) {
            throw  new IllegalStateException("Bad req");
        }

        if (userName.equals(null) || userName.trim().isEmpty()) {
            return  getAllUsers(page);
        }
        Pageable pageable = PageRequest.of(page-1,5);
        Page<UserListProjection> all = userRepository.findUsersByUsernameContains(pageable ,userName);

        return all;
    }
}
