package edu.sjsu.projectcloud.db;

import edu.sjsu.projectcloud.HelloApp;
import edu.sjsu.projectcloud.project.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by mallika on 5/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HelloApp.class)
public class ResourceAccessTest {

    @Test
    public void testAddProject() {
        ResourceAccess resourceAccess = new ResourceAccess();
        Project project = new Project("SCRUM", "P1", "05/01/2015", "05/31/2015", "m");
        try {
            resourceAccess.updateResourceAddProject("m", project);
        } catch (NullMongoTemplateException nmte) {
            System.out.println("Oops");
        }
    }

}