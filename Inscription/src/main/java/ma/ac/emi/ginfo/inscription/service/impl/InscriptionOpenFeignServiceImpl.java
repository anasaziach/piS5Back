package ma.ac.emi.ginfo.inscription.service.impl;


import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.dto.UserDTO;
import ma.ac.emi.ginfo.inscription.entity.Inscription;
import ma.ac.emi.ginfo.inscription.repository.InscriptionRepository;
import ma.ac.emi.ginfo.inscription.response.CourseResponse;
import ma.ac.emi.ginfo.inscription.response.InscriptionResponse;
import ma.ac.emi.ginfo.inscription.response.UserResponse;
import ma.ac.emi.ginfo.inscription.service.CourseFeignClient;
import ma.ac.emi.ginfo.inscription.service.InscriptionOpenFeignService;
import ma.ac.emi.ginfo.inscription.service.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscriptionOpenFeignServiceImpl implements InscriptionOpenFeignService {
    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private CourseFeignClient courseFeignClient;

    @Override
    public InscriptionResponse createInscription(InscriptionDTO inscriptionDTO) {
        UserDTO userDTO = userFeignClient.getUserById(inscriptionDTO.getUserId());
        CourseDTO courseDTO = courseFeignClient.getCourseById(inscriptionDTO.getCourseId());
        System.out.println(userDTO.toString());
        System.out.println(courseDTO.toString());

        Inscription inscription = new Inscription(userDTO.getId(), courseDTO.getId());
        Inscription savedInscription = inscriptionRepository.save(inscription);
        InscriptionResponse inscriptionResponse = new InscriptionResponse(savedInscription.getId(), userDTO, courseDTO);
        return inscriptionResponse;
    }

    @Override
    public List<CourseResponse> getAllCoursesByStudentId(Long Id) {
        List<CourseResponse> response = new ArrayList<>();
        List<Inscription> inscriptionList = inscriptionRepository.getInscriptionsByUserId(Id);
        for(Inscription inscription : inscriptionList){
            CourseDTO course = courseFeignClient.getCourseById(inscription.getCourseId());
            response.add(new CourseResponse(course.getId(),course.getName()));
        }
        return response;
    }
    @Override
    public List<UserResponse> getAllStudentByCourseId(Long id) {
        List<UserResponse> response = new ArrayList<>();
        List<Inscription> inscriptionList = inscriptionRepository.getInscriptionByCourseId(id);
        for(Inscription inscription : inscriptionList){
            UserDTO user = userFeignClient.getUserById(inscription.getUserId());
            response.add(new UserResponse(user.getId(),user.getName()));
        }
        return response;
    }

    @Override
    public List<InscriptionResponse> getAllInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        return inscriptions.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionResponse getInscriptionById(Long id) {
        return inscriptionRepository.findById(id)
                .map(this::convertToResponse)
                .orElse(null);
    }

    private InscriptionResponse convertToResponse(Inscription inscription) {
        UserDTO userDTO = userFeignClient.getUserById(inscription.getUserId());
        CourseDTO courseDTO = courseFeignClient.getCourseById(inscription.getCourseId());
        return new InscriptionResponse(inscription.getId(), userDTO, courseDTO);
    }


    @Override
    public InscriptionDTO updateInscription(Long id, InscriptionDTO updatedInscriptionDTO) {
        return null;
    }

    @Override
    public void deleteInscription(Long id) {

    }

    // ... (other methods)

    private InscriptionDTO convertToDTO(Inscription inscription, UserDTO userDTO, CourseDTO courseDTO) {
        InscriptionDTO inscriptionDTO = new InscriptionDTO();
        inscriptionDTO.setId(inscription.getId());
        inscriptionDTO.setUserId(inscription.getUserId());
        inscriptionDTO.setCourseId(inscription.getCourseId());

        return inscriptionDTO;
    }
}