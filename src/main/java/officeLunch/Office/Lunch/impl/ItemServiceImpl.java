package officeLunch.Office.Lunch.impl;

import lombok.extern.slf4j.Slf4j;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
import officeLunch.Office.Lunch.repository.ItemRepository;
import officeLunch.Office.Lunch.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {
    @Value("${items.price.plain-rice}")
    private String priceOfPlainRice;
    @Value("${items.price.polao}")
    private String priceOfPolao;
    @Value("${items.price.khichuri}")
    private String priceOfKhichuri;
    @Value("${items.price.kacchi}")
    private String priceOfKacchi;
    @Value("${items.price.tehari}")
    private String priceOfTehari;
    @Value("${items.price.chicken}")
    private String priceOfChicken;
    @Value("${items.price.beef}")
    private String priceOfBeef;
    @Value("${items.price.fish}")
    private String priceOfFish;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Long getPrice(MainDish mainDish, SecondaryDish secondaryDish) {
        Long price = null;
        if(mainDish == null || secondaryDish == null){
            log.info("MAIN DISH OR SECONDARY DISH CAN NOT BE NULL");
            return null;
        }
        else {
            if (mainDish.equals(MainDish.PLAIN_RICE) || mainDish.equals(MainDish.POLAO) || mainDish.equals(MainDish.KHICHURI)){
                price = mainDish.equals(MainDish.PLAIN_RICE) ? Long.valueOf(priceOfPlainRice) : Long.valueOf(priceOfKhichuri);
                if (secondaryDish.equals(SecondaryDish.CHICKEN)){
                    log.info("SELECTED MAIN DISH AND SECONDARY DISH ARE PLAIN RICE AND CHICKEN");
                    price += Long.valueOf(priceOfChicken);
                } else if (secondaryDish.equals(SecondaryDish.BEEF)) {
                    log.info("SELECTED MAIN DISH AND SECONDARY DISH ARE PLAIN RICE AND BEEF");
                    price += Long.valueOf(priceOfBeef);
                } else if (secondaryDish.equals(SecondaryDish.FISH)) {
                    log.info("SELECTED MAIN DISH AND SECONDARY DISH ARE PLAIN RICE AND FISH");
                    price += Long.valueOf(priceOfFish);
                }
            } else if (mainDish.equals(MainDish.KACCHI)) {
                log.info("KACCHI IS SELECTED FOR ORDERING");
                price += Long.valueOf(priceOfKacchi);
            }
            else if (mainDish.equals(MainDish.TEHARI)) {
                log.info("TEHARI IS SELECTED FOR ORDERING");
                price += Long.valueOf(priceOfTehari);
            }

        }
        return price;
    }
}