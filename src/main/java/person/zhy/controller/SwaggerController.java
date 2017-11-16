package person.zhy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "apis")
public class SwaggerController {

    public  ModelAndView apis(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("swagger-ui.html");
        return modelAndView;
    }
}
