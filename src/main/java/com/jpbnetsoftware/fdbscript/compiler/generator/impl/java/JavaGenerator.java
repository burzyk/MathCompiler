package com.jpbnetsoftware.fdbscript.compiler.generator.impl.java;

import com.jpbnetsoftware.fdbscript.compiler.generator.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by pawel on 03/04/15.
 */
public class JavaGenerator implements IGenerator {

    @Override
    public IModuleCodeBlock generateModule(String name, List<ICodeBlock> assignments, ICodeBlock expression) {
        return new ModuleCodeBlock(name, assignments, expression, System.out);
    }

    @Override
    public ICodeBlock generateFunction(List<ICodeBlock> argumentDefinitions, List<ICodeBlock> definitions, ICodeBlock expression) {

        String randomName = "Util_" + UUID.randomUUID().toString().replace("-", "").substring(0, 4);

        System.out.print(
                " class " +
                        randomName +
                        " { public " +
                        BlockTypeTranslator.getJavaTypeName(expression.getType()) +
                        " invoke(...) { ");

        for (ICodeBlock b : definitions) {
            b.emit();
        }

        System.out.print(" return ");
        expression.emit();
        System.out.print("; } }");

        return new FunctionCodeBlock(randomName, expression.getType(), System.out);
    }

    @Override
    public IDefinitionCodeBlock generateArgumentDefinition(String name) {
        return new DefinitionCodeBlock(name, null, System.out);
    }

    @Override
    public IDefinitionCodeBlock generateSelfDefinition() {
        return new DefinitionCodeBlock("this", null, System.out);
    }

    @Override
    public ICodeBlock generateInvoke(IDefinitionCodeBlock definition, List<ICodeBlock> arguments) {
        return new InvokeCodeBlock(definition, arguments, System.out);
    }

    @Override
    public ICodeBlock generateIf(List<ICodeBlock> conditions, ICodeBlock elseExpression) {
        return new IfCodeBlock(conditions, elseExpression, System.out);
    }

    @Override
    public ICodeBlock generateCondition(ICodeBlock booleanExpression, ICodeBlock expression) {
        return new ConditionCodeBlock(booleanExpression, expression, System.out);
    }

    @Override
    public ICodeBlock generateElse(ICodeBlock expression) {
        return new ElseCodeBlock(expression, System.out);
    }

    @Override
    public IDefinitionCodeBlock generateDefinition(String name, ICodeBlock expression) {
        return new DefinitionCodeBlock(name, expression, System.out);
    }

    @Override
    public ICodeBlock generateDefinitionInvoke(IDefinitionCodeBlock definition) {
        return new DefinitionInvokeCodeBlock(definition, System.out);
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
    public ICodeBlock generateNumber(double number) {
        return new NumberCodeBlock(number, System.out);
    }
}
