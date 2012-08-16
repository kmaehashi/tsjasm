package tsjasm.wrapper.managed;

import java.io.DataOutputStream;
import java.io.IOException;

import tsjasm.classfile.MethodInfo;


public class MMethod extends MethodInfo {
	public MCpInfo ptr_name;
	public MCpInfo ptr_signature;

	// not used for output
	public MCpInfo ptr_ref; // methodRef

	private final CpInfoManager cpMgr;
	private MMethodCode code;

	MMethod(CpInfoManager cpMgr) {
		super();
		this.cpMgr = cpMgr;
	}

	public MMethodCode getCode() {
		if (code == null) {
			this.code = new MMethodCode(cpMgr);
			this.attributes.add(this.code);
		}
		return this.code;
	}

	@Override
	public void writeTo(DataOutputStream data) throws IOException {
		this.name_index = cpMgr.getIndexOf(ptr_name);
		this.signature_index = cpMgr.getIndexOf(ptr_signature);
		super.writeTo(data);
	}
}
