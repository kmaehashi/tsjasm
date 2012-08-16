package tsjasm.wrapper.managed;

import java.io.IOException;
import java.io.OutputStream;

import tsjasm.classfile.ClassFile;


public class MClassFile extends ClassFile {
	public MCpInfo ptr_this_class;
	public MCpInfo ptr_super_class;
	public final CpInfoManager cpMgr;

	MClassFile(CpInfoManager cpMgr) {
		super();
		this.cpMgr = cpMgr;
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		this.this_class = cpMgr.getIndexOf(ptr_this_class);
		this.super_class = cpMgr.getIndexOf(ptr_super_class);
		super.writeTo(out);
	}
}
