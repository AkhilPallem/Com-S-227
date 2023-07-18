package lab3;
import balloon4.Balloon;
import org.junit.Test;
import static org.junit.Assert.*;

public class BalloonTests {
	
	@Test
	public void testInitial(){
		Balloon b = new Balloon(0);
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testPop(){
		Balloon b = new Balloon(0);
		assertEquals(false, b.isPopped());
    }
	
	@Test
    public void testPop3(){
		Balloon b = new Balloon(10);
		assertEquals(false, b.isPopped());
    }
	

	@Test
    public void testBlow(){
		Balloon b = new Balloon(10);
		b.blow(5);
		assertEquals(5, b.getRadius());
    }
	
	@Test
    public void testDeflate(){
		Balloon b = new Balloon(100);
		b.deflate();
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testPop2(){
		Balloon b = new Balloon(1000);
		b.pop();
		assertEquals(0, b.getRadius());
    }
	
	@Test
    public void testBlowRadius1(){
		Balloon b = new Balloon(10);
		b.blow(1000);
		assertEquals(true, b.isPopped());
    }
	
	@Test
    public void testBlowRadius2(){
		Balloon b = new Balloon(10);
		b.pop();
		b.blow(5);
		assertEquals(10, b.getRadius());
    }

}
