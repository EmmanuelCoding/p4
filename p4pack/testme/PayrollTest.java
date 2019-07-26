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
  //DICT TESTS/////////////////////////////////////////////////////////////////
  //Constructor tests
  @Test public void createDict(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    assertEquals(0,d.size());
  }
  //put() tests
  @Test public void put(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try {
      d.put("key1",0);
      d.put("key2",1);
      assertEquals((Integer)0,(Integer)d.get("key1"));
      assertEquals((Integer)1,(Integer)d.get("key2"));
      d.put("key2",2);
      assertEquals((Integer)2,(Integer)d.size());
      d.put(null, 5);
    }catch(UnsupportedOperationException u){
      }
  }
  //size() tests
  @Test public void testSize(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    assertEquals(0,d.size());
    d.put("key1",1);
    assertEquals(1,d.size());
    assertNotSame(2,d.size());
  }
  //has() tests
  @Test public void testHas(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    assertTrue(d.has("key1"));
    assertFalse(d.has("key2"));
  }
  //get() tests
  @Test public void testGet(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try{
      d.put("key1",1);
      assertEquals((Integer)1,(Integer)d.get("key1"));
      //test exception
      d.get("key2");
    }catch (NoSuchElementException no){
    }
  }
  //pop() tests
  @Test public void testPop(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try{
      d.put("key1",1);
      d.put("key2",2);
      assertEquals(2,d.size());
      d.pop("key1");
      assertEquals(1,d.size());
      //test exceptions
      d.pop("key1");
      d.pop(null);
    }catch (NoSuchElementException no){
    }
    catch (NullPointerException nu){
    }
  }
  //keys() tests
  @Test public void testKeys(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    assertTrue(d.keys().contains("key1"));
    assertTrue(d.keys().contains("key2"));
    assertTrue(d.keys().contains("key3"));
    assertFalse(d.keys().contains("key4"));
  }
  //clear() tests
  @Test public void testClear(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    assertEquals(3,d.size());
    d.clear();
    assertEquals(0,d.size());
  }
  //toString() tests
  @Test public void testString(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    assertEquals("{(key1:1),(key2:2),(key3:3)}",d.toString());
  }
  //PAYROLL TESTS//////////////////////////////////////////////////////////////
  //Constructor tests
  @Test public void createPayroll(){
    //empty payroll
    Payroll p = new Payroll();
    //pre-made payroll
    Dict<String,Integer> d = new Dict<String,Integer>();//make a Dict, then pass it into constructor
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
  }
}
/*fails:

 */