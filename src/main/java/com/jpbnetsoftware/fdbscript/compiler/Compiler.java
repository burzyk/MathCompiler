package com.jpbnetsoftware.fdbscript.compiler;

import com.jpbnetsoftware.fdbscript.compiler.antlr.FdbScriptLexer;
import com.jpbnetsoftware.fdbscript.compiler.generator.IModuleCodeBlock;
import com.jpbnetsoftware.fdbscript.compiler.antlr.FdbScriptParser;
import com.jpbnetsoftware.fdbscript.compiler.generator.impl.jvm.JvmGenerator;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

/**
 * Created by pawel on 05/04/15.
 */
public class Compiler {

    public static byte[] compileModule(String moduleCode){

        FdbScriptAstVisitor visitor = new FdbScriptAstVisitor(new JvmGenerator());
        CharStream input = new ANTLRInputStream(moduleCode);
        FdbScriptLexer lexer = new FdbScriptLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        FdbScriptParser parser = new FdbScriptParser(tokens);

        IModuleCodeBlock module = (IModuleCodeBlock)visitor.visit(parser.programDeclaration());

        module.emit();

        return module.getCompilationResult();
    }
}
