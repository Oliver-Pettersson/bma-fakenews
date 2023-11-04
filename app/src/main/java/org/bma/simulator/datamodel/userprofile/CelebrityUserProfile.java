package org.bma.simulator.datamodel.userprofile;

import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;

public class CelebrityUserProfile extends HumanUserProfile {
    public CelebrityUserProfile(PoliticalType politicalType, AgeGroup ageGroup) {
        super(politicalType, ageGroup);
    }

    public CelebrityUserProfile(String type, PoliticalType politicalType, AgeGroup ageGroup) {
        super(type, politicalType, ageGroup);
    }
}
