package covidstatics.covidstats.Entity;

public class State {
    private int stateid;
    private String state;
    private long totalcases;
    private long recovered;
    private long active;
    private long death;
    private long vaccinated;
    public State() {
    }
    public State(int stateid, String state, long totalcases, long recovered, long active, long death, long vaccinated) {
        this.stateid = stateid;
        this.state = state;
        this.totalcases = totalcases;
        this.recovered = recovered;
        this.active = active;
        this.death = death;
        this.vaccinated = vaccinated;
    }
    public int getStateid() {
        return stateid;
    }
    public void setStateid(int stateid) {
        this.stateid = stateid;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public long getTotalcases() {
        return totalcases;
    }
    public void setTotalcases(long totalcases) {
        this.totalcases = totalcases;
    }
    public long getRecovered() {
        return recovered;
    }
    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }
    public long getActive() {
        return active;
    }
    public void setActive(long active) {
        this.active = active;
    }
    public long getDeath() {
        return death;
    }
    public void setDeath(long death) {
        this.death = death;
    }
    public long getVaccinated() {
        return vaccinated;
    }
    public void setVaccinated(long vaccinated) {
        this.vaccinated = vaccinated;
    }
    @Override
    public String toString() {
        return "State [active=" + active + ", death=" + death + ", recovered=" + recovered + ", state=" + state
                + ", stateid=" + stateid + ", totalcases=" + totalcases + ", vaccinated=" + vaccinated + "]";
    }
    

    
}
