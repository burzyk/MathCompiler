package com.jpbnetsoftware.fdbscriptcompiler.generator.impl.java;

import com.jpbnetsoftware.fdbscriptcompiler.generator.*;

import java.util.List;

/**
 * Created by pawel on 03/04/15.
 */
public class JavaGenerator implements IGenerator {

    @Override
    public ICodeBlock generateModule(String name, List<ICodeBlock> assignments, ICodeBlock expression) {
        return new ModuleCodeBlock(name, assignments, expression, System.out);
    }

    @Override
    public ICodeBlock generateDefinition(String name, ICodeBlock expression) {
        return new DefinitionCodeBlock(name, expression, System.out);
    }

    @Override
    public ICodeBlock generateDefinitionInvoke(ICodeBlock definition) {
        return new DefinitionInvokeCodeBlock((DefinitionCodeBlock) definition, System.out);
    }

    @Override
    public ICodeBlock generateBoolean(ICodeBlock lhs, BooleanOperation operation, ICodeBlock rhs) {
        return new BooleanCodeBlock(lhs, operation, rhs, System.out);
    }

    @Override
    public ICodeBlock generateEqualityTest(ICodeBlock lhs, boolean testEqual, ICodeBlock rhs) {
        return new EqualityTestCodeBlock(lhs, testEqual, rhs, System.out);
    }

    @Override
    public ICodeBlock generateBoolPrimitive(boolean value) {
        return new BoolPrimitiveCodeBlock(value, System.out);
    }

    @Override
    public ICodeBlock generateCompare(ICodeBlock lhs, CompareOperation operation, ICodeBlock rhs) {
        return new CompareCodeBlock(lhs, operation, rhs, System.out);
    }

    @Override
    public ICodeBlock generateStringConcat(ICodeBlock lhs, ICodeBlock rhs) {
        return new StringConcatCodeBlock(lhs, rhs, System.out);
    }

    @Override
    public ICodeBlock generateString(String text) {
        return new StringCodeBock(text, System.out);
    }

    @Override
    public ICodeBlock generateMath(ICodeBlock lhs, MathOperation operation, ICodeBlock rhs) {
        return new MathCodeBlock(lhs, operation, rhs, System.out);
    }

    @Override
    public ICodeBlock generateNumber(float number) {
        return new NumberCodeBlock(number, System.out);
    }
}
