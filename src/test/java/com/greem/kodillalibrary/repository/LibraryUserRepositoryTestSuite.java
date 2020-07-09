package com.greem.kodillalibrary.repository;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryUserRepositoryTestSuite {
    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Test
    public void testLibraryUser() {
        // Given
        LibraryUser libraryUser = new LibraryUser("Matt", "Green");
        libraryUserRepository.save(libraryUser);

        // When
        long id = libraryUser.getId();

        // Then
        Assert.assertEquals(libraryUser, libraryUserRepository.findById(id).orElse(new LibraryUser()));

        // Clean-up
        libraryUserRepository.deleteById(id);
    }
}
