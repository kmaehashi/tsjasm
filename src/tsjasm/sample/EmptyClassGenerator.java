package tsjasm.sample;

import tsjasm.classfile.*;
import static tsjasm.classfile.AccessFlag.*;
import static tsjasm.classfile.CpInfoTag.*;

public class EmptyClassGenerator {
	public static ClassFile createEmptyClass(String className) {
		ClassFile file = new ClassFile();

		/*
		 * Version
		 */
		file.minor_version = 0;
		file.major_version = 50; // Java 1.5.0 compatibile (?)

		/*
		 * Constant Pool
		 */
		// 1
		CpInfo clazz = new CpInfo(CpClass);
		clazz.name_index = 2;
		file.constant_pool.add(clazz);

		// 2
		CpInfo clazzName = new CpInfo(CpUtf8);
		clazzName.str_bytes = className;
		file.constant_pool.add(clazzName);

		// 3
		CpInfo superClazz = new CpInfo(CpClass);
		superClazz.name_index = 4;
		file.constant_pool.add(superClazz);

		// 4
		CpInfo superClazzName = new CpInfo(CpUtf8);
		superClazzName.str_bytes = "java/lang/Object";
		file.constant_pool.add(superClazzName);

		// 5
		CpInfo initMethodName = new CpInfo(CpUtf8);
		initMethodName.str_bytes = "<init>";
		file.constant_pool.add(initMethodName);

		// 6
		CpInfo initMethodSignature = new CpInfo(CpUtf8);
		initMethodSignature.str_bytes = "()V";
		file.constant_pool.add(initMethodSignature);

		// 7
		CpInfo code = new CpInfo(CpUtf8);
		code.str_bytes = "Code";
		file.constant_pool.add(code);

		// 8
		CpInfo initMethod = new CpInfo(CpMethodref);
		initMethod.class_index = 3;
		initMethod.name_and_type_index = 9;
		file.constant_pool.add(initMethod);

		// 9
		CpInfo initMethodNameAndType = new CpInfo(CpNameAndType);
		initMethodNameAndType.name_index = 5;
		initMethodNameAndType.signature_index = 6;
		file.constant_pool.add(initMethodNameAndType);

		// 10
		CpInfo lineNumberTable = new CpInfo(CpUtf8);
		lineNumberTable.str_bytes = "LineNumberTable";
		file.constant_pool.add(lineNumberTable);

		// 11
		CpInfo localVariableTable = new CpInfo(CpUtf8);
		localVariableTable.str_bytes = "LocalVariableTable";
		file.constant_pool.add(localVariableTable);

		// 12
		CpInfo thisStr = new CpInfo(CpUtf8);
		thisStr.str_bytes = "this";
		file.constant_pool.add(thisStr);

		// 13
		CpInfo mySignature = new CpInfo(CpUtf8);
		mySignature.str_bytes = "L" + className + ";";
		file.constant_pool.add(mySignature);

		// 14
		CpInfo sourceFile = new CpInfo(CpUtf8);
		sourceFile.str_bytes = "SourceFile";
		file.constant_pool.add(sourceFile);

		// 15
		CpInfo sourceFileName = new CpInfo(CpUtf8);
		sourceFileName.str_bytes = className + ".java";
		file.constant_pool.add(sourceFileName);

		/*
		 * Class Declaration
		 */
		file.access_flags = ACC_PUBLIC | ACC_SYNCHRONIZED;
		file.this_class = 1; // from Cp
		file.super_class = 3; // from Cp
		// no interfaces
		// no fields

		/*
		 * <init> Method
		 */
		MethodInfo initMethodInfo = new MethodInfo();
		initMethodInfo.access_flags = ACC_PUBLIC;
		initMethodInfo.name_index = 5; // from Cp
		initMethodInfo.signature_index = 6; // fromCp
		file.methods.add(initMethodInfo);

		AttributeInfoCode initMethodInfoAttr = new AttributeInfoCode();
		initMethodInfoAttr.attribute_name = 7;
		initMethodInfoAttr.max_stack = 1;
		initMethodInfoAttr.max_locals = 1;

		byte[] initCode = new byte[] { // code
		(byte) 42, // (0x2a) aload_0
				(byte) 183, // (0xb7) invokenonvirtual
				(byte) 0, // (0x00) 0x00-
				(byte) 8, // (0x08) -08
				(byte) 177, // (0xb1) return
		};
		initMethodInfoAttr.code = initCode;

		// no exception table
		initMethodInfo.attributes.add(initMethodInfoAttr);

		AttributeInfoLineNumberTable initMethodInfoLineNumberTable = new AttributeInfoLineNumberTable();
		initMethodInfoLineNumberTable.attribute_name = 10; // LineNumberTable
		LineNumberTableEntity lnte = new LineNumberTableEntity();
		lnte.start_pc = 0x0000;
		lnte.line_number = 0x0001;
		initMethodInfoLineNumberTable.line_number_table.add(lnte);
		initMethodInfoAttr.attributes.add(initMethodInfoLineNumberTable);

		AttributeInfoLocalVariableTable initMethodInfoLocalVariableTable = new AttributeInfoLocalVariableTable();
		initMethodInfoLocalVariableTable.attribute_name = 11; // LocalVariableTable
		LocalVariableTableEntity lvte = new LocalVariableTableEntity();
		lvte.start_pc = 0x0000;
		lvte.length = 0x0005;
		lvte.name_index = 0x000c;
		lvte.signature_index = 0x000d;
		lvte.slot = 0x0000;
		initMethodInfoLocalVariableTable.local_variable_table.add(lvte);
		initMethodInfoAttr.attributes.add(initMethodInfoLocalVariableTable);

		/*
		 * Class Attributes
		 */
		AttributeInfoSourceFile attrSourceFile = new AttributeInfoSourceFile();
		attrSourceFile.attribute_name = 14; // SourceFile
		attrSourceFile.sourcefile_index = 15;
		file.attributes.add(attrSourceFile);

		return file;
	}

}
