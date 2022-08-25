public abstract class AntennaMobile extends Antenna {
    int signalStrength;
	boolean connected;
	public AntennaMobile(int signalStrength,boolean connected){
		this.signalStrength=signalStrength;
		this.connected=connected;
	}

    public boolean isConnected(){
		return connected;
	}

    public void setNetwork(boolean isConnected){
		connected=isConnected;
	}

    public int getSignalStrength(){
		return signalStrength;
	}

    public void setSignalStrength(int n){
		signalStrength=n;
	}

}
