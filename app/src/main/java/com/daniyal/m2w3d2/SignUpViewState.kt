package com.daniyal.m2w3d2

data class SignUpViewState(
    val isLoading: Boolean = false,
    val fullName: SignUpField = SignUpField(),
    val email: SignUpField = SignUpField(),
    val password: SignUpField = SignUpField(),
    val confirmPassword: SignUpField = SignUpField(),
)

data class SignUpField(
    val value: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)