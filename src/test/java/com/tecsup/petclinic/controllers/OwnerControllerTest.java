package com.tecsup.petclinic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.entities.OwnerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOwers() throws  Exception{
        int ID_FIRST_RECORD = 1;
        this.mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(ID_FIRST_RECORD));
    }

    @Test
    public void testFindOwnerOK() throws Exception{
        int ID = 1;
        String FIRST_NAME= "George";
        String LAST_NAME= "Franklin";
        String ADDRESS= "110 W. Liberty St.";
        String CITY= "Madison";
        String TELEPHONE= "6085551023";

        this.mockMvc.perform(get("/owners/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.id").value(ID))
                .andExpect( jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect( jsonPath("$.lastName").value(LAST_NAME))
                .andExpect( jsonPath("$.address").value(ADDRESS))
                .andExpect( jsonPath("$.city").value(CITY))
                .andExpect( jsonPath("$.telephone").value(TELEPHONE));
    }

    @Test
    public void testFindOwnerKO() throws Exception {
        this.mockMvc.perform(get("/owners/134"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testCreateOwnerOK() throws Exception{
        String FIRST_NAME= "Sandy";
        String LAST_NAME= "Leiva";
        String ADDRESS= "Alfornzo Ugarte 549";
        String CITY= "Ascope";
        String TELEPHONE= "929742698";
        OwnerDTO newOwner = new OwnerDTO(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);
        //logger.info(newOwner.toString());
        //logger.info(om.writeValueAsString(newOwner));
        this.mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwner))
                        .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect( jsonPath("$.lastName").value(LAST_NAME))
                .andExpect( jsonPath("$.address").value(ADDRESS))
                .andExpect( jsonPath("$.city").value(CITY))
                .andExpect( jsonPath("$.telephone").value(TELEPHONE));
    }
    @Test
    public void testDeleteOwner() throws Exception{
        String FIRST_NAME= "Sandy";
        String LAST_NAME= "Leiva";
        String ADDRESS= "Alfornzo Ugarte 549";
        String CITY= "Ascope";
        String TELEPHONE= "929742698";
        OwnerDTO newOwner = new OwnerDTO(FIRST_NAME,LAST_NAME,ADDRESS,CITY,TELEPHONE);

        ResultActions mvcActions = mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwner))
                        .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");
        mockMvc.perform(delete("/owners/"+id))
                //   .andDo(print())
                .andExpect(status().isOk());
    }
}
