package com.socotra.deployment.customer;

import com.socotra.coremodel.ValidationItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;

public class MyValidationPlugin implements ValidationPlugin{
    private static final Logger log = LoggerFactory.getLogger(MyValidationPlugin.class);
    @Override
    public ValidationItem validate(IndustrialSpecialPlantQuoteRequest request) {
        log.info("Validation Plugin");
        var validator = ValidationItem.builder();
        var fields =  Collections.<String>emptyList();
        //Validate schedule Of Machinery items
        for (var entry : request.quote().scheduleOfMachinerys()) {
            if(entry.data().machine().isEmpty()){
                fields.add(entry.locator() + " - Has empty items");
            }
        }
        return validator.addErrors(fields).build();
    }
}
