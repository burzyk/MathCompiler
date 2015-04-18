package com.jpbnetsoftware.fdbscript.compiler;

import com.jpbnetsoftware.fdbscript.compiler.impl.FileOutputManager;

/**
 * Created by pawel on 30/03/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        String program = "module Fib " +
                "one = 1" +
                "one";

        Compiler.compileModule(program, new FileOutputManager());

        System.out.println("Done");
    }
}
