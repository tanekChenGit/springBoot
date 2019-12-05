package com.tk.demo;

import org.mockserver.model.Header;
import org.mockserver.model.HttpStatusCode;

import com.google.common.base.Charsets;
import com.google.common.net.MediaType;

import org.mockserver.client.initialize.ExpectationInitializer;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import java.util.concurrent.TimeUnit;


import org.mockserver.client.MockServerClient;

public class MockExpectationInitializer implements ExpectationInitializer {
	@Override
    public void initializeExpectations(MockServerClient mockServerClient) {
		 mockServerClient
         .when(
                 request()
                         .withPath("/mockFirst")
         )
         .respond(
        		 request -> {
        			 boolean flag = Math.random() > 0.5;
        			    if (flag) {
        			    	return response()
            	                    .withBody("call back A");
        			    }else {
        			    	return response()
            	                    .withBody("{}")
            	                    .withDelay(TimeUnit.SECONDS, 10);
        			    }
        	     }
         );
    	mockServerClient
        .when(
                request()
                        .withPath("/mockSecond")
        )
        .respond(
                response()
                .withHeader(
                        "Content-Type",
                        //"charset=utf-16"
                        MediaType.create("application", "json").withCharset(Charsets.UTF_8).toString()
                    )
                        .withBody(("{\n"+
                        		"\"native\":{\n"+
                        		"\"assets\":[\n"+
                        		"{\n"+
                        		"\"type\":\"description\",\n"+
                        		"\"data\":{\n"+
                        		"\"value\":\"潮流必Buy品SYMVEGA125ABS/CBS，潮色上市再創銷售熱潮，加碼推出汰舊換新最高補助13,800元，再送一年式丟賠保障！\"\n"+
                        		"}\n"+
                        		"},\n"+
                        		"{\n"+
                        		"\"type\":\"imageUrl\",\n"+
                        		"\"img\":{\n"+
                        		"\"url\":\"//tenmaximg.cacafly.net/upload/2/3/8/3/3/c47ed352.jpg?v=1\",\n"+
                        		"\"w\":1200,\n"+
                        		"\"h\":627\n"+
                        		"}\n"+
                        		"},\n"+
                        		"{\n"+
                        		"\"type\":\"title\",\n"+
                        		"\"data\":{\n"+
                        		"\"value\":\"運動新潮色，VEGA125最出色！購車即享高優惠\"\n"+
                        		"}\n"+
                        		"},\n"+
                        		"{\n"+
                        		"\"type\":\"iconUrl\",\n"+
                        		"\"img\":{\n"+
                        		"\"url\":\"//tenmaximg.cacafly.net/upload/2/3/8/3/3/c47ed352_icon.jpg?v=1\",\n"+
                        		"\"w\":250,\n"+
                        		"\"h\":250\n"+
                        		"}\n"+
                        		"},\n"+
                        		"{\n"+
                        		"\"type\":\"clickUrl\",\n"+
                        		"\"link\":{\n"+
                        		"\"url\":\"https://www.tenmax.io\"\n"+
                        		"}\n"+
                        		"}\n"+
                        		"],\n"+
                        		"\"impressionLink\":[\n"+
                        		"\"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488004431/531e37c1-049c-11ea-b603-bd9939e8cf90/23810/23833/null/${WINNING_PRICE}/?optInfo=xlKYjpzNASd8QA\"\n"+
                        		"]\n"+
                        		"}\n"+
                        		"}").getBytes(Charsets.UTF_8))
                        
        );
    	mockServerClient.when(
    			request() 
    		     	.withMethod("GET") 
    		     	.withPath("/Sample") 
    	)
    	.respond(
    		    response()
    		    	.withBody(("{\n" + 
    		    			"  \"native\": {\n" + 
    		    			"    \"assets\": [\n" + 
    		    			"      {\n" + 
    		    			"        \"type\": \"description\",\n" + 
    		    			"        \"data\": {\n" + 
    		    			"          \"value\": \"潮流必Buy品SYM VEGA125 ABS/CBS，潮色上市再創銷售熱潮，加碼推出汰舊換新最高補助13,800元，再送一年式丟賠保障！\"\r\n" + 
    		    			"        }\n" + 
    		    			"      }\n" + 
    		    			"    ],\n" + 
    		    			"    \"impressionLink\": [\n" + 
    		    			"      \"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488004431/531e37c1-049c-11ea-b603-bd9939e8cf90/23810/23833/null/${WINNING_PRICE}/?optInfo=xlKYjpzNASd8QA\"\r\n" + 
    		    			"    ]\n" + 
    		    			"  }\n" + 
    		    			"}").getBytes(Charsets.UTF_8))
    		    	.withHeader(
                            "Content-Type",
                            MediaType.create("text", "plain").withCharset(Charsets.UTF_8).toString()
                        )
    		    	//.withDelay(TimeUnit.SECONDS, 10)
    		    );
    	mockServerClient.when(
    			request() 
    		     	.withMethod("GET") 
    		     	.withPath("/Empty") 
    	)
    	.respond(
    		    response()
    		    	.withBody(("{}").getBytes(Charsets.UTF_8))
    		    	.withHeader(
                            "Content-Type",
                            MediaType.create("text", "plain").withCharset(Charsets.UTF_8).toString()
                        )
    		    	//.withDelay(TimeUnit.SECONDS, 10)
    		    );
    }

}
