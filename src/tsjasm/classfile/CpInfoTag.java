package tsjasm.classfile;

public enum CpInfoTag {
	CpClass((byte) 7), CpFieldref((byte) 9), CpMethodref((byte) 10), CpInterfaceMethodref(
			(byte) 11), CpString((byte) 8), CpInteger((byte) 3), CpFloat(
			(byte) 4), CpLong((byte) 5), CpDouble((byte) 6), CpNameAndType(
			(byte) 12), CpUtf8((byte) 1), CpUnicode((byte) 2);

	private final byte tagId;

	private CpInfoTag(byte tagId) {
		this.tagId = tagId;
	}

	public byte getTagId() {
		return tagId;
	}

	public static CpInfoTag searchTag(byte tagId) {
		for (CpInfoTag tag : CpInfoTag.values()) {
			if (tag.getTagId() == tagId) {
				return tag;
			}
		}
		return null;
	}
}
