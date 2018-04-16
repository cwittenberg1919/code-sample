package com.charter.enterprise.motd;

import org.slf4j.Logger;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class MotdController {
    private static final String DEFAULT_MESSAGE = "Welcome to Charter.  All systems are nominal.";
    private String Motd = DEFAULT_MESSAGE;

    @GetMapping
    public String getMotd() {
        return Motd;
    }

    @PutMapping
    public ResponseEntity setMotd(String message){
        if (message == null || message.isEmpty()){
            return new ResponseEntity<String>("Message cannot be set to null or an empty string.", HttpStatus.BAD_REQUEST);
        }

        if ("reset".equals(message)){
            Motd = DEFAULT_MESSAGE;

            return new ResponseEntity<String>("Message has been reset successfully.", HttpStatus.OK);
        } else {
            Motd = message;
            return new ResponseEntity<String>("Message has been changed successfully.", HttpStatus.OK);
        }

    }

}
