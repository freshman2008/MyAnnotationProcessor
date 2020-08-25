1.创建自定义AbstractProcessor
    1.1 创建一个Java Module，命名为compiler，用于实现自定义注解处理器
    1.2 compiler依赖的buid.gradle文件内容如下
        apply plugin: 'java-library'

        dependencies {
            implementation fileTree(dir: 'libs', include: ['*.jar'])

            // 用于生成Java文件的库
            implementation 'com.squareup:javapoet:1.7.0'
            implementation 'com.google.auto.service:auto-service:1.0-rc2'
        }

        //jdk版本
        sourceCompatibility = "1.7"
        targetCompatibility = "1.7"
    1.3 定义AbstractProcessor
        @AutoService(Processor.class)
        public class CompilerProcessor extends AbstractProcessor {
            @Override
            public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
                //实现基本逻辑
            }
        }

    1.4 在应用工程中引入 annotationProcessor project(':compiler')

    参照：https://www.jianshu.com/p/085b3e081fc2

2.调试自定义AbstractProcessor
    参照：https://www.jianshu.com/p/80a14bc35000