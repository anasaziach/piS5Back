package ma.ac.emi.ginfo.inscription.controller;

import ma.ac.emi.ginfo.inscription.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CourseClient {

    private final String courseServiceUrl;

    private final RestTemplate restTemplate;

    public CourseClient(@Value("${course.service.url}") String courseServiceUrl, RestTemplate restTemplate) {
        this.courseServiceUrl = courseServiceUrl;
        this.restTemplate = restTemplate;
    }

    public CourseDTO getCourseById(Long id) {
        return restTemplate.getForObject(courseServiceUrl + "/api/courses/{id}", CourseDTO.class, id);
    }

}