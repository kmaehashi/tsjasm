package tsjasm.classfile;

import java.io.DataInputStream;
import java.io.IOException;

public class FieldInfo extends MethodInfo {
	/* same structure as method_info */

	public static FieldInfo constructFrom(DataInputStream data)
			throws IOException {
		FieldInfo m = new FieldInfo();
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
