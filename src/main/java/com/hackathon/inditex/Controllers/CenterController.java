package com.hackathon.inditex.Controllers;

import com.hackathon.inditex.Constants.Messages;
import com.hackathon.inditex.DTO.CenterDTO;
import com.hackathon.inditex.DTO.MessageResponseDTO;
import com.hackathon.inditex.Services.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for the Center entity.
 */
@RestController
@RequestMapping("/api/centers")
@RequiredArgsConstructor
public class CenterController {

    public static final String TEST = "test";
    private final CenterService service;

    /**
     * Creates a new center.
     * @param center The center to be created.
     * @return A message response.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createCenter(@RequestBody CenterDTO center) {
        service.createCenter(center);
        return new MessageResponseDTO(Messages.CENTER_CREATED);
    }
    /**
     * Retrieves all centers.
     * @return A list of all centers.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CenterDTO> getAllCenters() {
        return service.getAllCenters();
    }

    /**
     * Updates a center.
     * @param id The id of the center to be updated.
     * @param center The updated center.
     * @return A message response.
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO updateCenter(@PathVariable Long id, @RequestBody CenterDTO center) {
        service.updateCenter(id, center);
        return new MessageResponseDTO(Messages.CENTER_UPDATED);
    }

    /**
     * Deletes a center.
     * @param id The id of the center to be deleted.
     * @return A message response.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO deleteCenter(@PathVariable Long id) {
        service.deleteCenter(id);
        return new MessageResponseDTO(Messages.CENTER_DELETED);
    }
}
