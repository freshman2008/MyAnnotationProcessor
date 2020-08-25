package com.example.compiler;

import com.example.annotation.Route;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class CompilerProcessor extends AbstractProcessor {
    Elements elementUtils;
    Filer filer;
    Messager messager;

    /**
     * 初始化工具类对象
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    /**
     * 必须指定，这个注解处理器是注册给哪些注解的
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new HashSet<>();
        types.add(Override.class.getCanonicalName());
        return types;
    }

    /**
     * 用来指定你使用的Java版本
     *
     * @return
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 处理器的主函数
     * 这里实现扫描、评估和处理注解的代码，以及生成Java文件
     *
     * @param set
     * @param roundEnvironment
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
//        for (TypeElement typeElement : set) {
//            System.out.println(typeElement.getQualifiedName());
//        }

        printMsg(Diagnostic.Kind.NOTE, "process()");
        generateClass();
        return true;
    }

    /**
     * 生成一个测试类
     */
    private void generateClass() {
        // 创建一个测试类
        TypeSpec generateClass = TypeSpec.classBuilder("GenerateMyClass")
                .addModifiers(Modifier.PUBLIC)
                .build();

        // 创建类存储的路径
        JavaFile javaFile = JavaFile.builder("com.hello.test", generateClass).build();

        // 生成类文件
        try {
            javaFile.writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印日志
     *
     * @param kind
     * @param message
     */
    private void printMsg(Diagnostic.Kind kind, String message) {
        messager.printMessage(kind, message);
    }
}
