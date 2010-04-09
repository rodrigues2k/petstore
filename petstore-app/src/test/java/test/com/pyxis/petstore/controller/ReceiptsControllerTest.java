package test.com.pyxis.petstore.controller;

import com.pyxis.petstore.controller.ReceiptsController;
import com.pyxis.petstore.domain.order.Order;
import com.pyxis.petstore.domain.order.OrderLog;
import com.pyxis.petstore.domain.order.OrderNumber;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.servlet.ModelAndView;

import static com.pyxis.matchers.spring.SpringMatchers.hasAttribute;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static test.support.com.pyxis.petstore.builders.OrderBuilder.anOrder;

@RunWith(JMock.class)
public class ReceiptsControllerTest {

    Mockery context = new JUnit4Mockery();
    OrderLog orderLog = context.mock(OrderLog.class);
    ReceiptsController controller = new ReceiptsController(orderLog);
    Order order = anOrder().build();

    @Test public void
    fetchesOrderByNumberAndDisplaysReceipt() {
    	context.checking(new Expectations(){{
    		oneOf(orderLog).find(new OrderNumber("00000100")); will(returnValue(order));
    	}});
        ModelAndView modelAndView = controller.show("00000100");
        assertThat(modelAndView.getViewName(), equalTo("receipts/show"));
        assertThat(modelAndView.getModel(), hasAttribute("order", order));
    }
}