package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 LineNumberTable_attribute {
 u2 attribute_name_index;
 u4 attribute_length;
 u2 line_number_table_length;
 { u2 start_pc;
 u2 line_number;
 } line_number_table[line_number_table_length];
 }
 */
public class AttributeInfoLineNumberTable extends AttributeInfo {
	public List<LineNumberTableEntity> line_number_table = new ArrayList<LineNumberTableEntity>();

	@Override
	public int getBodyLength() {
		return 2 + LineNumberTableEntity.getLength() * line_number_table.size();
	}

	@Override
	public void writeBodyTo(DataOutputStream data) throws IOException {
		data.writeShort(line_number_table.size());
		for (LineNumberTableEntity l : line_number_table) {
			l.writeTo(data);
		}
	}
}
