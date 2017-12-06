# groovy-eclipse-compiler Java 8 bug

Minimal project that shows a bug in the current (as of Dec. 2017) implementation
of the `groovy-eclipse-compiler` available from https://github.com/groovy/groovy-eclipse.

Bug description: "[Using source 1.8 triggers a Java 9 dependency and fails compilation](https://github.com/groovy/groovy-eclipse/issues/401)"

To reproduce simply run
```
mvn clean compile
```
and the bug will be triggered.

It seems something is trying to load the `javax.lang.model.element.ModuleElement`
class that is available only in Java 9.

Tested on different Java 8 JDKs and many previous versions of the three plugins
involved: always reproducible.

Removing the following settings from the `maven-compiler-plugin` configuration
fixes the issue but disable Java 8 features.

```xml
<configuration>
    <source>1.8</source>
    <target>1.8</target>
</configuration>
```

Output of the program:

```
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building groovy-java8-bug 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ groovy-java8-bug ---
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ groovy-java8-bug ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /home/anci8360/Private/temp/groovy-mvn-test/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ groovy-java8-bug ---
[INFO] Changes detected - recompiling the module!
[INFO] Using Groovy-Eclipse compiler to compile both Java and Groovy files
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.176 s
[INFO] Finished at: 2017-12-06T12:10:16+01:00
[INFO] Final Memory: 14M/355M
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.7.0:compile (default-compile) on project groovy-java8-bug: Execution default-compile of goal org.apache.maven.plugins:maven-compiler-plugin:3.7.0:compile failed: A required class was missing while executing org.apache.maven.plugins:maven-compiler-plugin:3.7.0:compile: javax/lang/model/element/ModuleElement
[ERROR] -----------------------------------------------------
[ERROR] realm =    plugin>org.apache.maven.plugins:maven-compiler-plugin:3.7.0
[ERROR] strategy = org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy
[ERROR] urls[0] = file:/home/anci8360/.m2/repository/org/apache/maven/plugins/maven-compiler-plugin/3.7.0/maven-compiler-plugin-3.7.0.jar
[ERROR] urls[1] = file:/home/anci8360/.m2/repository/org/codehaus/groovy/groovy-eclipse-compiler/2.9.2-03/groovy-eclipse-compiler-2.9.2-03.jar
[ERROR] urls[2] = file:/home/anci8360/.m2/repository/org/apache/xbean/xbean-reflect/3.7/xbean-reflect-3.7.jar
[ERROR] urls[3] = file:/home/anci8360/.m2/repository/com/google/collections/google-collections/1.0/google-collections-1.0.jar
[ERROR] urls[4] = file:/home/anci8360/.m2/repository/org/codehaus/groovy/groovy-eclipse-batch/2.4.13-01/groovy-eclipse-batch-2.4.13-01.jar
[ERROR] urls[5] = file:/home/anci8360/.m2/repository/org/sonatype/sisu/sisu-inject-bean/1.4.2/sisu-inject-bean-1.4.2.jar
[ERROR] urls[6] = file:/home/anci8360/.m2/repository/org/sonatype/sisu/sisu-guice/2.1.7/sisu-guice-2.1.7-noaop.jar
[ERROR] urls[7] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-utils/2.0.4/plexus-utils-2.0.4.jar
[ERROR] urls[8] = file:/home/anci8360/.m2/repository/org/sonatype/aether/aether-util/1.7/aether-util-1.7.jar
[ERROR] urls[9] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-interpolation/1.14/plexus-interpolation-1.14.jar
[ERROR] urls[10] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-component-annotations/1.6/plexus-component-annotations-1.6.jar
[ERROR] urls[11] = file:/home/anci8360/.m2/repository/org/sonatype/plexus/plexus-sec-dispatcher/1.3/plexus-sec-dispatcher-1.3.jar
[ERROR] urls[12] = file:/home/anci8360/.m2/repository/org/sonatype/plexus/plexus-cipher/1.4/plexus-cipher-1.4.jar
[ERROR] urls[13] = file:/home/anci8360/.m2/repository/org/apache/maven/shared/maven-shared-utils/3.1.0/maven-shared-utils-3.1.0.jar
[ERROR] urls[14] = file:/home/anci8360/.m2/repository/commons-io/commons-io/2.5/commons-io-2.5.jar
[ERROR] urls[15] = file:/home/anci8360/.m2/repository/org/apache/maven/shared/maven-shared-incremental/1.1/maven-shared-incremental-1.1.jar
[ERROR] urls[16] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-java/0.9.2/plexus-java-0.9.2.jar
[ERROR] urls[17] = file:/home/anci8360/.m2/repository/org/ow2/asm/asm/6.0_BETA/asm-6.0_BETA.jar
[ERROR] urls[18] = file:/home/anci8360/.m2/repository/com/thoughtworks/qdox/qdox/2.0-M7/qdox-2.0-M7.jar
[ERROR] urls[19] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-compiler-api/2.8.2/plexus-compiler-api-2.8.2.jar
[ERROR] urls[20] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-compiler-manager/2.8.2/plexus-compiler-manager-2.8.2.jar
[ERROR] urls[21] = file:/home/anci8360/.m2/repository/org/codehaus/plexus/plexus-compiler-javac/2.8.2/plexus-compiler-javac-2.8.2.jar
[ERROR] Number of foreign imports: 1
[ERROR] import: Entry[import  from realm ClassRealm[maven.api, parent: null]]
[ERROR]
[ERROR] -----------------------------------------------------: javax.lang.model.element.ModuleElement
[ERROR] -> [Help 1]
[ERROR]
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR]
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/PluginContainerException
```
