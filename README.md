# Android object-to-object mapping framework benchmark

Multi-layered applications often require to map between different object models (e.g. DTOs and entities). Writing such boiler plate mapping code is a tedious and error-prone task. A lot of object-to-object mapping Java frameworks aims to simplify this work and automate it. Some uses code instrospection (eg. Dozer). Other uses code generation (eg: MapStruct). This micro-benchmark compares performance of 6 frameworks. Results could be compared to the benchmark of a code written manually.  

Benchmark demonstrate mapping frameworks usage on Android.

## Launch the benchmark

_Requirements: JDK 8 (I didn't try to run on other version), connected Android device(emulator or real)_

[//]: <> (todo update repo url after publishing)
`git clone {REPO_URL}` 

`./gradlew connectedCheck`

Results will be written to file at device (`{EXTERNAL_STORAGE}/perfRes}`, see `com.devindi.mapper.demo.data.PerformanceLogger` for details)

[//]: <> (todo how to run specified test on specified mapper)
[//]: <> (todo how to static measurements with grep)
[//]: <> (todo how to open test result pages)

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
* [OMapper](https://code.google.com/archive/p/omapper/) !Not implemented! !remove!
* [JMapper](https://github.com/jmapper-framework/jmapper-core)
* [java-object-merger](https://sourceforge.net/projects/javaobjectmerger/)
* [GeDA](http://www.inspire-software.com/confluence/display/GeDA/GeDA+-+Generic+DTO+Assembler)
* [JBeanMapper](http://jbeanmapper.sourceforge.net/)
* [MapStruct](http://mapstruct.org/)
* [MOO](https://github.com/geoffreywiseman/Moo)
* [Smooks](http://www.smooks.org/)
* [Orika](http://orika-mapper.github.io/orika-docs/)
* [ModelBridge]() !UNKNOWN! !REMOVE IT!
* [Nomin](http://nomin.sourceforge.net/)
* [ModelMapper](http://modelmapper.org/)

### Data models

There are 3 scenarios of mapping: simple, field rename and complex.

[//]: <> (todo describe scenarios with UML diagrams)

[//]: <> (todo describe instrumented and unit tests)

## Benchmark criteria list

* apk method count
* apk size
* size of mapper (`com.devindi.mapper.demo.Mapper`) class (LoC)
* time to perform mapping

[//]: <> (todo credits, contributing, result)

