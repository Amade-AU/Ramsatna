package amada.ramsatna.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Hamza on 25/04/2016.
 */
@DatabaseTable(tableName = "version")
public class Version {

    @DatabaseField
    private int size;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
