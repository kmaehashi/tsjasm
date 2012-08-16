package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/*
 { u2 start_pc;
 u2 length;
 u2 name_index;
 u2 signature_index;
 u2 slot;
 }
 */
public class LocalVariableTableEntity {
	public short start_pc;
	public short length;
	public short name_index;
	public short signature_index;
	public short slot;

	public static short getLength() {
		return 10;
	}

	public void writeTo(DataOutputStream data) throws IOException {
		data.writeShort(start_pc);
		data.writeShort(length);
		data.writeShort(name_index);
		data.writeShort(signature_index);
		data.writeShort(slot);
	}

}
