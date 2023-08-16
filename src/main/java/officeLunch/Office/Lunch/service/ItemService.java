package officeLunch.Office.Lunch.service;

import officeLunch.Office.Lunch.enums.MainDish;
import officeLunch.Office.Lunch.enums.SecondaryDish;

public interface ItemService {
    Long getPrice(MainDish mainDish , SecondaryDish secondaryDish);
}
