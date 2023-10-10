package goit.devProjectTeam2.link;

import goit.devProjectTeam2.ServiceInterface;
import goit.devProjectTeam2.entity.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LinkService implements ServiceInterface<Link> {

    public final LinkRepository linkRepository;

    @Override
    public Link getById(Long id) {
        return linkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Link with such id doesn't found"));
    }

    @Override
    public Link add(Link link) {
        link.setToken(generateShortLink(link));
        link.setExpireDate(LinkUtils.calculateExpireDate());
        return linkRepository.save(link);
    }

    @Override
    public void deleteById(Long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public List<Link> listAll() {
        return linkRepository.findAll();
    }

    public List<Link> listAllByUserId(Long id) {
        List<Link> listAllForSpecifiedUserId = new ArrayList<>();
        listAll().stream().forEach(
                l -> {
                    if (l.getUserId().getUserId().equals(id)) {
                        listAllForSpecifiedUserId.add(l);
                    }
                });
        return listAllForSpecifiedUserId;
    }

    public List<Link> findAllMoreThenExpirationDate(Timestamp timestamp) {
        List<Link> linkListWithExpirationDateMoreThenExpected = new ArrayList<>();
        listAll().stream().forEach(
                l -> {
                    if (l.getExpireDate().after(timestamp)) {
                        linkListWithExpirationDateMoreThenExpected.add(l);
                    }
                });
        return linkListWithExpirationDateMoreThenExpected;
    }

    public String generateShortLink(Link link) {
        String shortLink = "";
        if (link.getLongLink() != null && link.getLongLink().startsWith("http")) {
            link.getLongLink().replaceFirst("^https?:\\/\\/", "");
            shortLink = LinkUtils.generate();
        }
        return shortLink;
    }

    public Link increaseClickCounter(Long id) {
        Link recievedLink = getById(id);
        recievedLink.setCount(recievedLink.getCount() + 1);
        return linkRepository.save(recievedLink);
    }

}
