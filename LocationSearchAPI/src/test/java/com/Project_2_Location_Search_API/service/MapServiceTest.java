package com.Project_2_Location_Search_API.service;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MapServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MapService mapService;

    @Test
    public void shouldGetLocationInfo(){
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        ResponseEntity res = mapService.getLocationInfo("India", "json");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

//    @Test
//    public void shouldGetByPostalCode(){
//        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//                .thenReturn(responseEntity);
//        ResponseEntity res = mapService.getByPostalCode("10001", "us", "json");
//        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
//    }

    @Test
    public void shouldGetByStructured() {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        ResponseEntity res = mapService.getByStructured("20 W 34th St", "New York", "New York County", "New York", "United States of America", "10001", "json");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

//    @Test
//    public void shouldGetByQuery() {
//        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//                .thenReturn(responseEntity);
//        ResponseEntity res = mapService.getByQuery("Empire State Building", "json");
//        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
//    }

    @Test
    public void shouldGetStateInfo() {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        ResponseEntity res = mapService.getStateInfo("Texas", "json", "us");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

    @Test
    public void getStateInfoShouldThrowException() {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        UnsupportedOperationException ex = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            mapService.getStateInfo("Ontario", "json", "us");
        });
        Assertions.assertEquals("US states only", ex.getMessage(), "Exception not thrown when entered a non-US state");
    }

    @Test
    public void shouldGetGeneral() {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);
        ResponseEntity res = mapService.getGeneral("Empire State Building");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

    @Test
    public void shouldGetLocationMapSuccessfully(){

        String str = "[{\"place_id\":\"322697972714\",\"osm_id\":\"304716\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"22.3511148\",\"lon\":\"78.6677428\",\"boundingbox\":[\"6.5531169\",\"35.6745457\",\"67.9544415\",\"97.395561\"],\"class\":\"place\",\"type\":\"country\",\"display_name\":\"India, India\",\"display_place\":\"India\",\"display_address\":\"in\",\"address\":{\"name\":\"India\",\"country\":\"India\",\"country_code\":\"in\"}}]";

//                ",{\"place_id\":\"323582683775\",\"osm_id\":\"361709652\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"28.61298465\",\"lon\":\"77.22949432\",\"boundingbox\":[\"28.6128058\",\"28.6130584\",\"77.2293952\",\"77.2295867\"],\"class\":\"historic\",\"type\":\"monument\",\"display_name\":\"India Gate, Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"display_place\":\"India Gate\",\"display_address\":\"Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"address\":{\"name\":\"India Gate\",\"road\":\"Swami Vivekanada Road\",\"suburb\":\"Chanakya Puri Tehsil\",\"city\":\"New Delhi\",\"state\":\"Delhi\",\"postcode\":\"020626\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323582683775\",\"osm_id\":\"361709652\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"28.61298465\",\"lon\":\"77.22949432\",\"boundingbox\":[\"28.6128058\",\"28.6130584\",\"77.2293952\",\"77.2295867\"],\"class\":\"tourism\",\"type\":\"attraction\",\"display_name\":\"India Gate, Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"display_place\":\"India Gate\",\"display_address\":\"Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"address\":{\"name\":\"India Gate\",\"road\":\"Swami Vivekanada Road\",\"suburb\":\"Chanakya Puri Tehsil\",\"city\":\"New Delhi\",\"state\":\"Delhi\",\"postcode\":\"020626\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"324035376685\",\"osm_id\":\"743566214\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"1.30684265\",\"lon\":\"103.84927355\",\"boundingbox\":[\"1.3054061\",\"1.3082881\",\"103.8477125\",\"103.8506361\"],\"class\":\"railway\",\"type\":\"station\",\"display_name\":\"Little India, Mayne Road, Rochor, Singapore, Central Region, 229900, Singapore\",\"display_place\":\"Little India\",\"display_address\":\"Mayne Road, Rochor, Singapore, Central Region, 229900, Singapore\",\"address\":{\"name\":\"Little India\",\"road\":\"Mayne Road\",\"suburb\":\"Rochor\",\"city\":\"Singapore\",\"county\":\"Central Region\",\"postcode\":\"229900\",\"country\":\"Singapore\",\"country_code\":\"sg\"}},{\"place_id\":\"323926046646\",\"osm_id\":\"2511045451\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"1.3074571\",\"lon\":\"103.8525724\",\"boundingbox\":[\"1.2874571\",\"1.3274571\",\"103.8325724\",\"103.8725724\"],\"class\":\"place\",\"type\":\"suburb\",\"display_name\":\"Little India, Singapore, Central, 207309, Singapore\",\"display_place\":\"Little India\",\"display_address\":\"Singapore, Central, 207309, Singapore\",\"address\":{\"name\":\"Little India\",\"city\":\"Singapore\",\"county\":\"Central\",\"postcode\":\"207309\",\"country\":\"Singapore\",\"country_code\":\"sg\"}},{\"place_id\":\"320616009695\",\"osm_id\":\"2168597685\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9327876\",\"lon\":\"72.8369772\",\"boundingbox\":[\"18.9327376\",\"18.9328376\",\"72.8369272\",\"72.8370272\"],\"class\":\"amenity\",\"type\":\"bank\",\"display_name\":\"Reserve Bank of India, Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"display_place\":\"Reserve Bank of India\",\"display_address\":\"Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"address\":{\"name\":\"Reserve Bank of India\",\"road\":\"Shahid Bhagat Singh Marg\",\"neighbourhood\":\"Fort\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400038\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"320616009695\",\"osm_id\":\"42376828\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.93277125\",\"lon\":\"72.83708235\",\"boundingbox\":[\"18.9325358\",\"18.9330148\",\"72.8368066\",\"72.8373559\"],\"class\":\"building\",\"type\":\"yes\",\"display_name\":\"Reserve Bank of India, Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"display_place\":\"Reserve Bank of India\",\"display_address\":\"Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"address\":{\"name\":\"Reserve Bank of India\",\"road\":\"Shahid Bhagat Singh Marg\",\"neighbourhood\":\"Fort\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400038\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"320358209991\",\"osm_id\":\"6855148535\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"8.5386859\",\"lon\":\"77.0212491\",\"boundingbox\":[\"8.5386359\",\"8.5387359\",\"77.0211991\",\"77.0212991\"],\"class\":\"amenity\",\"type\":\"bank\",\"display_name\":\"State Bank of India, Vilappil Panchayath No.6\\/1396-1398, Manibhavan, Puliyarakonam, Thiruvananthapuram, Kattakkada, Kerala, 695573, India\",\"display_place\":\"State Bank of India\",\"display_address\":\"Vilappil Panchayath No.6\\/1396-1398, Manibhavan, Puliyarakonam, Thiruvananthapuram, Kattakkada, Kerala, 695573, India\",\"address\":{\"name\":\"State Bank of India\",\"house_number\":\"Vilappil Panchayath No.6\\/1396-1398, Manibhavan\",\"road\":\"Puliyarakonam\",\"city\":\"Thiruvananthapuram\",\"county\":\"Kattakkada\",\"state\":\"Kerala\",\"postcode\":\"695573\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323053658077\",\"osm_id\":\"6356858\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9225901\",\"lon\":\"72.83433537\",\"boundingbox\":[\"18.9216417\",\"18.9233973\",\"72.833402\",\"72.8349277\"],\"class\":\"tourism\",\"type\":\"attraction\",\"display_name\":\"Gateway of India, Chhatrapati Shivaji Maharaj Road, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, 400032, India\",\"display_place\":\"Gateway of India\",\"display_address\":\"Chhatrapati Shivaji Maharaj Road, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, 400032, India\",\"address\":{\"name\":\"Gateway of India\",\"road\":\"Chhatrapati Shivaji Maharaj Road\",\"neighbourhood\":\"Kala Ghoda\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400032\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323578586422\",\"osm_id\":\"6356858\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9225901\",\"lon\":\"72.83433537\",\"boundingbox\":[\"18.9216417\",\"18.9233973\",\"72.833402\",\"72.8349277\"],\"class\":\"highway\",\"type\":\"pedestrian\",\"display_name\":\"Gateway of India, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, India\",\"display_place\":\"Gateway of India\",\"display_address\":\"Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, India\",\"address\":{\"name\":\"Gateway of India\",\"neighbourhood\":\"Kala Ghoda\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"country\":\"India\",\"country_code\":\"in\"}}";

        ResponseEntity responseEntity = new ResponseEntity(str, HttpStatus.OK);

        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<String>>any()))
                .thenReturn(responseEntity);

        ResponseEntity res = mapService.getLocationMap("India", "json");
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), res.getStatusCodeValue());
    }

//    @Test
//    public void shouldNotGetLocationMapWithParseException(){
//
//        String str = "[{{{{{\"place_id\":\"322697972714\",\"osm_id\":\"304716\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"22.3511148\",\"lon\":\"78.6677428\",\"boundingbox\":[\"6.5531169\",\"35.6745457\",\"67.9544415\",\"97.395561\"],\"class\":\"place\",\"type\":\"country\",\"display_name\":\"India, India\",\"display_place\":\"India\",\"display_address\":\"in\",\"address\":{\"name\":\"India\",\"country\":\"India\",\"country_code\":\"in\"}}]";
//
////                ",{\"place_id\":\"323582683775\",\"osm_id\":\"361709652\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"28.61298465\",\"lon\":\"77.22949432\",\"boundingbox\":[\"28.6128058\",\"28.6130584\",\"77.2293952\",\"77.2295867\"],\"class\":\"historic\",\"type\":\"monument\",\"display_name\":\"India Gate, Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"display_place\":\"India Gate\",\"display_address\":\"Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"address\":{\"name\":\"India Gate\",\"road\":\"Swami Vivekanada Road\",\"suburb\":\"Chanakya Puri Tehsil\",\"city\":\"New Delhi\",\"state\":\"Delhi\",\"postcode\":\"020626\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323582683775\",\"osm_id\":\"361709652\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"28.61298465\",\"lon\":\"77.22949432\",\"boundingbox\":[\"28.6128058\",\"28.6130584\",\"77.2293952\",\"77.2295867\"],\"class\":\"tourism\",\"type\":\"attraction\",\"display_name\":\"India Gate, Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"display_place\":\"India Gate\",\"display_address\":\"Swami Vivekanada Road, Chanakya Puri Tehsil, New Delhi, Delhi, 020626, India\",\"address\":{\"name\":\"India Gate\",\"road\":\"Swami Vivekanada Road\",\"suburb\":\"Chanakya Puri Tehsil\",\"city\":\"New Delhi\",\"state\":\"Delhi\",\"postcode\":\"020626\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"324035376685\",\"osm_id\":\"743566214\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"1.30684265\",\"lon\":\"103.84927355\",\"boundingbox\":[\"1.3054061\",\"1.3082881\",\"103.8477125\",\"103.8506361\"],\"class\":\"railway\",\"type\":\"station\",\"display_name\":\"Little India, Mayne Road, Rochor, Singapore, Central Region, 229900, Singapore\",\"display_place\":\"Little India\",\"display_address\":\"Mayne Road, Rochor, Singapore, Central Region, 229900, Singapore\",\"address\":{\"name\":\"Little India\",\"road\":\"Mayne Road\",\"suburb\":\"Rochor\",\"city\":\"Singapore\",\"county\":\"Central Region\",\"postcode\":\"229900\",\"country\":\"Singapore\",\"country_code\":\"sg\"}},{\"place_id\":\"323926046646\",\"osm_id\":\"2511045451\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"1.3074571\",\"lon\":\"103.8525724\",\"boundingbox\":[\"1.2874571\",\"1.3274571\",\"103.8325724\",\"103.8725724\"],\"class\":\"place\",\"type\":\"suburb\",\"display_name\":\"Little India, Singapore, Central, 207309, Singapore\",\"display_place\":\"Little India\",\"display_address\":\"Singapore, Central, 207309, Singapore\",\"address\":{\"name\":\"Little India\",\"city\":\"Singapore\",\"county\":\"Central\",\"postcode\":\"207309\",\"country\":\"Singapore\",\"country_code\":\"sg\"}},{\"place_id\":\"320616009695\",\"osm_id\":\"2168597685\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9327876\",\"lon\":\"72.8369772\",\"boundingbox\":[\"18.9327376\",\"18.9328376\",\"72.8369272\",\"72.8370272\"],\"class\":\"amenity\",\"type\":\"bank\",\"display_name\":\"Reserve Bank of India, Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"display_place\":\"Reserve Bank of India\",\"display_address\":\"Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"address\":{\"name\":\"Reserve Bank of India\",\"road\":\"Shahid Bhagat Singh Marg\",\"neighbourhood\":\"Fort\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400038\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"320616009695\",\"osm_id\":\"42376828\",\"osm_type\":\"way\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.93277125\",\"lon\":\"72.83708235\",\"boundingbox\":[\"18.9325358\",\"18.9330148\",\"72.8368066\",\"72.8373559\"],\"class\":\"building\",\"type\":\"yes\",\"display_name\":\"Reserve Bank of India, Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"display_place\":\"Reserve Bank of India\",\"display_address\":\"Shahid Bhagat Singh Marg, Fort, A Ward, Mumbai, Mumbai City, Maharashtra, 400038, India\",\"address\":{\"name\":\"Reserve Bank of India\",\"road\":\"Shahid Bhagat Singh Marg\",\"neighbourhood\":\"Fort\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400038\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"320358209991\",\"osm_id\":\"6855148535\",\"osm_type\":\"node\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"8.5386859\",\"lon\":\"77.0212491\",\"boundingbox\":[\"8.5386359\",\"8.5387359\",\"77.0211991\",\"77.0212991\"],\"class\":\"amenity\",\"type\":\"bank\",\"display_name\":\"State Bank of India, Vilappil Panchayath No.6\\/1396-1398, Manibhavan, Puliyarakonam, Thiruvananthapuram, Kattakkada, Kerala, 695573, India\",\"display_place\":\"State Bank of India\",\"display_address\":\"Vilappil Panchayath No.6\\/1396-1398, Manibhavan, Puliyarakonam, Thiruvananthapuram, Kattakkada, Kerala, 695573, India\",\"address\":{\"name\":\"State Bank of India\",\"house_number\":\"Vilappil Panchayath No.6\\/1396-1398, Manibhavan\",\"road\":\"Puliyarakonam\",\"city\":\"Thiruvananthapuram\",\"county\":\"Kattakkada\",\"state\":\"Kerala\",\"postcode\":\"695573\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323053658077\",\"osm_id\":\"6356858\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9225901\",\"lon\":\"72.83433537\",\"boundingbox\":[\"18.9216417\",\"18.9233973\",\"72.833402\",\"72.8349277\"],\"class\":\"tourism\",\"type\":\"attraction\",\"display_name\":\"Gateway of India, Chhatrapati Shivaji Maharaj Road, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, 400032, India\",\"display_place\":\"Gateway of India\",\"display_address\":\"Chhatrapati Shivaji Maharaj Road, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, 400032, India\",\"address\":{\"name\":\"Gateway of India\",\"road\":\"Chhatrapati Shivaji Maharaj Road\",\"neighbourhood\":\"Kala Ghoda\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"postcode\":\"400032\",\"country\":\"India\",\"country_code\":\"in\"}},{\"place_id\":\"323578586422\",\"osm_id\":\"6356858\",\"osm_type\":\"relation\",\"licence\":\"https:\\/\\/locationiq.com\\/attribution\",\"lat\":\"18.9225901\",\"lon\":\"72.83433537\",\"boundingbox\":[\"18.9216417\",\"18.9233973\",\"72.833402\",\"72.8349277\"],\"class\":\"highway\",\"type\":\"pedestrian\",\"display_name\":\"Gateway of India, Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, India\",\"display_place\":\"Gateway of India\",\"display_address\":\"Kala Ghoda, A Ward, Mumbai, Mumbai City, Maharashtra, India\",\"address\":{\"name\":\"Gateway of India\",\"neighbourhood\":\"Kala Ghoda\",\"suburb\":\"A Ward\",\"city\":\"Mumbai\",\"county\":\"Mumbai City\",\"state\":\"Maharashtra\",\"country\":\"India\",\"country_code\":\"in\"}}";
//
//        ResponseEntity responseEntity = new ResponseEntity(str, HttpStatus.OK);
//
//        when(restTemplate.exchange(
//                ArgumentMatchers.anyString(),
//                ArgumentMatchers.any(HttpMethod.class),
//                ArgumentMatchers.any(),
//                ArgumentMatchers.<Class<String>>any()))
//                .thenReturn(responseEntity);
//
//        ParseException ex = Assertions.assertThrows(ParseException.class, () -> {
//            mapService.getLocationMap("India", "json");
//        });
//        Assertions.assertEquals("US states only", ex.getMessage(), "Exception not thrown when entered a non-US state");
//    }
}
