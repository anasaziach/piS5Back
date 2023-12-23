package ma.ac.emi.ginfo.inscription.repository;

import ma.ac.emi.ginfo.inscription.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> getInscriptionsByUserId(Long id);
    List<Inscription> getInscriptionByCourseId(Long id);
}