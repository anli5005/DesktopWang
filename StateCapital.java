package Lab10_3;

public class StateCapital {

    private String state;
    private String capital;

    public StateCapital(String state, String capital){
        this.state = state;
        this.capital = capital;
    }

    public boolean equals(Object o){
        StateCapital imposter = (StateCapital) o;
        if(state.equals(imposter.state)){
            return true;
        }
        return false;
    }

	public String getState() {
		return state;
	}

	public String getCapital() {
		return capital;
    }       
}
