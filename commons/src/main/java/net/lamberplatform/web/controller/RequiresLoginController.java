package net.lamberplatform.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * DATE: 2025/1/19
 * AUTHOR: hchery
 * URL: https://github.com/hchery
 * EMAIL: h.chery@qq.com
 */
@RequestMapping(RequiresLoginController.ApiPrefix)
public interface RequiresLoginController {

    String ApiPrefix = "/api/r/v1";
}
