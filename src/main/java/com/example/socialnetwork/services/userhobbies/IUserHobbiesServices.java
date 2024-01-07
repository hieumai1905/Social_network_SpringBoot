package com.example.socialnetwork.services.userhobbies;

import com.example.socialnetwork.models.UserHobbies;
import com.example.socialnetwork.models.enums.Hobbies;
import com.example.socialnetwork.services.IGeneralService;

import java.util.List;

public interface IUserHobbiesServices extends IGeneralService<UserHobbies, String> {
    List<UserHobbies> getAllByUserId(String userId);
}
