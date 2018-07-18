package com.app.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.app.util.constant.CommonConstants;
import com.app.util.pojo.PaginationInfo;

/**
 * 
 * Common utility class
 * 
 */
public class CommonUtil {
    
    private CommonUtil(){
        
    }

    /**
     * get corresponding map key for map value
     * 
     * @param inputMap
     * @param inputValue
     * @return K{map key by value}
     */
    public static <K, V> K getMapKeyByValue(Map<K, V> inputMap, V inputValue) {

        for (Map.Entry<K, V> entry : inputMap.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();

            if (value.equals(inputValue)) {
                return key;
            }
        }
        return null;
    }

    /**
     * filters location based based on page number
     * 
     * @param paginationInfo
     * @param inputList
     * @return List<T>{paged list of selected type}
     */
    public static <T> List<T> getPagedList(PaginationInfo paginationInfo, List<T> inputList) {

        List<T> outputList = new ArrayList<>();

        getPaginationInfo(paginationInfo, inputList.size());

        int startIndex;
        startIndex = (paginationInfo.getCurPage() - 1) * paginationInfo.getPageSize();
        int endIndex = startIndex + paginationInfo.getPageSize();

        if (endIndex > inputList.size() - 1) {
            endIndex = inputList.size();
        }
        if (startIndex <= endIndex) {
            outputList = inputList.subList(startIndex, endIndex);
        }

        return outputList;
    }

    /**
     * gets pagination information
     * 
     * @param paginationInfo
     * @param totalRecords
     */
    public static void getPaginationInfo(PaginationInfo paginationInfo, int totalRecords) {
        int totalPages = 0;

        int pageNo = paginationInfo.getCurPage() <= 0 ? CommonConstants.DEFAULT_PAGE_NO
            : paginationInfo.getCurPage();
        int pageSize = paginationInfo.getPageSize();
        
        if(pageSize <= 0){
            pageSize = CommonConstants.DEFAULT_PAGE_SIZE;
        }else if(pageSize > CommonConstants.MAX_ALLOWED_PAGE_SIZE){
            pageSize = CommonConstants.MAX_ALLOWED_PAGE_SIZE;
        }

        if (totalRecords > 0 && pageSize > 0) {
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        }

        // setting pagination details
        paginationInfo.setCurPage(pageNo);
        paginationInfo.setPageSize(pageSize);
        paginationInfo.setTotalPages(totalPages);
        paginationInfo.setTotalRecords(totalRecords);
    }
    
    /**
     * validates if specified url's server is accessible or not
     * 
     * @param url
     * @return
     */
    public static Boolean validateURL(String url) {
        try {
         final URLConnection connection = new URL(url).openConnection();
            connection.connect();
            return true;
        } catch (final MalformedURLException e) {
            throw new IllegalStateException("Bad URL: " + url, e);
        } catch (final IOException e) {
            return false;
        }
        
       }
    
    public static String restClient(String headerToken,String url,Map<String,Object> body,String headerTokenProperty)
    {
    	HttpHeaders headers = new HttpHeaders();
		headers.set(headerTokenProperty, headerToken);
		headers.set("Content-Type", "application/json");
		HttpEntity<Object> entity = new HttpEntity<>(body, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
    	
    }
}