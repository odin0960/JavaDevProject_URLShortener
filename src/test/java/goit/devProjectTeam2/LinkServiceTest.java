package goit.devProjectTeam2;

import goit.devProjectTeam2.entity.Link;
import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.link.LinkRepository;
import goit.devProjectTeam2.link.LinkService;
import goit.devProjectTeam2.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LinkServiceTest {

    @Mock
    private LinkRepository linkRepository;
    @InjectMocks
    private LinkService linkService;
    private Link link;
    @InjectMocks
    private SecurityConfig securityConfig ;
    private User user;

    @BeforeEach
    public void setup() {
        link = Link.builder()
                .linkId(1L)
                .longLink("https://test.test1")
                .token("test1")
                .createDate(Timestamp.valueOf("2023-10-09 16:34:29.72075"))
                .expireDate(Timestamp.valueOf("2023-10-11 00:00:11.0"))
//                .userId(new User(1L))
                .user(new User(1L))
                .count(0L)
                .build();
    }

    @Test
    @DisplayName("givenIdThenShouldReturnLinkOfThatId")
    @Order(1)
    void givenIdThenShouldReturnLinkOfThatId() {
        given(linkRepository.findById(link.getLinkId())).willReturn(Optional.of(link));
        Link savedLink = linkService.getById(link.getLinkId());
        assertThat(savedLink).isNotNull();
        assertEquals(link, savedLink);
    }

    @Test
    @DisplayName("givenNonExistIdThenShouldThrowNoSuchElementException")
    @Order(2)
    void givenNonExistIdThenShouldThrowNoSuchElementException() {
        given(linkRepository.findById(link.getLinkId())).willReturn(Optional.of(link));
        assertThrows(NoSuchElementException.class, () -> linkService.getById(5L));
    }

    @Test
    @DisplayName("givenLinkToAddShouldReturnAddedLink")
    @Order(3)
    void givenLinkToAddShouldReturnAddedLink() {
        given(linkRepository.save(link)).willReturn(link);
        Link savedLink = linkService.add(link);
        System.out.println(savedLink);
        assertThat(savedLink).isNotNull();
    }

    @Test
    @DisplayName("givenIdThenShouldDeleteLinkOfThatId")
    @Order(4)
    void givenIdThenShouldDeleteLinkOfThatId() {
        long linkId = 1L;
        willDoNothing().given(linkRepository).deleteById(linkId);
        linkService.deleteById(linkId);
        verify(linkRepository, times(1)).deleteById(linkId);
    }

    @Test
    @DisplayName("givenLinkListThenShouldReturnAllLinkList")
    @Order(5)
    void givenLinkListThenShouldReturnAllLinkList() {
        Link link1 = Link.builder()
                .linkId(2L)
                .longLink("https://test.test2")
                .token("test2")
                .createDate(Timestamp.valueOf("2023-10-07 15:55:29.72075"))
                .expireDate(Timestamp.valueOf("2023-10-10 13:33:11.0"))
                .user(new User(2L))
                .count(1L)
                .build();

        given(linkRepository.findAll()).willReturn(List.of(link, link1));

        List<Link> linkList = linkService.listAll();

        assertThat(linkList).isNotNull();
        assertThat(linkList.size()).isEqualTo(2);
        assertEquals(linkList.get(0), link);
        assertEquals(linkList.get(1), link1);
    }

    @Test
    @DisplayName("givenUserIdThenShouldReturnListOfAllListsByThatUserId")
    @Order(6)
    void givenUserIdThenShouldReturnListOfAllListsByThatUserId() {
        Link link3 = Link.builder()
                .linkId(3L)
                .longLink("https://test.test3")
                .token("test3")
                .createDate(Timestamp.valueOf("2023-10-03 15:55:29.72075"))
                .expireDate(Timestamp.valueOf("2023-10-12 13:33:11.0"))
                .user(new User(3L))
                .count(1L)
                .build();
        given(linkRepository.findAll()).willReturn(List.of(link, link3));

        List<Link> linkList = linkService.listAllForUser();

        assertThat(linkList).isNotNull();
        assertThat(linkList.size()).isEqualTo(1);
        assertEquals(linkList.get(0), link);
    }

    @Test
    @DisplayName("givenNonExistUserIdThenShouldReturnListOfAllListsByThatUserId")
    @Order(7)
    void givenNonExistUserIdThenShouldReturnListOfAllListsByThatUserId() {
        given(linkRepository.findAll()).willReturn(List.of(link));

        List<Link> linkList = linkService.listAllForUser();

        assertThat(linkList).isNotNull();
        assertThat(linkList.size()).isZero();
    }

    @Test
    @DisplayName("givenExpireDateShouldReturnAllLinksMoreThenExpirationDate")
    @Order(8)
    void givenExpireDateShouldReturnAllLinksMoreThenExpirationDate() {
        Link link4 = Link.builder()
                .linkId(4L)
                .longLink("https://test.test4")
                .token("test4")
                .createDate(Timestamp.valueOf("2023-10-04 15:55:29.72075"))
                .expireDate(Timestamp.valueOf("2023-10-07 13:33:11.0"))
                .user(new User(3L))
                .count(1L)
                .build();
        given(linkRepository.findAll()).willReturn(List.of(link, link4));

        List<Link> linkList = linkService.findAllMoreThenExpirationDate(Timestamp.valueOf("2023-10-08 15:55:29"));

        assertThat(linkList).isNotNull();
        assertThat(linkList.size()).isEqualTo(1);
        assertEquals(linkList.get(0), link);
    }

    @Test
    @DisplayName("givenLinkThenShouldGenerateShortLink")
    @Order(9)
    void givenLinkThenShouldGenerateShortLink() {
        String shortLink = linkService.generateToken(link);
        System.out.println(shortLink);
        assertThat(shortLink).isNotNull();
        assertThat(shortLink).hasSize(7);
    }

    @Test
    @DisplayName("givenLinkThenShouldIncreaseClickCounter")
    @Order(10)
    void givenLinkThenShouldIncreaseClickCounter() {
        given(linkRepository.findById(link.getLinkId())).willReturn(Optional.of(link));
        linkService.increaseClickCounter(link.getLinkId());
        Link savedLink = linkService.getById(link.getLinkId());
        System.out.println(savedLink);
        assertThat(savedLink).isNotNull();
        assertEquals(1, savedLink.getCount());
    }

}
