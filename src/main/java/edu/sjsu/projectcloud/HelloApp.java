package edu.sjsu.projectcloud;

import edu.sjsu.projectcloud.dataAccess.DAO;
import edu.sjsu.projectcloud.dataAccess.DAOFactory;
import edu.sjsu.projectcloud.db.ProjectAccess;
import edu.sjsu.projectcloud.db.ResourceAccess;
import edu.sjsu.projectcloud.project.Project;
import edu.sjsu.projectcloud.resource.Resource;
import edu.sjsu.projectcloud.task.Task;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EnableAutoConfiguration
@Controller
@RequestMapping("/cmpe281project")
public class HelloApp {


    public static void main(String... args) {
        SpringApplication.run(HelloApp.class);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getUserRegistrationPage(Model model) {
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        return "register";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String createResource(@ModelAttribute Resource resource, Model model) {
        ResourceAccess resourceAccess = new ResourceAccess();
        resourceAccess.insertResource(resource);
        String username = resource.getUsername();
        Project project = new Project();
        model.addAttribute("project", project);
        model.addAttribute("username", resource.getUsername());
        return "preferences";
    }

    private void getAllResources(ResourceAccess resourceAccess) {
        List<Resource> resources = resourceAccess.getAllResources();
        for (Resource r : resources) {
            System.out.println(r.toString());
        }
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public String createProject(@ModelAttribute Project project, @ModelAttribute String username, Model model) {
        String type = project.getProjecttype();
        ProjectAccess projectAccess = new ProjectAccess();
        projectAccess.insertProject(project);
        Resource resource = new Resource("s", "s");
        projectAccess.updateProjectAddResource(resource, project.getProjectname());

        Task task = new Task("planned", "task1", "dummy task");
        projectAccess.updateProjectAddTask(task, project.getProjectname());

        DAO dao = this.getDAO(type);
        Task task1 = dao.getTask();
        model.addAttribute("task", task1);
        return task.getClass().getSimpleName();
    }

    private DAO getDAO(String type) {
        return DAOFactory.getInstance(type);
    }



}


/*


//for success or failure on login

    @RequestMapping(value = "/loginresult", method = RequestMethod.POST)

    public ModelAndView giveLoginResult (@RequestParam("username") String username,

                                         @RequestParam("password") String password)

    {

        ModelAndView mv = new ModelAndView();


        MongoDBConnection authObj = new MongoDBConnection();

        if(authObj.mongoAuthentication(username, password))

        {


            mv.addObject("message", "Successful Authentication");

            mv.setViewName("success");


        }

        else

        {


            mv.addObject("message", "Failed Authentication");

            mv.setViewName("success");

        }


        return mv;

    }




    @RequestMapping(value = "/login", method = RequestMethod.GET)

    public ModelAndView giveLogin()

    {

        ModelAndView mv = new ModelAndView("login");

        return mv;

    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)

    public ModelAndView showregister()

    {

        ModelAndView mv = new ModelAndView("register");

        return mv;

    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)

    public ModelAndView registerUser (@RequestParam("username") String username,

                                      @RequestParam("password") String password)

    {

        ModelAndView mv = new ModelAndView();


        User user = new User(username, password);


        MongoDBConnection authObj = new MongoDBConnection();

        authObj.addUser(user);

        mv.addObject("message", "Successful Registration");

        mv.setViewName("success");

        return mv;

    }

}


Click here to Reply or Forward
}

*/
