package com.example.socialnetwork.services.request;

import com.example.socialnetwork.models.Request;
import com.example.socialnetwork.repositories.IRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestService implements IRequestService {
    @Autowired
    private IRequestRepository requestRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Optional<Request> save(Request object) {
        object.setRequestCode(generateCode());
        Optional<Request> request = Optional.ofNullable(requestRepository.save(object));
        if (request.isPresent()) {
            return request;
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Request object) {
        try {
            requestRepository.delete(object);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String generateCode() {
        int codeRandom = (int) Math.floor(((Math.random() * 899999) + 100000));
        return Integer.toString(codeRandom);
    }


    @Override
    public Optional<Request> findByEmailRequest(String email) {
        Optional<Request> request = Optional.ofNullable(requestRepository.findByEmailRequest(email));
        if (request.isPresent()) {
            return request;
        }
        return Optional.empty();
    }

    @Override
    public Boolean sendCodeToEmail(String toEmail, String subject, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setText(code);
            message.setSubject(subject);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean clearRequestCodeExpired() {
        try {
            requestRepository.deleteAllByExpiredRequestCode();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
