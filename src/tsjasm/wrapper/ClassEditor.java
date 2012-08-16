package tsjasm.wrapper;

import tsjasm.classfile.*;
import tsjasm.wrapper.managed.*;
import static tsjasm.classfile.AccessFlag.*;
import static tsjasm.wrapper.managed.JvmOpcode.*;

public class ClassEditor {
	public static final String BASE_CLASS = "java/lang/Object";
	private final MClassFile classFile;
	private final CpInfoManager cp;

	public ClassEditor(String className, String superClass) {
		cp = new CpInfoManager();
		this.classFile = cp.newClassFile();
		init(className, superClass);
	}

	public ClassEditor(String className) {
		this(className, BASE_CLASS);
	}

	private void init(String className, String superClass) {
		classFile.major_version = 50;
		classFile.minor_version = 0;
		classFile.access_flags = ACC_PUBLIC | ACC_SYNCHRONIZED;

		// This Class
		MCpInfo cpClass = cp.newCpInfoClass(className);
		classFile.ptr_this_class = cpClass;

		// Super Class
		MCpInfo cpSuperClass = cp.newCpInfoClass(superClass);
		classFile.ptr_super_class = cpSuperClass;

		// <init> method
		MMethod initMethod = addMethod(cpSuperClass, "<init>", "()V");
		initMethod.access_flags = ACC_PUBLIC;
		MMethodCode initMethodCode = initMethod.getCode();
		initMethodCode.add(_aload_0);
		initMethodCode.add(_invokespecial, initMethod.ptr_ref);
		initMethodCode.add(_return);
		initMethodCode.max_locals = 1;
		initMethodCode.max_stack = 1;

		// some constants
		cp.newCpInfoUtf8("Code");
		cp.newCpInfoUtf8("LineNumberTable");
		cp.newCpInfoUtf8("LocalVariableTable");
		cp.newCpInfoUtf8("this");
		cp.newCpInfoUtf8("L" + className + ";");

		//
		cp.newCpInfoUtf8("SourceFile");
		cp.newCpInfoUtf8(className + ".java");
	}

	public MMethod addMethod(MCpInfo inheritClass, String name, String signature) {
		MMethod method = cp.newMethod(inheritClass, name, signature);
		classFile.methods.add(method);
		return method;
	}

	public MMethod addMethod(String name, String signature) {
		return this.addMethod(classFile.ptr_this_class, name, signature);
	}

	public MCpInfo classRef(String className) {
		return cp.newCpInfoClass(className);
	}

	public MCpInfo methodRef(MCpInfo clazz, String name, String signature) {
		MMethod method = cp.newMethod(clazz, name, signature);
		return method.ptr_ref;
	}

	public MCpInfo fieldRef(MCpInfo clazz, String name, String signature) {
		MField field = cp.newField(clazz, name, signature);
		return field.ptr_ref;
	}

	public MCpInfo stringRef(String str) {
		return cp.newCpInfoString(str);
	}

	public void implementInterface(MInterface iface) {
		// classFile.interfaces.add(iface);
		throw new Error("not implemented yet");
	}

	public CpInfoManager getManager() {
		return cp;
	}

	public ClassFile generateClassFile() {
		classFile.constant_pool.addAll(cp.getAll());
		return classFile;
	}
}
