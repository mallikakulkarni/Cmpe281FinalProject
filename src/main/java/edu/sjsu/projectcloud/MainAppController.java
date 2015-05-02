package edu.sjsu.projectcloud;

import edu.sjsu.projectcloud.project.Project;
import edu.sjsu.projectcloud.session.UserSessionInfo;
import edu.sjsu.projectcloud.sprint.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/cmpe281project")
public class MainAppController {
    @Autowired
    UserSessionInfo userSessionInfo;

    @RequestMapping(value = "/project/{PID}/status", method = RequestMethod.GET)
    public String viewProjectStatus(@PathVariable("PID") String PID, Model model) {
        Project p = new Project();
        p.setId(PID);
        model.addAttribute("pagetype", "Display Project Status Page");
        model.addAttribute("project", p);
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }

    @RequestMapping(value = "/project/{PID}/taskList", method = RequestMethod.GET)
    public String viewTaskList(@PathVariable("PID") String PID, Model model) {
        Project p = new Project();
        p.setId(PID);
        model.addAttribute("project", p);
        model.addAttribute("pagetype", "Display Project Task List Page");
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }

    @RequestMapping(value = "/project/{PID}/sprintList", method = RequestMethod.GET)
    public String viewSprintList(@PathVariable("PID") String PID, Model model) {
        Project p = new Project();
        p.setId(PID);
        model.addAttribute("project", p);
        model.addAttribute("pagetype", "Display Sprint List Page");
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }

    @RequestMapping(value = "/project/{PID}/sprint/{SID}", method = RequestMethod.GET)
    public String viewSprint(@PathVariable("PID") String PID, @PathVariable("SID") String SID, Model model) {
        Project p = new Project();
        p.setId(PID);

        Sprint s = new Sprint();
        s.setId(SID);
        model.addAttribute("project", p);
        model.addAttribute("sprint", s);
        model.addAttribute("pagetype", "Display Sprint for given Project Page");
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }

    @RequestMapping(value = "/project/{PID}", method = RequestMethod.GET)
    public String viewProjectPage(@PathVariable("PID") String PID, Model model) {
        Project p = new Project();
        p.setId(PID);
        model.addAttribute("project", p);
        model.addAttribute("pagetype", "Display Project Page");
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String viewProjectList(@PathVariable("PID") String PID, Model model) {
        model.addAttribute("pagetype", "Display List Page");
        model.addAttribute("userSessionInfo", userSessionInfo);
        return "polymorphicView";
    }
}