package com.socotra.deployment.customer;

import com.socotra.coremodel.UnderwritingFlagCore;
import com.socotra.coremodel.UnderwritingLevel;
import com.socotra.coremodel.UnderwritingModification;
import com.socotra.platform.tools.ULID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyUnderwritingPlugin implements UnderwritingPlugin {
    private static final Logger log = LoggerFactory.getLogger(MyUnderwritingPlugin.class);
    @Override
    public UnderwritingModification underwrite(IndustrialSpecialPlantQuoteRequest request) {
        var quote = request.quote();
        var uwFlags = new ArrayList<UnderwritingFlagCore>();

//      - Any Sum Insured on a coverage above $1m.
        ULID elementLocator = quote.locator();
       quote.scheduleOfMachinerys().stream().findFirst().ifPresent(machinery -> {
           if(machinery.damage()!=null &&
                   machinery.damage().data().totalSumInsured() > 1000000){
               uwFlags.add(UnderwritingFlagCore.builder()
                       .level(UnderwritingLevel.block)
                       .note("Section 1 - Damage with Sum Insured > 1M triggers manual underwriting")
                       .elementLocator(machinery.damage().locator())
                       .build());
           }
           var hiredInPlant = Optional.ofNullable(machinery.hiredInPlant());
           hiredInPlant.ifPresent(hiredInPlantQuote -> {
               if(hiredInPlantQuote.data().totalSumInsured() > 1000000){
                   uwFlags.add(UnderwritingFlagCore.builder()
                           .level(UnderwritingLevel.block)
                           .note("Section 2 - Hired in Plant with Sum Insured > 1M triggers manual underwriting")
                           .elementLocator(hiredInPlantQuote.locator())
                           .build());
               }
           });
            if( machinery.financialProtection() != null &&
                    machinery.financialProtection().data().totalSumInsured()!= null){
                var sumIns =  Integer.valueOf(machinery.financialProtection().data().totalSumInsured());
                if(sumIns > 1000000){
                    uwFlags.add(UnderwritingFlagCore.builder()
                            .level(UnderwritingLevel.block)
                            .note("Section 4 - Financial Protection with Sum Insured > 1M triggers manual underwriting")
                            .elementLocator(machinery.financialProtection().locator())
                            .build());
                }
            }

           if(machinery.breakdown()!=null &&
                   machinery.breakdown().data().totalSumInsured() > 1000000){
               uwFlags.add(UnderwritingFlagCore.builder()
                       .level(UnderwritingLevel.block)
                       .note("Section 5 - Breakdown with Sum Insured > 1M triggers manual underwriting")
                       .elementLocator(machinery.breakdown().locator())
                       .build());
           }

           //Any single piece of equipment above $200k for Market Value. I may change this to Sum Insured.
           for (var machine : machinery.data().machine()){
               if(machine.marketValue()!= null &&
                       machine.marketValue()> 200000){
                   uwFlags.add(UnderwritingFlagCore.builder()
                           .level(UnderwritingLevel.block)
                           .note("Machine with Market Value > $200K triggers manual underwriting")
                           .elementLocator(machinery.locator())
                           .build());
               }

           }
       });

        if(quote.additionalBenefits() != null && quote.additionalBenefits().data().totalSumInsured() > 1000000){
            uwFlags.add(UnderwritingFlagCore.builder()
                    .level(UnderwritingLevel.block)
                    .note("Section 3 - Additional Benefits with Sum Insured > 1M triggers manual underwriting")
                    .elementLocator(quote.additionalBenefits().locator())
                    .build());
        }
        uwFlags.forEach(flag -> log.info("flag {}, {}",flag.elementLocator(), flag.note()));
        return uwFlags.isEmpty()
                ?UnderwritingModification.builder()
                .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                        .level(UnderwritingLevel.approve)
                        .build()))
                .build()
                :UnderwritingModification.builder()
                .flagsToCreate(uwFlags)
                .build();
    }


}