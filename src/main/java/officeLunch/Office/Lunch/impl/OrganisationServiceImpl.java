package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import officeLunch.Office.Lunch.model.Address;
import officeLunch.Office.Lunch.model.Organisation;
import officeLunch.Office.Lunch.repository.AddressRepository;
import officeLunch.Office.Lunch.repository.OrganisationRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.OrganisationService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class OrganisationServiceImpl implements OrganisationService {
    private final OrganisationRepository organisationRepository;
    private final AddressRepository addressRepository;

    public OrganisationServiceImpl(OrganisationRepository organisationRepository, AddressRepository addressRepository) {
        this.organisationRepository = organisationRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public CommonResponse createOrganisation(Organisation organisation) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (organisation==null){
            log.info("Organisation can not be null");
            return this.universalFailedCommonResponseForOrganisation();
        }
        if (organisation.getAddress().getId()==0){
            log.info("Organisation address id can not be null");
            return this.universalFailedCommonResponseForOrganisation();
        }
        Optional<Address> organisationAddress = addressRepository.findById(organisation.getAddress().getId());
        if (organisationAddress.isPresent()) {
            log.info("Address is present!");
            organisation.setAddress(organisationAddress.get());
        } else {
            log.info("Organisation address could not be found");
            return this.universalFailedCommonResponseForOrganisation();
        }
        Organisation newOrganisation = organisationRepository.save(organisation);
        log.info("Organisation added successfully");
        return CommonResponse.builder()
                .hasError(false)
                .message("Organisation added successfully!")
                .content(objectMapper.writeValueAsString(newOrganisation)).build();
    }
    private CommonResponse universalFailedCommonResponseForOrganisation(){
        return CommonResponse.builder()
                .hasError(true)
                .message("Could not add organisation!")
                .content(null).build();
    }
}
