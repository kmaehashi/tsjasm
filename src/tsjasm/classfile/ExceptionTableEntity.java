package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

public class ExceptionTableEntity {
	public short start_pc;
	public short end_pc;
	public short handler_pc;
	public short catch_type;

	public short getLength() {
		return 8;
	}

	public void writeTo(DataOutputStream data) throws IOException {
		data.writeShort(start_pc);
		data.writeShort(end_pc);
		data.writeShort(handler_pc);
		data.writeShort(catch_type);
	}
}
