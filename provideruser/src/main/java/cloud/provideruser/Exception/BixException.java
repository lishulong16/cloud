package cloud.provideruser.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RestController
public class BixException {


    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(RuntimeException exception){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception.getMessage());
        return modelAndView;
    }
}
