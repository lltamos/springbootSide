package org.font.controller.exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ExceptionController {


    /**
     * throw exception
     */
    @RequestMapping("throwIllegalArgumentException")
    public ModelAndView throwIllegalArgumentException() throws IllegalArgumentException {
        throw new IllegalArgumentException("参数异常");
    }

    /**
     * throw exception
     */
    @RequestMapping("throwException")
    public ModelAndView throwException() throws Exception {
        throw new Exception("异常");
    }

}
