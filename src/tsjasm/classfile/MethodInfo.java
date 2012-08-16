package tsjasm.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MethodInfo {
	public short access_flags;
	public short name_index;
	public short signature_index;

	public List<AttributeInfo> attributes = new ArrayList<AttributeInfo>();

	public void writeTo(DataOutputStream data) throws IOException {
		data.writeShort(access_flags);
		data.writeShort(name_index);
		data.writeShort(signature_index);
		data.writeShort(attributes.size());
		for (AttributeInfo a : attributes) {
			a.writeTo(data);
		}
	}

	public static MethodInfo constructFrom(DataInputStream data)
			throws IOException {
		MethodInfo m = new MethodInfo();
		m.access_flags = data.readShort();
		m.name_index = data.readShort();
		m.signature_index = data.readShort();

		int attributes_count = data.readShort();
		for (int i = 0; i < attributes_count; i++) {
			m.attributes.add(AttributeInfo.constructFrom(data));
		}

		return m;
	}
}
