package com.tk.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import java.text.SimpleDateFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.tk.obj.JsonObj;

import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH24:mm:ss");
	//sample1-1 第一次延遲1秒執行，執行後再1分執行
	@Scheduled(initialDelay = 1000, fixedDelay = 60000)
	@RequestMapping(value = "/ads")
	public String getRequest() {
		String uri = "https://tenmax-mock-dsp.azurewebsites.net/api/getAds?" + 
				"code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==";
	    RestTemplate restTemplate = new RestTemplate();
	    try {
        	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        	converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        	restTemplate.getMessageConverters().add(0, converter);
        	JsonObj result = restTemplate.getForObject(uri, JsonObj.class);
        	System.out.println(dateFormat.format(new Date())+result);
	    }catch(Exception e){
	        e.printStackTrace();
	        return "FAIL";
	    }
	    return "SUCCESS";
	}
	
	@Autowired
	private AdsDataJPARepository adsDataJPARepository;
	//sample1-3
	@GetMapping("/queryAdsDataJPA")
    public List<AdsDataJPA> getAdsDataJPA(@RequestParam(value="title", defaultValue="title") String title) {
        return adsDataJPARepository.findByTitleLike("%"+title+"%");
    }
	//sample1-2
	@RequestMapping("/storeData")
    public String createAdsDataJPA() {
		final String uri = "https://tenmax-mock-dsp.azurewebsites.net/api/getAds?" + 
				"code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==";
	    RestTemplate restTemplate = new RestTemplate();
	    JsonObj result = restTemplate.getForObject(uri, JsonObj.class);
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
	    	return "Data save!";
	    }
	    return "execute!";
    }
	//sample2-2
	@Autowired
	private CompletableFutureService service;
	
	@Scheduled(initialDelay = 1000, fixedDelay = 60000)
	@RequestMapping("/completableFutureSample")
	public void completableFutureSimple() {
		
	    try {
	    	CompletableFuture<JsonObj> cf1 = service.getAdsData("thread1", "http://localhost:8080:/object");
	    	CompletableFuture<JsonObj> cf2 = service.getAdsData("thread2", "https://tenmax-mock-dsp.azurewebsites.net/api/getAds?" + 
					"code=s9Ybtsb6hwigndO6a5OwsLmXOPR0olrW7nBFFE7QmHfvaQ6p9GWXwg==");
//	    	CompletableFuture<JsonObj> cf3 = service.getAdsData("thread3", "http://localhost:8080:/object");
	        CompletableFuture.allOf(cf1,cf2).join();
	        
	        logger.info("cf1->"+cf1.get().toString());
	        logger.info("cf2->"+cf2.get().toString());
//	        logger.info("cf3->"+cf3.get().toString());
	    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//sample2-1 mock
	@RequestMapping("/object")
	public String getObject() {
		boolean flag = Math.random() > 0.5;
		try {
			if (flag) {
				Thread.sleep(5000);
				return "{}";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\r\n" + 
				"  \"native\": {\r\n" + 
				"    \"assets\": [\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"description\",\r\n" + 
				"        \"data\": {\r\n" + 
				"          \"value\": \"潮流必Buy品SYM VEGA125 ABS/CBS，潮色上市再創銷售熱潮，加碼推出汰舊換新最高補助13,800元，再送一年式丟賠保障！\"\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"imageUrl\",\r\n" + 
				"        \"img\": {\r\n" + 
				"          \"url\": \"//tenmaximg.cacafly.net/upload/2/3/8/3/3/c47ed352.jpg?v=1\",\r\n" + 
				"          \"w\": 1200,\r\n" + 
				"          \"h\": 627\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"title\",\r\n" + 
				"        \"data\": {\r\n" + 
				"          \"value\": \"運動新潮色，VEGA125最出色！購車即享高優惠\"\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"iconUrl\",\r\n" + 
				"        \"img\": {\r\n" + 
				"          \"url\": \"//tenmaximg.cacafly.net/upload/2/3/8/3/3/c47ed352_icon.jpg?v=1\",\r\n" + 
				"          \"w\": 250,\r\n" + 
				"          \"h\": 250\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"        \"type\": \"clickUrl\",\r\n" + 
				"        \"link\": {\r\n" + 
				"          \"url\": \"https://www.tenmax.io\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    ],\r\n" + 
				"    \"impressionLink\": [\r\n" + 
				"      \"https://beta-rtb.tenmax.io/bid/asiamax/impreWithPrice/1573488002965/523c1521-049c-11ea-b603-bd9939e8cf90/24093/23833/null/${WINNING_PRICE}/?optInfo=xlKYg0XrXsSg1g\"\r\n" + 
				"    ]\r\n" + 
				"  }\r\n" + 
				"}";
	}
}
