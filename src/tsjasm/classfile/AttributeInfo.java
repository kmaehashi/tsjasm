package tsjasm.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class AttributeInfo {
	public short attribute_name;

	protected abstract int getBodyLength() throws IOException;

	protected abstract void writeBodyTo(DataOutputStream data)
			throws IOException;

	public int getLength() throws IOException {
		return getBodyLength() + 6;
	}

	public void writeTo(DataOutputStream data) throws IOException {
		data.writeShort(attribute_name); // 4 bytes
		data.writeInt(getBodyLength()); // 2 bytes
		writeBodyTo(data); // getBodyLength() bytes
	}

	public static AttributeInfo constructFrom(DataInputStream data)
			throws IOException {
		AttributeInfoGeneric a = new AttributeInfoGeneric();
		a.attribute_name = data.readShort();

		int attribute_length = data.readInt();
		for (int i = 0; i < attribute_length; i++) {
			a.info.add(data.readByte());
		}

		return a;
	}
}
