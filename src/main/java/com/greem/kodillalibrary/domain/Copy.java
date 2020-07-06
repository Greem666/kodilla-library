package com.greem.kodillalibrary.domain;

import com.greem.kodillalibrary.domain.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "TITLE_COPIES")
public class Copy {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "TITLE_ID")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private RentStatus rentStatus;

    @OneToMany(
            targetEntity = RentLog.class,
            mappedBy = "copyId",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<RentLog> rentLogs;

    public void setBook(Book book) {
        this.book = book;
        book.getCopies().add(this);
    }
}
