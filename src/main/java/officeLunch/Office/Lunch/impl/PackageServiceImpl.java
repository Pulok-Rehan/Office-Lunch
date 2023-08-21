package officeLunch.Office.Lunch.impl;

import officeLunch.Office.Lunch.model.Item;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.repository.PackageRepository;
import officeLunch.Office.Lunch.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    @Autowired
    private final ItemServiceImpl itemService;

    public PackageServiceImpl(PackageRepository packageRepository, ItemServiceImpl itemService) {
        this.packageRepository = packageRepository;
        this.itemService = itemService;
    }

    @Override
    public LunchPackage getPackage(long id) throws Exception {
        Optional<LunchPackage> packageOptional = packageRepository.findById(id);
        if (packageOptional.isPresent()){
            return packageOptional.get();
        }
        else throw new Exception("Package not found");
    }

    @Override
    public List<LunchPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Long getTotalPriceOfPackage(long packageId) {
        Optional<LunchPackage> lunchPackage = packageRepository.findById(packageId);
        Long totalPrice = null;
        if (lunchPackage.isPresent()){
            for(Item item: lunchPackage.get().getItems()){
                totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }
}
