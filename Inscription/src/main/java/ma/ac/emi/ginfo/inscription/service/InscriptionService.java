package ma.ac.emi.ginfo.inscription.service;

import ma.ac.emi.ginfo.inscription.dto.InscriptionDTO;

import java.util.List;

public interface InscriptionService {
    InscriptionDTO createInscription(InscriptionDTO inscriptionDTO);
    List<InscriptionDTO> getAllInscriptions();
    InscriptionDTO getInscriptionById(Long id);
    InscriptionDTO updateInscription(Long id, InscriptionDTO updatedInscriptionDTO);
    void deleteInscription(Long id);
}
