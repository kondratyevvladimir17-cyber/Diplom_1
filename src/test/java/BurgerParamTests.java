import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.mockito.Mockito;
import java.util.*;


@RunWith(Parameterized.class)
public class BurgerParamTests {

    private Bun bun;
    private Ingredient ingredientMock;
    private Burger burger;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;
    private final String expectedSubstring;

    public BurgerParamTests(String bunName, float bunPrice, IngredientType ingredientType,
                            String ingredientName, float ingredientPrice, String expectedSubstring) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.expectedSubstring = expectedSubstring;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {"Зерновая", 70.0f, IngredientType.SAUCE, "Песто", 199.99f, "(==== Зерновая ===="},

                {"Зерновая", 70.0f, IngredientType.SAUCE, "Песто", 199.99f, "= sauce Песто ="},

                {"Бриошь", 90.0f, IngredientType.FILLING, "Сыр", 250.00f, "(==== Бриошь ===="},

                {"Бриошь", 90.0f, IngredientType.FILLING, "Сыр", 250.00f, "= filling Сыр ="},

                {"Бриошь", 90.0f, IngredientType.FILLING, "Сыр", 250.00f, "Price: "}
        });
    }

    @Before
    public void setUp() {

        bun = Mockito.spy(new Bun(bunName, bunPrice));

        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        ingredientMock = Mockito.mock(Ingredient.class);
        when(ingredientMock.getType()).thenReturn(ingredientType);
        when(ingredientMock.getName()).thenReturn(ingredientName);
        when(ingredientMock.getPrice()).thenReturn(ingredientPrice);

        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredientMock);
    }

    @Test
    public void getReceiptContainsLine() {
        String receipt = burger.getReceipt();
        assertTrue("Чек должен содержать строку: '" + expectedSubstring + "'.\nПолный чек:\n" + receipt,
                receipt.contains(expectedSubstring));
    }
}
