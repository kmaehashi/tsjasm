package tsjasm;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import tsjasm.classfile.ClassFile;
import tsjasm.sample.EmptyClassGenerator;
import tsjasm.sample.HelloWorldClassGenerator;

public class TSJasm {
	public static void main(String[] args) throws IOException {
		String className;

		// Create an empty class
		className = "EmptyClass";
		ClassFile file1 = EmptyClassGenerator.createEmptyClass(className);
		BufferedOutputStream bos1 = new BufferedOutputStream(
				new FileOutputStream(className + ".class"));
		file1.writeTo(bos1);
		bos1.close();
		System.out.println("created: " + className + ".class");

		// Create an class that prints message
		className = "HelloWorld";
		ClassFile file2 = HelloWorldClassGenerator.createClass(className,
				"Hello World!!");
		BufferedOutputStream bos2 = new BufferedOutputStream(
				new FileOutputStream(className + ".class"));
		file2.writeTo(bos2);
		bos2.close();
		System.out.println("created: " + className + ".class");
	}
}
