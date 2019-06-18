package com.example.mankomania;

import com.example.mankomania.network.server.ServerQueueHandler;
import com.example.mankomania.network.server.cheating.CheatLogic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class CheatLogicTest {
    private CheatLogic cheatLogic;

    @Mock
    ServerQueueHandler handler;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testCheaterSimple() {
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        Assert.assertTrue(this.cheatLogic.isCurrentlyCheating(0));
    }

    @Test
    public void testCheaterAdvanced() {
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        this.cheatLogic.decrementCheater();
        this.cheatLogic.decrementCheater();
        Assert.assertTrue(this.cheatLogic.isCurrentlyCheating(0));
        this.cheatLogic.decrementCheater();
        verify(handler).sendDidCheatSuccessfully(0);
        Assert.assertFalse(this.cheatLogic.isCurrentlyCheating(0));
    }

    @Test
    public void testDoubleCheating(){
        this.cheatLogic = new CheatLogic(handler,3);
        Assert.assertTrue(this.cheatLogic.setCheater(1));
        Assert.assertFalse(this.cheatLogic.setCheater(1));
    }

    @Test
    public void testBlame(){
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        Assert.assertTrue(this.cheatLogic.blamePerson(1,0));
    }

    @Test
    public void testSelfBlame(){
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        Assert.assertFalse(this.cheatLogic.blamePerson(0,0));
    }

    @Test
    public void testBlameAdvanced(){
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        this.cheatLogic.decrementCheater();
        this.cheatLogic.decrementCheater();
        Assert.assertTrue(this.cheatLogic.blamePerson(2,0));
        this.cheatLogic.decrementCheater();
        Assert.assertFalse(this.cheatLogic.blamePerson(2,0));
    }


    @Test
    public void testDoubleBlame(){
        this.cheatLogic = new CheatLogic(handler,3);
        this.cheatLogic.setCheater(0);
        this.cheatLogic.setCheater(2);
        Assert.assertTrue(this.cheatLogic.blamePerson(1,0));
        Assert.assertFalse(this.cheatLogic.blamePerson(1,2));
        Assert.assertTrue(this.cheatLogic.blamePerson(0,2));
    }

}