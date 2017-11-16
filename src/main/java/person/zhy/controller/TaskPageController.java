package person.zhy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class TaskPageController {

    @RequestMapping(value = "/singuppage")
    public ModelAndView Index(){
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/singinpage")
    public ModelAndView singIn(){
        return new ModelAndView("singIn");
    }

    @RequestMapping(value = "/")
    public ModelAndView taskIndex(){
        return new ModelAndView("todo");
    }



}
