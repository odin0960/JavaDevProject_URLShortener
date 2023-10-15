package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.Link;
import goit.devProjectTeam2.entity.dto.LinkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1")
public class LinkController {

    private final LinkService linkService;

    @GetMapping("/{token}")
    @Cacheable(value = "links", key = "#token", sync = true)
    public ModelAndView redirectByToken(@PathVariable String token) {
        Link linkByToken = linkService.getLinkByToken(token);
        linkService.increaseClickCounter(linkByToken.getLinkId());
        return new ModelAndView("redirect:" + linkByToken.getLongLink());
    }

    @GetMapping("/user/link/create")
    public ModelAndView getCreatePage(Link link) {
        ModelAndView modelAndView = new ModelAndView("create");
        return modelAndView.addObject("link", link);
    }

    @PostMapping(produces = "application/json", value = "/user/link/create")
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

    @GetMapping(value = "/user/allLinks")
    public ModelAndView getAllLinks() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("links", linkService.listAll());
        return modelAndView;
    }

    @GetMapping(value = "/user/activeLinks")
    public ModelAndView getActiveLinks() {
        ModelAndView modelAndView = new ModelAndView("activeLinks");
        modelAndView.addObject("activeLinks",
                linkService.findAllMoreThenExpirationDate(new Timestamp(System.currentTimeMillis())));
        return modelAndView;
    }

    @RequestMapping("/user/link/delete/{linkId}")
    public ModelAndView delete(@PathVariable("linkId") long linkId) {
        ModelAndView modelAndView = new ModelAndView("redirect:/v1/user/allLinks");
        linkService.deleteById(linkId);
        return modelAndView;
    }

}
