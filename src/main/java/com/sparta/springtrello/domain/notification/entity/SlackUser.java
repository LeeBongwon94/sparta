package com.sparta.springtrello.domain.notification.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SlackUser {
    @Email @NotBlank
    private final String email;
    @NotBlank
    private final String name;
    @NotBlank
    private final String slackId;
}
