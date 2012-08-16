package tsjasm.classfile;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 LocalVariableTable_attribute {
 u2 attribute_name_index;
 u4 attribute_length;
 u2 local_variable_table_length;
 { u2 start_pc;
 u2 length;
 u2 name_index;
 u2 signature_index;
 u2 slot;
 } local_variable_table[local_variable_table_length];
 }
 */
public class AttributeInfoLocalVariableTable extends AttributeInfo {
	public List<LocalVariableTableEntity> local_variable_table = new ArrayList<LocalVariableTableEntity>();

	@Override
	public int getBodyLength() {
		return 2 + LocalVariableTableEntity.getLength()
				* local_variable_table.size();
	}

	@Override
	public void writeBodyTo(DataOutputStream data) throws IOException {
		data.writeShort(local_variable_table.size());
		for (LocalVariableTableEntity l : local_variable_table) {
			l.writeTo(data);
		}
	}
}
