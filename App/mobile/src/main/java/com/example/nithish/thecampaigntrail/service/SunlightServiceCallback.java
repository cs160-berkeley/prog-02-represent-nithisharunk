package com.example.nithish.thecampaigntrail.service;

import com.example.nithish.thecampaigntrail.data.BillResults;
import com.example.nithish.thecampaigntrail.data.CommitteeResults;
import com.example.nithish.thecampaigntrail.data.GeoResults;
import com.example.nithish.thecampaigntrail.data.Results;

/**
 * Created by Nithish on 3/8/16.
 */
public interface SunlightServiceCallback {
    void serviceSuccess(Results result);
    void serviceSuccessComm(CommitteeResults commresult);
    void serviceSuccessBill(BillResults billResult);
    void serviceSuccessGeo(GeoResults geoResult);
    void serviceFailure(Exception exception);
}
