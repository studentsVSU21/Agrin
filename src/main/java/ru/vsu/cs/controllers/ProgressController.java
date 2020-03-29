package ru.vsu.cs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.services.ProgressService;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private static final Logger LOG = LoggerFactory.getLogger(ProgressController.class);

    @Autowired
    private ProgressService progressService;

    @GetMapping("/{id}")
    public Progress getProgressByID(@PathVariable(value = "id") Long id) {

        Progress progress = progressService.getProgressByID(id);

        return progress;

    }

}
