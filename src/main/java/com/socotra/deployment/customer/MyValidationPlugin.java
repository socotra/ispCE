package com.socotra.deployment.customer;

import com.socotra.coremodel.ValidationItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyValidationPlugin implements ValidationPlugin{
    private static final Logger log = LoggerFactory.getLogger(MyValidationPlugin.class);
    @Override
    public ValidationItem validate(IndustrialSpecialPlantQuoteRequest request) {
        log.info("Validation Plugin");
        var validator = ValidationItem.builder();
        return validator.build();
    }
}
