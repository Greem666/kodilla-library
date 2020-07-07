package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.LibraryUser;
import org.springframework.data.repository.CrudRepository;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Long> {
}
