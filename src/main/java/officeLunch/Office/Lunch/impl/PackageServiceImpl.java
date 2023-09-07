package officeLunch.Office.Lunch.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import officeLunch.Office.Lunch.dto.PackageDto;
import officeLunch.Office.Lunch.model.Item;
import officeLunch.Office.Lunch.model.LunchPackage;
import officeLunch.Office.Lunch.repository.ItemRepository;
import officeLunch.Office.Lunch.repository.PackageRepository;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.UtilService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class PackageServiceImpl implements PackageService {
    private final PackageRepository packageRepository;
    private final ItemRepository itemRepository;
    @Autowired
    private final ItemServiceImpl itemService;

    public PackageServiceImpl(PackageRepository packageRepository, ItemRepository itemRepository, ItemServiceImpl itemService) {
        this.packageRepository = packageRepository;
        this.itemRepository = itemRepository;
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
    public double getTotalPriceOfPackage(long packageId) {
        Optional<LunchPackage> lunchPackage = packageRepository.findById(packageId);
        double totalPrice = 0;
        if (lunchPackage.isPresent()){
            for(Item item: lunchPackage.get().getItems()){
                totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }

    @Override
    public CommonResponse addPackage(PackageDto packageDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Item> items = new ArrayList<>();
        if(packageDto.getItemIdlist().isEmpty()){
            return UtilService.universalFailedResponse();
        }
        for (Long itemId: packageDto.getItemIdlist()){
            Optional<Item> itemOptional = itemRepository.findById(itemId);
            items.add(itemOptional.get());
        }
        LunchPackage lunchPackage = packageRepository.save(LunchPackage.builder()
                .items(items)
                .discount(packageDto.getDiscount())
                .totalPrice(0)
                .discountAmount(0)
                .build());
        double totalPrice = this.getTotalPriceOfPackage(lunchPackage.getId());
        lunchPackage.setTotalPrice(totalPrice);
        lunchPackage.setDiscountAmount(totalPrice*this.getDiscountedPrice(packageDto.getDiscount()));
        lunchPackage = packageRepository.save(lunchPackage);

        return CommonResponse.builder()
                .hasError(false)
                .message("Package added succesfully")
                .content(objectMapper.writeValueAsString(lunchPackage)).build();
    }

    private double getDiscountedPrice(double discount){
        double discountPercentage = 1;
        if(discount !=0){
            discountPercentage = discount/100;
        }
        return discountPercentage;
    }
}
