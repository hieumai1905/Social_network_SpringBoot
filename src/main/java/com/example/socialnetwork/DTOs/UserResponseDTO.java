package com.example.socialnetwork.DTOs;

import com.example.socialnetwork.models.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private String userId;

    private String fullName;

    private String email;

    private Date dob;

    private String country;

    private String avatar;

    private String coverPhoto;

    private Gender gender;

    private String aboutMe;

    private Date registerAt;

    private Date updateAt;
}
