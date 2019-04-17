package com.bdqn.ssm.controller;


import com.bdqn.ssm.service.ProviderService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    private ProviderService ps;
    private Logger logger=Logger.getLogger(UserController.class);

    


}
