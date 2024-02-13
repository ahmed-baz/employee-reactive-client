package org.demo.app.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date joinDate;

}


