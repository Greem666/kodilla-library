package com.greem.kodillalibrary.mapper;

import com.greem.kodillalibrary.domain.libraryuser.LibraryUser;
import com.greem.kodillalibrary.domain.libraryuser.LibraryUserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryUserMapperTestSuite {
    @Autowired
    private LibraryUserMapper libraryUserMapper;
    @Test
    public void testMapToLibraryUser() {
        // Given
        Date creationDate = new Date();
        LibraryUserDto libraryUserDto = new LibraryUserDto(
                1,
                "test name",
                "test surname",
                creationDate,
                new ArrayList<>());

        // When
        LibraryUser mappedLibraryUser = libraryUserMapper.mapToLibraryUser(libraryUserDto);

        // Then
        Assert.assertEquals(1, mappedLibraryUser.getId());
        Assert.assertEquals("test name", mappedLibraryUser.getFirstName());
        Assert.assertEquals("test surname", mappedLibraryUser.getLastName());
        Assert.assertEquals(creationDate, mappedLibraryUser.getAccountCreated());
        Assert.assertEquals(new ArrayList<>(), mappedLibraryUser.getRentLogs());
    }

    @Test
    public void testMapToLibraryUserDto() {
        // Given
        Date creationDate = new Date();
        LibraryUser libraryUser = new LibraryUser(
                1,
                "test name",
                "test surname",
                creationDate,
                new ArrayList<>());

        // When
        LibraryUserDto mappedLibraryUserDto = libraryUserMapper.mapToLibraryUserDto(libraryUser);

        // Then
        Assert.assertEquals(1, mappedLibraryUserDto.getId());
        Assert.assertEquals("test name", mappedLibraryUserDto.getFirstName());
        Assert.assertEquals("test surname", mappedLibraryUserDto.getLastName());
        Assert.assertEquals(creationDate, mappedLibraryUserDto.getAccountCreated());
        Assert.assertEquals(new ArrayList<>(), mappedLibraryUserDto.getRentLogsDto());
    }

    @Test
    public void testMapToListOfLibraryUserDto() {
        // Given
        Date creationDate1 = new Date();
        LibraryUser libraryUser1 = new LibraryUser(
                1,
                "test name0",
                "test surname0",
                creationDate1,
                new ArrayList<>());

        Date creationDate2 = new Date();
        LibraryUser libraryUser2 = new LibraryUser(
                2,
                "test name1",
                "test surname1",
                creationDate2,
                new ArrayList<>());

        // When
        List<LibraryUserDto> mappedLibraryUserDtoList = libraryUserMapper.mapToLibraryUserDtoList(
                Arrays.asList(libraryUser1, libraryUser2)
        );

        // Then
        List<Date> creationDates = Arrays.asList(creationDate1, creationDate2);

        for (int i = 0; i < mappedLibraryUserDtoList.size(); i++) {
            LibraryUserDto libraryUserDto = mappedLibraryUserDtoList.get(i);

            Assert.assertEquals(1 + i, libraryUserDto.getId());
            Assert.assertEquals("test name" + i, libraryUserDto.getFirstName());
            Assert.assertEquals("test surname" + i, libraryUserDto.getLastName());
            Assert.assertEquals(creationDates.get(i), libraryUserDto.getAccountCreated());
            Assert.assertEquals(new ArrayList<>(), libraryUserDto.getRentLogsDto());
        }
    }
}

