package com.socotra.deployment.customer;

import com.socotra.coremodel.DocumentDataSnapshot;
import com.socotra.deployment.DataFetcherFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class DocDataSnapshotPlugin implements DocumentDataSnapshotPlugin {
    private static final Logger log = LoggerFactory.getLogger(DocDataSnapshotPlugin.class);
    public DocumentDataSnapshot dataSnapshot(IndustrialSpecialPlantQuoteRequest request) {

        var account = (BusinessAccount) DataFetcherFactory.get()
                .getAccount(request.quote().accountLocator());
        var pricing = DataFetcherFactory.get().getQuotePricing(request.quote().locator());
        var combinedData = new HashMap<String, Object>();

        combinedData.put("quote", request.quote());
        combinedData.put("pricing", pricing);
        combinedData.put("account", account);
        return DocumentDataSnapshot.builder().renderingData(combinedData).build();
    }

}