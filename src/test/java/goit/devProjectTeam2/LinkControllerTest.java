package goit.devProjectTeam2;

import goit.devProjectTeam2.entity.Link;
import goit.devProjectTeam2.link.LinkController;
import goit.devProjectTeam2.link.LinkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LinkControllerTest {

    private LinkController linkController;
    private LinkService linkService;

    @BeforeEach
    public void setUp() {
        linkService = mock(LinkService.class);
        linkController = new LinkController(linkService);
    }

    @Test
    public void testRedirectByToken() {
        String token = "testToken";
        Link link = new Link();
        link.setLongLink("");
        when(linkService.getLinkByToken(token)).thenReturn(link);
        ModelAndView modelAndView = linkController.redirectByToken(token);
        assertEquals("redirect:", modelAndView.getViewName());
        verify(linkService).validateLinkDoNotExpired(link);
        verify(linkService).increaseClickCounter(link.getLinkId());
    }

    @Test
    public void testGetCreatePage() {
        Link link = new Link();
        ModelAndView modelAndView = linkController.getCreatePage(link);
        assertEquals("create", modelAndView.getViewName());
        assertEquals(link, modelAndView.getModel().get("link"));
    }

    @Test
    public void testCreateJson() {
        Link link = new Link();
        ModelAndView modelAndView = linkController.createJson(link);
        assertEquals("redirect:/v1/user/allLinks", modelAndView.getViewName());
        verify(linkService).add(link);
    }

    @Test
    public void testGetAllLinks() {
        List<Link> links = List.of(new Link(), new Link());
        when(linkService.listAll()).thenReturn(links);
        ModelAndView modelAndView = linkController.getAllLinks();
        assertEquals("list", modelAndView.getViewName());
        assertEquals(links, modelAndView.getModel().get("links"));
    }

    @Test
    public void testGetActiveLinks() {
        List<Link> activeLinks = List.of(new Link(), new Link());
        Timestamp testTimestamp = new Timestamp(System.currentTimeMillis());
        when(linkService.findAllMoreThenExpirationDate(ArgumentMatchers.eq(testTimestamp))).thenReturn(activeLinks);
        ModelAndView modelAndView = linkController.getActiveLinks();
        assertEquals("activeLinks", modelAndView.getViewName());
        assertEquals(activeLinks, modelAndView.getModel().get("activeLinks"));
        verify(linkService).findAllMoreThenExpirationDate(ArgumentMatchers.eq(testTimestamp));
    }

    @Test
    public void testDelete() {
        long linkId = 1L;
        ModelAndView modelAndView = linkController.delete(linkId);
        assertEquals("redirect:/v1/user/allLinks", modelAndView.getViewName());
        verify(linkService).deleteById(linkId);
    }

    @Test
    public void testEdit() {
        Long id = 1L;
        Link link = new Link();
        when(linkService.getById(id)).thenReturn(link);
        ModelAndView modelAndView = linkController.edit(id);
        assertEquals("edit", modelAndView.getViewName());
        assertEquals(link, modelAndView.getModel().get("link"));
    }

    @Test
    public void testEditLink() {
        Link link = new Link();
        ModelAndView modelAndView = linkController.editLink(link);
        assertEquals("redirect:/v1/allLinks", modelAndView.getViewName());
        verify(linkService).updateLink(link);
    }
}