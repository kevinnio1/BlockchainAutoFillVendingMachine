package be.ordina.controller;

import be.ordina.service.blockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by KeLe on 27/04/2017.
 */
@CrossOrigin
@RestController
@RequestMapping(value = RequestMappings.BLOCKCHAIN)
public class blockchainController {


    //private final blockchainService blockchainService;

    @Autowired
    private blockchainService blockchainService;

    @RequestMapping(value="/getPeersOfNode",method = RequestMethod.GET)
    public String getPeersOfNode() {
           String res = blockchainService.getPeersOfNode("https://83e0829f9b1d4746b9d3e46314c35f57-vp1.us.blockchain.ibm.com:5001");
           System.out.println("respones : " + res);
           return res;

    }
}
