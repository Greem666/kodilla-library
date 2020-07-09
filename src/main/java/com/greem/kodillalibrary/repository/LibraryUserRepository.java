package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Long> {
}
