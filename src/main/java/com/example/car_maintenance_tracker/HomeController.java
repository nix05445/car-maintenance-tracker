package com.example.car_maintenance_tracker;

import java.util.List;

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

        List<ServiceEntry> allEntries = serviceManager.getEntries();

        if (category == null || category.isEmpty()) {
            model.addAttribute("entries", allEntries);
        } else {
            model.addAttribute(
                "entries",
                serviceManager.getEntriesByCategory(category)
            );
        }

        int totalEntries = allEntries.size();
        int maintenanceCount = 0;
        int repairCount = 0;
        double totalSpent = 0.0;

        for (ServiceEntry entry : allEntries) {
            if ("Maintenance".equalsIgnoreCase(entry.getCategory())) {
                maintenanceCount++;
            }

            if ("Repair".equalsIgnoreCase(entry.getCategory())) {
                repairCount++;
            }

            totalSpent += entry.getCost();
        }

        // Send statistics to the index.html template
        model.addAttribute("totalEntries", totalEntries);
        model.addAttribute("maintenanceCount", maintenanceCount);
        model.addAttribute("repairCount", repairCount);
        model.addAttribute("totalSpent", totalSpent);
        model.addAttribute("selectedCategory", category);
        return "index";
    }
}