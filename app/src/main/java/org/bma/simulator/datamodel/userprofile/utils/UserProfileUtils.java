package org.bma.simulator.datamodel.userprofile.utils;

import java.util.ArrayList;
import java.util.List;
import org.bma.simulator.datamodel.userprofile.BotUserProfile;
import org.bma.simulator.datamodel.userprofile.CelebrityUserProfile;
import org.bma.simulator.datamodel.userprofile.HumanUserProfile;
import org.bma.simulator.datamodel.userprofile.ProfileOccurrence;

public class UserProfileUtils {
  private static final List<ProfileOccurrence> profiles = new ArrayList<>(5);

  static {
    profiles.add(new ProfileOccurrence(new BotUserProfile(), 10));
    profiles.add(new ProfileOccurrence(new CelebrityUserProfile(), 10));
    profiles.add(new ProfileOccurrence(new HumanUserProfile(), 10));
  }

  private UserProfileUtils() {}

  public static List<ProfileOccurrence> getProfiles() {
    return profiles;
  }
}
