package com.sample.users.controller;

import com.sample.users.model.User;
import com.sample.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final int USER_ID = 42;
    private static final String USER_NAME = "Arthur Dent";
    private static final String USER_EMAIL = "arthur@dent.com";
    private static final String USER_PASSWORD = "arthur.dent";
    private static final BigDecimal USER_WALLET = BigDecimal.valueOf(79);
    private static final String USER_TOKEN = "OSNTfwVVFN1hc1p3d5KnSYg7Hsh1s7E8";

    private User mockUser = new User(USER_ID, USER_NAME, USER_EMAIL, USER_PASSWORD, USER_TOKEN, USER_WALLET);

    private String exampleUser = "{\"name\":\"" + USER_NAME + "\",\"email\":\"" + USER_EMAIL + "\",\"password\":\"" + USER_PASSWORD + "\"}";
    private String exampleLogin = "{\"email\":\"" + USER_EMAIL + "\",\"password\":\"" + USER_PASSWORD + "\"}";

    @Test
    public void retrieveMe() throws Exception {

        when(userService.getUserByToken(USER_TOKEN)).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/me")
                .header("Authorization", USER_TOKEN)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{id:" + USER_ID + ",name:\"" + USER_NAME + "\",email:\"" + USER_EMAIL + "\"}";
        System.out.println(expected);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void retrieveUser() throws Exception {

        when(userService.getUser(String.valueOf(USER_ID))).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/" + USER_ID)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{id:" + USER_ID + ",name:\"" + USER_NAME + "\",email:\"" + USER_EMAIL + "\"}";
        System.out.println(expected);

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void retrieveWalletUser() throws Exception {

        when(userService.getUserByToken(USER_TOKEN)).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/me/wallet")
                .header("Authorization", USER_TOKEN);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(USER_WALLET.toString(), result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveAddToWalletUser() throws Exception {

        when(userService.addToWallet(USER_TOKEN, BigDecimal.valueOf(4))).thenReturn(BigDecimal.valueOf(83).setScale(2, BigDecimal.ROUND_HALF_UP));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/me/wallet/add/4")
                .header("Authorization", USER_TOKEN);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(
                BigDecimal.valueOf(83).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void retrieveRemoveFromWalletUser() throws Exception {

        when(userService.removeFromWallet(USER_TOKEN, BigDecimal.valueOf(4))).thenReturn(BigDecimal.valueOf(75).setScale(2, BigDecimal.ROUND_HALF_UP));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/me/wallet/remove/4")
                .header("Authorization", USER_TOKEN);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(
                BigDecimal.valueOf(75).setScale(2, BigDecimal.ROUND_HALF_UP).toString(),
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void createUser() throws Exception {
        when(userService.addUser(any(User.class))).thenReturn(USER_TOKEN);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/signup")
                .content(exampleUser)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(
                USER_TOKEN,
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void login() throws Exception {
        when(userService.getUserByEmailAndPassword(USER_EMAIL, USER_PASSWORD)).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .content(exampleLogin)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(
                USER_TOKEN,
                result.getResponse().getContentAsString()
        );
    }

    @Test
    public void login_noEmail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .content("\"password\":\"" + USER_PASSWORD + "\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void login_noPassword() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .content("\"email\":\"" + USER_EMAIL + "\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void login_noPasswordAndEmail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .content("")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

}