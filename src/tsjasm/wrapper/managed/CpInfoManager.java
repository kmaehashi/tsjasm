package tsjasm.wrapper.managed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tsjasm.classfile.CpInfoTag;

import static tsjasm.classfile.CpInfoTag.*;

public class CpInfoManager {
	public final List<MCpInfo> cpList;
	public final Map<String, MCpInfo> cpUtf8Map;

	public CpInfoManager() {
		cpList = new ArrayList<MCpInfo>();
		cpUtf8Map = new HashMap<String, MCpInfo>();
	}

	public short getIndexOf(Object ptr) {
		short index = (short) cpList.indexOf(ptr);
		if (index < 0) {
			throw new IndexOutOfBoundsException(
					"Failed to dereference CpInfo: " + ptr);
		}
		return (short) (index + 1);
	}

	public MClassFile newClassFile() {
		MClassFile classFile = new MClassFile(this);
		return classFile;
	}

	private MCpInfo newCpInfo(CpInfoTag tag) {
		MCpInfo mci = new MCpInfo(tag, this);
		cpList.add(mci);
		return mci;
	}

	public MCpInfo newCpInfoClass(String className) {
		MCpInfo clazz = newCpInfo(CpClass);
		MCpInfo clazzName = newCpInfo(CpUtf8);
		clazz.ptr_name = clazzName;
		clazzName.str_bytes = className;
		return clazz;
	}

	public MCpInfo newCpInfoUtf8(String str) {
		MCpInfo strUtf8 = cpUtf8Map.get(str);
		if (strUtf8 == null) {
			strUtf8 = newCpInfo(CpUtf8);
			strUtf8.str_bytes = str;
			cpUtf8Map.put(str, strUtf8);
		}
		return strUtf8;
	}

	public MCpInfo newCpInfoString(String str) {
		MCpInfo cpStr = newCpInfo(CpString);
		cpStr.ptr_string = newCpInfoUtf8(str);
		return cpStr;
	}

	public MMethod newMethod(MCpInfo clazz, String name, String signature) {
		MMethod method = new MMethod(this);
		MCpInfo methodName = newCpInfoUtf8(name);
		MCpInfo methodSign = newCpInfoUtf8(signature);
		MCpInfo methodNat = newCpInfo(CpNameAndType);
		MCpInfo methodRef = newCpInfo(CpMethodref);
		method.ptr_name = methodName;
		method.ptr_signature = methodSign;
		method.ptr_ref = methodRef;
		methodNat.ptr_name = methodName;
		methodNat.ptr_signature = methodSign;
		methodRef.ptr_class = clazz;
		methodRef.ptr_name_and_type = methodNat;
		return method;
	}

	public MField newField(MCpInfo clazz, String name, String signature) {
		MField field = new MField(this);
		MCpInfo fieldName = newCpInfoUtf8(name);
		MCpInfo fieldSign = newCpInfoUtf8(signature);
		MCpInfo fieldNat = newCpInfo(CpNameAndType);
		MCpInfo fieldRef = newCpInfo(CpFieldref);
		field.ptr_name = fieldName;
		field.ptr_signature = fieldSign;
		field.ptr_ref = fieldRef;
		fieldNat.ptr_name = fieldName;
		fieldNat.ptr_signature = fieldSign;
		fieldRef.ptr_class = clazz;
		fieldRef.ptr_name_and_type = fieldNat;
		return field;
	}

	public List<MCpInfo> getAll() {
		/*
		for (int i = 0; i < cpList.size(); i++) {
			while (true) {
				int lastIndex = cpList.lastIndexOf(cpList.get(i));
				if (i < lastIndex) {
					break;
				}
				cpList.remove(lastIndex);
			}
		}
		*/
		return cpList;
	}
}
