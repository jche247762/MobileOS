public abstract class mobilePhoneBattery extends Battery {
	int battery;
    public void setLevel(int value){
		this.battery=value;
	}

    public int getLevel(){
		return battery;
	}

}
