package com.zelyder.physics.help;

import junit.framework.Assert;

import org.junit.Test;

public class PPNTest {
    @Test
    public void eval() throws Exception {

    String input = "0-5";
    float output;
    float expected = -5;
    double delta = .1;

    PPN ppn = new PPN();
    output = ppn.eval(input);

        Assert.assertEquals(expected, output, delta);
    }

}