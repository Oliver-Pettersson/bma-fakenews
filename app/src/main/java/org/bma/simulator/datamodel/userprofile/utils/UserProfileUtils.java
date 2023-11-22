package org.bma.simulator.datamodel.userprofile.utils;

import java.util.*;

import org.bma.simulator.datamodel.UserNode;
import org.bma.simulator.datamodel.userprofile.*;
import org.bma.simulator.datamodel.userprofile.attributetype.AgeGroup;
import org.bma.simulator.datamodel.userprofile.attributetype.PoliticalType;
import org.graphstream.graph.Node;

public class UserProfileUtils {
    private static final Map<String, ProfileOccurrence> profiles = new HashMap<>(5, 1.0f);

    static {
        profiles.put(UserProfileConstants.DEFAULT_CELEBRITY, new ProfileOccurrence(new CelebrityUserProfile(UserProfileConstants.DEFAULT_CELEBRITY, PoliticalType.LEFT, AgeGroup.SENIOR), 10));
        profiles.put(UserProfileConstants.DEFAULT_USER, new ProfileOccurrence(new HumanUserProfile(PoliticalType.NONE, AgeGroup.YOUNG_ADULT), 10));
        profiles.put(UserProfileConstants.BOT_TYPE, new ProfileOccurrence(new BotUserProfile(), 0));
    }

    private UserProfileUtils() {
    }

    public static Map<String, ProfileOccurrence> getProfiles() {
        return profiles;
    }

    public static void spreadUserProfiles(List<Node> nodes) {
        List<ProfileOccurrence> profileOccurrences = profiles.values().stream().toList();
        Collections.shuffle(nodes);
        assignCelebrityProfiles(nodes, profileOccurrences.stream().filter(
                profileOccurrence -> profileOccurrence.getProfile() instanceof CelebrityUserProfile).toList());
        profileOccurrences = profileOccurrences.stream().filter(
                profileOccurrence -> !(profileOccurrence.getProfile() instanceof CelebrityUserProfile)).toList();
        nodes = nodes.stream().filter(node -> !((UserNode) node.getAttribute("data")).isCelebrity()).toList();
        UserNode data;
        int currentProfileIndex = 0;
        int currentIndex = 0;
        int occurrences = 0;
        ProfileOccurrence currentProfile = null;
        for (Node node :
                nodes) {
            if (currentIndex == 0) {
                while (occurrences == 0 && currentProfileIndex < profileOccurrences.size()) {
                    currentProfile = profileOccurrences.get(currentProfileIndex);
                    occurrences = currentProfile.getOccurrence();
                    currentProfileIndex++;
                }
                if (currentProfileIndex >= profileOccurrences.size())
                    break;
            }
            data = (UserNode) node.getAttribute("data");
            data.setProfile(currentProfile.getProfile());
            currentIndex++;
            if (currentIndex >= occurrences) {
                currentIndex = 0;
            }
        }
    }

    private static void assignCelebrityProfiles(List<Node> nodes, List<ProfileOccurrence> celebrityProfileOccurrences) {
        List<Node> celebrityNodes = nodes.stream().filter(node -> ((UserNode) node.getAttribute("data")).isCelebrity()).toList();
        UserNode data;
        int currentProfileIndex = 0;
        int currentIndex = 0;
        int occurrences = 0;
        ProfileOccurrence currentProfile = null;
        for (Node node :
                celebrityNodes) {
            if (currentIndex == 0) {
                currentProfile = celebrityProfileOccurrences.get(currentProfileIndex);
                occurrences = currentProfile.getOccurrence();
                currentProfileIndex++;
            }
            data = (UserNode) node.getAttribute("data");
            data.setProfile(currentProfile.getProfile());
            currentIndex++;
            if (currentIndex == occurrences) {
                currentIndex = 0;
            }
        }
    }
}
