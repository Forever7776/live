package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kz on 2017-11-06.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

    @RequestMapping
    public String index(){
        logger.info("{}","shouey");
        return "hello";
    }
}
