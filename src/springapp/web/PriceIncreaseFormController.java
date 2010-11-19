package springapp.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import springapp.service.PriceIncrease;
import springapp.service.ProductManager;

public class PriceIncreaseFormController extends SimpleFormController
{

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    private ProductManager productManager;


    @Override
    public ModelAndView onSubmit(final Object command) throws ServletException
    {
        int increase = ((PriceIncrease)command).getPercentage();
        this.logger.info("Increasing prices by " + increase + "%.");

        this.productManager.increasePrice(increase);

        this.logger.info("returning from PriceIncreaseForm view to " + getSuccessView());

        return new ModelAndView(new RedirectView(getSuccessView()));
    }


    @Override
    protected Object formBackingObject(final HttpServletRequest request) throws ServletException
    {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        return priceIncrease;
    }


    public void setProductManager(final ProductManager productManager)
    {
        this.productManager = productManager;
    }


    public ProductManager getProductManager()
    {
        return this.productManager;
    }

}