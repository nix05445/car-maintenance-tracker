package com.example.car_maintenance_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServiceController {

    private final ServiceManager serviceManager;

    public ServiceController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @PostMapping("/services")
    public String addService(
            @RequestParam String date,
            @RequestParam String workDone,
            @RequestParam int mileage,
            @RequestParam String category,
            @RequestParam double cost) {

        ServiceEntry entry = new ServiceEntry(
                date,
                workDone,
                mileage,
                category,
                cost
        );

        serviceManager.addEntry(entry);
        return "redirect:/";
    }

    @PostMapping("/services/delete")
    public String deleteService(@RequestParam int id) {

        serviceManager.deleteEntry(id);
        return "redirect:/";
    }

    @GetMapping("/services/edit")
    public String editService(
            @RequestParam int id,
            Model model) {

        ServiceEntry entry = serviceManager.getEntryById(id);

        model.addAttribute("entry", entry);
        return "edit";
    }

    @PostMapping("/services/update")
    public String updateService(
            @RequestParam int id,
            @RequestParam String date,
            @RequestParam String workDone,
            @RequestParam int mileage,
            @RequestParam String category,
            @RequestParam double cost) {

        ServiceEntry entry = new ServiceEntry(
                id,
                date,
                workDone,
                mileage,
                category,
                cost
        );

        serviceManager.updateEntry(entry);
        return "redirect:/";
    }
}