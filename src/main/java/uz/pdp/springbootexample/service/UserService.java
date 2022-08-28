package uz.pdp.springbootexample.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.pdp.springbootexample.entity.*;
import uz.pdp.springbootexample.projection.UserListProjection;
import uz.pdp.springbootexample.repository.UserRepository;
import uz.pdp.springbootexample.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    final UserRepository UserRepository;
    final PositionRepository positionRepository;

    public UserService(UserRepository UserRepository, PositionRepository positionRepository) {
        this.UserRepository = UserRepository;
        this.positionRepository = positionRepository;
    }

    public void saveUser(UserDto UserDto) {

        Integer positionId = UserDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {

            throw new IllegalStateException("Position not found ");

        }


        User User = User
                .builder()
                .fullName(UserDto.getFullName())
                .position(optionalPosition.get())
                .salary(UserDto.getSalary())
                .build();

        UserRepository.save(User);

    }

    public Page<UserListProjection> getAllUsers(Integer page) {
        if (page < 1) {
            throw  new IllegalStateException("Bad req");

        }

        Pageable pageable = PageRequest.of(page-1,5, Sort.Direction.DESC,"updated_at");
        Page<UserListProjection> all = UserRepository.getAllUsers(pageable);


        return all;
    }


    public User findById(Integer id){ return UserRepository.getOne(id);
    }


    public void updateUser(UserDto UserDto) {


        Integer positionId = UserDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {


            throw new IllegalStateException("Position not found ");

        }


        User User = User
                .builder()
                .id(UserDto.getId())
                .fullName(UserDto.getFullName())
                .position(optionalPosition.get())
                .salary(UserDto.getSalary())
                .build();

        UserRepository.save(User);


    }

    public void deleteEmploye(User User) {

        UserRepository.delete(User);

    }





























}
