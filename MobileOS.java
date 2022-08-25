
import java.util.ArrayList;
import java.util.*;
public class EspressOSMobile
{
 public static final int MAXIMUM_CONTACTS = 10;


 /* Use this to store contacts. Do not modify. */
 protected EspressOSContact[] contacts;
 public EspressOSContact contactInformation;
 public boolean on;
 public int battery;
 public boolean connected;
 public int signalStrength;
 //public List<EspressOSContact> mycontact;

 public EspressOSMobile() {
   /* given */
   contacts = new EspressOSContact[MAXIMUM_CONTACTS];
   this.on=false;
   this.battery=25;
   this.connected=false;
   this.signalStrength=0;
   contactInformation=new EspressOSContact("EspressOS","Incorporated","180076237867");
   contactInformation.addChatMessage("EspressOS","Thank you for choosing EspressOS products");
   //this.mycontact = new ArrayList<>();
 }

 /* returns a copy of the owner contact details
  * return null if the phone is off
  */
 public EspressOSContact getCopyOfOwnerContact() {
   //System.out.println(this.on);

   if(on){
     //System.out.println("sss");
     return contactInformation.copy();
   }else{
     return null;
 }
}

 /* only works if phone is on
  * will add the contact in the array only if there is space and does not exist
  * The method will find an element that is null and set it to be the contact
  */
 public boolean addContact(EspressOSContact contact) {
   if(!isPhoneOn()){
     return false;
   }else{
     int i=0;
     while(i<contacts.length){
       if(contacts[i]!=null){
         if(contacts[i] == contact){
           return false;//duplicate
         }
       }
       i++;
     }
     //add contact
     //this.mycontact.add(contact);
     int j=0;
     while(j<contacts.length){
       if(contacts[j]==null){
         contacts[j]=contact;
         return true;
       }
       j++;
     }
     return false;
   }
 }

 /* only works if phone is on
  * find the object and set the array element to null
  * return true on successful remove
  */
 public boolean removeContact(EspressOSContact contact) {
   if(!isPhoneOn()){
     return false;
   }else{
     boolean ans = false;
     int i=0;
     while(i<contacts.length){
       if(contacts[i] == contact){
         contacts[i]=null;
         //this.mycontact.remove(contacts[i]);
         ans = true;
     }
     i++;
   }
   return ans;
 }
}
 /* only works if phone is on
  * return the number of contacts, or -1 if phone is off
  */
 public int getNumberOfContacts() {
   int contactNumber=0;
   if(isPhoneOn()==false){
     return -1;
   }else{
     int i=0;
     while(i<contacts.length){
       if(contacts[i]!=null){
         contactNumber++;
       }
       i++;
     }
   }
   return contactNumber;
 }

 /* only works if phone is on
  * returns all contacts that match firstname OR lastname
  * if phone is off, or no results, null is returned
  */
 public EspressOSContact[] searchContact(String name) {
   //System.out.println("aa");
   ArrayList<EspressOSContact> result = new ArrayList<>();
   if(!on){
     return null;
   }

   int i=0;
   while(i<contacts.length){
     if(contacts[i]!=null){
       if(contacts[i].firstName.equals(name)||contacts[i].lastName.equals(name)){
         result.add(contacts[i]);
       }
     }
     i++;
   }
   //System.out.println("bb");
   //System.out.println(contact);
   if(result.size() == 0){
     return null;
   }

   EspressOSContact[] array=new EspressOSContact[result.size()];
   array = result.toArray(array);
   return array;

 }

 /* returns true if phone is on
  */
 public boolean isPhoneOn() {
   if(on==true){
     return true;
   }else{
     return false;
   }
 }

 /* when phone turns on, it costs 5 battery for startup. network is initially disconnected
  * when phone turns off it costs 0 battery, network is disconnected
  * always return true if turning off
  * return false if do not have enough battery level to turn on
  * return true otherwise
  */
  public boolean setPhoneOn(boolean on) {
   if(on==false){
     this.on = false;
     this.connected=false;
     return true;
   }else{
     if(battery <= 5){
       return false;
     }else{
       this.on = true;
       this.battery=battery-5;
       return true;
     }
   }
  }

 /* Return the battery life level. if the phone is off, zero is returned.
  */
 public int getBatteryLife() {
   if(isPhoneOn()==false){
     return 0;
   }else{
     return battery;
   }
 }

 /* Change battery of phone.
  * On success. The phone is off and new battery level adjusted and returns true
  * If newBatteryLevel is outside manufacturer specification of [0,100], then
  * no changes occur and returns false.
  */
 public boolean changeBattery(Battery battery) {
   int level = battery.getLevel();
   if(level>=0&&level<=100){
     this.battery = level;
     this.on = false;
     return true;
   }else{
     return false;
   }
 }

 /* only works if phone is on.
  * returns true if the phone is connected to the network
  */
 public boolean isConnectedNetwork() {
   if(!isPhoneOn()){
     return false;
   }else if(connected==true){
     return true;
   }else{
     return false;
   }
 }

 /* only works if phone is on.
  * when disconnecting, the signal strength becomes zero
  */
 public void disconnectNetwork() {
   if(!on){
     return;
   }
   if(connected==false){
     signalStrength=0;
   }
 }

 /* only works if phone is on.
  * Connect to network
  * if already connected do nothing
  * if connecting:
  *  1) signal strength is set to 1 if it was 0
  *  2) signal strength will be the previous value if it is not zero
  *  3) it will cost 2 battery life to do so
  * returns the network connected status
  */
 public boolean connectNetwork() {
   if(!isPhoneOn()){
     return false;
   }else if(connected==true){
     return true;
   }else{
     battery=battery-2;
     connected=true;
     if(battery<=0){
       on=false;
       connected=false;
       signalStrength=0;
       return false;//no battery-->do not connect to the network
     }else if(signalStrength==0){
       signalStrength=1;
       return true;
     }else if(signalStrength!=0){
       return true;
     }
   }
   return false;
 }

 /* only works if phone is on.
  * returns a value in range [1,5] if connected to network
  * otherwise returns 0
  */
 public int getSignalStrength() {
   if(isPhoneOn()==false){
     return 0;
   }else if(connected==true&&signalStrength>=1&&signalStrength<=5){
     return signalStrength;
   }else{
     return 0;
   }
 }

 /* only works if phone is on.
  * sets the signal strength and may change the network connection status to on or off
  * signal of 0 disconnects network
  * signal [1,5] can connect to network if not already connected
  * if the signal is set outside the range [0,5], nothing will occur and will return false
  */
 public boolean setSignalStrength(int x) {
   if(isPhoneOn()==false){
     return false;
   }else if(x>=1&&x<=5){
     connected=true;
     signalStrength=x;
     return true;
   }else if(x==0){
     connected=false;
     signalStrength = 0;
     return true;
   }else if(x>5||x<0){
     return false;
   }
   return false;
   }

 /* changes the antenna object
  * signal strength is set to default and is not connected to a network
  * if this constraint is violated then the antenna should not be changed.
  * return true if antenna is changed.
  */
 public boolean changeAntenna(Antenna antenna) {
   if(antenna==null){
     signalStrength=0;
     connected=false;
     return false;
   }
   if(connected&&0<=antenna.getSignalStrength()&&antenna.getSignalStrength()<=5){
     antenna.setSignalStrength(signalStrength);
     connected=true;
     return true;
   }
   if(!connected&&antenna.getSignalStrength() >= 1 &&antenna.getSignalStrength()<=5){
     signalStrength=antenna.getSignalStrength();
     connected=true;
     return true;
   }
   if(antenna.getSignalStrength()<0||antenna.getSignalStrength()>5){
     return false;
   }
   return false;
 }

 /* each charge increases battery by 10
  * the phone has overcharge protection and cannot exceed 100
  * returns true if the phone was charged by 10
  */
 public boolean chargePhone() {
   battery=battery+10;
   if(battery>100){
     battery=100;
     return false;
   }else{
     return true;
   }

 }

 /* Use the phone which costs k units of battery life.
  * if the activity exceeds the battery life, the battery automatically
  * becomes zero and the phone turns off.
  */
 public void usePhone(int k) {
   battery=battery-k;
   if(battery<=0){
     battery=0;
     setPhoneOn(false);
   }
 }
}
