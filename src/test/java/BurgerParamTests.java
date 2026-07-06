import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(Parameterized.class)
public class BurgerParamTests {

    private Burger burger;

    private final String bunName;
    private final float bunPrice;
    private final List<Ingredient> ingredients;

    public BurgerParamTests(String bunName, float bunPrice,
                                         List<Ingredient> ingredients) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredients = ingredients;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "Зерновая", 70.0f,
                        List.of(new Ingredient(SAUCE, "Песто", 199.99f))
                },

                {
                        "Бриошь", 90.0f,
                        List.of(
                                new Ingredient(FILLING, "Сыр", 200.0f)
                        )
                }
        });
    }

    @Before
    public void setUp() {
        Bun bun = new Bun(bunName, bunPrice);
        burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
    }
    @Test
    public void getReceiptTest() {
        String receipt = burger.getReceipt();
        String bunHeader = String.format("(==== %s ====)", bunName);
        assertTrue(receipt.contains(bunHeader));
        for (Ingredient ing : ingredients) {
            String typeLower = ing.getType().toString().toLowerCase();
            String expectedLine = String.format("= %s %s =", typeLower, ing.getName());
            assertTrue(receipt.contains(expectedLine));
        }
        assertTrue(receipt.contains("Price:"));
    }
}

