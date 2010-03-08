package test.com.pyxis.petstore.controller;

import com.pyxis.petstore.controller.ItemsController;
import com.pyxis.petstore.domain.Item;
import com.pyxis.petstore.domain.ItemRepository;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

import static com.pyxis.matchers.spring.SpringMatchers.hasAttribute;
import static org.hamcrest.MatcherAssert.assertThat;
import static test.support.com.pyxis.petstore.builders.ItemBuilder.anItem;

@RunWith(JMock.class)
public class ItemsControllerTest {

    Mockery context = new JUnit4Mockery();
    ItemRepository itemRepository = context.mock(ItemRepository.class);
    ItemsController itemController = new ItemsController(itemRepository);
    
    @Test
    public void listsItemsByProductNumberAndMakeThemAvailableToView() {
    	final List<Item> anItemList = Arrays.asList(anItem().build());
    	context.checking(new Expectations(){{
    		oneOf(itemRepository).findByProductNumber("LAB-1234");
			will(returnValue(anItemList));
    	}});
    	ModelMap map = itemController.index("LAB-1234");
    	assertThat(map, hasAttribute("itemList", anItemList));
    }
}