package tsjasm.wrapper.managed;

import java.io.DataOutputStream;
import java.io.IOException;

import tsjasm.classfile.CpInfo;
import tsjasm.classfile.CpInfoTag;


public class MCpInfo extends CpInfo {
	public MCpInfo ptr_name;
	public MCpInfo ptr_class;
	public MCpInfo ptr_name_and_type;
	public MCpInfo ptr_string;
	public MCpInfo ptr_signature;
	public final CpInfoManager cpMgr;

	MCpInfo(CpInfoTag tag, CpInfoManager cpMgr) {
		super(tag);
		this.cpMgr = cpMgr;
	}

	@Override
	public void writeTo(DataOutputStream data) throws IOException {
		switch (tag) {
		case CpClass:
			name_index = cpMgr.getIndexOf(ptr_name);
			break;
		case CpFieldref:
		case CpMethodref:
		case CpInterfaceMethodref:
			class_index = cpMgr.getIndexOf(ptr_class);
			name_and_type_index = cpMgr.getIndexOf(ptr_name_and_type);
			break;
		case CpString:
			string_index = cpMgr.getIndexOf(ptr_string);
			break;
		case CpInteger:
		case CpFloat:
			break;
		case CpLong:
		case CpDouble:
			break;
		case CpNameAndType:
			name_index = cpMgr.getIndexOf(ptr_name);
			signature_index = cpMgr.getIndexOf(ptr_signature);
			break;
		case CpUtf8:
		case CpUnicode:
			break;
		}
		super.writeTo(data);
	}
}