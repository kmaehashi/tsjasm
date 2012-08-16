package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeInfoSourceFile extends AttributeInfo {
	public short sourcefile_index;

	@Override
	public int getBodyLength() {
		return 2;
	}

	@Override
	public void writeBodyTo(DataOutputStream data) throws IOException {
		data.writeShort(sourcefile_index);
	}
}
