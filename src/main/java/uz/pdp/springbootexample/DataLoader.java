//package uz.pdp.springbootexample;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import uz.pdp.springbootexample.entity.*;
//import uz.pdp.springbootexample.repository.AddressRepository;
//import uz.pdp.springbootexample.repository.PositionRepository;
//import uz.pdp.springbootexample.repository.RoleRepository;
//import uz.pdp.springbootexample.repository.UserRepository;
//
//import java.util.Collections;
//
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//
//    @Value("${spring.sql.init.mode}")
//    private String initMode;
//
//    final UserRepository userRepository;
//    final RoleRepository roleRepository;
//    final PositionRepository positionRepository;
//
//    final AddressRepository addressRepository;
//
//    final PasswordEncoder passwordEncoder;
//
//    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PositionRepository positionRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.positionRepository = positionRepository;
//        this.addressRepository = addressRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Data loader is working....");
//        if (initMode.equals("always")) {
//
//            Position seniorDeveloper = Position.builder()
//                    .name("Senior Developer")
//                    .description("This role is about....")
//                    .build();
//            Position middleDeveloper = Position.builder()
//                    .name("Middle Developer")
//                    .description("This role is about....")
//                    .build();
//            Position juniorDeveloper = Position.builder()
//                    .name("Junior Developer")
//                    .description("This role is about....")
//                    .build();
//            Position internDeveloper = Position.builder()
//                    .name("Intern Developer")
//                    .description("This role is about....")
//                    .build();
//
//            Position savedPosition1 = positionRepository.save(seniorDeveloper);
//            Position savedPosition2 = positionRepository.save(middleDeveloper);
//            Position savedPosition3 = positionRepository.save(juniorDeveloper);
//            Position savedPosition4 = positionRepository.save(internDeveloper);
//
//            Role admin = roleRepository.save(Role.builder()
//                    .roleEnum(RoleEnum.ROLE_ADMIN)
//                    .build());
//
//
//            userRepository.save(
//                    User.builder()
//                            .username("behzod11")
//                            .password(passwordEncoder.encode("123"))
//                            .roles(Collections.singleton(admin))
//                            .fullName("Behzod")
//                            .salary(2000.0)
//                            .position(internDeveloper)
//                            .description("asdasdasdadasad asdasdasdasasd")
//                            .build()
//            );
//            userRepository.save(
//                    User.builder()
//                            .username("davlatbek22")
//                            .password(passwordEncoder.encode("123"))
//                            .roles(Collections.singleton(admin))
//                            .fullName("Davlatbek")
//                            .salary(3000.0)
//                            .position(juniorDeveloper)
//                            .description("zxcvzxcvzxcb vbcvbcvbcvbcvb asdasdasdasasd")
//                            .build()
//            );
//            userRepository.save(
//                    User.builder()
//                            .username("bekzod123")
//                            .password("123")
//                            .fullName("Bekzod")
//                            .salary(2000.0)
//                            .position(seniorDeveloper)
//                            .description("xvcv,xmc,vmxc, asdasdasdadasad asdasdasdasasd")
//                            .build()
//            );
//            userRepository.save(
//                    User.builder()
//                            .username("umid77")
//                            .password("123")
//                            .fullName("Umid")
//                            .salary(6000.0)
//                            .position(seniorDeveloper)
//                            .description("qeweqwe qeweq asdasdasdadasad asdasdasdasasd")
//                            .build()
//            );
//            userRepository.save(
//                    User.builder()
//                            .username("abdulloh")
//                            .password("123")
//                            .fullName("Abdulloh")
//                            .salary(20000.0)
//                            .position(middleDeveloper)
//                            .description("tertewrtert tertertasdasdasdadasad asdasdasdasasd")
//                            .build()
//            );
//
//
//            User shahboz = User.builder()
//                    .username("shahboz")
//                    .password("123")
//                    .fullName("Shahboz")
//                    .salary(20000.0)
//                    .position(seniorDeveloper)
//                    .description("tertewrtert tertertasdasdasdadasad asdasdasdasasd")
//                    .build();
//
//            Address shahbozAddress = Address.builder()
//                    .addressLine("Tashkent, 5, 10, 100000")
//                    .user(shahboz)
//                    .build();
//
//            shahboz.setAddress(shahbozAddress);
//            userRepository.save(shahboz);
//
//            System.out.println("Databasega select query ketdi....");
//            System.out.println(userRepository.findById(6).orElseThrow(()
//                    -> new IllegalStateException("user not found")));
//
//            Address address = shahboz.getAddress();
//            System.out.println(address);
////            addressRepository.save(shahbozAddress);
//
//
//        }
//
//    }
//}
