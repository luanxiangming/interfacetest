package com.vipabc.interfacetest.utils;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static javax.ws.rs.core.MediaType.APPLICATION_XML;

public class RestClient {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    private static final String DEF_LANGUAGE = "ZH";
    
    public <T> T post(String url, Object data, Class<T> type) {
    	return this.post(url, data, type,DEF_LANGUAGE);
    }
    
    public <T> T post(String url, Object data, Class<T> type,String language) {
        ClientRequest request = clientRequest(url,language);
        request.body(APPLICATION_XML, data);
        ClientResponse<?> response = null;
        try {
            response = request.post();
        } catch (Exception e) {
            throw new RestClientException("post: " + url, e, Status.INTERNAL_SERVER_ERROR);
        }

        checkStatus(url, response);

        if (response.getResponseStatus().equals(Status.OK)) {
            return response.getEntity(type);
        }
        return null;
    }

    public <T> T postXML(String url, Object data, Class<T> type) {
        ClientRequest request = clientRequest(url,DEF_LANGUAGE);
        request.body(MediaType.TEXT_XML, data);
        ClientResponse<?> response = null;
        try {
            response = request.post();
        } catch (Exception e) {
            throw new RestClientException("post: " + url, e, Status.INTERNAL_SERVER_ERROR);
        }

        checkStatus(url, response);

        if (response.getResponseStatus().equals(Status.OK)) {
            return response.getEntity(type);
        }
        return null;
    }
    
    public void post(String url, Object data) {
        this.post(url, data,DEF_LANGUAGE);
    }

    public void postJson(String url,Object json){
    	 ClientRequest request = clientRequest(url, DEF_LANGUAGE);
         request.body(MediaType.APPLICATION_JSON, json);
         ClientResponse<?> response;
         try {
             response = request.post();
         } catch (Exception e) {
             throw new RestClientException("post: " + url, e, Response.Status.INTERNAL_SERVER_ERROR);
         }

         checkStatus(url, response);
    }
    public void post(String url, Object data, String language) {
        ClientRequest request = clientRequest(url, language);
        request.body(APPLICATION_XML, data);
        ClientResponse<?> response;
        try {
            response = request.post();
        } catch (Exception e) {
            throw new RestClientException("post: " + url, e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        checkStatus(url, response);
    }
    
    public <T> T get(String url, Class<T> classType) {
        return this.get(url, classType,DEF_LANGUAGE);
    }
    
    public <T> T get(String url, Class<T> classType,String language) {
        ClientRequest request = clientRequest(url, language);
        ClientResponse<?> response;
        try {
            response = request.get();
        } catch (Exception e) {
			throw new RestClientException(url, e,
					Response.Status.INTERNAL_SERVER_ERROR);
        }

        checkStatus(url, response);
        return response.getEntity(classType);
    }
    
    public <T> T put(String url, Object data, Class<T> type) {
    	return this.put(url, data, type, DEF_LANGUAGE);
    }

    public <T> T put(String url, Object data, Class<T> type,String language) {
    	ClientRequest request = clientRequest(url,language);
      if (data != null) {
          request.body(APPLICATION_XML, data);
      }
      ClientResponse<?> response;
      try {
          response = request.put();
      } catch (Exception e) {
          throw new RestClientException(url, e, Response.Status.INTERNAL_SERVER_ERROR);
      }

      checkStatus(url, response);
      
      if (response.getResponseStatus().equals(Status.OK)) {
	        return response.getEntity(type);
	    }
	    return null;
    }
    
    public void put(String url, Object data) {
        this.put(url, data, APPLICATION_XML);
    }
    
    public void put(String url, Object data, String contentType) {
        this.put(url, data, contentType,DEF_LANGUAGE);
    }

    public void put(String url, Object data, String contentType,String language) {
        ClientRequest request = clientRequest(url,language);
        if (data != null) {
            request.body(contentType, data);
        }
        ClientResponse<?> response;
        try {
            response = request.put();
        } catch (Exception e) {
            throw new RestClientException("put: " + url, e, Response.Status.INTERNAL_SERVER_ERROR);
        }

        checkStatus(url, response);
    }
    
    public void put(String url){
        ClientRequest request = clientRequest(url,DEF_LANGUAGE);
        ClientResponse<?> response;
        try {
            response = request.put();
        } catch (Exception e) {
            throw new RestClientException("put: " + url, e, Response.Status.INTERNAL_SERVER_ERROR);
        }
        checkStatus(url, response);
    }
    
    public void delete(String url){
    	ClientRequest request = clientRequest(url,DEF_LANGUAGE);
    	ClientResponse<?> response;
    	try {
            response = request.delete();
        } catch (Exception e) {
            throw new RestClientException("delete: " + url, e, Response.Status.INTERNAL_SERVER_ERROR);
        }
        checkStatus(url, response);
    }
    protected void checkStatus(String url, ClientResponse<?> response)
            throws RestClientException {
        if (!response.getResponseStatus().equals(Status.OK)
				&& !response.getResponseStatus().equals(Status.NO_CONTENT) && !response.getResponseStatus().equals(Status.CREATED)) {
            throw new RestClientException(url + " "
                    + response.getResponseStatus().toString() + "\n"
					+ response.getEntity(String.class),
					response.getResponseStatus());
        }
    }

    protected ClientRequest clientRequest(String url,String language) throws RestClientException {
        logger.debug("RestClient url:" + url);
        ClientRequest request = new ClientRequest(url);
        request.header("charset", "UTF-8");
        request.header("Accept-Language", language);
        return request;
    }
    
}