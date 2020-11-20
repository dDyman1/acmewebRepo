package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    protected static final String template = "Server Status requested by %s";
    protected final AtomicLong counter = new AtomicLong();


    /*
    renamed to requestHandler()
     */
    @RequestMapping("/status")
    public StatusInterface requestHandler(@RequestParam(value="name", defaultValue="Anonymous") String name, @RequestParam(required = false) List<String> details) {
        /*Debugging code for string list*/
        System.out.println("*** DEBUG INFO ***" + details);
        return new ServerStatus(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/status/detailed")
    public StatusInterface detailedRequestHandler(@RequestParam(value="name", defaultValue="Anonymous") String name, @RequestParam(required = false) List<String> details) {
        /*Debugging code for string list*/
        System.out.println("*** DEBUG INFO ***" + details);
        StatusInterface status = new ServerStatus(counter.incrementAndGet(), String.format(template, name));

        if(details == null){
            throw new BadRequestException();
        }
        for(String detail : details){
            switch (detail) {
                case "operations":
                    status = new SatusOpDecorator(status);
                    break;
                case "extensions":
                    status = new SatusExtDecorator(status);
                    break;
                case "memory":
                    status = new SatusMemDecorator(status);
                    break;
                default:
                    throw new BadRequestException();
            }
        }

        return status;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="my custom message")
    public class BadRequestException extends RuntimeException {}

}
