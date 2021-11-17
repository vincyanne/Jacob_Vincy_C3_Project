import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void createRestaurantBeforeEach(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        LocalTime fourHoursBeforeClosingTime = LocalTime.parse("22:00:00").minusHours(4);
        assertTrue(restaurant.isRestaurantOpen());

        LocalTime fourHoursAfterOpeningTime = LocalTime.parse("10:30:00").plusHours(4);
        assertTrue(restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant restaurant = Mockito.mock(Restaurant.class);
        LocalTime fourHoursBeforeOpeningTime = LocalTime.parse("10:30:00").minusHours(4);

        Mockito.when(restaurant.getCurrentTime()).thenReturn(fourHoursBeforeOpeningTime);

        assertFalse(restaurant.isRestaurantOpen());

        LocalTime fourHoursAfterClosingTime = LocalTime.parse("22:00:00").plusHours(4);

        Mockito.when(restaurant.getCurrentTime()).thenReturn(fourHoursAfterClosingTime);

        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void when_item_selected_total_amount_should_be_returned(){
        List<String> selectedItems = null;
        int orderValue = restaurant.calculateOrderTotal(selectedItems);
    }
}