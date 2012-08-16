package tsjasm.wrapper.managed;

import java.io.DataOutputStream;
import java.io.IOException;

import tsjasm.wrapper.managed.MMethodCode.BP;

import static tsjasm.wrapper.managed.JvmOpcode.OPTYPE.*;

public enum JvmOpcode {
	_nop(0x00), // 00
	_aconst_null(0x01), // 01
	_iconst_m1(0x02), // 02
	_iconst_0(0x03), // 03
	_iconst_1(0x04), // 04
	_iconst_2(0x05), // 05
	_iconst_3(0x06), // 06
	_iconst_4(0x07), // 07
	_iconst_5(0x08), // 08
	_lconst_0(0x09), // 09
	_lconst_1(0x0a), // 10
	_fconst_0(0x0b), // 11
	_fconst_1(0x0c), // 12
	_fconst_2(0x0d), // 13
	_dconst_0(0x0e), // 14
	_dconst_1(0x0f), // 15
	_bipush(0x10, BYTE), // 16
	_sipush(0x11, SHORT), // 17
	_ldc(0x12, BYTE), // 18
	_ldc_w(0x13, SHORT), // 19
	_ldc2_w(0x14, SHORT), // 20
	_iload(0x15, BYTE), // 21
	_lload(0x16, BYTE), // 22
	_fload(0x17, BYTE), // 23
	_dload(0x18, BYTE), // 24
	_aload(0x19, BYTE), // 25
	_iload_0(0x1a), // 26
	_iload_1(0x1b), // 27
	_iload_2(0x1c), // 28
	_iload_3(0x1d), // 29
	_lload_0(0x1e), // 30
	_lload_1(0x1f), // 31
	_lload_2(0x20), // 32
	_lload_3(0x21), // 33
	_fload_0(0x22), // 34
	_fload_1(0x23), // 35
	_fload_2(0x24), // 36
	_fload_3(0x25), // 37
	_dload_0(0x26), // 38
	_dload_1(0x27), // 39
	_dload_2(0x28), // 40
	_dload_3(0x29), // 41
	_aload_0(0x2a), // 42
	_aload_1(0x2b), // 43
	_aload_2(0x2c), // 44
	_aload_3(0x2d), // 45
	_iaload(0x2e), // 46
	_laload(0x2f), // 47
	_faload(0x30), // 48
	_daload(0x31), // 49
	_aaload(0x32), // 50
	_baload(0x33), // 51
	_caload(0x34), // 52
	_saload(0x35), // 53
	_istore(0x36, BYTE), // 54
	_lstore(0x37, BYTE), // 55
	_fstore(0x38, BYTE), // 56
	_dstore(0x39, BYTE), // 57
	_astore(0x3a, BYTE), // 58
	_istore_0(0x3b), // 59
	_istore_1(0x3c), // 60
	_istore_2(0x3d), // 61
	_istore_3(0x3e), // 62
	_lstore_0(0x3f), // 63
	_lstore_1(0x40), // 64
	_lstore_2(0x41), // 65
	_lstore_3(0x42), // 66
	_fstore_0(0x43), // 67
	_fstore_1(0x44), // 68
	_fstore_2(0x45), // 69
	_fstore_3(0x46), // 70
	_dstore_0(0x47), // 71
	_dstore_1(0x48), // 72
	_dstore_2(0x49), // 73
	_dstore_3(0x4a), // 74
	_astore_0(0x4b), // 75
	_astore_1(0x4c), // 76
	_astore_2(0x4d), // 77
	_astore_3(0x4e), // 78
	_iastore(0x4f), // 79
	_lastore(0x50), // 80
	_fastore(0x51), // 81
	_dastore(0x52), // 82
	_aastore(0x53), // 83
	_bastore(0x54), // 84
	_castore(0x55), // 85
	_sastore(0x56), // 86
	_pop(0x57), // 87
	_pop2(0x58), // 88
	_dup(0x59), // 089
	_dup_x1(0x5a), // 090
	_dup_x2(0x5b), // 091
	_dup2(0x5c), // 092
	_dup2_x1(0x5d), // 093
	_dup2_x2(0x5e), // 094
	_swap(0x5f), // 095
	_iadd(0x60), // 096
	_ladd(0x61), // 097
	_fadd(0x62), // 098
	_dadd(0x63), // 099
	_isub(0x64), // 100
	_lsub(0x65), // 101
	_fsub(0x66), // 102
	_dsub(0x67), // 103
	_imul(0x68), // 104
	_lmul(0x69), // 105
	_fmul(0x6a), // 106
	_dmul(0x6b), // 107
	_idiv(0x6c), // 108
	_ldiv(0x6d), // 109
	_fdiv(0x6e), // 110
	_ddiv(0x6f), // 111
	_irem(0x70), // 112
	_lrem(0x71), // 113
	_frem(0x72), // 114
	_drem(0x73), // 115
	_ineg(0x74), // 116
	_lneg(0x75), // 117
	_fneg(0x76), // 118
	_dneg(0x77), // 119
	_ishl(0x78), // 120
	_lshl(0x79), // 121
	_ishr(0x7a), // 122
	_lshr(0x7b), // 123
	_iushr(0x7c), // 124
	_lushr(0x7d), // 125
	_iand(0x7e), // 126
	_land(0x7f), // 127
	_ior(0x80), // 128
	_lor(0x81), // 129
	_ixor(0x82), // 130
	_lxor(0x83), // 131
	_iinc(0x84, BYTE, BYTE), // 132
	_i2l(0x85), // 133
	_i2f(0x86), // 134
	_i2d(0x87), // 135
	_l2i(0x88), // 136
	_l2f(0x89), // 137
	_l2d(0x8a), // 138
	_f2i(0x8b), // 139
	_f2l(0x8c), // 140
	_f2d(0x8d), // 141
	_d2i(0x8e), // 142
	_d2l(0x8f), // 143
	_d2f(0x90), // 144
	_i2b(0x91), // 145
	_i2c(0x92), // 146
	_i2s(0x93), // 147
	_lcmp(0x94), // 148
	_fcmpl(0x95), // 149
	_fcmpg(0x96), // 150
	_dcmpl(0x97), // 151
	_dcmpg(0x98), // 152
	_ifeq(0x99, SHORT), // 153
	_ifne(0x9a, SHORT), // 154
	_iflt(0x9b, SHORT), // 155
	_ifge(0x9c, SHORT), // 156
	_ifgt(0x9d, SHORT), // 157
	_ifle(0x9e, SHORT), // 158
	_if_icmpeq(0x9f, SHORT), // 159
	_if_icmpne(0xa0, SHORT), // 160
	_if_icmplt(0xa1, SHORT), // 161
	_if_icmpge(0xa2, SHORT), // 162
	_if_icmpgt(0xa3, SHORT), // 163
	_if_icmple(0xa4, SHORT), // 164
	_if_acmpeq(0xa5, SHORT), // 165
	_if_acmpne(0xa6, SHORT), // 166
	_goto(0xa7, SHORT), // 167
	_jsr(0xa8, SHORT), // 168
	_ret(0xa9, BYTE), // 169
	_tableswitch(0xaa /* Variable Length */), // 170
	_lookupswitch(0xab /* Variable Length */), // 171
	_ireturn(0xac), // 172
	_lreturn(0xad), // 173
	_freturn(0xae), // 174
	_dreturn(0xaf), // 175
	_areturn(0xb0), // 176
	_return(0xb1), // 177
	_getstatic(0xb2, SHORT), // 178
	_putstatic(0xb3, SHORT), // 179
	_getfield(0xb4, SHORT), // 180
	_putfield(0xb5, SHORT), // 181
	_invokevirtual(0xb6, SHORT), // 182
	_invokespecial(0xb7, SHORT), // 183
	_invokestatic(0xb8, SHORT), // 184
	_invokeinterface(0xb9, SHORT, BYTE, BYTE), // 185
	_xxxunusedxxx1(0xba), // 186
	_new(0xbb, SHORT), // 187
	_newarray(0xbc, BYTE), // 188
	_anewarray(0xbd, SHORT), // 189
	_arraylength(0xbe), // 190
	_athrow(0xbf), // 191
	_checkcast(0xc0, SHORT), // 192
	_instanceof(0xc1, SHORT), // 193
	_monitorenter(0xc2), // 194
	_monitorexit(0xc3), // 195
	_wide(0xc4 /* Variable Length */), // 196
	_multianewarray(0xc5, SHORT, BYTE), // 197
	_ifnull(0xc6), // 198
	_ifnonnull(0xc7), // 199
	_goto_w(0xc8, INT), // 200
	_jsr_w(0xc9, INT), // 201
	_breakpoint(0xca), // 202
	_impdep1(0xfe), // 254
	_impdep2(0xff) // 255
	;

	private final byte opcode;
	private final OPTYPE[] operand_types;

	private JvmOpcode(int opcode, OPTYPE... operand_types) {
		this.opcode = (byte) opcode;
		this.operand_types = operand_types;
	}

	public int getOpcode() {
		return opcode;
	}

	public void writeTo(DataOutputStream data, Object[] operand,
			CpInfoManager cpMgr) throws IOException {
		data.writeByte(opcode);
		switch (this) {
		case _tableswitch:
		case _lookupswitch:
		case _wide:
			throw new RuntimeException("instruction currently unsupported: "
					+ this.name());
		default:
			for (int i = 0; i < operand_types.length; i++) {
				int operand_val = operandToInt(operand[i], cpMgr);
				switch (operand_types[i]) {
				case INT:
					data.writeInt(operand_val);
					break;
				case SHORT:
					data.writeShort(operand_val);
					break;
				case BYTE:
					data.writeByte(operand_val);
					break;
				}
			}
		}
	}

	public int getBytes() {
		int bytes = 1;
		for (OPTYPE t : operand_types) {
			bytes += t.getBytes();
		}
		return bytes;
	}

	private int operandToInt(Object operand, CpInfoManager cpMgr) {
		if (operand instanceof Integer) {
			return (Integer) operand;
		} else if (operand instanceof BP) {
			return ((BP) operand).get();
		} else {
			return cpMgr.getIndexOf(operand);
		}
	}

	public enum OPTYPE {
		INT(4), SHORT(2), BYTE(1);
		private final int bytes;

		private OPTYPE(int bytes) {
			this.bytes = bytes;
		}

		public int getBytes() {
			return bytes;
		}
	}
}
