package com.greem.kodillalibrary.service;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.repository.LibraryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryUserDbService {
    @Autowired
    private LibraryUserRepository libraryUserRepository;

    public LibraryUser saveUser(LibraryUser libraryUser) {
        return libraryUserRepository.save(libraryUser);
    }
}
