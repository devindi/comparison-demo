# Android object-to-object mapping framework benchmark

Multi-layered applications often require to map between different object models (e.g. DTOs and entities). Writing such boiler plate mapping code is a tedious and error-prone task. A lot of object-to-object mapping Java frameworks aims to simplify this work and automate it. Some uses code instrospection (eg. Dozer). Other uses code generation (eg: MapStruct). This micro-benchmark compares performance of 6 frameworks. Results could be compared to the benchmark of a code written manually.  

Benchmark demonstrate mapping frameworks usage on Android.

## Launch the benchmark

_Requirements: JDK 8 (I didn't try to run on other version), connected Android device(emulator or real)_   

### Method count benchmark

`./gradlew clean assemble | grep "Total methods" >> methods`

Detailed method count will be written as text and graph under `${buildDir}/outputs/dexcount/${variant}`


### APK size benchmark

`./gradlew clean assemble | grep "Total APK Size" >> size`

### Performance benchmark

`./gradlew connectedCheck`

Results will be written to file at device (`{EXTERNAL_STORAGE}/perfRes}`, see `com.devindi.mapper.demo.data.PerformanceLogger` for details)

To run performance benchmark on specified mapper run task `connected{maper}DebugAndroidTest`. To see list of available tasks run: `./gradlew tasks | grep connected`

To run android tests and open failure reports for each flavor run `./gradlew connectedCheck | grep file: | cut -c 46- | xargs open`

## Benchmark structure

Each mapping framework used at separate flavor.

### List of mapper frameworks
* [Manual mapping]()
* [Commons BeanUtils](http://commons.apache.org/proper/commons-beanutils/)
* [Spring 4 Type Conversion](https://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/validation.html#core-convert)
* [Transmorph](https://github.com/cchabanois/transmorph)
* [EZMorph](http://ezmorph.sourceforge.net/)
* [Morph](http://morph.sourceforge.net/)
* [Dozer](http://dozer.sourceforge.net/)
* [JMapper](https://github.com/jmapper-framework/jmapper-core)
* [java-object-merger](https://sourceforge.net/projects/javaobjectmerger/)
* [GeDA](http://www.inspire-software.com/confluence/display/GeDA/GeDA+-+Generic+DTO+Assembler)
* [JBeanMapper](http://jbeanmapper.sourceforge.net/)
* [MapStruct](http://mapstruct.org/)
* [MOO](https://github.com/geoffreywiseman/Moo)
* [Smooks](http://www.smooks.org/)
* [Orika](http://orika-mapper.github.io/orika-docs/)
* [Nomin](http://nomin.sourceforge.net/)
* [ModelMapper](http://modelmapper.org/)

### Data models

There are 3 scenarios of mapping: simple, field rename and complex.

Simple scenario is mapping between identical classes  
![Simple UML](https://bitbucket.org/mikhin/comparison-demo/raw/f72ed17b30b1d2bc2a7567053b2f3485e55f3a67/uml/simple.png)

Rename scenario is mapping between identical classes, but one field is renamed  
![Rename UML](https://bitbucket.org/mikhin/comparison-demo/raw/f72ed17b30b1d2bc2a7567053b2f3485e55f3a67/uml/rename.png)

Complex scenario use model from [Comparison](https://github.com/jhalterman/modelmapper/blob/master/core/src/test/java/org/modelmapper/performance/Comparison.java) class from ModelMapper framework.  
![Complex UML](https://bitbucket.org/mikhin/comparison-demo/raw/f72ed17b30b1d2bc2a7567053b2f3485e55f3a67/uml/complex.png)

## Benchmark criteria list

* apk method count
* apk size
* size of mapper (`com.devindi.mapper.demo.Mapper`) class (LoC)
* time to perform mapping

## Benchmarks results

## Credits

* [Object mapper benchmark](https://github.com/arey/java-object-mapper-benchmark)
* Java mapping for pragmatic programmer (Conference talk, Russian. [youtube](https://www.youtube.com/watch?v=8Rx5gKURmT0),[pdf](http://2013.jokerconf.com/presentations/java_mapping_dlya_pragmatichnyh_programmistov_-_45_mins_-_joker.pdf))
* [Method count plugin](https://github.com/KeepSafe/dexcount-gradle-plugin)
* [APK size plugin](https://github.com/vanniktech/gradle-android-apk-size-plugin)
* [UML diagram renderer](http://plantuml.com/)

## Contributing

You are welcome to add more mappers (eg OTOM, OMapper) and improve existing mappers usage

