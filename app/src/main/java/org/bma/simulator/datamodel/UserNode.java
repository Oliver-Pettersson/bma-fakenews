package org.bma.simulator.datamodel;

public class UserNode {
    private String id;
    private boolean isInfected;
    private String culpritId;
    private String infectionWave;

    public UserNode(String id) {
        this.id = id;
    }

    public UserNode(String id, boolean isInfected, String culpritId, String infectionWave) {
        this.id = id;
        this.isInfected = isInfected;
        this.culpritId = culpritId;
        this.infectionWave = infectionWave;
    }

    public Object[][] getData() {
        return new Object[][]{
                {"id", this.id},
                {"infected", this.isInfected},
                {"culprit_id", this.culpritId},
                {"wave", this.infectionWave},
        };
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInfected() {
        return isInfected;
    }

    public void setInfected(boolean infected) {
        isInfected = infected;
    }

    public String getCulpritId() {
        return culpritId;
    }

    public void setCulpritId(String culpritId) {
        this.culpritId = culpritId;
    }

    public String getInfectionWave() {
        return infectionWave;
    }

    public void setInfectionWave(String infectionWave) {
        this.infectionWave = infectionWave;
    }
}
