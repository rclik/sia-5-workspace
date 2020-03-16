package com.rcelik.siafive.chapterone;

import com.rcelik.siafive.chapterone.home.HomeController;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
class ChapterOneApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public final void testHomeController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.view()
                        .name("home"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.containsString("Welcome to...")));
    }

}
