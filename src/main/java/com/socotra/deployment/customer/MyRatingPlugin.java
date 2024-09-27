package com.socotra.deployment.customer;


import com.socotra.coremodel.RatingItem;
import com.socotra.coremodel.RatingSet;
import com.socotra.platform.tools.ULID;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MyRatingPlugin implements RatePlugin {


    @Override
    public RatingSet rate(IndustrialSpecialPlantQuoteRequest request) {
        var quote = request.quote();
        var duration = request.duration();
        List<RatingItem> ratingItems = new ArrayList<>();

        if(quote.additionalBenefits() != null){
            ratingItems.add(rateAdditionalBenefits(quote.additionalBenefits()));
        }

        if(quote.broadformLiability() != null){
            ratingItems.add(rateBroadForm(quote.broadformLiability()));
        }

        for(var scheduleMachinery : quote.scheduleOfMachinerys()){
            if(scheduleMachinery.breakdown() !=null){
                ratingItems.add(rateBreakDown(scheduleMachinery));
            }
            if(scheduleMachinery.damage() !=null){
                ratingItems.add(rateDamage(scheduleMachinery));
            }
            if(scheduleMachinery.financialProtection() !=null){
                ratingItems.add(rateFinancialProtection(scheduleMachinery));
            }
            if(scheduleMachinery.hiredInPlant() !=null){
                ratingItems.add(rateHireInPlant(scheduleMachinery));
            }
            if(scheduleMachinery.roadRisk() !=null){
                ratingItems.add(rateRoadRisk(scheduleMachinery));
            }
        }

        return RatingSet.builder().ok(true).ratingItems(ratingItems).build();
    }
    @Override
    public RatingSet rate(IndustrialSpecialPlantRequest request) {
        var duration = request.duration();
        List<RatingItem> ratingItems = new ArrayList<>();
        List<ScheduleOfMachinery> machineries = new ArrayList<>();

        request.segment().ifPresent(segment -> {
            if(segment.additionalBenefits() !=null) {
                ratingItems.add(rateAdditionalBenefits(segment.additionalBenefits()));
            }
            if(segment.broadformLiability() != null) {
                ratingItems.add(rateBroadForm(segment.broadformLiability()));
            }

            machineries.addAll(segment.scheduleOfMachinerys());

            for(var machinery : machineries){
                if(machinery.breakdown()!= null){
                    ratingItems.add(rateBreakDown(machinery));
                }
                if(machinery.damage()!= null){
                    ratingItems.add(rateDamage(machinery));
                }
                if(machinery.financialProtection()!= null){
                    ratingItems.add(rateFinancialProtection(machinery));
                }
                if(machinery.hiredInPlant()!= null){
                    ratingItems.add(rateHireInPlant(machinery));
                }
                if(machinery.roadRisk()!= null){
                    ratingItems.add(rateRoadRisk(machinery));
                }
            }
            //ratingItems.add(rateFee(segment.locator(), duration));

            if (!ratingItems.isEmpty()) {
                ratingItems.add(rateTax(segment.locator(), ratingItems));
            }
        });

        return RatingSet.builder().ok(true).ratingItems(ratingItems).build();
    }

    private RatingItem rateAdditionalBenefits(AdditionalBenefits addBenefits){
        var rate = 0.001 * new BigDecimal(addBenefits.data().totalSumInsured()).doubleValue();
        return RatingItem.builder()
                .elementLocator(addBenefits.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }
    private RatingItem rateBroadForm(BroadformLiability broadLiability){
        //Broadform Liability = BroadformLimit Selection x Factor for each limit
        var rate = .5 *
        switch (broadLiability.broadformLimit()) {
            case BFL_5 -> 0.8;
            case BFL_10 -> 0.82;
            case BFL_20 -> 0.85;
            case BFL_50 -> 0.85;
            default -> 1;
        };
        return RatingItem.builder()
                .elementLocator(broadLiability.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();

    }
    private RatingItem rateBreakDown(ScheduleOfMachinery machinery){
        var cover = machinery.breakdown();
        var rate = .002 * cover.data().totalSumInsured();
        return RatingItem.builder()
                .elementLocator(cover.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }
    private  RatingItem rateDamage(ScheduleOfMachinery machinery){
        var cover = machinery.damage();
        var rate = .004 * cover.data().totalSumInsured();
        return RatingItem.builder()
                .elementLocator(cover.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }
    private  RatingItem rateFinancialProtection(ScheduleOfMachinery machinery){
        var cover = machinery.financialProtection();
        var rate = .001 * new BigDecimal(cover.data().totalSumInsured()).doubleValue();
        return RatingItem.builder()
                .elementLocator(cover.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }
    private  RatingItem rateHireInPlant(ScheduleOfMachinery machinery){
        var cover = machinery.hiredInPlant();
        var rate = .001 * cover.data().totalSumInsured();
        return RatingItem.builder()
                .elementLocator(cover.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }
    private  RatingItem rateRoadRisk(ScheduleOfMachinery machinery){
        var cover = machinery.roadRisk();
        var rate = .2 *
                switch (cover.roadriskLimit()) {
                    case RRL_20 -> 0.8;
                    case RRL_30 -> 0.82;
                    default -> 1;
                };

        return RatingItem.builder()
                .elementLocator(cover.locator())
                .chargeType(ChargeType.premium)
                .rate(BigDecimal.valueOf(rate))
                .build();
    }



    private RatingItem rateTax(ULID locator, List<RatingItem> ratingItems) {
        BigDecimal sum = ratingItems.stream().map(RatingItem::rate).reduce(BigDecimal.ZERO, BigDecimal::add);
        return RatingItem.builder()
                .elementLocator(locator)
                .chargeType(ChargeType.GST)
                .rate(sum.multiply(BigDecimal.valueOf(0.0525)))
                .build();
    }

//    private RatingItem rateFee(ULID locator, BigDecimal duration) {
//        return RatingItem.builder()
//                .elementLocator(locator)
//                .chargeType(ChargeType.administrationFee)
//                .rate(BigDecimal.valueOf(10L / duration.doubleValue()))
//                .build();
//    }

}