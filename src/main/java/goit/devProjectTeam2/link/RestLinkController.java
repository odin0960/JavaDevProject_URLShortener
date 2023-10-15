package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
@Tag(name="Лінк-контролер", description="операції з посиланнями")
public class RestLinkController {

    private LinkService linkService;

    @GetMapping("/api/link/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Tag(name="Лінк-контролер", description="операції з посиланнями")
    public Link findById(@PathVariable Long id) {
        try {
            return linkService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Link Not Found", e);
        }
    }

    @PostMapping("/api/user/link/create")
    @ResponseStatus(HttpStatus.OK)
    public Link add(Link link) {
        return linkService.add(link);
    }

    @GetMapping("/api/user/allLinks")
    @ResponseStatus(HttpStatus.OK)
    public List<Link> findAllLinks() {
        return linkService.listAll();
    }

    @GetMapping(value = "/api/user/activeLinks")
    public List<Link> getActiveLinks() {
        return linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis()));
    }

    @DeleteMapping("/api/user/link/delete/{linkId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long linkId) {
        linkService.deleteById(linkId);
    }

    @GetMapping("/api/{token}")
    void redirectFromToken(@PathVariable String token, HttpServletResponse response)
            throws IOException {
        try {
            Link longLinkByToken = linkService.getLinkByToken(token);
            linkService.increaseClickCounter(longLinkByToken.getLinkId());
            response.sendRedirect(longLinkByToken.getLongLink());
        } catch (NoSuchElementException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}

