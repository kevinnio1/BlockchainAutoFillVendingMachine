package be.ordina.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by KeLe on 27/04/2017.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class testController {


    @RequestMapping(value="/hallo",method = RequestMethod.GET)
    public String getPeersOfNode() {
        return "hallo";

    }
}
