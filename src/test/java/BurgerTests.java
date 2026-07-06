
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.*;
import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {


    @Mock
    public Bun buner;
    @Mock
    public Ingredient ingredientsmock;
    @Mock
    public Ingredient ingredientsmockTwo;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        buner = new Bun("Бриошь", 50.99f);
        ingredientsmock = new Ingredient(SAUCE, "Терияки", 199.99f);
        ingredientsmockTwo = new Ingredient(FILLING, "Сыр", 199.99f);
    }

    @Test
    public void setBunsTest(){
      burger.setBuns(buner);
     assertEquals(burger.bun, buner);
    }

    @Test
    public void addIngredientTest(){
        burger.addIngredient(ingredientsmock);
        List<Ingredient> ingredientslist = new ArrayList<>();
        ingredientslist.add(ingredientsmock);
        assertEquals(burger.ingredients, ingredientslist);
    }

    @Test
    public void removeIngredientTest(){
        burger.addIngredient(ingredientsmock);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredientsmock));
    }

    @Test
    public void moveIngredientTest(){
        burger.addIngredient(ingredientsmock);
        burger.addIngredient(ingredientsmockTwo);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientsmock, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(buner);
        burger.addIngredient(ingredientsmock);
        burger.addIngredient(ingredientsmockTwo);
        float expectedPrice = (50.99f * 2) + 199.99f + 199.99f;
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

}
