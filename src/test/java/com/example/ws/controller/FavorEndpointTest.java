package com.example.ws.controller;

import com.example.ws.dao.FavorRepository;
import com.example.ws.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@SpringBootTest
public class FavorEndpointTest {

    private static final String GET_ALL_FAVORS_REQUEST =
            "<getAllFavorsRequest xmlns=\"http://spring.io/guides/gs-producing-web-service\"> \n" +
            "</getAllFavorsRequest>\n";

    private static final String GET_ALL_FAVORS_RESPONSE =
            "<ns2:getFavorsResponse xmlns:ns2=\"http://spring.io/guides/gs-producing-web-service\">\n" +
            "            <ns2:favors>\n" +
            "                <ns2:id>1</ns2:id>\n" +
            "                <ns2:description>Description</ns2:description>\n" +
            "            </ns2:favors>\n" +
            "</ns2:getFavorsResponse>";

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private FavorRepository favorRepository;

    private MockWebServiceClient mockClient;

    @BeforeEach
    public void initClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void testGetAllFavors() {

        var favors = List.of(TestUtils.getExpectedFavor());

        when(favorRepository.getAllFavors())
                .thenReturn(favors);

        var requestPayload = new StringSource(GET_ALL_FAVORS_REQUEST);

        var responsePayload = new StringSource(GET_ALL_FAVORS_RESPONSE);

        mockClient
                .sendRequest(withPayload(requestPayload))
                .andExpect(payload(responsePayload));
    }

}
