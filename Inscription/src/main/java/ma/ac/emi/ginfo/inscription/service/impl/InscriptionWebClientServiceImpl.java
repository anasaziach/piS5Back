package ma.ac.emi.ginfo.inscription.service.impl;


import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.entity.Inscription;
import ma.ac.emi.ginfo.inscription.repository.InscriptionRepository;
import ma.ac.emi.ginfo.inscription.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionWebClientServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private WebClient webClient;


    //for web Client
    @Override
    public InscriptionDTO createInscription(InscriptionDTO inscriptionDTO) {
        WebClient.ResponseSpec responseSpec = webClient.get().uri("http://localhost:8082/api/courses/"+inscriptionDTO.getCourseId()).retrieve();
        WebClient.ResponseSpec responseSpec2 = webClient.get().uri("http://localhost:8081/api/users/"+inscriptionDTO.getUserId()).retrieve();
        if (responseSpec != null && responseSpec2!=null) {
            responseSpec.bodyToMono(String.class)
                    .subscribe(responseBody -> {
                        // Handle the case when a response was received
                        System.out.println("course: " + responseBody);
                    });
            responseSpec2.bodyToMono(String.class)
                    .subscribe(responseBody -> {
                        // Handle the case when a response was received
                        System.out.println("user: " + responseBody);
                    });
            inscriptionRepository.save(new Inscription(inscriptionDTO.getUserId(),inscriptionDTO.getCourseId()));
        } else {
            // Handle the case when no response was received
            System.out.println("error on IDs");
        }
        return inscriptionDTO;
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