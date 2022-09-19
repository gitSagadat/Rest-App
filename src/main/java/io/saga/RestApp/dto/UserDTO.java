package io.saga.RestApp.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Sagadat Kuandykov
 */
public class UserDTO {
    @NotEmpty(message = "Name field is empty,please write name")
    @Size(min = 2,max = 30,message = "between 2 and less 30 characters")
    private String name;


    @Min(value = 0,message = "Please write your age")
    private int age;


    @Email
    @NotEmpty(message = "Field email is empty, please write email")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
