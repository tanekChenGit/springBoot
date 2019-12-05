package com.tk.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tk.obj.JsonObj;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

@Service
public class CompletableFutureService {
	private static final Logger logger = LoggerFactory.getLogger(CompletableFutureService.class);

	@Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
    	return new RestTemplate();
    }
    @Autowired
	private AdsDataJPARepository adsDataJPARepository;
    
    @Async("taskExecutor")
    public CompletableFuture<JsonObj> getAdsData(String ads,String tar) throws InterruptedException {
        logger.info( ads + "Get " +" START-------");
        String url = tar;
        // configure timeout
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(500); // set short connect timeout
        requestFactory.setReadTimeout(5000); // set slightly longer read timeout
        restTemplate.setRequestFactory(requestFactory);
        JsonObj result = new JsonObj();
        try {
        	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        	converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        	restTemplate.getMessageConverters().add(0, converter);
        	result = restTemplate.getForObject(url, JsonObj.class);
        	
        	AdsDataJPA adsDataJPA = new AdsDataJPA();
    	    if (result.getNative() != null) {
    	    	for(int i = 0;i < result.getNative().getAssets().size();i++) {
    	    		if (result.getNative().getAssets().get(i).getType().equals("description"))
    	    			adsDataJPA.setDescription(result.getNative().getAssets().get(i).getData().getValue());
    	    		if (result.getNative().getAssets().get(i).getType().equals("imageUrl"))
    	    			adsDataJPA.setImageUrl(result.getNative().getAssets().get(i).getImg().getUrl());
    	    		if (result.getNative().getAssets().get(i).getType().equals("title"))
    	    			adsDataJPA.setTitle(result.getNative().getAssets().get(i).getData().getValue());
    	    		if (result.getNative().getAssets().get(i).getType().equals("iconUrl"))
    	    			adsDataJPA.setIconUrl(result.getNative().getAssets().get(i).getImg().getUrl());
    	    		if (result.getNative().getAssets().get(i).getType().equals("clickUrl"))
    	    			adsDataJPA.setClickUrl(result.getNative().getAssets().get(i).getLink().getUrl());
    	    	}
    	    	for(int i = 0;i < result.getNative().getImpressionLink().size();i++) {
    	    		adsDataJPA.setImpressionLink(result.getNative().getImpressionLink().get(i));
    	    	}
    	    	adsDataJPARepository.save(adsDataJPA);
    	    	logger.info("AdsDataJPA,saveData");
    	    }
        }catch(RestClientException e) {
        	logger.info("AdsDataJPA,read time out");
        }catch(Exception e){
	        e.printStackTrace();
        }
        // Artificial delay of 1s for demonstration purposes
//        logger.info("AdsDataJPA," + results+" START===");
//        Thread.sleep(1000L);
        logger.info("AdsDataJPA," + result+" END-------");
        return CompletableFuture.completedFuture(result);
    }
}
