package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AttributeInfoGeneric extends AttributeInfo {
	public List<Byte> info = new ArrayList<Byte>();

	@Override
	public int getBodyLength() {
		return info.size();
	}

	@Override
	public void writeBodyTo(DataOutputStream data) throws IOException {
		byte[] arr = new byte[info.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = info.get(i);
		}
		data.write(arr);
	}
}
