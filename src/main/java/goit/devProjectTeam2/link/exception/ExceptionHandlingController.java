package goit.devProjectTeam2.link.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/v1")
public class ExceptionHandlingController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Link has been expired")
    @ExceptionHandler(IllegalArgumentException.class)
    public void conflict() {
        // TODO document why this method is empty
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String expireError() {
        return "expireError";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("url", req.getRequestURL());
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
