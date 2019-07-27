import org.junit.*; // Rule, Test
import org.junit.rules.Timeout;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import payroll.*;

public class PayrollTest {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("PayrollTest");
  }
  
  // 1 second max per method tested
  @Rule public Timeout globalTimeout = Timeout.seconds(1);

  // BEGIN TESTS HERE.

  //DICT TESTS/////////////////////////////////////////////////////////////////
  //Constructor tests////
  @Test public void createDict(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    assertEquals(0,d.size());
  }
  //put() tests////
  @Test public void testPut(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try {
      d.put("key1",0);
      d.put("key2",1);
      assertEquals((Integer)0,(Integer)d.get("key1"));
      assertEquals((Integer)1,(Integer)d.get("key2"));
      d.put("key2",2);
      assertEquals((Integer)2,(Integer)d.get("key2"));
      assertEquals((Integer)2,(Integer)d.size());
    }catch(UnsupportedOperationException u){System.out.print("nosuch test");
    }
  }
    //test exceptions
  @Test (expected = UnsupportedOperationException.class)
  public void testPut_unSup(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put(null, 5);
  }
  //size() tests////
  @Test public void testSize(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    assertEquals(0,d.size());
    d.put("key1",1);
    assertEquals(1,d.size());
    assertNotEquals(2,d.size());
  }
  //has() tests////
  @Test public void testHas(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    assertTrue(d.has("key1"));
    assertFalse(d.has("key2"));
  }
  //get() tests////
  @Test public void testGet(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try{
      d.put("key1",1);
      assertEquals((Integer)1,(Integer)d.get("key1"));
      //test exception
      //
    }catch (NoSuchElementException no){System.out.print("nosuch test");
    }
  }
  @Test (expected = NoSuchElementException.class)
  public void testGet_noSuch_null(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.get("null");
  }
  @Test (expected = NoSuchElementException.class)
  public void testGet_noSuch_norm() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.get("key2");
  }
  //pop() tests////
  @Test public void testPop(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    try{
      d.put("key1",1);
      d.put("key2",2);
      assertEquals(2,d.size());
      d.pop("key1");
      assertEquals(1,d.size());

    }catch (NoSuchElementException no){System.out.print("nosuch test");
    }
    catch (NullPointerException nu){System.out.print("null test");
    }
  }
  //test exceptions
  @Test (expected = NoSuchElementException.class)
  public void testPop_noSuch(){
    Dict<String,Integer> d = new Dict<String,Integer>();
      d.put("key1",1);
      d.put("key2",2);
      d.pop("key3");
  }
  @Test (expected = NullPointerException.class)
  public void testPop_null(){
    Dict<String,Integer> d = new Dict<String,Integer>();
      d.put("key1",1);
      d.put("key2",2);
      d.pop(null);
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
    assertEquals((Integer)0,(Integer)p.employees().size());
    assertEquals((Integer)0,(Integer)p.topSalary());
    //pre-made payroll
    Dict<String,Integer> d = new Dict<String,Integer>();//make a Dict, then pass it into constructor
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    assertEquals((Integer)3,(Integer)pp.employees().size());
    List<String> testlist = Stream.of("key1","key2","key3").collect(Collectors.toList());
    assertEquals(testlist,pp.employees());
  }
  //employees() tests
  @Test public void testEmployees(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    List<String> testlist = Stream.of("key1","key2","key3").collect(Collectors.toList());
    assertEquals(testlist,pp.employees());
    //making sure that's not just coincidence
    testlist.add("key4");
    assertNotEquals(testlist,pp.employees());
    pp.hire("key4",4);
    assertEquals(testlist,pp.employees());
  }
  //getSalary() tests
  @Test public void testGetSalary(){
    try{
      Dict<String,Integer> d = new Dict<String,Integer>();
      d.put("key1",1);
      d.put("key2",2);
      d.put("key3",3);
      Payroll pp = new Payroll(d);
      assertEquals((Integer)2,(Integer)pp.getSalary("key2"));
    }catch (NullPointerException nu){System.out.print("null test");
    }catch (NoSuchElementException no){System.out.print("such test");
    }
  }
  @Test (expected = NullPointerException.class)
  public void testGetSalary_null(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    pp.getSalary(null);
  }
  @Test (expected = NoSuchElementException.class)
  public void testGetSalary_noSuch(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    pp.getSalary("key4");
  }
  //topSalary() tests
  @Test public void testTopSalary(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    assertEquals((Integer)3,(Integer)pp.topSalary());
    pp.hire("key4",4);
    assertEquals((Integer)4,(Integer)pp.topSalary());
  }
  //hire() tests
  @Test public void testHire(){
    try{
        Dict<String,Integer> d = new Dict<String,Integer>();
        d.put("key1",1);
        d.put("key2",2);
        d.put("key3",3);
        Payroll pp = new Payroll(d);
        pp.hire("key4",4);
        pp.hire("key4",0);
        assertEquals((Integer)4,(Integer)pp.employees().size());
    }catch (NullPointerException nu){System.out.print("null test");
    }catch (RuntimeException ru){System.out.print("run test");
    }
  }
  @Test (expected = NullPointerException.class)
  public void testHire_null() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.hire(null,10);
  }
  @Test (expected = RuntimeException.class)
  public void testHire_run() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.hire("key5",-9);
  }
  //fire() tests
  @Test public void testFire(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    assertTrue(pp.fire("key3"));
    assertFalse(pp.fire("key3"));
    assertEquals((Integer)2,(Integer)pp.employees().size());
  }
  //monthlyExpense() tests
  @Test public void testMonthlyExpense(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    d.put("key4",4);
    d.put("key5",5);
    d.put("key6",6);
    Payroll pp = new Payroll(d);
    assertEquals((Integer)2,(Integer)pp.monthlyExpense());//divides to 1.75
    pp.fire("key5");
    assertEquals((Integer)2,(Integer)pp.monthlyExpense());//test that it rounds up;divides to 1.25
  }
  //giveRaise() tests
  //give to EVERYONE
  @Test public void test_everyone_GiveRaise(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    try {
        pp.giveRaise(0.2);
        assertEquals(2,pp.getSalary("key1"));
        assertEquals(3,pp.getSalary("key2"));
        assertEquals(4,pp.getSalary("key3"));
    }catch (RuntimeException ru){System.out.print("run test");
    }
  }
  @Test (expected = RuntimeException.class)
  public void test_everyoneGiveRaise_run() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.giveRaise(-0.05);
  }
  //give to ONLY ONE
  @Test public void test_onlyONE_GiveRaise(){
    Dict<String,Integer> d = new Dict<String,Integer>();
    d.put("key1",1);
    d.put("key2",2);
    d.put("key3",3);
    Payroll pp = new Payroll(d);
    try{
        pp.giveRaise("key2",0.2);
        assertEquals(3,pp.getSalary("key2"));
        assertEquals(1,pp.getSalary("key1"));
        assertEquals(3,pp.getSalary("key3"));
    }catch (NullPointerException nu){System.out.print("null test");
    }catch (NoSuchElementException no){System.out.print("nosuch test");
    }catch (RuntimeException ru){System.out.print("run test");
    }
  }
  @Test (expected = RuntimeException.class)
  public void test_onlyONEGiveRaise_run() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.giveRaise("key2",-0.2);
  }
  @Test (expected = NullPointerException.class)
  public void test_onlyONEGiveRaise_null() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.giveRaise(null,0.2);
  }
  @Test (expected = NoSuchElementException.class)
  public void test_onlyONEGiveRaise_noSuch() {
    Dict<String, Integer> d = new Dict<String, Integer>();
    d.put("key1", 1);
    d.put("key2", 2);
    d.put("key3", 3);
    Payroll pp = new Payroll(d);
    pp.giveRaise("key4",0.2);
  }
}
/*possible:
    3,9,10,12

 */
