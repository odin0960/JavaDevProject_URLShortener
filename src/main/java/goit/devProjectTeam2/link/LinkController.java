package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1")
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/token/{token}")
    public ModelAndView redirectByToken(@PathVariable String token) {
        Link linkByToken = linkService.getLinkByToken(token);
        linkService.validateLinkDoNotExpired(linkByToken);
        linkService.increaseClickCounter(linkByToken.getLinkId());
        return new ModelAndView("redirect:" + linkByToken.getLongLink());
    }

    @GetMapping("/link/create")
    public ModelAndView getCreatePage(Link link) {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView.addObject("link", link);
    }

    @PostMapping(value = "/link/create")
    public ModelAndView createJson(@Valid @ModelAttribute("link") Link link) {
        ModelAndView modelAndView = new ModelAndView("redirect:/v1/user/allLinks");
        linkService.add(link);
        return modelAndView;
    }

    @GetMapping(value = "/allLinks")
    public ModelAndView getAllLinks() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("links", linkService.listAll());
        return modelAndView;
    }

    @GetMapping(value = "/activeLinks")
    public ModelAndView getActiveLinks() {
        ModelAndView modelAndView = new ModelAndView("activeLinks");
        modelAndView.addObject("activeLinks",
                linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis())));
        return modelAndView;
    }

    @RequestMapping("/link/delete/{linkId}")
    public ModelAndView delete(@PathVariable("linkId") long linkId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/v1/user/allLinks");
        linkService.deleteById(linkId);
        return modelAndView;
    }

}
