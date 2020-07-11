package com.greem.kodillalibrary.domain.libraryuser;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.greem.kodillalibrary.domain.rentlog.RentLog;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @Column(name = "ACCOUNT_CREATED")
    private LocalDateTime accountCreated;

    @JsonBackReference
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
