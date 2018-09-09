package com.yang.beet.controller


import org.apache.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

import org.springframework.web.bind.annotation.RequestMethod.GET


@Controller
@RequestMapping("/home")
class BeetlController {

    /**
     * 测试beetl模板
     *
     * @return
     */
    @RequestMapping(value = "/add", method = arrayOf(GET))
    fun home(): ModelAndView {

        val modelAndView = ModelAndView()
        logger.info("add request")
        modelAndView.addObject("email", "apk2sf@163.com")
        modelAndView.viewName = "add"
        modelAndView.viewName = ""
        return modelAndView
    }

    companion object {

        private val logger = Logger.getLogger(BeetlController::class.java!!)
    }


}
