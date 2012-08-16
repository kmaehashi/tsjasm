package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttributeInfoCode extends AttributeInfo {
	public short max_stack;
	public short max_locals;
	public byte[] code = new byte[] { (byte) 0xb1 }; // return;
	public final List<ExceptionTableEntity> exception_table = new ArrayList<ExceptionTableEntity>();
	public final List<AttributeInfo> attributes = new ArrayList<AttributeInfo>();

	@Override
	protected void writeBodyTo(DataOutputStream data) throws IOException {
		data.writeShort(max_stack);
		data.writeShort(max_locals);

		data.writeInt(code.length);
		for (byte b : code) {
			data.write(b);
		}

		data.writeShort(exception_table.size());
		for (ExceptionTableEntity e : exception_table) {
			e.writeTo(data);
		}

		data.writeShort(attributes.size());
		for (AttributeInfo a : attributes) {
			a.writeTo(data);
		}
	}

	@Override
	public int getBodyLength() throws IOException {
		int length = 2 + 2 + 4 + code.length + 2 + 8 * exception_table.size()
				+ 2;
		for (AttributeInfo a : attributes) {
			length += a.getLength();
		}
		return length;
	}
}
