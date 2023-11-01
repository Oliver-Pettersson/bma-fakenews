package org.bma.simulator.datamodel.userprofile.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bma.simulator.datamodel.userprofile.*;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;

public class UserProfileUtils {
    private static final Map<String, ProfileOccurrence> profiles = new HashMap<>(5, 1.0f);

    static {
        profiles.put(UserProfileConstants.DEFAULT_CELEBRITY, new ProfileOccurrence(new CelebrityUserProfile(PoliticalType.LEFT, AgeGroup.SENIOR), 10));
        profiles.put(UserProfileConstants.DEFAULT_USER, new ProfileOccurrence(new HumanUserProfile(PoliticalType.NONE, AgeGroup.YOUNG_ADULT), 10));
        profiles.put(UserProfileConstants.BOT_TYPE, new ProfileOccurrence(new BotUserProfile(), 10));
    }

    private UserProfileUtils() {
    }

    public static Map<String, ProfileOccurrence> getProfiles() {
        return profiles;
    }
}
