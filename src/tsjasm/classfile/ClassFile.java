package tsjasm.classfile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFile {
	public static final int magic = 0xCAFEBABE;
	public short minor_version;
	public short major_version;
	public List<CpInfo> constant_pool = new ArrayList<CpInfo>();
	public short access_flags;
	public short this_class;
	public short super_class = 0; // java.lang.Object
	public List<Short> interfaces = new ArrayList<Short>();
	public List<FieldInfo> fields = new ArrayList<FieldInfo>();
	public List<MethodInfo> methods = new ArrayList<MethodInfo>();
	public List<AttributeInfo> attributes = new ArrayList<AttributeInfo>();

	public ClassFile() {

	}

	public ClassFile(InputStream in) throws IOException {
		DataInputStream data = new DataInputStream(in);

		if (data.readInt() != ClassFile.magic) {
			return;
		}

		minor_version = data.readShort();
		major_version = data.readShort();

		int constant_pool_count = data.readShort();
		for (int i = 1; i < constant_pool_count; i++) { // i = 1 is not a
														// mistake :-)
			constant_pool.add(CpInfo.constructFrom(data));
		}

		access_flags = data.readShort();
		this_class = data.readShort();
		super_class = data.readShort();
		int interfaces_count = data.readShort();
		for (int i = 0; i < interfaces_count; i++) {
			interfaces.add(data.readShort());
		}

		int fields_count = data.readShort();
		for (int i = 0; i < fields_count; i++) {
			fields.add(FieldInfo.constructFrom(data));
		}

		int methods_count = data.readShort();
		for (int i = 0; i < methods_count; i++) {
			methods.add(MethodInfo.constructFrom(data));
		}

		int attributes_count = data.readShort();
		for (int i = 0; i < attributes_count; i++) {
			attributes.add(AttributeInfo.constructFrom(data));
		}
	}

	public void writeTo(OutputStream out) throws IOException {
		DataOutputStream data = new DataOutputStream(out);
		data.writeInt(magic);
		data.writeShort(minor_version);
		data.writeShort(major_version);

		data.writeShort(constant_pool.size() + 1);
		for (CpInfo c : constant_pool) {
			c.writeTo(data);
		}

		data.writeShort(access_flags);
		data.writeShort(this_class);
		data.writeShort(super_class);

		data.writeShort(interfaces.size());
		for (Short i : interfaces) {
			data.writeShort(i);
		}

		data.writeShort(fields.size());
		for (FieldInfo f : fields) {
			f.writeTo(data);
		}

		data.writeShort(methods.size());
		for (MethodInfo m : methods) {
			m.writeTo(data);
		}

		data.writeShort(attributes.size());
		for (AttributeInfo a : attributes) {
			a.writeTo(data);
		}
	}
}
