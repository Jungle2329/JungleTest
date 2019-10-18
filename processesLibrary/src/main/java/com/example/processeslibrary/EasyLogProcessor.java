package com.example.processeslibrary;

import com.example.annotationslibrary.EasyLog;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.zy.jungletest.annotationTest.EasyLog"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class EasyLogProcessor extends AbstractProcessor {

    private Elements elementsUtils;
    private Filer mFiler;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        elementsUtils = processingEnvironment.getElementUtils();
        mFiler = processingEnvironment.getFiler();
        super.init(processingEnvironment);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        System.out.println("------------------------------");
        Messager messager = processingEnv.getMessager();
        for (TypeElement te : annotations) {
            for (Element e : env.getElementsAnnotatedWith(te)) {
                EasyLog mEasyLog = e.getAnnotation(EasyLog.class);
                messager.printMessage(Diagnostic.Kind.WARNING, "HelloWorld" + mEasyLog.tag() + mEasyLog.value());
                analysisAnnotated(e);
            }
        }
        System.out.println("------------------------------");
        return true;
    }

    private void analysisAnnotated(Element classElement) {
        EasyLog annotation = classElement.getAnnotation(EasyLog.class);
        String name = annotation.tag();
        String text = annotation.value();

//        TypeElement superClassName = mElementUtils.getTypeElement(name);
        String newClassName = name + "YYYYY";

        StringBuilder builder = new StringBuilder()
                .append("package com.zy.jungletest.antotion;\n\n")
                .append("public class ")
                .append(newClassName)
                .append(" {\n\n") // open class
                .append("\tpublic String getMessage() {\n") // open method
                .append("\t\treturn \"");

        // this is appending to the return statement
        builder.append(text).append(name).append(" !\\n");


        builder.append("\";\n") // end return
                .append("\t}\n") // close method
                .append("}\n"); // close class


        try { // write the file
            JavaFileObject source = mFiler.createSourceFile("com.zy.jungletest.antotion." + newClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {

        }

    }

}
