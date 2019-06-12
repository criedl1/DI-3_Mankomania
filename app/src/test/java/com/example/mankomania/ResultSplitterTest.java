package com.example.mankomania;

import com.example.mankomania.dice.ResultSplitter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResultSplitterTest {
    private int [] results={2,3,4,5,6,7,8,9,10,11,12};
    private int [] splittedResults;
    private int min;
    private int max;

    @Before
    public void setUp() {
        splittedResults = new int[2];
        min=1;
        max=6;

    }
    @After
    public void tearDown(){
        splittedResults=null;
    }
    @Test
    public void possibilityTest(){
        for (int i = 0; i < results.length; i++) {
            splittedResults = ResultSplitter.splitResult(results[i]);
            Assert.assertEquals(results[i],splittedResults[0]+splittedResults[1]);
        }
    }
    @Test
    public void biggerMinFirst2(){
        splittedResults = ResultSplitter.splitResult(results[0]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst2(){
        splittedResults = ResultSplitter.splitResult(results[0]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst3(){
        splittedResults = ResultSplitter.splitResult(results[1]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst3(){
        splittedResults = ResultSplitter.splitResult(results[1]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst4(){
        splittedResults = ResultSplitter.splitResult(results[2]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst4(){
        splittedResults = ResultSplitter.splitResult(results[2]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst5(){
        splittedResults = ResultSplitter.splitResult(results[3]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst5(){
        splittedResults = ResultSplitter.splitResult(results[3]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst6(){
        splittedResults = ResultSplitter.splitResult(results[4]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst6(){
        splittedResults = ResultSplitter.splitResult(results[4]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst7(){
        splittedResults = ResultSplitter.splitResult(results[5]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst7(){
        splittedResults = ResultSplitter.splitResult(results[5]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst8(){
        splittedResults = ResultSplitter.splitResult(results[6]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst8(){
        splittedResults = ResultSplitter.splitResult(results[6]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst9(){
        splittedResults = ResultSplitter.splitResult(results[7]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst9(){
        splittedResults = ResultSplitter.splitResult(results[7]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst10(){
        splittedResults = ResultSplitter.splitResult(results[8]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst10(){
        splittedResults = ResultSplitter.splitResult(results[8]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst11(){
        splittedResults = ResultSplitter.splitResult(results[9]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst11(){
        splittedResults = ResultSplitter.splitResult(results[9]);
        Assert.assertTrue(splittedResults[0]<=max);
    }
    @Test
    public void biggerMinFirst12(){
        splittedResults = ResultSplitter.splitResult(results[10]);
        Assert.assertTrue(splittedResults[0]>=min);
    }
    @Test
    public void smallerMaxFirst12(){
        splittedResults = ResultSplitter.splitResult(results[10]);
        Assert.assertTrue(splittedResults[0]<=max);
    }















    @Test
    public void biggerMinSecond2(){
        splittedResults = ResultSplitter.splitResult(results[0]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond2(){
        splittedResults = ResultSplitter.splitResult(results[0]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond3(){
        splittedResults = ResultSplitter.splitResult(results[1]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond3(){
        splittedResults = ResultSplitter.splitResult(results[1]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond4(){
        splittedResults = ResultSplitter.splitResult(results[2]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond4(){
        splittedResults = ResultSplitter.splitResult(results[2]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond5(){
        splittedResults = ResultSplitter.splitResult(results[3]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond5(){
        splittedResults = ResultSplitter.splitResult(results[3]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond6(){
        splittedResults = ResultSplitter.splitResult(results[4]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond6(){
        splittedResults = ResultSplitter.splitResult(results[4]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond7(){
        splittedResults = ResultSplitter.splitResult(results[5]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond7(){
        splittedResults = ResultSplitter.splitResult(results[5]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond8(){
        splittedResults = ResultSplitter.splitResult(results[6]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond8(){
        splittedResults = ResultSplitter.splitResult(results[6]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond9(){
        splittedResults = ResultSplitter.splitResult(results[7]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond9(){
        splittedResults = ResultSplitter.splitResult(results[7]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond10(){
        splittedResults = ResultSplitter.splitResult(results[8]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond10(){
        splittedResults = ResultSplitter.splitResult(results[8]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond11(){
        splittedResults = ResultSplitter.splitResult(results[9]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond11(){
        splittedResults = ResultSplitter.splitResult(results[9]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
    @Test
    public void biggerMinSecond12(){
        splittedResults = ResultSplitter.splitResult(results[10]);
        Assert.assertTrue(splittedResults[1]>=min);
    }
    @Test
    public void smallerMaxSecond12(){
        splittedResults = ResultSplitter.splitResult(results[10]);
        Assert.assertTrue(splittedResults[1]<=max);
    }
}
