package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import goit.devProjectTeam2.entity.User;
import goit.devProjectTeam2.entity.dto.LinkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class LinkController {

    private final LinkService linkService;
    @GetMapping(value = "/user/link/create")
    public String createLink() {
        return ("create-link");
    }

    @PostMapping(produces="application/json", value = "/user/link/create")
    public RedirectView createJson(@RequestBody LinkDTO linkDTO) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/v1/user/link/create");
        Link link = new Link();
        link.setLinkId(linkDTO.getLinkId());
        linkService.add(link);
        return redirectView;
    }
    @PostMapping(value = "/user/link/create")
    public RedirectView create(@ModelAttribute Link link) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/v1/user/link/create");
        linkService.add(link);
        linkService.increaseClickCounter(link.getCount());
        return redirectView;
    }

    @GetMapping(value = "/user/all/links" )
    public ModelAndView getAllLinks() {
        ModelAndView modelAndView = new ModelAndView("link");
        modelAndView.addObject("links", linkService.listAll());
        return modelAndView;
    }

    @GetMapping(value = "/user/active/links" )
    public ModelAndView getActiveLinks() {
        ModelAndView modelAndView = new ModelAndView("activeLink");
        modelAndView.addObject("activeLinks", linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis())));
        return modelAndView;
    }

    @PostMapping("/user/link/delete")
    public void delete(@RequestParam long linkId) {
        linkService.deleteById(linkId);
    }
}
