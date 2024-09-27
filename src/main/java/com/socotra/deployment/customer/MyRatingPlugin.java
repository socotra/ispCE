package com.socotra.deployment.customer;


public class MyRatingPlugin implements RatePlugin {
//    @Override
//    public RatingSet rate(MotorhomeInsuranceQuoteRequest request) {
//        var quote = request.quote();
//        var duration = request.duration();
//        List<RatingItem> ratingItems = new ArrayList<>();
//        for( var motorHome: quote.motorhomes()){
//            if(motorHome.motorhomeCover()!=null){
//                ratingItems.add(rateMotorHomeCover(motorHome));
//            }
//            if(motorHome.contentsCover()!=null){
//                ratingItems.add(rateContentsCover(motorHome));
//            }
//            if(motorHome.liabilityCover()!=null){
//                ratingItems.add(rateLiabilityCover(motorHome));
//            }
//            if(motorHome.layupCover()!=null){
//                ratingItems.add(rateLayupCover(motorHome));
//            }
//            if(motorHome.windscreenCover()!=null){
//                ratingItems.add(rateWindscreenCover(motorHome));
//            }
//        }
//        return RatingSet.builder().ok(true).ratingItems(ratingItems).build();
//    }
//    @Override
//    public RatingSet rate(MotorhomeInsuranceRequest request) {
//        var duration = request.duration();
//        List<RatingItem> ratingItems = new ArrayList<>();
//        List<MotorhomePolicy> motorHomes = new ArrayList<>();
//        request.segment().ifPresent(s-> motorHomes.addAll(s.motorhomes()));
//        for( var motorHome : motorHomes){
//            if(motorHome.motorhomeCover()!=null){
//                ratingItems.add(rateMotorHomeCover(motorHome));
//            }
//            if(motorHome.contentsCover()!=null){
//                ratingItems.add(rateContentsCover(motorHome));
//            }
//            if(motorHome.liabilityCover()!=null){
//                ratingItems.add(rateLiabilityCover(motorHome));
//            }
//            if(motorHome.layupCover()!=null){
//                ratingItems.add(rateLayupCover(motorHome));
//            }
//            if(motorHome.windscreenCover()!=null){
//                ratingItems.add(rateWindscreenCover(motorHome));
//            }
//        }
//        request.segment().ifPresent(s-> ratingItems.add(rateFee(s.locator(), duration)));
//        if (!ratingItems.isEmpty()) {
//            request.segment().ifPresent(s-> ratingItems.add(rateTax(s.locator(), ratingItems)));
//        }
//        return RatingSet.builder().ok(true).ratingItems(ratingItems).build();
//    }
//
//    private RatingItem rateMotorHomeCover(Motorhome motorHome) {
//        var cover = motorHome.motorhomeCover();
//        var rate = 0.003 * new BigDecimal(cover.data().motorhomeSumInsured()).doubleValue();
//        rate *= switch (cover.BE()) {
//            case BE_1000 -> 0.8;
//            case BE_2000 -> 0.82;
//            case BE_1500 -> 0.85;
//            default -> 1;
//        };
//        return RatingItem.builder()
//                .elementLocator(cover.locator())
//                .chargeType(ChargeType.premium)
//                .rate(BigDecimal.valueOf(rate))
//                .build();
//    }
//
//    private RatingItem rateContentsCover(Motorhome motorHome){
//        var contents = motorHome.contentsCover();
//        double rate = 30;
//        rate *= switch (contents.CSI()) {
//            case CSI_1000 -> 0.9;
//            case CSI_10000 -> 0.91;
//            case CSI_2000 -> 0.92;
//            case CSI_20000 -> 0.95;
//            case CSI_3000 -> 0.96;
//            case CSI_4000 -> 0.97;
//            case CSI_5000 -> 0.98;
//            default -> 1;
//        };
//        return RatingItem.builder()
//                .elementLocator(contents.locator())
//                .chargeType(ChargeType.premium)
//                .rate(BigDecimal.valueOf(rate))
//                .build();
//    }
//
//    private RatingItem rateLiabilityCover(Motorhome motorHome){
//        double rate = 40;
//        var liab = motorHome.liabilityCover();
//        rate *= switch (liab.LLE()) {
//            case LLE_1000 -> 0.9;
//            case LLE_1500 -> 0.91;
//            case LLE_2000 -> 0.92;
//            default -> 1;
//        };
//        return RatingItem.builder()
//                .elementLocator(liab.locator())
//                .chargeType(ChargeType.premium)
//                .rate(BigDecimal.valueOf(rate))
//                .build();
//    }
//
//    private RatingItem rateLayupCover(Motorhome motorHome){
//        var layUp = motorHome.layupCover();
//        double rate = 0.001 * 10;
//        rate *= switch (layUp.data().months()) {
//            case "No" -> 0.1;
//            case "Jan" -> 0.90;
//            case "Feb"-> 0.91;
//            case "Mar" -> 0.92;
//            case "Apr" -> 0.93;
//            case "May" -> 0.94;
//            case "Jun" -> 0.95;
//            case "Jul" -> 0.96;
//            case "Aug" -> 0.97;
//            case "Sep" -> 0.98;
//            case "Oct" -> 0.99;
//            case "Nov" -> 0.70;
//            case "Dec" -> 0.71;
//            default -> 1;
//        };
//        return RatingItem.builder()
//                .elementLocator(layUp.locator())
//                .chargeType(ChargeType.premium)
//                .rate(BigDecimal.valueOf(rate))
//                .build();
//    }
//
//    private RatingItem rateWindscreenCover(Motorhome motorHome){
//        var windScreen = motorHome.windscreenCover();
//        double rate = 0.001 * 40;
//        rate *= switch (windScreen.WL()) {
//            case WL_1000 -> 0.9;
//            default -> 1;
//        };
//        return RatingItem.builder()
//                .elementLocator(windScreen.locator())
//                .chargeType(ChargeType.premium)
//                .rate(BigDecimal.valueOf(rate))
//                .build();
//    }
//
//
//
//    private RatingItem rateTax(ULID locator, List<RatingItem> ratingItems) {
//        BigDecimal sum = ratingItems.stream().map(RatingItem::rate).reduce(BigDecimal.ZERO, BigDecimal::add);
//        return RatingItem.builder()
//                .elementLocator(locator)
//                .chargeType(ChargeType.GST)
//                .rate(sum.multiply(BigDecimal.valueOf(0.0525)))
//                .build();
//    }
//
//    private RatingItem rateFee(ULID locator, BigDecimal duration) {
//        return RatingItem.builder()
//                .elementLocator(locator)
//                .chargeType(ChargeType.administrationFee)
//                .rate(BigDecimal.valueOf(10L / duration.doubleValue()))
//                .build();
//    }

}