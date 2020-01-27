package com.telran.ilcarro.service.comment;

import com.telran.ilcarro.model.comment.AddCommentDTO;
import com.telran.ilcarro.model.comment.FullCommentDTO;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.CommentEntity;
import com.telran.ilcarro.repository.entity.LocationEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * @author vitalii_adler
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @MockBean
    UserEntityRepository userEntityRepository;

    private UserEntity userEntity;
    private UserEntity tester;


    @Before
    public void init() {
        List<CommentEntity> comments = new ArrayList<>();
        comments.add(CommentEntity.builder().firstName("Ivan")
                .lastName("Ivanov")
                .postDateTime(LocalDateTime.now().minusDays(5))
                .photo("https://someurl.com/image.jpeg")
                .post("Bad Car")
                .build());

        comments.add(CommentEntity.builder().firstName("Anton")
                .lastName("Antonov")
                .postDateTime(LocalDateTime.now().minusDays(5))
                .photo("https://someurl.com/image.jpeg")
                .post("Very Good Car")
                .build());

        userEntity = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(comments)
                .firstName("Vasya")
                .history(null)
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(LocalDateTime.now().minusDays(3))
                .driverLicense("5r1325136135")
                .isDeleted(false)
                .location(LocationEntity.builder()
                        .zip(12124)
                        .street("strit")
                        .state("staaate")
                        .lon("4323.564345")
                        .lat("5345.323423")
                        .isVehicle(false)
                        .country("cauntri")
                        .city("ceeety")
                        .build())
                .ownCars(List.of("111-222-333", "333-222-111"))
                .phone("452346236235")
                .build();

        tester = UserEntity.builder()
                .email("petya@mail.com")
                .comments(emptyList())
                .firstName("Petya")
                .history(null)
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(LocalDateTime.now().minusDays(3))
                .driverLicense("768768686")
                .isDeleted(false)
                .location(LocationEntity.builder()
                        .zip(12124)
                        .street("strit")
                        .state("staaate")
                        .lon("4323.564345")
                        .lat("5345.323423")
                        .isVehicle(false)
                        .country("cauntri")
                        .city("ceeety")
                        .build())
                .phone("452346236235")
                .build();
    }

    @Test
    public void getLatestComments() {
        init();
        doReturn(List.of(userEntity, tester)).when(userEntityRepository).findAll();
        List<FullCommentDTO> check = Assertions.assertDoesNotThrow(() -> commentService.getLatestComments(1));
        Assert.assertEquals(1, check.size());
    }

    @Test
    public void getLatestTwoComments() {
        init();
        doReturn(List.of(userEntity, tester)).when(userEntityRepository).findAll();
        List<FullCommentDTO> check = Assertions.assertDoesNotThrow(() -> commentService.getLatestComments(2));
        Assert.assertEquals(2, check.size());
    }

    @Test
    public void getLatestCommentsIfValueIsNull() {
        init();
        doReturn(List.of(userEntity, tester)).when(userEntityRepository).findAll();
        assertThrows(ServiceException.class, () -> commentService.getLatestComments(null));
    }

    @Test
    public void getLatestCommentsIfValueIsMoreThanMaxInteger() {
        init();
        doReturn(List.of(userEntity, tester)).when(userEntityRepository).findAll();
        assertThrows(ServiceException.class, () -> commentService.getLatestComments(Integer.MAX_VALUE + 1));
    }

    @Test
    public void getLatestCommentsIfValueIsLessThanZero() {
        init();
        doReturn(List.of(userEntity, tester)).when(userEntityRepository).findAll();
        assertThrows(ServiceException.class, () -> commentService.getLatestComments(-1));
    }

    @Test
    public void postComment() {
        init();
        doReturn(Optional.of(userEntity)).when(userEntityRepository).getUserEntityByOwnCarsContains("111-222-333");
        doReturn(Optional.of(tester)).when(userEntityRepository).findById(anyString());
        Assert.assertTrue(commentService.postComment("111-222-333", "petya@mail.com",
                AddCommentDTO.builder().post("good Car").build()));
    }

    @Test
    public void postCommentIfSerialNumberDoesNotExist() {
        init();
        doThrow(NotFoundServiceException.class).when(userEntityRepository).getUserEntityByOwnCarsContains("999-222-333");
        doReturn(Optional.of(tester)).when(userEntityRepository).findById("petya@mail.com");
        Assertions.assertThrows(ServiceException.class, () -> commentService.postComment("999-222-333", "petya@mail.com",
                AddCommentDTO.builder().post("good Car").build()));

    }

    @Test
    public void postCommentIfOwnerEmailDoesNotExist() {
        init();
        doThrow(NotFoundServiceException.class).when(userEntityRepository).findById("aaaa@ddd.com");
        doReturn(Optional.of(userEntity)).when(userEntityRepository).findById("vasyapupkin1234@mail.com");
        Assertions.assertThrows(ServiceException.class, () -> commentService.postComment("333-222-111",
                "aaaa@ddd.com", AddCommentDTO.builder().post("good Car").build()));
    }

    @Test
    public void postCommentIfOwnerEmailNumberIsNull() {
        doReturn(Optional.of(userEntity)).when(userEntityRepository).getUserEntityByOwnCarsContains("333-222-111");
        doReturn(Optional.of(tester)).when(userEntityRepository).findById(anyString());
        Assertions.assertThrows(ServiceException.class,
                () -> commentService.postComment("333-222-111",
                        null,
                        AddCommentDTO.builder().post("good Car").build()));
    }

    @Test
    public void postCommentIfMessageIsNull() {
        doReturn(Optional.of(userEntity)).when(userEntityRepository).getUserEntityByOwnCarsContains("111-222-333");
        doReturn(Optional.of(tester)).when(userEntityRepository).findById(anyString());
        Assertions.assertThrows(ServiceException.class,
                () -> commentService.postComment("111-222-333",
                        "petya@mail.com",
                        null));
    }

    @Test
    public void postCommentIfSerialNumberIsNull() {
        doReturn(Optional.of(userEntity)).when(userEntityRepository).getUserEntityByOwnCarsContains("111-222-333");
        doReturn(Optional.of(tester)).when(userEntityRepository).findById(anyString());
        Assertions.assertThrows(ServiceException.class,
                () -> commentService.postComment(null,
                        "petya@mail.com",
                        AddCommentDTO.builder().post("good Car").build()));
    }
}