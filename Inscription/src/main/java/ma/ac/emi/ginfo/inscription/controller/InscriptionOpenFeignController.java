package ma.ac.emi.ginfo.inscription.controller;


import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.response.CourseResponse;
import ma.ac.emi.ginfo.inscription.response.InscriptionResponse;
import ma.ac.emi.ginfo.inscription.response.UserResponse;
import ma.ac.emi.ginfo.inscription.service.impl.InscriptionOpenFeignServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptionsOpenFeign")
public class InscriptionOpenFeignController {

    @Autowired
    private InscriptionOpenFeignServiceImpl inscriptionService;

    @PostMapping
    public InscriptionResponse createInscription(@RequestBody InscriptionDTO inscriptionDTO) {
        return inscriptionService.createInscription(inscriptionDTO);
    }

    @GetMapping
    public List<InscriptionResponse> getAllInscriptions() {
        // Call InscriptionService to get all inscriptions
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    public InscriptionResponse getInscriptionById(@PathVariable Long id) {
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

    @GetMapping("details/courses/user/{id}")
    public List<CourseResponse> getAllCoursesByStudentId(@PathVariable Long id){
        return this.inscriptionService.getAllCoursesByStudentId(id);
    }
    @GetMapping("details/user/courses/{id}")
    public List<UserResponse> getAllStudentByCourseId(@PathVariable Long id){
        return this.inscriptionService.getAllStudentByCourseId(id);
    }
}