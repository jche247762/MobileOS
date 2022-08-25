import java.util.ArrayList;
import java.util.*;

public class EspressOSContact
{
	public static final int MAXIMUM_CHAT_HISTORY = 20;

	/* given */
	public String[] chatHistory;
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public List<String> textMessage;

	public EspressOSContact(String fname, String lname, String pnumber) {
		/* given */
		chatHistory = new String[MAXIMUM_CHAT_HISTORY];
		this.firstName=fname;
		this.lastName=lname;
		this.phoneNumber=pnumber;
		this.textMessage = new ArrayList<>();
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/* if firstName is null the method will do nothing and return
	 */
	public void updateFirstName(String firstName) {
		if(firstName==null){
			return;
		}else{
			this.firstName=firstName;
		}
	}
	/* if lastName is null the method will do nothing and return
	 */
	public void updateLastName(String lastName) {
		if(lastName==null){
			return;
		}else{
			this.lastName=lastName;
		}
	}

	/* only allows integer numbers (long type) between 6 and 14 digits
	 * no spaces allowed, or prefixes of + symbols
	 * leading 0 digits are allowed
	 * return true if successfully updated
	 * if number is null, number is set to an empty string and the method returns false
	 */
	public boolean updatePhoneNumber(String number) {
		if(number==null){
			return false;
		}else if(number.matches("[0-9]{6,14}")){
			this.phoneNumber=number;
			return true;
		}else{
			return false;
		}
	}

	/* add a new message to the chat
	 * The message will take the form
	 * whoSaidIt + ": " + message
	 *
	 * if the history is full, the oldest message is replaced
	 * Hint: keep track of the position of the oldest or newest message!
	 */
	public void addChatMessage(String whoSaidIt, String message) {
		String a=whoSaidIt+": "+message;
		textMessage.add(a);

		if(textMessage.size()>MAXIMUM_CHAT_HISTORY){
			textMessage.remove(0);
		}
		this.chatHistory = this.textMessage.toArray(this.chatHistory);
	}
	/* after this, both last and oldest message should be referring to index 0
	 * all entries of chatHistory are set to null
	 */
	public void clearChatHistory() {
		for(int i=0;i<MAXIMUM_CHAT_HISTORY;i++){
			chatHistory[i]=null;
		}
		this.textMessage.clear();
	}

	/* returns the last message
	 * if no messages, returns null
	 */
	public String getLastMessage() {
		if(this.textMessage.size() == 0){
			return null;
		}else{
			return this.textMessage.get(this.textMessage.size() - 1);
		}
	}
	/* returns the oldest message in the chat history
	 * depending on if there was ever MAXIMUM_CHAT_HISTORY messages
	 * 1) less than MAXIMUM_CHAT_HISTORY, returns the first message
	 * 2) more than MAXIMUM_CHAT_HISTORY, returns the oldest
	 * returns null if no messages exist
	 */
	public String getOldestMessage() {
		if(this.textMessage.size() == 0){
			return null;
		}
		return this.textMessage.get(0);
	}

	/* creates a copy of this contact
	 * returns a new EspressOSContact object with all data same as the current object
	 */
	public EspressOSContact copy()
	{
		EspressOSContact a = new EspressOSContact(this.firstName,this.lastName,this.phoneNumber);
		for(int i=0;i<MAXIMUM_CHAT_HISTORY;i++){
			a.chatHistory[i]=this.chatHistory[i];
		}
		a.textMessage = new ArrayList<>(this.textMessage);
		return a;
	}
	public void printMessagesOldestToNewest(){
		int i=0;
		while(i<MAXIMUM_CHAT_HISTORY){
			if(chatHistory[i]!=null){
				System.out.printf("%d %s\n", i, chatHistory[i]);
			}
			i=i+1;
		}
	}
	public String toString(){
		return this.firstName + " " + this.lastName;
	}
}
