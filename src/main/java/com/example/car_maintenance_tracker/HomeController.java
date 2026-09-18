package com.example.car_maintenance_tracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final ServiceManager serviceManager;

    public HomeController(ServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @GetMapping("/")
    public String home(
        @RequestParam(required = false) String category,
        Model model) {

        if (category == null || category.isEmpty()) {
            model.addAttribute("entries", serviceManager.getEntries());
        } else {
            model.addAttribute(
                "entries",
                serviceManager.getEntriesByCategory(category)
            );
        }

        model.addAttribute("selectedCategory", category);
        return "index";
    }
}