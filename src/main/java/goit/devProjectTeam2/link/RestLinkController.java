package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(name = "Лінк-контролер", description = "операції з посиланнями")
public class RestLinkController {

    private LinkService linkService;

    @GetMapping("/link/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Tag(name = "Лінк-контролер", description = "операції з посиланнями")
    public Link findById(@PathVariable Long id) {
        try {
            return linkService.getById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Link Not Found", e);
        }
    }

    @PostMapping("/link/create")
    public ResponseEntity<?> add(@RequestBody Link link) {
        try {
            linkService.add(link);
            return ResponseEntity.status(HttpStatus.CREATED).body("Link is created!");
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/allLinks")
    @ResponseStatus(HttpStatus.OK)
    public List<Link> findAllLinksForUser() {
        return linkService.listAllForUser();
    }

    @GetMapping(value = "/activeLinks")
    public List<Link> getActiveLinksForUser() {
        return linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis()));
    }

    @DeleteMapping("/link/delete/{linkId}")
    public ResponseEntity<?> delete(@PathVariable long linkId) {
        try {
            linkService.deleteById(linkId);
            return ResponseEntity.status(HttpStatus.OK).body("Link is deleted!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/token/{token}")
    void redirectFromToken(@PathVariable String token, HttpServletResponse response)
            throws IOException {
        try {
            Link longLinkByToken = linkService.getLinkByToken(token);
            linkService.validateLinkDoNotExpired(longLinkByToken);
            linkService.increaseClickCounter(longLinkByToken.getLinkId());
            response.sendRedirect(longLinkByToken.getLongLink());
        } catch (NoSuchElementException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Link doesn't exist or has been expired!");
        }
    }

    @PutMapping(value = "/link/edit/{linkId}")
    public ResponseEntity<?> update(@PathVariable long linkId, @RequestBody Map<String, Object> payload) {
        try {
            linkService.updateLinkViaApi(linkId, String.valueOf(payload.get("longLink")));
            return ResponseEntity.status(HttpStatus.OK).body("Link is updated!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}

