package com.grocery_project.auth.user.dto;

public record ChangePasswordRequest(String currentPassword, String newPassword, String confirmationPassword) {
}
