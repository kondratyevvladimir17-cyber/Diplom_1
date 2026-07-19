
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
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    public Bun bunMock;
    @Mock
    public Ingredient ingredientMock;
    @Mock
    public Ingredient ingredientTwoMock;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();


        when(bunMock.getPrice()).thenReturn(50.99f);
        when(ingredientMock.getPrice()).thenReturn(199.99f);
        when(ingredientTwoMock.getPrice()).thenReturn(199.99f);


    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock);
        List<Ingredient> expectedList = new ArrayList<>();
        expectedList.add(ingredientMock);
        assertEquals(expectedList, burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredientMock));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientTwoMock);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientTwoMock);

        float expectedPrice = (50.99f * 2) + 199.99f + 199.99f;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}

