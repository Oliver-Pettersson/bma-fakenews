package org.bma.simulator.datamodel;

public class UserNode {
    private String id;
    private boolean isInfected;
    private String culpritId;
    private String infectionWave;
    private int amountOfFollowers;
    private int amountOfFollows;

    public UserNode(String id) {
        this.id = id;
    }

    public UserNode(String id, boolean isInfected, String culpritId, String infectionWave, int amountOfFollowers, int amountOfFollows) {
        this.id = id;
        this.isInfected = isInfected;
        this.culpritId = culpritId;
        this.infectionWave = infectionWave;
        this.amountOfFollowers = amountOfFollowers;
        this.amountOfFollows = amountOfFollows;
    }

    public Object[][] getData() {
        return new Object[][]{
                {"id", this.id},
                {"infected", this.isInfected},
                {"culprit_id", this.culpritId},
                {"wave", this.infectionWave},
                {"follower_count", this.amountOfFollowers},
                {"follow_count", this.amountOfFollows}
        };
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    public void setCulpritId(String culpritId) {
        this.culpritId = culpritId;
    }

    public void setInfectionWave(String infectionWave) {
        this.infectionWave = infectionWave;
    }

    public void setAmountOfFollowers(int amountOfFollowers) {
        this.amountOfFollowers = amountOfFollowers;
    }

    public void setAmountOfFollows(int amountOfFollows) {
        this.amountOfFollows = amountOfFollows;
    }
}
