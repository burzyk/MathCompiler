package com.jpbnetsoftware.fdbscript.compiler.generator.impl.jvm;

import com.jpbnetsoftware.fdbscript.compiler.generator.BlockType;
import com.jpbnetsoftware.fdbscript.compiler.generator.IDefinitionCodeBlock;
import com.jpbnetsoftware.fdbscript.compiler.generator.impl.jvm.helpers.BytecodeProvider;

/**
 * Created by pawel on 10/04/15.
 */
public class ArgumentDefinitionCodeBlock extends BaseDefinitionCodeBlock {

    public ArgumentDefinitionCodeBlock(BytecodeProvider provider, int argumentId, String name) {
        super(provider, argumentId, name);
    }

    @Override
    public void emit() {
        // emit bytecode definition which load the argumentId element
        // of the input array into a local variable
    }

    @Override
    public BlockType getType() {
        return BlockType.Any;
    }
}
