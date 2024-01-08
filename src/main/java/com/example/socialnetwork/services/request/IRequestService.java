package com.example.socialnetwork.services.request;

import com.example.socialnetwork.models.Request;
import com.example.socialnetwork.services.IGeneralService;

import java.util.Optional;

public interface IRequestService extends IGeneralService<Request, Long> {
    Optional<Request> findByEmailRequest(String email);
    Boolean sendCodeToEmail(String toEmail, String subject, String code);

    boolean clearRequestCodeExpired();
}
