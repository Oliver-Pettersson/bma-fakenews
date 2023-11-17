package org.bma.simulator.datamodel.userprofile;

import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;

import java.util.concurrent.ThreadLocalRandom;

public class CelebrityUserProfile extends HumanUserProfile {

    public CelebrityUserProfile(String type, PoliticalType politicalType, AgeGroup ageGroup) {
        super(type, politicalType, ageGroup);
    }
}
