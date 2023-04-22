package net.swordie.ms.world.field;

public class Portal {
    private PortalType type;
    private String name = "";
    private int targetMapId;
    private String targetPortalName = "";
    private int x;
    private int y;
    private int horizontalImpact;
    private int verticalImpact;
    private String script = "";
    private boolean onlyOnce;
    private boolean hideTooltip;
    private int delay;
    private int id;

    public Portal(int id, PortalType type, String name, int targetMapId, String targetPortalName, int x, int y,
                  int horizontalImpact, int verticalImpact, String script, boolean onlyOnce, boolean hideTooltip,
                  int delay) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.targetMapId = targetMapId;
        this.targetPortalName = targetPortalName;
        this.x = x;
        this.y = y;
        this.horizontalImpact = horizontalImpact;
        this.verticalImpact = verticalImpact;
        this.script = script;
        this.onlyOnce = onlyOnce;
        this.hideTooltip = hideTooltip;
        this.delay = delay;
    }

    public Portal(int id) {
        this.id = id;
    }

    public PortalType getType() {
        return type;
    }

    public void setType(PortalType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetMapId() {
        return targetMapId;
    }

    public void setTargetMapId(int targetMapId) {
        this.targetMapId = targetMapId;
    }

    public String getTargetPortalName() {
        return targetPortalName;
    }

    public void setTargetPortalName(String targetPortalName) {
        this.targetPortalName = targetPortalName;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHorizontalImpact() {
        return horizontalImpact;
    }

    public void setHorizontalImpact(int horizontalImpact) {
        this.horizontalImpact = horizontalImpact;
    }

    public int getVerticalImpact() {
        return verticalImpact;
    }

    public void setVerticalImpact(int verticalImpact) {
        this.verticalImpact = verticalImpact;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public boolean isOnlyOnce() {
        return onlyOnce;
    }

    public void setOnlyOnce(boolean onlyOnce) {
        this.onlyOnce = onlyOnce;
    }

    public boolean isHideTooltip() {
        return hideTooltip;
    }

    public void setHideTooltip(boolean hideTooltip) {
        this.hideTooltip = hideTooltip;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public Portal deepCopy() {
        Portal copy = new Portal(getId(), getType(), getName(), getTargetMapId(), getTargetPortalName(), getX(), getY(), getHorizontalImpact(), getVerticalImpact(), getScript(), isOnlyOnce(), isHideTooltip(), getDelay());
        return copy;
    }

    @Override
    public String toString() {
        return "Portal{" +
                "name='" + name + '\'' +
                ", targetPortalName='" + targetPortalName + '\'' +
                ", script='" + script + '\'' +
                '}';
    }
}
