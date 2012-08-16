package tsjasm.sample;

import tsjasm.classfile.*;
import static tsjasm.classfile.AccessFlag.*;
import static tsjasm.classfile.CpInfoTag.*;

public class HelloWorldClassGenerator {
	public static ClassFile createClass(String className, String msg) {
		// start with the "template" empty class (with <init> method)
		ClassFile file = EmptyClassGenerator.createEmptyClass(className);

		/*
		 * constant pool items used for main
		 */
		// 16
		CpInfo mainMethodName = new CpInfo(CpUtf8);
		mainMethodName.str_bytes = "main";
		file.constant_pool.add(mainMethodName);

		// 17
		CpInfo mainMethodSignature = new CpInfo(CpUtf8);
		// SIGNATURE = void *** (String[] ***)
		mainMethodSignature.str_bytes = "([Ljava/lang/String;)V";
		file.constant_pool.add(mainMethodSignature);

		// 18
		CpInfo mainMethod = new CpInfo(CpMethodref);
		mainMethod.class_index = 3; // this class
		mainMethod.name_and_type_index = 19;
		file.constant_pool.add(mainMethod);

		// 19
		CpInfo mainMethodNameAndType = new CpInfo(CpNameAndType);
		mainMethodNameAndType.name_index = 16;
		mainMethodNameAndType.signature_index = 17;
		file.constant_pool.add(mainMethodNameAndType);

		// 20
		CpInfo helloWorldText = new CpInfo(CpUtf8);
		helloWorldText.str_bytes = msg;
		file.constant_pool.add(helloWorldText);

		// 21
		CpInfo javaLangSystem = new CpInfo(CpUtf8);
		javaLangSystem.str_bytes = "java/lang/System";
		file.constant_pool.add(javaLangSystem);

		// 22
		CpInfo outText = new CpInfo(CpUtf8);
		outText.str_bytes = "out";
		file.constant_pool.add(outText);

		// 23
		CpInfo javaLangSystemOutNAT = new CpInfo(CpNameAndType);
		javaLangSystemOutNAT.name_index = 22;
		javaLangSystemOutNAT.signature_index = 25;
		file.constant_pool.add(javaLangSystemOutNAT);

		// 24
		CpInfo javaLangSystemOutField = new CpInfo(CpFieldref);
		javaLangSystemOutField.class_index = 32;
		javaLangSystemOutField.name_and_type_index = 23;
		file.constant_pool.add(javaLangSystemOutField);

		// 25
		CpInfo javaIoPrintStreamSign = new CpInfo(CpUtf8);
		javaIoPrintStreamSign.str_bytes = "Ljava/io/PrintStream;";
		file.constant_pool.add(javaIoPrintStreamSign);

		// 26
		CpInfo javaIoPrintStreamPrintln = new CpInfo(CpUtf8);
		javaIoPrintStreamPrintln.str_bytes = "println";
		file.constant_pool.add(javaIoPrintStreamPrintln);

		// 27
		CpInfo printlnSignature = new CpInfo(CpUtf8);
		// SIGNATURE = void *** (String ***)
		printlnSignature.str_bytes = "(Ljava/lang/String;)V";
		file.constant_pool.add(printlnSignature);

		// 28
		CpInfo javaIoPrintStreamPrintlnNAT = new CpInfo(CpNameAndType);
		javaIoPrintStreamPrintlnNAT.name_index = 26;
		javaIoPrintStreamPrintlnNAT.signature_index = 27;
		file.constant_pool.add(javaIoPrintStreamPrintlnNAT);

		// 29
		CpInfo javaIoPrintStreamPrintlnMethod = new CpInfo(CpMethodref);
		javaIoPrintStreamPrintlnMethod.class_index = 30;
		javaIoPrintStreamPrintlnMethod.name_and_type_index = 28;
		file.constant_pool.add(javaIoPrintStreamPrintlnMethod);

		// 30
		CpInfo javaIoPrintStreamClass = new CpInfo(CpClass);
		javaIoPrintStreamClass.name_index = 31;
		file.constant_pool.add(javaIoPrintStreamClass);

		// 31
		CpInfo javaIoPrintStreamClassText = new CpInfo(CpUtf8);
		javaIoPrintStreamClassText.str_bytes = "java/io/PrintStream";
		file.constant_pool.add(javaIoPrintStreamClassText);

		// 32
		CpInfo javaLangSystemClass = new CpInfo(CpClass);
		javaLangSystemClass.name_index = 21;
		file.constant_pool.add(javaLangSystemClass);

		// 33
		CpInfo argsText = new CpInfo(CpUtf8);
		argsText.str_bytes = "args";
		file.constant_pool.add(argsText);

		// 34
		CpInfo argsSignature = new CpInfo(CpUtf8);
		argsSignature.str_bytes = "[Ljava/lang/String;";
		file.constant_pool.add(argsSignature);

		// 35
		CpInfo helloWorldString = new CpInfo(CpString);
		helloWorldString.string_index = 20;
		file.constant_pool.add(helloWorldString);

		/*
		 * main Method
		 */
		MethodInfo mainMethodInfo = new MethodInfo();
		mainMethodInfo.access_flags = ACC_PUBLIC | ACC_STATIC; // public |
		// static
		mainMethodInfo.name_index = 16; // "main"
		mainMethodInfo.signature_index = 17; // return: "void", argtype:
		// "String[]"
		file.methods.add(mainMethodInfo);

		AttributeInfoCode mainMethodInfoAttr = new AttributeInfoCode();
		mainMethodInfoAttr.attribute_name = 7; // Code
		mainMethodInfoAttr.max_stack = 2;
		mainMethodInfoAttr.max_locals = 2;

		byte[] code = new byte[] { // code
		(byte) 178, // getstatic cp[24]
				(byte) 00, //
				(byte) 24, //
				(byte) 18, // ldc1 cp[35]
				(byte) 35, //
				(byte) 182, // invokevirtual cp[29]
				(byte) 0, //
				(byte) 29, //
				(byte) 177, // return
		};
		mainMethodInfoAttr.code = code;
		// no exception table
		mainMethodInfo.attributes.add(mainMethodInfoAttr);

		/*
		 * AttributeInfoLocalVariableTable mainMethodInfoLocalVariableTable =
		 * new AttributeInfoLocalVariableTable();
		 * mainMethodInfoLocalVariableTable.attribute_name = 11; //
		 * LocalVariableTable LocalVariableTableEntity lvte = new
		 * LocalVariableTableEntity(); lvte.start_pc = 0x0000; lvte.length =
		 * 0x0009; lvte.name_index = 33; lvte.signature_index = 34; lvte.slot =
		 * 0x0000;
		 * mainMethodInfoLocalVariableTable.local_variable_table.add(lvte);
		 * mainMethodInfoAttr.attributes.add(mainMethodInfoLocalVariableTable);
		 */

		return file;
	}
}
