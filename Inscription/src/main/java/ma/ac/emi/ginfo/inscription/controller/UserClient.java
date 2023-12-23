package ma.ac.emi.ginfo.inscription.controller;


import ma.ac.emi.ginfo.inscription.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final String userServiceUrl;

    private final RestTemplate restTemplate;

    public UserClient(@Value("${user.service.url}") String userServiceUrl, RestTemplate restTemplate) {
        this.userServiceUrl = userServiceUrl;
        this.restTemplate = restTemplate;
    }

    public UserDTO getUserById(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/api/users/{id}", UserDTO.class, id);
    }
}