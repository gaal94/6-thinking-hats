package com.ssafy.sixhats.controller;

import com.ssafy.sixhats.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    RoomService roomService;

}
