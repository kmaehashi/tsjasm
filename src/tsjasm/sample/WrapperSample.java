package tsjasm.sample;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import tsjasm.classfile.ClassFile;
import tsjasm.wrapper.ClassEditor;
import tsjasm.wrapper.managed.*;
import tsjasm.wrapper.managed.MMethodCode.BP;

import static tsjasm.classfile.AccessFlag.*;
import static tsjasm.wrapper.managed.JvmOpcode.*;

public class WrapperSample {
	public static void main(String[] args) throws IOException {
		String className = "EmptyClazz";
		ClassEditor e = new ClassEditor(className);

		// public static void main (String[])
		MMethod mainMethod = e.addMethod("main", "([Ljava/lang/String;)V");
		mainMethod.access_flags = ACC_PUBLIC | ACC_STATIC;

		MMethodCode mainMethodCode = mainMethod.getCode();

		mainMethodCode.max_locals = 2; // number of local variables (0-based)
		mainMethodCode.max_stack = 3; // stacks (bytes)

		// System.out.println("Hello world");
		mainMethodCode.add(_getstatic,
			e.fieldRef(e.classRef("java/lang/System"), "out", "Ljava/io/PrintStream;"));
		mainMethodCode.add(_ldc, e.stringRef("Hello world"));
		mainMethodCode.add(_invokevirtual,
				e.methodRef(e.classRef("java/io/PrintStream"), "println", "(Ljava/lang/String;)V"));

		// var[0] = 50;
		mainMethodCode.add(_bipush, 50);
		mainMethodCode.add(_istore_0);
		// var[1] = 49;
		mainMethodCode.add(_bipush, 49);
		mainMethodCode.add(_istore_1);
		// if ((var[0] - var[1]) != 0) { System.out.println((var[0] - var[1])); }
		mainMethodCode.add(_iload_0);
		mainMethodCode.add(_iload_1);
		mainMethodCode.add(_isub);
		BP jump_when_eq;
		mainMethodCode.add(_ifeq, (jump_when_eq = mainMethodCode.newBp()));
		mainMethodCode.add(_getstatic,
				e.fieldRef(e.classRef("java/lang/System"), "out", "Ljava/io/PrintStream;"));
		mainMethodCode.add(_iload_0);
		mainMethodCode.add(_iload_1);
		mainMethodCode.add(_isub);
		mainMethodCode.add(_invokevirtual,
				e.methodRef(e.classRef("java/io/PrintStream"), "println", "(I)V"));
		mainMethodCode.backpatch(jump_when_eq);

		// return;
		mainMethodCode.add(_return);

		ClassFile file = e.generateClassFile();
		BufferedOutputStream bos1 = new BufferedOutputStream(
				new FileOutputStream(className + ".class"));
		file.writeTo(bos1);
		bos1.close();
	}
}
