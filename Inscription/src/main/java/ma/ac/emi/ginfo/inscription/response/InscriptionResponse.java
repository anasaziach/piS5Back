package ma.ac.emi.ginfo.inscription.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import ma.ac.emi.ginfo.inscription.dto.UserDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionResponse {
    private Long id;

    private UserDTO userDTO;

    private CourseDTO courseDTO;


}
