package com.Project_2_Location_Search_API.service;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.Arrays;

@Service
@Slf4j
public class MapService {

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    private final String key = "pk.4e3f27d87b71d3a12326e0641a621a8d";
    private final String baseURL = "https://api.locationiq.com/v1";
    private final String mapBaseURL = "https://maps.locationiq.com/v3";

    /**
     * Consume 3rd party API and fetch map request
     * @param url
     * @return requested URL
     */
    private ResponseEntity fetchRequest(String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    /**
     * Consume 3rd party API and fetch map interface image
     * @param url
     * @return response/map image
     */
    public ResponseEntity fetchImage(String url) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
            ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.POST,entity, byte[].class);
            return response;
        } catch( HttpServerErrorException hse ){
            throw hse;
        }
    }

    /**
     * Get map by well known structure, Ex. Empire State Building
     * @param street
     * @param city
     * @param county
     * @param state
     * @param country
     * @param postalcode
     * @param format
     * @return map
     */
    public ResponseEntity getByStructured(String street, String city, String county, String state, String country, String postalcode, String format) {
        String url = String.format("%s/search.php?key=%s&street=%s&city=%s&county=%s&state=%s&country=%s&postalcode=%s&format=%s", baseURL, key, street, city, county, state, country, postalcode, format);
        return fetchRequest(url);
    }

    /**
     * Consume 3rd party API and get map by US state
     * @param state
     * @param format
     * @param countryCodes
     * @return map
     */
    public ResponseEntity getStateInfo(String state, String format, String countryCodes) {
        String[] listOfUsStates = new String[] {"alabama", "alaska", "arizona", "arkansas", "california", "colorado", "connecticut", "delaware", "florida", "georgia", "hawaii", "idaho", "illinois", "indiana", "iowa", "kansas", "kentucky", "louisiana", "maine", "maryland", "massachusetts", "michigan", "minnesota", "mississippi", "missouri", "montana", "nebraska", "nevada", "new hampshire", "new jersey", "new mexico", "new york", "north carolina", "ohio", "oklahoma", "oregon", "pennsylvania", "rhode island", "south carolina", "south dakota", "tennessee", "texas", "utah", "vermont", "virginia", "washington", "west virginia", "wisconsin", "wyoming"};
        if (!Arrays.asList(listOfUsStates).contains(state.toLowerCase())) throw new UnsupportedOperationException("US states only");
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s&countryCodes=%s", baseURL, key, state, format, countryCodes);
        return fetchRequest(url);
    }

    /**
     * Get map by country
     * @param country
     * @param format
     * @return map
     */
    public ResponseEntity getLocationInfo(String country, String format){
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s", baseURL, key, country, format);
        return fetchRequest(url);
    }

    /**
     * Get map by queried data
     * @param q
     * @param format
     * @return map
     */
    public ResponseEntity getByQuery(String q, String format) {
        String url = String.format("%s/autocomplete.php?key=%s&q=%s&format=%s", baseURL, key, q, format);
        return fetchRequest(url);
    }

    /**
     * Get map by general information
     * @param q
     * @return map
     */
    public ResponseEntity getGeneral(String q) {
        String url = String.format("%s/autocomplete.php?key=%s&q=%s", baseURL, key, q);
        return fetchRequest(url);
    }

    /**
     * Get Location Map by country using longitude and latitude
     * @param country
     * @param format
     * @return map image
     */
    public ResponseEntity getLocationMap(String country, String format) {
        ResponseEntity response = getLocationInfo(country, format);
        try {
            JSONArray jsonArray = (JSONArray) new JSONParser().parse(response.getBody().toString());
            Object first = jsonArray.get(0);
            String latitude = "";
            String longitude = "";
            String[] jsonParts = first.toString().split(",");
            for (int i=0; i < jsonParts.length; i++) {
                String curr = jsonParts[i];
                if (curr.contains("lat")) latitude = curr.split(":")[1];
                else if (curr.contains("lon")) longitude = curr.split(":")[1];
            }
            latitude = latitude.replaceAll("[^a-zA-Z0-9.-]", "");
            longitude = longitude.replaceAll("[^a-zA-Z0-9.-]", "");
            String center = String.format("%s,%s", latitude, longitude);
            String marker = String.format("icon:large-red-cutout|%s", center);
            String url = String.format("%s/staticmap?key=%s&center=%s&zoom=4&size=480x480&markers=%s", mapBaseURL, key, center, marker);
            return fetchImage(url);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
