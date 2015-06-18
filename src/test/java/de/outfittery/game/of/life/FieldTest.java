package de.outfittery.game.of.life;

import de.outfittery.game.of.life.entity.Field;
import de.outfittery.game.of.life.factory.FieldFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/rest-servlet.xml")
public class FieldTest {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {

    }

    @Test
    public void testUnderPopulationRule() throws Exception {

        Field oldGeneration = FieldFactory.createNewField(5,5);
        oldGeneration.setAliveState(2, 1, true);
        oldGeneration.setAliveState(2, 2, true);
        Field newGeneration = oldGeneration.computeNextGeneration();
        Assert.assertFalse(newGeneration.getAliveStates()[2][1]);
        Assert.assertFalse(newGeneration.getAliveStates()[2][2]);
    }

    @Test
    public void testNeighbourRule() throws Exception {

        Field oldGeneration = FieldFactory.createNewField(5,5);
        oldGeneration.setAliveState(2, 1, true);
        oldGeneration.setAliveState(2, 2, true);
        oldGeneration.setAliveState(2, 3, true);
        Field newGeneration = oldGeneration.computeNextGeneration();
        Assert.assertFalse(newGeneration.getAliveStates()[2][1]);
        Assert.assertFalse(newGeneration.getAliveStates()[2][2]);
    }
}
