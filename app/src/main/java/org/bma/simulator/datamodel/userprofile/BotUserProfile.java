package org.bma.simulator.datamodel.userprofile;

public class BotUserProfile extends UserProfile {
  private static Double botCredibility = 0.1;
  private static final double BOT_SCEPTICISM = 0.0;

  public BotUserProfile() {
    super(UserProfileConstants.BOT_TYPE);
  }

  public static void setBotCredibility(Double botCredibility) {
    BotUserProfile.botCredibility = botCredibility;
  }

  public static Double getBotCredibility() {
    return botCredibility;
  }
}
