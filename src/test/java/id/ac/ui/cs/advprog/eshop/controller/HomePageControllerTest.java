package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class HomePageControllerTest {

    private MockMvc mockMvc;
    private HomePageController homePageController;

    @BeforeEach
    void setUp() {
        homePageController = new HomePageController();
        mockMvc = MockMvcBuilders.standaloneSetup(homePageController).build();
    }

    @Test
    void testHomePageMethod() {
        String viewName = homePageController.homePage();
        assertEquals("homePage", viewName);
    }

    @Test
    void testHomePageRequest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("homePage"));
    }
}
