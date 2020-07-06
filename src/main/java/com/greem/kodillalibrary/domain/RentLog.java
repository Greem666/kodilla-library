package com.greem.kodillalibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "RENT_LOGS")
public class RentLog {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Column(name = "COPY_ID")
    private Copy copyId;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @Column(name = "LIBRARY_USER_ID")
    private LibraryUser libraryUserId;

    public void setCopyId(Copy copyId) {
        this.copyId = copyId;
        copyId.getRentLogs().add(this);
    }

    public void setLibraryUserId(LibraryUser libraryUserId) {
        this.libraryUserId = libraryUserId;
        libraryUserId.getRentedBooks().add(this);
    }
}
