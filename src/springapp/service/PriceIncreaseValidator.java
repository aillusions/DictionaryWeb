package springapp.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PriceIncreaseValidator implements Validator
{
    private final int DEFAULT_MIN_PERCENTAGE = 0;
    private final int DEFAULT_MAX_PERCENTAGE = 50;
    private int minPercentage = this.DEFAULT_MIN_PERCENTAGE;
    private int maxPercentage = this.DEFAULT_MAX_PERCENTAGE;

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());


    public boolean supports(final Class clazz)
    {
        return PriceIncrease.class.equals(clazz);
    }


    public void validate(final Object obj, final Errors errors)
    {
        PriceIncrease pi = (PriceIncrease)obj;
        if(pi == null)
        {
            errors.rejectValue("percentage", "error.not-specified", null, "Value required.");
        }
        else
        {
            this.logger.info("Validating with " + pi + ": " + pi.getPercentage());
            if(pi.getPercentage() > this.maxPercentage)
            {
                errors.rejectValue("percentage", "error.too-high", new Object[] { new Integer(this.maxPercentage) },
                        "Value too high.");
            }
            if(pi.getPercentage() <= this.minPercentage)
            {
                errors.rejectValue("percentage", "error.too-low", new Object[] { new Integer(this.minPercentage) },
                        "Value too low.");
            }
        }
    }


    public void setMinPercentage(final int i)
    {
        this.minPercentage = i;
    }


    public int getMinPercentage()
    {
        return this.minPercentage;
    }


    public void setMaxPercentage(final int i)
    {
        this.maxPercentage = i;
    }


    public int getMaxPercentage()
    {
        return this.maxPercentage;
    }

}