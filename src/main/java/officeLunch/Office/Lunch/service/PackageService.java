package officeLunch.Office.Lunch.service;

import officeLunch.Office.Lunch.model.LunchPackage;

import java.util.List;

public interface PackageService {
    LunchPackage getPackage(long id) throws Exception;
    List<LunchPackage> getAllPackages();
    double getTotalPriceOfPackage(long packageId);


}
