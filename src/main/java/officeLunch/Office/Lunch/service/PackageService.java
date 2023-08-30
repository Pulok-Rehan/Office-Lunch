package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.dto.PackageDto;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.response.CommonResponse;

import java.util.List;

public interface PackageService {
    LunchPackage getPackage(long id) throws Exception;
    List<LunchPackage> getAllPackages();
    double getTotalPriceOfPackage(long packageId);
    CommonResponse addPackage(PackageDto packageDto) throws JsonProcessingException;


}
