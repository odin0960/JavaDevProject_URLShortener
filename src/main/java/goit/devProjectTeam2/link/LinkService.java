package goit.devProjectTeam2.link;

import goit.devProjectTeam2.ServiceInterface;
import goit.devProjectTeam2.entity.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Cacheable(value = "links", key = "#token", sync = true)
    public Link getLinkByToken(String token) {
        return linkRepository.getLinkByToken(token)
                .orElseThrow(() -> new NoSuchElementException("Link with such token doesn't found"));
    }

    @Override
    public Link add(Link link) {
        if(LinkValidator.linkValid(link.getLongLink())) {
            link.setToken(generateToken(link));
            link.setExpireDate(LinkUtils.calculateExpireDate());
            return linkRepository.save(link);
        } else {
            throw new NoSuchElementException("Link is not valid!");
        }
    }

    public Link validateLinkDoNotExpired(Link link) {
        if (link.getExpireDate().after(new Timestamp(System.currentTimeMillis()))) {
            return link;
        } else {
            throw new NoSuchElementException("Link has been expired!");
        }
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
                    if (l.getUser().getUserId().equals(id)) {
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

    public String generateToken(Link link) {
        String token = "";
        if (link.getLongLink() != null && link.getLongLink().startsWith("http")) {
            token = LinkUtils.generate();
        }
        return token;
    }

    @Transactional
    public Link increaseClickCounter(Long id) {
        Link recievedLink = getById(id);
        recievedLink.setCount(recievedLink.getCount() + 1);

        return linkRepository.save(recievedLink);
    }

}
