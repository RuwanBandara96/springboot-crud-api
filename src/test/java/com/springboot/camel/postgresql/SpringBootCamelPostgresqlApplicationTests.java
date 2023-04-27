package com.springboot.camel.postgresql;


import com.springboot.camel.postgresql.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCamelPostgresqlApplicationTests {
	@Test
	void contextLoads() {
	}
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TestRestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();
	//headers.setContentType(MediaType.APPLICATION_JSON);

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}
	@Test
	public void getSampleDataTest() {
		// Call the REST API
		ResponseEntity<String> response = restTemplate.getForEntity("/rest/data/sampleapi", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String s = response.getBody();
		assertThat(s.equals("Successfully"));
	}
	@Test
	public void getDataTest() {
		// Call the REST API
		ResponseEntity<String> response = restTemplate.getForEntity("/rest/data", String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		String s = response.getBody();
		assertThat(s.equals("Successfully"));
	}
	@Test
	public void getDataByIdTest() {
		// Call the REST API
		String url = "http://localhost:8080/rest/data/{eventId}";
		Map< String, Integer > params = new HashMap< String, Integer >();
		params.put("eventId", 1);
		Event response = restTemplate.getForObject(url, Event.class,params);
		System.out.println(response);
	}
	@Test
	public void updateData() {
		String url = "http://localhost:8080/rest/data/{eventId}";
		Map < String, String > params = new HashMap < String, String > ();
		params.put("eventId", "1");
		Event updatedobj = new Event(1,"0000abf8-d1f5-4536-8fb0-36fe934b1f29", "20161022102011927EDT", "RPS-00001","RPS-00001", 3, "DESTINATION","T8C", "1J7", "0000000001");
		//RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, updatedobj, params);
	}
	@Test
	public void postDataTest() {
		// Call the REST API
		Event obj = new Event();
		obj.setTransId("0000abf8-d1f5-4536-8fb0-36fe934b1f28");
		obj.setTransTms("20151022102011927EDT");
		obj.setClientId("RPS-00001");
		obj.setLocationId1("T8C");
		obj.setLocationId2("1J7");
		obj.setLocationCd("DESTINATION");
		obj.setRcNum("10002");
		obj.setEventCnt(1);
		obj.setAddrNbr("0000000001");



		HttpEntity<Event> requestEntity = new HttpEntity<>(obj, headers);
		ResponseEntity<Event> response = restTemplate.postForEntity("/rest/data",requestEntity, Event.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("TransId: " + response.getBody().getTransId());
		System.out.println("TransTms: " + response.getBody().getTransTms());
		System.out.println("RcNum: " + response.getBody().getRcNum());
		System.out.println("ClientId: " + response.getBody().getClientId());
		System.out.println("EventCnt: " + response.getBody().getEventCnt());
		System.out.println("LocationCd: " + response.getBody().getLocationCd());
		System.out.println("LocationId1: " + response.getBody().getLocationId1());
		System.out.println("LocationId2: " + response.getBody().getLocationId2());

		System.out.println("addrNbr: " + response.getBody().getAddrNbr());


	}
	@Test
	public void DeleteTest() {
		// Call the REST API

		String url = "http://localhost:8080/rest/data/{eventId}";

		Map<String,String> map = new HashMap<>();
		map.put("eventId","1" );
		restTemplate.delete(url, map);


	}

}
