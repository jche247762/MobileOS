import java.util.*;
public class Test{
	public static void main(String[] args){
		EspressOSMobile mobile = new EspressOSMobile();
		//System.out.println(mobile.contactInformation);
		//System.out.println(Arrays.toString(mobile.contactInformation.chatHistory));
		mobile.setPhoneOn(true);
		EspressOSContact copy = mobile.getCopyOfOwnerContact();

		//System.out.println(mobile.on);
		EspressOSContact test = new EspressOSContact("Jeff", "A","123");
		for(int i=0;i<10;i++){
			mobile.addContact(test);
		}
		System.out.println(Arrays.toString(mobile.searchContact("Jeff")));
		// System.out.println(Arrays.toString(mobile.searchContact("A")));
		// System.out.println(Arrays.toString(mobile.searchContact("C")));



// 		EspressOSContact test = new EspressOSContact("first", "last","12344566");
// 		System.out.println(test);
// 		EspressOSContact copy1 = test.copy();
// 		System.out.println(copy1);
// 		copy1.addChatMessage("bbb","sth");
// 		System.out.println(copy1.getOldestMessage());


// 		System.out.println(copy1.getLastMessage());


		//System.out.println(copy.textMessage);
		//System.out.println(copy.getLastMessage());
		//copy.addChatMessage("ccc","hhh");

		// for(int i = 0; i < 19; i++){
		// 	copy.addChatMessage("aaa", "something");
		// }
		// copy.addChatMessage("bbb","sth");
		// System.out.println(copy.getOldestMessage());
		// System.out.println(copy.getLastMessage());


	}
}
