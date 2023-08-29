package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.model.Organisation;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface OrganisationService {
    CommonResponse createOrganisation(Organisation organisation) throws JsonProcessingException;
}
