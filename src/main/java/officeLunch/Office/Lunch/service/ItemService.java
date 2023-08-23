package officeLunch.Office.Lunch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;
import officeLunch.Office.Lunch.model.Item;
import officeLunch.Office.Lunch.response.CommonResponse;

public interface ItemService {
    Long getPrice(MainDish mainDish , SecondaryDish secondaryDish);
    CommonResponse addItem(Item item) throws JsonProcessingException;
}
