package sk.edenis.homeworkunion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.edenis.homeworkunion.dto.ErrorResponseDTO;
import sk.edenis.homeworkunion.dto.InsuredRequestDTO;
import sk.edenis.homeworkunion.dto.ResponseDTO;
import sk.edenis.homeworkunion.service.InsuredService;
import sk.edenis.homeworkunion.utility.ErrorUtil;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/insured")
public class InsuredRestController {
    private final InsuredService insuredService;

    @Autowired
    public InsuredRestController(InsuredService insuredService) {
        this.insuredService = insuredService;
    }

    @PostMapping
    public ResponseEntity<?> registerNewInsured(@RequestBody @Valid InsuredRequestDTO insuredRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ErrorResponseDTO errorResponseDTO = ErrorUtil.formatErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(errorResponseDTO);
        }

        return ResponseEntity.status(201).body(new ResponseDTO(insuredService.saveInsured(insuredRequestDTO).getInsuredId()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getInsuredById(@PathVariable String id) {
        return ResponseEntity.ok(new ResponseDTO(insuredService.getInsuredById(id)));
    }

    @GetMapping
    public ResponseEntity<?> getAllInsured() {
        return ResponseEntity.ok(new ResponseDTO(insuredService.getAllInsured()));
    }
}
