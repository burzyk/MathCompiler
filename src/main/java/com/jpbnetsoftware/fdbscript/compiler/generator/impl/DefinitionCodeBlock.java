package com.jpbnetsoftware.fdbscript.compiler.generator.impl;

import com.jpbnetsoftware.fdbscript.compiler.generator.ICodeBlock;
import com.jpbnetsoftware.fdbscript.compiler.generator.impl.helpers.BytecodeProvider;
import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.InstructionList;

/**
 * Created by pawel on 10/04/15.
 */
public class DefinitionCodeBlock extends BaseDefinitionCodeBlock {

    private ICodeBlock expression;

    public DefinitionCodeBlock(BytecodeProvider provider, int variableId, String name, ICodeBlock expression) {
        super(provider, variableId, name);

        this.expression = expression;
    }

    @Override
    public void emit() {
        InstructionList il = this.provider.getInstructionList();

        this.expression.emit();

        il.append(new ASTORE(this.variableId));
    }
}
