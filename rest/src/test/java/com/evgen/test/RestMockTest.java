package com.evgen.test;

import com.evgen.Company;
import com.evgen.Phone;
import com.evgen.config.RestMockTestConfig;
import com.evgen.service.CompanyPhoneService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.replay;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestMockTestConfig.class)
@WebAppConfiguration
public class RestMockTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private CompanyPhoneService companyPhoneService;

    @After
    public void tearDown() {
        verify(companyPhoneService);
        reset(companyPhoneService);
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addPhoneTest() throws Exception {
        expect(companyPhoneService.addPhone(anyObject(Phone.class))).andReturn(8);
        replay(companyPhoneService);

        String phone = new ObjectMapper().writeValueAsString(new Phone("Iphone 3g", 10, new Company(1, "Apple")));
        mockMvc.perform(
                post("/phones")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(phone)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":8}"));
    }

    @Test
    public void getPhoneByIdTest() throws Exception {
        Phone phone = new Phone(1, "Iphone 3g", 10, new Company(1,"Apple"));

        expect(companyPhoneService.getPhoneById(anyInt())).andReturn(phone);
        replay(companyPhoneService);

        mockMvc.perform(
                get("/phones/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addCompanyTest() throws Exception {
        expect(companyPhoneService.addCompany(anyObject(Company.class))).andReturn(6);
        replay(companyPhoneService);

        String company = new ObjectMapper().writeValueAsString(new Company("IBM", 300));
        mockMvc.perform(
                post("/companies")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(company)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":6}"));
    }

    @Test
    public void getCompanyByIdTest() throws Exception {
        Company company = new Company(1, "IBM", 100);

        expect(companyPhoneService.getCompanyById(anyInt())).andReturn(company);
        replay(companyPhoneService);

        mockMvc.perform(
                get("/companies/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
