package org.bma.simulator.datamodel.userprofile;

public class UserProfileConstants {
  public static final String AVERAGE = "AVERAGE";

  public static UserProfile getProfileFromString(String s) {
    if (AVERAGE.equals(s)) {
      return new AverageUserProfile();
    }
    return null;
  }

  private UserProfileConstants() {
  }
}
