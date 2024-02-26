package com.example.marketplace.presentation.user.dto.queries;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery implements Serializable {
    private String firstName;
    private String lastName;
    private Date birthDate;
}
