package officeLunch.Office.Lunch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
import officeLunch.Office.Lunch.model.Item;
import officeLunch.Office.Lunch.response.CommonResponse;
import officeLunch.Office.Lunch.service.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/items/price")
    public CommonResponse getPrice(@RequestParam("mainDish") MainDish mainDish, @RequestParam("secondaryDish") SecondaryDish secondaryDish){

        long price = itemService.getPrice(mainDish, secondaryDish);
        if (Long.valueOf(price) == null){
            return CommonResponse.builder()
                    .hasError(true)
                    .content(null)
                    .message("Price could not be found").build();
        }
        return CommonResponse.builder()
                .hasError(false)
                .message("Price found for item")
                .content(String.valueOf(price)).build();
    }
    @PostMapping("/items")
    public CommonResponse addItem(@RequestBody Item item) throws JsonProcessingException {
        return itemService.addItem(item);
    }
}
