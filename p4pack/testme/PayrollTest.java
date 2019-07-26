import org.junit.*; // Rule, Test
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import java.util.*;

import payroll.*;

public class PayrollTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("PayrollTest");
  }
  
  // 1 second max per method tested
  @Rule public Timeout globalTimeout = Timeout.seconds(1);

  // BEGIN TESTS HERE.

  // Eliminate this test after you get the hang of things
  @Test public void example(){
    assertEquals(5,5);
    assertFalse(5==6);
    assertTrue(6==6);
  }
  @Test public void emptyDict(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    assertEquals(0,d.size());
  }
  @Test public void put_nullkey(){
    try {
      d.put(null, 5);
      fail("cant put a null");
    }catch(UnsupportedOperationException u){
      }
  }
}