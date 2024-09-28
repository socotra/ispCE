package com.socotra.deployment.customer;

import com.socotra.coremodel.UnderwritingFlagCore;
import com.socotra.coremodel.UnderwritingLevel;
import com.socotra.coremodel.UnderwritingModification;
import com.socotra.deployment.DataFetcher;
import com.socotra.platform.tools.ULID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class MyUnderwritingPlugin implements UnderwritingPlugin {
    private static final Logger log = LoggerFactory.getLogger(MyUnderwritingPlugin.class);
     /*
    @Override
    public UnderwritingModification underwrite(IndustrialSpecialPlantQuoteRequest request) {
        var quote = request.quote();
        log.info("quote: " + quote);


            - Referral based on postal code
           - Referal based on Exeding Facilities sum insured > xx

        var quoteResp = DataFetcher.getInstance().getQuote(quote.locator());
        var data = quoteResp.data();
        ULID elementLocator = quote.locator();

        var dogbreed = quote.dwelling().data().dogBreed();
        if (dogbreed.equals("Pitbull")) {
            return UnderwritingModification.builder()
                .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                .level(UnderwritingLevel.block)
                .note("Dog breed " + dogbreed + " triggers manual underwriting")
                .elementLocator(elementLocator)
                .build()))
            .build();
        }
        var roofShape = quote.dwelling().data().roofShape();
        if (roofShape.equals("Unknown")) {
            return UnderwritingModification.builder()
                    .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                            .level(UnderwritingLevel.block)
                            .note("Roof shape " + roofShape + " triggers manual underwriting")
                            .elementLocator(elementLocator)
                            .build()))
                    .build();
        }

        var covALimit = quote.dwelling().coverage_a().data().limit();
        var yearBuilt = Integer.parseInt(quote.dwelling().data().yearBuilt());
        if(!covALimit.equals("600000") && yearBuilt < 1995){
            return UnderwritingModification.builder()
                    .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                            .level(UnderwritingLevel.block)
                            .note("For Year Built " + yearBuilt + ", limit must be less than $600,000. requires UW approval")
                            .elementLocator(elementLocator)
                            .build()))
                    .build();
        }
        var waterBackupLimit = quote.dwelling().water_backup().data().limit();
        var yearPumReplaced = quote.dwelling().data().yearPlumbingReplaced();
        if(!waterBackupLimit.equals("25,0000") && yearPumReplaced.isBefore(LocalDate
                .parse("2010-01-01"))){
            return UnderwritingModification.builder()
                    .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                            .level(UnderwritingLevel.block)
                            .note("Plumbing replaced before " + 2010 + " must have water backup limit of  $25,0000, requires UW approval")
                            .elementLocator(elementLocator)
                            .build()))
                    .build();
        }
        return UnderwritingModification.builder()
                .flagsToCreate(List.of(UnderwritingFlagCore.builder()
                        .level(UnderwritingLevel.approve)
                        .build()))
                .build();
    }
   */

}