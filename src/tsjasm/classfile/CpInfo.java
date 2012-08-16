package tsjasm.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CpInfo {
	// Common
	public final CpInfoTag tag;

	// Class, NameAndType
	public short name_index;

	// FieldRef, MethodRef, InterfaceMethodRef
	public short class_index;
	public short name_and_type_index;

	// String
	public short string_index;

	// Integer, Float
	public int bytes;

	// Long, Double
	public int high_bytes;
	public int low_bytes;

	// NameAndType
	public short signature_index;

	// UTF8, Unicode
	public String str_bytes;

	public CpInfo(CpInfoTag tag) {
		this.tag = tag;
	}

	public static CpInfo constructFrom(DataInputStream data) throws IOException {
		CpInfo cp = new CpInfo(CpInfoTag.searchTag(data.readByte()));
		cp.readFrom(data);
		return cp;
	}

	public void readFrom(DataInputStream data) throws IOException {
		switch (tag) {
		case CpClass:
			name_index = data.readShort();
			break;
		case CpFieldref:
		case CpMethodref:
		case CpInterfaceMethodref:
			class_index = data.readShort();
			name_and_type_index = data.readShort();
			break;
		case CpString:
			string_index = data.readShort();
			break;
		case CpInteger:
		case CpFloat:
			bytes = data.readInt();
			break;
		case CpLong:
		case CpDouble:
			high_bytes = data.readInt();
			low_bytes = data.readInt();
			break;
		case CpNameAndType:
			name_index = data.readShort();
			signature_index = data.readShort();
			break;
		case CpUtf8:
		case CpUnicode:
			str_bytes = data.readUTF();
			break;
		}
	}

	public void writeTo(DataOutputStream data) throws IOException {
		data.write(tag.getTagId());

		switch (tag) {
		case CpClass:
			data.writeShort(name_index);
			break;
		case CpFieldref:
		case CpMethodref:
		case CpInterfaceMethodref:
			data.writeShort(class_index);
			data.writeShort(name_and_type_index);
			break;
		case CpString:
			data.writeShort(string_index);
			break;
		case CpInteger:
		case CpFloat:
			data.writeInt(bytes);
			break;
		case CpLong:
		case CpDouble:
			data.writeInt(high_bytes);
			data.writeInt(low_bytes);
			break;
		case CpNameAndType:
			data.writeShort(name_index);
			data.writeShort(signature_index);
			break;
		case CpUtf8:
		case CpUnicode:
			data.writeUTF(str_bytes);
			break;
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(tag.name());
		builder.append(": ");
		switch (tag) {
		case CpClass:
			builder.append("name_index => " + name_index);
			break;
		case CpFieldref:
		case CpMethodref:
		case CpInterfaceMethodref:
			builder.append("class_index => " + class_index + ", ");
			builder.append("name_and_type_index => " + name_and_type_index);
			break;
		case CpString:
			builder.append("string_index => " + string_index);
			break;
		case CpInteger:
		case CpFloat:
			builder.append("bytes => " + bytes);
			break;
		case CpLong:
		case CpDouble:
			builder.append("high_bytes => " + high_bytes + ", ");
			builder.append("low_bytes => " + low_bytes);
			break;
		case CpNameAndType:
			builder.append("name_index => " + name_index + ", ");
			builder.append("signature_index => " + signature_index);
			break;
		case CpUtf8:
		case CpUnicode:
			builder.append("[String] => " + str_bytes);
			break;
		}

		return builder.toString();

	}
}
