package com.example.ws.controller;

import com.example.ws.dao.UserRepository;
import com.example.ws.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

import java.math.BigInteger;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@SpringBootTest
class UserEndpointTest {


    private static final String GET_ALL_USERS_REQUEST =
            "      <getAllUsersRequest xmlns=\"http://spring.io/guides/gs-producing-web-service\"> \n" +
            "      </getAllUsersRequest>\n";

    private static final String GET_USER_BY_ID_REQUEST =
                    "<getUserByIdRequest xmlns=\"http://spring.io/guides/gs-producing-web-service\"> \n" +
                    "   <id>%d</id> \n" +
                    "</getUserByIdRequest>\n";

    private static final String GET_ALL_USERS_EXPECTED_RESPONSE =
            "        <ns2:getAllUsersResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\">\n" +
            "            <ns2:users>\n" +
            "                <ns2:id>1</ns2:id>\n" +
            "                <ns2:name>George</ns2:name>\n" +
            "                <ns2:orders>\n" +
            "                    <ns2:id>1</ns2:id>\n" +
            "                    <ns2:status>CREATED</ns2:status>\n" +
            "                    <ns2:favorId>1</ns2:favorId>\n" +
            "                    <ns2:orderedById>1</ns2:orderedById>\n" +
            "                </ns2:orders>\n" +
            "            </ns2:users>\n" +
            "        </ns2:getAllUsersResponse>\n";

    private static final String GET_USER_BY_VALID_ID_RESPONSE =
            "<ns2:getUserResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\">\n" +
            "            <ns2:user>\n" +
            "                <ns2:id>1</ns2:id>\n" +
            "                <ns2:name>George</ns2:name>\n" +
            "                <ns2:orders>\n" +
            "                    <ns2:id>1</ns2:id>\n" +
            "                    <ns2:status>CREATED</ns2:status>\n" +
            "                    <ns2:favorId>1</ns2:favorId>\n" +
            "                    <ns2:orderedById>1</ns2:orderedById>\n" +
            "                </ns2:orders>\n" +
            "            </ns2:user>\n" +
            "</ns2:getUserResponse>";

    private MockWebServiceClient mockClient;

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void prepareClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testGetAllUsers() {

        var users = List.of(TestUtils.getExpectedUser());

        when(userRepository.getAllUsers())
                .thenReturn(users);

        var requestPayload = new StringSource(GET_ALL_USERS_REQUEST);

        var responsePayload = new StringSource(GET_ALL_USERS_EXPECTED_RESPONSE);

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(payload(responsePayload));
    }

    @Test
    public void testGetUserById_WhenGivenExistentUserById_ThenReturnUserDetails() {
        final BigInteger userId = BigInteger.ONE;

        var user = TestUtils.getExpectedUser();

        when(userRepository.getUserById(userId))
                .thenReturn(user);

        var requestPayload = new StringSource(String.format(GET_USER_BY_ID_REQUEST, userId));

        var responsePayload = new StringSource(GET_USER_BY_VALID_ID_RESPONSE);

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(payload(responsePayload));
    }

    @Test
    public void testGetUserById_WhenGivenNotExistentUserById_ThenReturnFault() {

        when(userRepository.getUserById(any()))
                .thenThrow(RuntimeException.class);

        var requestPayload = new StringSource(String.format(GET_USER_BY_ID_REQUEST, 5));

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(ResponseMatchers.serverOrReceiverFault());
    }


}