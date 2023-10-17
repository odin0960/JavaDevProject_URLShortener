package goit.devProjectTeam2;

import goit.devProjectTeam2.entity.Link;
import goit.devProjectTeam2.link.LinkService;
import goit.devProjectTeam2.link.RestLinkController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RestLinkControllerTest {

    @InjectMocks
    private RestLinkController restLinkController;

    @Mock
    private LinkService linkService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Long linkId = 1L;
        Link mockLink = new Link();
        when(linkService.getById(linkId)).thenReturn(mockLink);

        Link responseLink = restLinkController.findById(linkId);

        assertEquals(mockLink, responseLink);
    }

    @Test
    public void testFindByIdNotFound() {
        Long linkId = 1L;
        when(linkService.getById(linkId)).thenThrow(NoSuchElementException.class);

        assertThrows(ResponseStatusException.class, () -> restLinkController.findById(linkId));
    }

    @Test
    public void testAddLink() {
        Link linkToAdd = new Link();
        when(linkService.add(linkToAdd)).thenReturn(linkToAdd);

        ResponseEntity<?> responseEntity = restLinkController.add(linkToAdd);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Link is created!", responseEntity.getBody());
    }

    @Test
    public void testAddLinkBadRequest() {
        Link linkToAdd = new Link();
        when(linkService.add(linkToAdd)).thenThrow(IllegalArgumentException.class);

        ResponseEntity<?> responseEntity = restLinkController.add(linkToAdd);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}