package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/*
 { u2 start_pc;
 u2 line_number;
 }
 */
public class LineNumberTableEntity {
	public short start_pc;
	public short line_number;

	public static short getLength() {
		return 4;
	}

	public void writeTo(DataOutputStream data) throws IOException {
		data.writeShort(start_pc);
		data.writeShort(line_number);
	}
}
