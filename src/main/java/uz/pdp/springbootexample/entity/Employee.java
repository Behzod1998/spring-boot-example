package uz.pdp.springbootexample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  String fullName;

     @ManyToOne
    private  Position position;

     private Double salary;


}
