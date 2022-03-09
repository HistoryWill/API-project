package com.cognixia.jump.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.SignupRequest;
import com.cognixia.jump.model.User;
import com.cognixia.jump.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthenticationController.class)

public class AuthenticationControllerTest {

	private final String STARTING_URI = "http://localhost:8080/api";

	// mocks the http request/response
	@Autowired
	private MockMvc mvc;

	// when methods from the service are called within the controller
	// we want to decide what data gets returned from those methods instead of
	// actually
	// running that method
	@MockBean
	private UserDetailsServiceImpl service;

	// when controller tries to autowire the service, mock the creation of the
	// service,
	// don't actually create a proper service object (mock service object created
	// instead)
	@InjectMocks
	private AuthenticationController controller;
	
	


	// test for when we do a Account by its id
	/*@Test
	void loadUserByUsername() throws Exception {

		String id ="admin";
		String uri = STARTING_URI + "/auth/authenticate";

		// the Account that will be returned in response to our request
	

		when(service.loadUserByUsername(id)).thenReturn(null);

		mvc.perform(get(uri, id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // check if data returned is json
				.andExpect(jsonPath("$.id").value(Account.getId())).andExpect(jsonPath("$.type").value(Account.getType()))
				.andExpect(jsonPath("$.miles").value(Account.getMiles()));

		verify(service, times(1)).getAccountById(id);
		verifyNoMoreInteractions(service);

	}*/

	// test for when a Account is not found
	/*@Test
	void testUserNameNotFound() throws Exception {

		String id ="admin";
		String uri = STARTING_URI + "/Account/{id}";

		when(service.loadUserByUsername(id)).thenThrow(new ResourceNotFoundException("Account with id = " + id + " not found"));

		mvc.perform(get(uri, id)).andDo(print()).andExpect(status().isNotFound()); // 404 status code

		verify(service, times(1)).getAccountById(id);
		verifyNoMoreInteractions(service);
	}
*/
	@Test
	void testCreateUser() throws Exception {

		String uri = STARTING_URI + "/auth/signup";

		SignupRequest user = new SignupRequest("Admin", "doug");

		// Mockito.any() -> have an object of the type specified, doesn't matter what
		// that object is, just expect an object to be passed
		when(controller.RegisterUser(Mockito.any(SignupRequest.class)));

		// need to convert and send Account object in json format
		mvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(user))).andDo(print())
				.andExpect(content().contentType(MediaType.TEXT_EVENT_STREAM_VALUE));

	}
/*
	@Test
	void testDeleteAccount() throws Exception {
		long id = 1;
		String uri = STARTING_URI + "/delete/Account/{id}";

		Account Account = new Account(3L, "Gofastion", 2000);

		// Mockito.any() -> have an object of the type specified, doesn't matter what
		// that object is, just expect an object to be passed
		when(service.deleteAccount(Mockito.any(long.class))).thenReturn(Account);

		// need to convert and send Account object in json format
		mvc.perform(delete(uri, id)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) // check if data returned is json
				.andExpect(jsonPath("$.id").value(Account.getId())).andExpect(jsonPath("$.type").value(Account.getType()))
				.andExpect(jsonPath("$.miles").value(Account.getMiles()));

	}

	@Test
	void testUpdateAccount() throws Exception {
		String uri = STARTING_URI + "/update/Account/";

		Account updAccount = new Account(1L, "BigJohn", 1000);

		when(service.updateAccount(Mockito.any(Account.class))).thenReturn(updAccount);

		mvc.perform(put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(asJsonString(updAccount))).andDo(print())

				.andExpect(jsonPath("$.id").value(updAccount.getId())).andExpect(jsonPath("$.type").value(updAccount.getType()))
				.andExpect(jsonPath("$.miles").value(updAccount.getMiles()))

		;

	}
*/
	// Finish setting up testing for the rest of the controller file
	// 1. test for the status code, return type, and content (if applies)
	// 2. testing for any ResourceNotFound throws
	// 3. do verify checks

	public static String asJsonString(final Object obj) {

		try {
			return new ObjectMapper().writeValueAsString(obj);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
