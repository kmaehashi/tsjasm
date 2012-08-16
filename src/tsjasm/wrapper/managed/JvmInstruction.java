package tsjasm.wrapper.managed;

import java.io.DataOutputStream;
import java.io.IOException;

public class JvmInstruction {
	private final JvmOpcode opcode;
	private final Object[] operand;

	public JvmInstruction(JvmOpcode opcode, Object[] operand) {
		this.opcode = opcode;
		this.operand = operand;
	}

	public void writeTo(DataOutputStream data, CpInfoManager cpMgr)
			throws IOException {
		opcode.writeTo(data, operand, cpMgr);
	}
}
