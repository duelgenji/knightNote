package com.knightNote.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Api(tags = "测试", description = "描述")
public class SwaggerTestController {

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ApiOperation(value="get venue List", notes = "Get venue list with page", responseContainer = "List")
    @ApiParam(value = "123")
    public Object play() {
        return "{\"ret_code\" : 0}";
    }

}
