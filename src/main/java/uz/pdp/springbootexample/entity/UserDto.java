package uz.pdp.springbootexample.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {


    private Integer id;

    @NotEmpty(message = "Ism Familya kiritish majburiy....")
    @Size(min = 3, max = 30, message = "Harflar soni 3 tadan kam bo'lmasin")
    private String fullName;

    @NotNull(message = "Position tanlash shart!!!")
    private Integer positionId;

    @NotNull(message = "Qiymat kiriting")
    private Double salary;


}