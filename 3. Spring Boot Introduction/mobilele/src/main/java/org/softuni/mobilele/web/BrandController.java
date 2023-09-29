package org.softuni.mobilele.web;

import org.softuni.mobilele.model.dto.ModelDTO;
import org.softuni.mobilele.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrandController {

    private final ModelService modelService;

    public BrandController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/brands/all")
    public String index(Model model) {
        List<ModelDTO> modelsPerBrand = this.modelService.getModelsPerBrand();

        model.addAttribute("models", modelsPerBrand);

        return "brands";
    }

}
