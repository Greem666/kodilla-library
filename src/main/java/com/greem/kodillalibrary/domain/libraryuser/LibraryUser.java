package com.greem.kodillalibrary.domain.libraryuser;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
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
//@Builder(access = AccessLevel.PUBLIC)
//@SuperBuilder
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

    @EqualsAndHashCode.Exclude
    @Column(name = "ACCOUNT_CREATED")
    private Date accountCreated = new Date();

    @JsonBackReference
    @EqualsAndHashCode.Exclude
    @OneToMany(
            targetEntity = RentLog.class,
            mappedBy = "libraryUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<RentLog> rentLogs = new ArrayList<>();

    public LibraryUser(long id, String firstName, String lastName, Date accountCreated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreated = accountCreated;
    }

    public LibraryUser(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public LibraryUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
