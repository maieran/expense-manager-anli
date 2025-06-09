package com.anli.expensemana.model.DTO;

public class LoginDTO {
    //TODO: Custom Validation to Userinput
    //see: https://agussyahrilmubarok.medium.com/guide-to-field-validation-with-jakarta-validation-in-spring-8c9eca68022e
    private String email;
    private String password;

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
