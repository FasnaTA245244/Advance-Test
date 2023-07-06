package com.ust.websecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Boolean subscribed=false;
    // Define the relationship with the issue table
    @OneToMany(mappedBy = "user") // Assuming "user" is the name of the column in the issue table referencing the User entity
    private List<Issue> issues;
}
