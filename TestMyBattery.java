import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyBattery{
	@Test
	public void test1(){
		EspressOSMobile phone=new EspressOSMobile();
		Battery mybattery=new Battery(110);
		phone.changeBattery(mybattery);
		assertEquals(25,phone.mybattery);
	}
	@Test
	public void test2(){
		EspressOSMobile phone=new EspressOSMobile();
		Battery mybattery=new Battery(80);
		phone.changeBattery(mybattery);
		assertEquals(80,phone.mybattery);
	}
}
