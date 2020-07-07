package com.greem.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Entity
@Table(name = "LIBRARY_USERS")
public class LibraryUser {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @EqualsAndHashCode.Exclude
    @Column(name = "ACCOUNT_CREATED")
    private Date accountCreated = new Date();

    @EqualsAndHashCode.Exclude
    @OneToMany(
            targetEntity = RentLog.class,
            mappedBy = "libraryUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RentLog> rentLogs = new ArrayList<>();

    public LibraryUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
