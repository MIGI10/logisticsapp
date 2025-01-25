package com.hackathon.inditex.controller;

import com.hackathon.inditex.constant.Messages;
import com.hackathon.inditex.dto.CenterDTO;
import com.hackathon.inditex.dto.MessageResponseDTO;
import com.hackathon.inditex.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

    private final CenterService service;

    @Autowired
    public CenterController(CenterService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createCenter(@RequestBody CenterDTO center) {
        service.createCenter(center);
        return new MessageResponseDTO(Messages.CENTER_CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CenterDTO> getAllCenters() {
        return service.getAllCenters();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateCenter(@PathVariable Long id, @RequestBody CenterDTO center) {
        service.updateCenter(id, center);
        return new MessageResponseDTO(Messages.CENTER_UPDATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO deleteCenter(@PathVariable Long id) {
        service.deleteCenter(id);
        return new MessageResponseDTO(Messages.CENTER_DELETED);
    }
}
