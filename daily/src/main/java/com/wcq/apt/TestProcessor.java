package com.wcq.apt;

public class TestProcessor {
}
//@AutoService(Processor.class)
//public class TestProcessor extends AbstractProcessor {
//
//
//    @Override
//    public SourceVersion getSupportedSourceVersion() {
//        return SourceVersion.latestSupported();
//    }
//
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> set = new HashSet<>();
//        set.add(AnnotationApt.class.getCanonicalName());
//        return set;
//    }
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
//        File file = new File("G://3.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(AnnotationApt.class);
//        System.out.println("----AAAAA------" + elements.size() + "----------");
//        //由于编译器的输出无法打印到控制台，因此这里借助javapoet库把需要输出的信息写入到一个新的类
//        //这个是我封装的一个简单的工具
//        ProcessorTool builder = new ProcessorTool(processingEnv);
//        for (Element element : elements) {
//            AnnotationApt aaaaa = element.getAnnotation(AnnotationApt.class);
//            if (element instanceof TypeElement) {
//                builder.addArgs(" TypeElement: " + aaaaa.value());
//
//                /*===============打印包信息=================*/
//                builder.addArgs("=============================打印包信息================================");
//                PackageElement packageElement = processingEnv.getElementUtils().getPackageOf(element);
//                builder.addArgs("packageElement:  " + packageElement.getSimpleName().toString());
//                builder.addArgs("packageElement:  " + packageElement.getQualifiedName());
//
//
//                builder.addArgs("=============================打印泛型信息================================");
//                List<? extends TypeParameterElement> typeParameters = ((TypeElement) element).getTypeParameters();
//                for (TypeParameterElement typeParameter : typeParameters) {
//                    builder.addArgs(typeParameter.getSimpleName().toString());
//                }
//                builder.addArgs("=============================================================");
//
//            } else if (element instanceof ExecutableElement) {
//                builder.addArgs("ExecutableElement: " + aaaaa.value());
//            } else if (element instanceof VariableElement) {
//                builder.addArgs(" VariableElement: " + aaaaa.value());
//            }
//        }
//        builder.printLog();
//        return true;
//    }
//}
