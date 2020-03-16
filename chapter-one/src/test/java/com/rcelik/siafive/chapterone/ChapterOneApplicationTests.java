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

// means this class is added to spring application context and run in there
@RunWith(SpringRunner.class)
// means this is web mvc test for HomeController class and MockMvc autowired object type is HomeController
@WebMvcTest(HomeController.class)
class ChapterOneApplicationTests {

    @Autowired
    private MockMvc mockMvc; // mock of HomeController class

    @Test
    public final void testHomeController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")) // perform http get request to the / path
                .andExpect(MockMvcResultMatchers.status()
                        .isOk()) // expect status code is 200 OK
                .andExpect(MockMvcResultMatchers.view()
                        .name("home")) // view name is home. means we are inside SAC
                .andExpect(MockMvcResultMatchers.content()
                        .string(CoreMatchers.containsString("Welcome to..."))); // content has ...
    }

}
