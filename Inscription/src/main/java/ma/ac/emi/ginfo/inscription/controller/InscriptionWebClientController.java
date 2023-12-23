package ma.ac.emi.ginfo.inscription.controller;


import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.dto.UserDTO;
import ma.ac.emi.ginfo.inscription.service.InscriptionService;
import ma.ac.emi.ginfo.inscription.service.impl.InscriptionWebClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptionsWebClient")
public class InscriptionWebClientController {

    @Autowired
    private InscriptionWebClientServiceImpl inscriptionService;

    @PostMapping
    public InscriptionDTO createInscription(@RequestBody InscriptionDTO inscriptionDTO) {
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