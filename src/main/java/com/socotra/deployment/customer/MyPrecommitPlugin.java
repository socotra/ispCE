package com.socotra.deployment.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPrecommitPlugin implements PreCommitPlugin {
    private static final Logger log = LoggerFactory.getLogger(MyPrecommitPlugin.class);
 //   @Override
//    public IndustrialSpecialPlantQuote preCommit(IndustrialSpecialPlantQuoteRequest quoteRequest) {
//        var quote = quoteRequest.quote();
//        var builder = quote.toBuilder();
//        var data = quote.data();
//        if(quote.scheduleOfMachinerys().isEmpty()){
//            log.info("scheduleOfMachinerys is empty");
//            builder.addScheduleOfMachinery(machineryBuilder -> {
//                if(data.hiredInPlant().equals("Yes")){
//                    machineryBuilder.addHiredInPlant(hiredInPlantBuilder ->{});
//                }
//                if(data.breakdown().equals("Yes")) {
//                    machineryBuilder.addBreakdown(breakDownBuilder -> {
//                    });
//                }
//                if(data.damage().equals("Yes")) {
//                    machineryBuilder.addDamage(damageBuilder -> {
//                    });
//                }
//                if(data.roadRisk().equals("Yes")){
//                    machineryBuilder.addRoadRisk(roadRiskBuilder->{});
//                }
//                if(data.financialProtection().equals("Yes")) {
//                    machineryBuilder.addFinancialProtection(financialProtectionQuoteBuilder -> {
//                    });
//                }
//            });
//        }else{
//            log.info("scheduleOfMachinerys has elements {}", quote.scheduleOfMachinerys().size());
//            builder.scheduleOfMachinerys(
//                    quote.scheduleOfMachinerys().stream()
//                            .map(scheduleOfMachinery -> {
//                                var machineryBuilder = scheduleOfMachinery.toBuilder();
//                                if (scheduleOfMachinery.hiredInPlant() == null &&
//                                        data.hiredInPlant().equals("Yes")) {
//                                    machineryBuilder.addHiredInPlant(hiredInPlantBuilder -> {
//                                    });
//                                }
//                                if (scheduleOfMachinery.breakdown() == null &&
//                                        data.breakdown().equals("Yes")) {
//                                    machineryBuilder.addBreakdown(breakdownQuoteBuilder -> {
//                                    });
//                                }
//                                if (scheduleOfMachinery.damage() == null &&
//                                        data.damage().equals("Yes")) {
//                                    machineryBuilder.addDamage(damageQuoteBuilder -> {
//                                    });
//                                }
//                                if (scheduleOfMachinery.roadRisk() == null &&
//                                        data.roadRisk().equals("Yes")) {
//                                    machineryBuilder.addRoadRisk(roadRiskQuoteBuilder -> {
//                                    });
//                                }
//                                if (scheduleOfMachinery.financialProtection() == null &&
//                                        data.financialProtection().equals("Yes")) {
//                                    machineryBuilder.addFinancialProtection(financialProtectionQuoteBuilder -> {
//                                    });
//                                }
//                                return machineryBuilder.build();
//                            }).toList());
//        }
//
//
//        if(quote.additionalBenefits() == null  && data.additionalBenefits().equals("Yes")) {
//            builder.addScheduleOfMachinery(scheduleOfMachineryQuoteBuilder -> {});
//        }
//        if(quote.broadformLiability() == null && data.legalLiability().equals("Yes")) {
//            builder.addBroadformLiability(broadformLiabilityQuoteBuilder -> {});
//        }
//        var returnQuote = builder.build();
//        log.info("Machinaries final {}", returnQuote.scheduleOfMachinerys().size());
//        return returnQuote;
//    }
}
