#!/usr/bin/env bash
javac *.java */*.java
#java -ea TestRunner tests/ CalculatorTest
cd ../../
java -ea lesson5_reflection._4_custom_classloaders.TestRunner lesson5_reflection/_4_custom_classloaders/tests/ lesson5_reflection._4_custom_classloaders.tests.CalculatorTest
