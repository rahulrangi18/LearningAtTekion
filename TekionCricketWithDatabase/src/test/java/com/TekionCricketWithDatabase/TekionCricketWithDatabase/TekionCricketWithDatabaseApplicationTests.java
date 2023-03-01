package com.TekionCricketWithDatabase.TekionCricketWithDatabase;


import com.TekionCricketWithDatabase.TekionCricketWithDatabase.controllers.MatchController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TekionCricketWithDatabaseApplicationTests {


	@Autowired
	private MatchController matchController;

	@Test
	void contextLoads() {
		assertNotNull(matchController);
	}

	//The @LocalServerPort annotation is used to get the port number of the embedded web server that is started when the test is run.
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
    //The testEndpoint() method sends a GET request to the /play-match endpoint using the TestRestTemplate and verifies that the response status code is 200 OK using the assertEquals() assertion.
	@Test
	public void testEndpoint() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/play-match", String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
