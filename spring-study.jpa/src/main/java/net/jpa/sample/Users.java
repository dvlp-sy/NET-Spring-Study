package net.jpa.sample;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users
{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
}
