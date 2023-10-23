package org.bma.simulator.datamodel.userprofile;

public class AverageUserProfile extends UserProfile {
  private static final String TYPE = UserProfileConstants.AVERAGE;
  private static Double averageUserCredibility = 0.5;
  private static Double averageUserScepticism = 0.5;


  public AverageUserProfile() {
    super(TYPE, averageUserCredibility, averageUserScepticism);
  }

  public static void setAverageUserCredibility(Double averageUserCredibility) {
    AverageUserProfile.averageUserCredibility = averageUserCredibility;
  }

  public static void setAverageUserScepticism(Double averageUserSceptisism) {
    AverageUserProfile.averageUserScepticism = averageUserSceptisism;
  }
}
