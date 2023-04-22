package net.swordie.ms.connection.db.converters;

import net.swordie.ms.util.FileTime;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.*;

/**
 * @author Sjonnie
 * Created on 9/13/2018.
 */
public class FileTimeConverter implements AttributeConverter<FileTime, Timestamp> {

    // create "custom" LDTs for our own min/max filetime values (max FT value is invalid, as it has 5 year digits)
    private static final LocalDateTime MAX_LDT = LocalDateTime.of(LocalDate.of(9999, 1, 1), LocalTime.of(0, 0, 1));
    private static final LocalDateTime MIN_LDT = LocalDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.of(0, 0, 1));

    @Override
    public Timestamp convertToDatabaseColumn(FileTime fileTime) {
        Instant instant;
        if (fileTime == null) {
            instant = null;
        } else if (fileTime.isMaxTime()) {
            instant = MAX_LDT.atZone(ZoneId.systemDefault()).toInstant();
        } else if (fileTime.isMinTime()) {
            instant = MIN_LDT.atZone(ZoneId.systemDefault()).toInstant();
        } else {
            instant = fileTime.toInstant();
        }
        return instant == null ? null : Timestamp.from(instant);
    }

    @Override
    public FileTime convertToEntityAttribute(Timestamp ts) {
        LocalDateTime ldt = null;
        if (ts != null) {
            ldt = ts.toLocalDateTime();
        }
        FileTime ft;
        if (ldt == null) {
            ft = null;
        } else if (ldt.getYear() == MAX_LDT.getYear()) {
            ft = FileTime.fromType(FileTime.Type.MAX_TIME);
        } else if (ldt.getYear() == MIN_LDT.getYear()) {
            ft = FileTime.fromType(FileTime.Type.ZERO_TIME);
        } else {
            ft = FileTime.fromDate(ts.toLocalDateTime());
        }
        return ft;
    }
}
