package officeLunch.Office.Lunch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import officeLunch.Office.Lunch.dto.PackageDto;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PackageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PackageController {
    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @GetMapping(path = "/packages")
    public CommonResponse getAllPackages() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<LunchPackage> aPackages = packageService.getAllPackages();
        if (aPackages.size()==0){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("No Package found for today").build();
        }
        else {
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Packages found for today")
                    .content(objectMapper.writeValueAsString(aPackages)).build();
        }
    }
    @PostMapping(path = "/packages")
    public CommonResponse getPackage(@RequestBody Long packageId) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LunchPackage lunchPackage = packageService.getPackage(packageId);
        if(lunchPackage == null){
            return CommonResponse.builder()
                    .hasError(true)
                    .message("No Package found for today").build();
        }
        else {
            return CommonResponse.builder()
                    .hasError(false)
                    .message("Packages found for today")
                    .content(objectMapper.writeValueAsString(lunchPackage)).build();
        }
    }
    @PostMapping(path = "/addPackage")
    public CommonResponse addPackage(@RequestBody PackageDto packageDto) throws JsonProcessingException {
        return packageService.addPackage(packageDto);
    }
}
