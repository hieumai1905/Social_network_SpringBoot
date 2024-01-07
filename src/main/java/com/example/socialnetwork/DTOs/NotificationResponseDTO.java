package com.example.socialnetwork.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private Long notificationId;

    private String notificationContent;

    private Boolean seen;

    private String urlRedirect;

    private Date notificationAt;

    private String userId;
}
