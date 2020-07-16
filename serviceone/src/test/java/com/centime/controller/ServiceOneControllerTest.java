package com.centime.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.centime.client.ClientService;
import com.centime.exception.CentimeException;
import com.centime.modal.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author tarun
 *
 */
@SpringBootTest
public class ServiceOneControllerTest {

	@Mock
	private ClientService clientService;

	@InjectMocks
	private ServiceOneController serviceOneController;

	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();

	private User user;

	private final static String POSY_URL = "/serviceone/concatenate";
	private final static String GET_URL = "/serviceone/check";

	@BeforeEach
	public void setup() {
		user = new User();
		user.setName("dummyName");
		user.setSurName("dummySurName");
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(serviceOneController).setControllerAdvice(new ControllerAdvice()).build();
	}
	
	@Test
	public void checkTest() throws Exception {
		mockMvc.perform(get(GET_URL))
				.andExpect(status().isOk())
				.andExpect(content().string("Up"))
				.andDo(print());
	}

	@Test
	public void concatenateResponseSuccessTest() throws Exception {
		when(clientService.callServiceTwo()).thenReturn("Hello");
		when(clientService.callServiceThree((User) any())).thenReturn("dummyName dummySurName");
		mockMvc.perform(post(POSY_URL).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello dummyName dummySurName"))
				.andDo(print());
	}
	
	@Test
	public void concatenateResponseFBadRequestTest() throws Exception {
		user.setName("");
		mockMvc.perform(post(POSY_URL).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().isBadRequest())
				.andDo(print());
	}
	
	@Test
	public void concatenateResponseInternalServerErrorTest() throws Exception {
		when(clientService.callServiceTwo()).thenThrow(new CentimeException(new RuntimeException("failure"), "Something went wrong"));
		System.out.println(mockMvc.perform(post(POSY_URL).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
				.andExpect(status().isInternalServerError())
				.andDo(print()).andReturn().getResponse().getContentAsString());
	}

}