package com.jpbnetsoftware.fdbscript.compiler.generator.impl.jvm;

import com.jpbnetsoftware.fdbscript.compiler.generator.ICodeBlock;
import org.apache.bcel.Constants;
import org.apache.bcel.generic.*;

import java.util.List;

/**
 * Created by pawel on 10/04/15.
 */
public class ClassGenerator {

    private ConstantPoolGen constantPoolGen;

    private InstructionList instructionList;

    private InstructionFactory instructionFactory;

    private ClassGen moduleClass;

    private MethodGen methodGen;

    private ClassGenerator() {
    }

    public static ClassGenerator beginClass(String name) {
        ClassGenerator generator = new ClassGenerator();

        generator.moduleClass = new ClassGen(
                name,
                "java.lang.Object",
                "<generated>",
                Constants.ACC_PUBLIC | Constants.ACC_SUPER,
                new String[]{"com.jpbnetsoftware.fdbscript.runtime.IInvokable"});

        generator.constantPoolGen = generator.moduleClass.getConstantPool();
        generator.instructionList = new InstructionList();

        generator.methodGen = new MethodGen(
                Constants.ACC_PUBLIC, // access flags
                Type.OBJECT,               // return type
                new Type[]{new ArrayType(Type.OBJECT, 1)},
                new String[]{"args"}, // arg names
                "invoke",
                name,    // method, class
                generator.instructionList,
                generator.constantPoolGen);

        generator.instructionFactory = new InstructionFactory(generator.moduleClass);

        return generator;
    }

    public byte[] endClass() {
        this.methodGen.setMaxStack();
        this.methodGen.setMaxLocals();
        this.moduleClass.addMethod(this.methodGen.getMethod());
        this.instructionList.dispose(); // Allow instruction handles to be reused
        this.moduleClass.addEmptyConstructor(Constants.ACC_PUBLIC);

        return this.moduleClass.getJavaClass().getBytes();
    }

    public InstructionFactory getInstructionFactory() {
        return instructionFactory;
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }
}
