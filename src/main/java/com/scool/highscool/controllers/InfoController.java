package com.scool.highscool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${server.port}")
    Integer port;

    private Logger logger = LoggerFactory.getLogger(InfoController.class);

    @GetMapping(value ="/getPort")
    public ResponseEntity<Integer> getPort() {
        logger.info("Was invoked method for getPort()");
        return ResponseEntity.ok(port);
    }
}
