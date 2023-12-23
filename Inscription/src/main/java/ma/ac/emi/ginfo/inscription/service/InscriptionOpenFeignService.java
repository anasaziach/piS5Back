package ma.ac.emi.ginfo.inscription.service;

import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;
import ma.ac.emi.ginfo.inscription.dto.UserDTO;
import ma.ac.emi.ginfo.inscription.entity.Inscription;
import ma.ac.emi.ginfo.inscription.response.CourseResponse;
import ma.ac.emi.ginfo.inscription.response.InscriptionResponse;
import ma.ac.emi.ginfo.inscription.response.UserResponse;

import java.util.List;

public interface InscriptionOpenFeignService {

    InscriptionResponse createInscription(InscriptionDTO inscriptionDTO);
    List<InscriptionResponse> getAllInscriptions();
    InscriptionResponse getInscriptionById(Long id);
    InscriptionDTO updateInscription(Long id, InscriptionDTO updatedInscriptionDTO);
    void deleteInscription(Long id);
    List<CourseResponse> getAllCoursesByStudentId(Long id);
    List<UserResponse> getAllStudentByCourseId(Long id);

}
