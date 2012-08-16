package tsjasm.wrapper.managed;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tsjasm.classfile.AttributeInfoCode;


public class MMethodCode extends AttributeInfoCode {
	public MCpInfo ptr_attribute_name;

	private final CpInfoManager cpMgr;
	private final List<JvmInstruction> instructions;
	private int len = 0;

	public MMethodCode(CpInfoManager cpMgr) {
		this.cpMgr = cpMgr;
		this.ptr_attribute_name = cpMgr.newCpInfoUtf8("Code");
		this.instructions = new ArrayList<JvmInstruction>();
	}

	public void add(JvmOpcode opcode, Object... operand) {
		JvmInstruction inst = new JvmInstruction(opcode, operand);
/*		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			inst.writeTo(new DataOutputStream(baos), cpMgr);
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		}
		len += baos.toByteArray().length;
*/
		len += opcode.getBytes();
		instructions.add(inst);
	}

	@Override
	public int getBodyLength() throws IOException {
		updateCode();
		if (code.length != len) {
			throw new RuntimeException("Length mismatch: got " + code.length
					+ ", expected " + len);
		}
		return super.getBodyLength();
	}

	@Override
	public void writeTo(DataOutputStream data) throws IOException {
		this.attribute_name = cpMgr.getIndexOf(ptr_attribute_name);
		updateCode();
		super.writeTo(data);
	}

	private void updateCode() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream data2 = new DataOutputStream(out);
		for (JvmInstruction i : instructions) {
			i.writeTo(data2, cpMgr);
		}
		this.code = out.toByteArray();
		out.close();
	}

	public void backpatch(BP bp) {
		bp.apply(len);
	}

	public BP newBp() {
		// Relative (offset) BackPatcher
		return newBp(true);
	}

	public BP newBp(boolean offset) {
		if (offset) {
			return new BP(len);
		}
		return new BP();
	}

	public class BP {
		private final int begin;
		private int ptr = -1;

		// Backpatch with offset
		BP(int begin) {
			this.begin = begin;
		}

		BP() {
			this(0);
		}

		public void apply(int ptr) {
			this.ptr = ptr;
		}

		public int get() {
			return ptr - begin;
		}
	}
}
