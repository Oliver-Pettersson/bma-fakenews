package org.bma.simulator.utils;

import org.bma.simulator.datamodel.UserNode;
import org.bma.simulator.datamodel.userprofile.BotUserProfile;
import org.bma.simulator.datamodel.userprofile.HumanUserProfile;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;

import java.util.concurrent.ThreadLocalRandom;

public class FakeNewsCalcUtils {
    private static final double HIGHEST_RECOGNITION_PROBABILITY = 0.79; // Average person is 0.54 accurate
    private static final double YA_NON_POLITICAL_SCEPTICISM = 0.346;
    private static final double ADULT_NON_POLITICAL_SCEPTICISM = 0.296;
    private static final double SENIOR_NON_POLITICAL_SCEPTICISM = 0.246;
    private static final double YA_POLITICAL_SCEPTICISM = 0.538;
    private static final double ADULT_POLITICAL_SCEPTICISM = 0.698;
    private static final double SENIOR_POLITICAL_SCEPTICISM = 0.775;
    private static final double YA_CELEBRITY_INFLUENCE = 0.164;
    private static final double ADULT_CELEBRITY_INFLUENCE = 0.123;
    private static final double SENIOR_CELEBRITY_INFLUENCE =  0.822;


    private static double believability;
    private static PoliticalType offendedPoliticalType;

    private FakeNewsCalcUtils() {}

    public static boolean calcIfIsInfected(UserNode source, UserNode target) {
        if (target.getProfile() instanceof BotUserProfile)
            return true;
        double adjustedRecognitionProbability = HIGHEST_RECOGNITION_PROBABILITY;

        adjustedRecognitionProbability = adjustForFakeNewsProps(adjustedRecognitionProbability, target);
        adjustedRecognitionProbability = adjustForSource(adjustedRecognitionProbability, source, target);

        if (source.getProfile() instanceof BotUserProfile)
            adjustedRecognitionProbability = adjustedRecognitionProbability * BotUserProfile.getBotCredibility();

        return ThreadLocalRandom.current().nextDouble() > adjustedRecognitionProbability;
    }

    private static double adjustForFakeNewsProps(double originalProbability, UserNode target) {
        HumanUserProfile profile = (HumanUserProfile) target.getProfile();
        double adjustedProbability = originalProbability - (believability / 2);

        adjustedProbability = adjustForPoliticalTypeAndAge(adjustedProbability, profile);

        return adjustedProbability;
    }

    // https://www.prnewswire.com/news-releases/37-of-consumers-trust-social-media-influencers-over-brands-301538111.html
    private static double adjustForSource(double originalProbability, UserNode source, UserNode target) {
        if (source.isCelebrity()) {
            HumanUserProfile targetProfile = (HumanUserProfile) target.getProfile();
            if (targetProfile.getAgeGroup() == AgeGroup.YOUNG_ADULT)
                return originalProbability - YA_CELEBRITY_INFLUENCE;
            if (targetProfile.getAgeGroup() == AgeGroup.ADULT)
                return  originalProbability - ADULT_CELEBRITY_INFLUENCE;
            if (targetProfile.getAgeGroup() == AgeGroup.SENIOR)
                return  originalProbability - SENIOR_CELEBRITY_INFLUENCE;
        }
        return originalProbability;
    }

    private static double adjustForPoliticalTypeAndAge(double originalProbability, HumanUserProfile targetProfile) {
        double adjustedProbability = originalProbability;
        // Adult probability = 29.6, YA = 34.6, Senior = 24.6
        if (offendedPoliticalType == PoliticalType.NONE) {
            if (targetProfile.getAgeGroup() == AgeGroup.YOUNG_ADULT)
                adjustedProbability += YA_NON_POLITICAL_SCEPTICISM - ADULT_NON_POLITICAL_SCEPTICISM; // +0.05
            if (targetProfile.getAgeGroup() == AgeGroup.SENIOR)
                adjustedProbability += SENIOR_NON_POLITICAL_SCEPTICISM - ADULT_NON_POLITICAL_SCEPTICISM; // -0.05
            return adjustedProbability;
        }
        // Adult probability = 69.8, YA = 53.8, Senior = 77.5
        if (targetProfile.getPoliticalType() == offendedPoliticalType) {
            if (targetProfile.getAgeGroup() == AgeGroup.YOUNG_ADULT) {
                adjustedProbability += YA_POLITICAL_SCEPTICISM - YA_NON_POLITICAL_SCEPTICISM;
            }
            if (targetProfile.getAgeGroup() == AgeGroup.ADULT) {
                adjustedProbability += ADULT_POLITICAL_SCEPTICISM - ADULT_NON_POLITICAL_SCEPTICISM;
            }
            if (targetProfile.getAgeGroup() == AgeGroup.SENIOR) {
                adjustedProbability += SENIOR_POLITICAL_SCEPTICISM - SENIOR_NON_POLITICAL_SCEPTICISM;
            }
            return adjustedProbability;
        }
        if (targetProfile.getPoliticalType() == PoliticalType.NONE) {
            if (targetProfile.getAgeGroup() == AgeGroup.YOUNG_ADULT) {
                adjustedProbability += (YA_POLITICAL_SCEPTICISM - YA_NON_POLITICAL_SCEPTICISM) / 1.1;
            }
            if (targetProfile.getAgeGroup() == AgeGroup.ADULT) {
                adjustedProbability += (ADULT_POLITICAL_SCEPTICISM - ADULT_NON_POLITICAL_SCEPTICISM) / 1.1;
            }
            if (targetProfile.getAgeGroup() == AgeGroup.SENIOR) {
                adjustedProbability += (SENIOR_POLITICAL_SCEPTICISM - SENIOR_NON_POLITICAL_SCEPTICISM) / 1.1;
            }
            return adjustedProbability;
        }
        if (targetProfile.getAgeGroup() == AgeGroup.YOUNG_ADULT) {
            adjustedProbability -= YA_POLITICAL_SCEPTICISM - YA_NON_POLITICAL_SCEPTICISM;
        }
        if (targetProfile.getAgeGroup() == AgeGroup.ADULT) {
            adjustedProbability -= ADULT_POLITICAL_SCEPTICISM - ADULT_NON_POLITICAL_SCEPTICISM;
        }
        if (targetProfile.getAgeGroup() == AgeGroup.SENIOR) {
            adjustedProbability -= SENIOR_POLITICAL_SCEPTICISM - SENIOR_NON_POLITICAL_SCEPTICISM;
        }
        return adjustedProbability;
    }
    public static void setOffendedPoliticalType(PoliticalType offendedPoliticalType) {
        FakeNewsCalcUtils.offendedPoliticalType = offendedPoliticalType;
    }

    public static void setBelievability(double believability) {
        FakeNewsCalcUtils.believability = believability;
    }
}
