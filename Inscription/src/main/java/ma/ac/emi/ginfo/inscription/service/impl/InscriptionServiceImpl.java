package ma.ac.emi.ginfo.inscription.service.impl;


import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.entity.Inscription;
import ma.ac.emi.ginfo.inscription.repository.InscriptionRepository;
import ma.ac.emi.ginfo.inscription.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

//    for RestTemplate
    @Override
    public InscriptionDTO createInscription(InscriptionDTO inscriptionDTO) {
        Inscription inscription = new Inscription(inscriptionDTO.getUserId(), inscriptionDTO.getCourseId());
        // Set user and course using userId and courseId
        // Set other fields as needed
        System.out.println(inscription);
        Inscription savedInscription = inscriptionRepository.save(inscription);
        return convertToDTO(savedInscription);
    }

    @Override
    public List<InscriptionDTO> getAllInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        return inscriptions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionDTO getInscriptionById(Long id) {
        return inscriptionRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public InscriptionDTO updateInscription(Long id, InscriptionDTO updatedInscriptionDTO) {
        Inscription inscription = inscriptionRepository.findById(id).orElse(null);
        if (inscription != null) {
            // Update user and course using userId and courseId
            // (you might need to fetch them from UserRepository and CourseRepository)
            // inscription.setUser(user);
            // inscription.setCourse(course);
            // Set other fields as needed
            Inscription updatedInscription = inscriptionRepository.save(inscription);
            return convertToDTO(updatedInscription);
        }
        return null;
    }

    @Override
    public void deleteInscription(Long id) {
        inscriptionRepository.deleteById(id);
    }

    private InscriptionDTO convertToDTO(Inscription inscription) {
        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        inscriptionDTO.setId(inscription.getId());
        // Set userId and courseId
        inscriptionDTO.setUserId(inscription.getUserId());
        inscriptionDTO.setCourseId(inscription.getCourseId());
        // Set other fields as needed
        return inscriptionDTO;
    }
}