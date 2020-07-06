package com.greem.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "ACCOUNT_CREATED")
    private Date accountCreated = new Date();

    @OneToMany(
            targetEntity = RentLog.class,
            mappedBy = "libraryUserId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RentLog> rentedBooks;
}
