package name.nicholasgribanov.recipe.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView numberFormatHandling(Exception e) {
        log.error("Invalid number format");
        log.error(e.getMessage());
        ModelAndView modelAndView = new ModelAndView("400error");
        modelAndView.addObject("exception", e);

        return modelAndView;
    }
}
