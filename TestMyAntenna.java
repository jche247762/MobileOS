import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyAntenna{
	@Test
	public void test3(){
		EspressOSMobile phone=new EspressOSMobile();
		Antenna myantenna=new Antenna(3);
		phone.changeAntenna(myantenna);
		assertTrue(phone.connected);
	}
	@Test
	public void test4(){
		EspressOSMobile phone=new EspressOSMobile();
		Antenna myantenna=new Antenna(6);
		phone.changeAntenna(myantenna);
		assertFalse(phone.connected);
	}
}
