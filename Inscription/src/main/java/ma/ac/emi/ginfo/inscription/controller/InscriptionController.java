package ma.ac.emi.ginfo.inscription.controller;


import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.dto.UserDTO;
import ma.ac.emi.ginfo.inscription.service.InscriptionService;
import ma.ac.emi.ginfo.inscription.service.impl.InscriptionServiceImpl;
import ma.ac.emi.ginfo.inscription.service.impl.InscriptionWebClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionServiceImpl inscriptionService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private CourseClient courseClient;

    @PostMapping
    public InscriptionDTO createInscription(@RequestBody InscriptionDTO inscriptionDTO) {
        // Assuming inscriptionDTO contains userId and courseId
        UserDTO userDTO = userClient.getUserById(inscriptionDTO.getUserId());
        CourseDTO courseDTO = courseClient.getCourseById(inscriptionDTO.getCourseId());
        //check for errors by printing client and course objects
        System.out.println(userDTO);
        System.out.println(courseDTO);

        // Call InscriptionService to create the inscription
        return inscriptionService.createInscription(inscriptionDTO);
    }

    @GetMapping
    public List<InscriptionDTO> getAllInscriptions() {
        // Call InscriptionService to get all inscriptions
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    public InscriptionDTO getInscriptionById(@PathVariable Long id) {
        // Call InscriptionService to get inscription by id
        return inscriptionService.getInscriptionById(id);
    }

    @PutMapping("/{id}")
    public InscriptionDTO updateInscription(@PathVariable Long id, @RequestBody InscriptionDTO updatedInscriptionDTO) {
        // Call InscriptionService to update inscription
        return inscriptionService.updateInscription(id, updatedInscriptionDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id) {
        // Call InscriptionService to delete inscription
        inscriptionService.deleteInscription(id);
    }

}