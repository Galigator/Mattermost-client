package com.cloudtemple.mattermost.traders.user;

public class Timezone {
    protected String automaticTimezone;
    protected String manualTimezone;
    protected boolean useAutomaticTimezone;

    public String getAutomaticTimezone() {
        return automaticTimezone;
    }

    public void setAutomaticTimezone(String automaticTimezone) {
        this.automaticTimezone = automaticTimezone;
    }

    public String getManualTimezone() {
        return manualTimezone;
    }

    public void setManualTimezone(String manualTimezone) {
        this.manualTimezone = manualTimezone;
    }

    public boolean isUseAutomaticTimezone() {
        return useAutomaticTimezone;
    }

    public void setUseAutomaticTimezone(boolean useAutomaticTimezone) {
        this.useAutomaticTimezone = useAutomaticTimezone;
    }
}
