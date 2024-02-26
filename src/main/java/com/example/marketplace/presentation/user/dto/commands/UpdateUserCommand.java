package com.example.marketplace.presentation.user.dto.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserCommand implements Serializable {
    private String firstName;
    private String lastName;
    private Date birthDate;
}
