package com.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.service.user.adapter.web.UserRestController;
import com.service.user.domain.entity.Email;
import com.service.user.domain.entity.PhoneNumber;
import com.service.user.domain.entity.User;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class UserServiceApplicationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void contextLoads() {}

  @Test
  void testShouldSaveUserWithContactsGiveValidUserAndContacts() throws Exception {
    String json =
        "{\"id\":200,\"firstName\":\"mozammal\",\"lastName\":\"hossain\",\"emails\":[{\"mail\":\"mozammaltomal.100@gmail.com\"},{\"mail\":\"mozammaltomal_1001@yahoo.com\"}],\"phoneNumbers\":[{\"number\":\"01753193627\"}]}";

    ObjectMapper objectMapper = new ObjectMapper();
    User user = objectMapper.readValue(json, User.class);
    MvcResult mvcResult =
        mockMvc
            .perform(
                MockMvcRequestBuilders.post("/api/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andReturn();
    Integer id = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
    String firstName = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.firstName");
    String lastName = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.lastName");
    List<Email> emails = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.emails");
    List<PhoneNumber> phoneNumbers =
        JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.phoneNumbers");

    assertThat(id).isEqualTo(user.getId());
    assertThat(firstName).isEqualTo(user.getFirstName());
    assertThat(lastName).isEqualTo(user.getLastName());
    assertThat(emails.size()).isEqualTo(user.getEmails().size());
    assertThat(phoneNumbers.size()).isEqualTo(user.getPhoneNumbers().size());
  }
}
