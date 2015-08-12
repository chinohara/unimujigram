package com.example.rest;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * RESTリクエストを行うためのクライアントクラス
 */
public class RestClient {
 
    private String account = null;
    private String password = null;
     
    public RestClient(String account, String password) {
        this.account = account;
        this.password = password;
    }
     
    private Client getClient() {
        Client client = new Client();
        return client;
    }
    
    public String getString(String url, MediaType type) {
        Client client = getClient();
        WebResource resource = client.resource(url);
        ClientResponse response = resource.accept(type).get(ClientResponse.class);
        switch (response.getStatus()) {
        case 200 :  // OK
            break;
        default:
            return String.format("Code:%s Entity:%s",
                    response.getStatus(),
                    response.getEntity(String.class));
        }
        return response.getEntity(String.class);
    }
}
