package com.aryahamrah.app.user;

import com.aryahamrah.app.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Indexed
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<>();

    private boolean active;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true; // default to active
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
