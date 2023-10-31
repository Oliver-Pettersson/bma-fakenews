package org.bma.simulator.datamodel.userprofile;

public class BotUserProfile extends UserProfile {
  private static final String BOT_TYPE = "BOT";
  private static Double botCredibility = 0.1;
  private static Double botScepticism = 0.0;

  public BotUserProfile() {
    super(BOT_TYPE, botCredibility, botScepticism);
  }

  public static void setBotCredibility(Double botCredibility) {
    BotUserProfile.botCredibility = botCredibility;
  }

  public static void setBotScepticism(Double botScepticism) {
    BotUserProfile.botScepticism = botScepticism;
  }
}
