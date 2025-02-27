package kz.bitlab.techorda2025.thirdProject.controllers;

import kz.bitlab.techorda2025.thirdProject.db.ApplicationRequest;
import kz.bitlab.techorda2025.thirdProject.repositories.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/applications")
public class ApplicationRequestController {

    @Autowired
    ApplicationRequestRepository applicationRequestRepository;

    @GetMapping
    public String getApplications(Model model) {
        List<ApplicationRequest> applications = applicationRequestRepository.findAll();
        model.addAttribute("applications", applications);
        return "applications";
    }

    @GetMapping("/add")
    public String showAddApplicationForm(Model model) {
        return "addApplication";
    }

    @PostMapping("/add")
    public String addApplicationRequest(
            @RequestParam(name="name") String userName,
            @RequestParam(name="course") String courseName,
            @RequestParam(name="comment") String commentary,
            @RequestParam(name="phone") String phone) {

        ApplicationRequest application = ApplicationRequest.builder()
                .userName(userName)
                .courseName(courseName)
                .commentary(commentary)
                .phone(phone)
                .handled(false)
                .build();

        applicationRequestRepository.save(application);
        return "redirect:/applications";
    }

    @GetMapping("/details/{id}")
    public String getApplicationDetails(@PathVariable("id") Long id, Model model) {
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        if (applicationRequest.isHandled() == false) {
            model.addAttribute("newRequest", "Новая необработанная заявка");
        }
        model.addAttribute("appReq", applicationRequest);
        return "details";
    }

    @PostMapping("/save")
    public String saveApplication(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "app_username") String userName,
            @RequestParam(name = "app_coursename") String courseName,
            @RequestParam(name = "app_comment") String commentary,
            @RequestParam(name = "app_phone") String phone,
            @RequestParam(name = "app_handled") boolean handled) {

        ApplicationRequest application = ApplicationRequest.builder()
                .id(id)
                .userName(userName)
                .courseName(courseName)
                .commentary(commentary)
                .phone(phone)
                .handled(handled)
                .build();

        applicationRequestRepository.save(application);
        return "redirect:/applications";
    }

    @PostMapping("/delete")
    public String deleteApplication(@RequestParam("id") Long id) {
        applicationRequestRepository.deleteById(id);
        return "redirect:/applications";
    }

    @PostMapping("/update")
    public String saveApplication(@RequestParam("id") Long id) {
        ApplicationRequest applicationRequest = applicationRequestRepository.findById(id).orElse(null);
        if(applicationRequest != null) {
            applicationRequest.setHandled(true);
            applicationRequestRepository.save(applicationRequest);
        }
        return "redirect:/applications";
    }

    @PostMapping("/search")
    public String searchApplications(@RequestParam(name="searchValue") String searchValue, Model model) {
        List<ApplicationRequest> applicationList = applicationRequestRepository.findAllByUserNameContainsIgnoreCase(searchValue);
        model.addAttribute("applications", applicationList);
        return "applications";
    }

    @GetMapping("/filter")
    public String filterApplications(@RequestParam boolean handled, Model model) {
        List<ApplicationRequest> filteredApps = applicationRequestRepository.findByHandled(handled);
        model.addAttribute("applications", filteredApps);
        return "applications";
    }
}
