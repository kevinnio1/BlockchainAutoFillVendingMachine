package be.ordina.service;

import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Service;
import sun.misc.IOUtils;

import javax.xml.ws.ServiceMode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Created by KeLe on 27/04/2017.
 */
@Service
public class blockchainService {


    public String getPeersOfNode(String address) {
        String res = "leeg";

        try {
            String request = address + "/network/peers";
            URL url = new URL("https://83e0829f9b1d4746b9d3e46314c35f57-vp1.us.blockchain.ibm.com:5001/network/peers");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            res = connection.getResponseMessage();
            res = connection.getContent().toString();

            res = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()))
                    .lines().collect(Collectors.joining("\n"));




        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;

    }
}
