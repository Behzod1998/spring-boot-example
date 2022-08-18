package uz.pdp.springbootexample.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {


    private Integer id;

    @NotNull
    @NotBlank
    @NotEmpty(message = "Ubu narsa yoz baraka topkur....")
    private String fullName;

    @NotNull(message = "Position tanlash shart!!!")
    private Integer positionId;

    private Double salary;


}