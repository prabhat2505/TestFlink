package org.example;


import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.csv.CsvReaderFormat;


import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.function.SerializableFunction;
import org.example.pojo.Employee;
import java.io.File;
import java.util.function.Function;


public class CsvReader {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        /*SerializableFunction<CsvMapper, CsvSchema> schemaGenerator = mapper ->
                mapper.schemaFor(Employee.class).withoutQuoteChar().withColumnSeparator(',');

       CsvReaderFormat<Employee> csvFormat =
                CsvReaderFormat.forSchema(CsvMapper::new, schemaGenerator, (TypeInformation<Employee>) TypeInformation.of(Employee.class));*/
         CsvReaderFormat<Employee> csvFormat = CsvReaderFormat.forPojo(Employee.class);
        FileSource<Employee> source =
                FileSource.forRecordStreamFormat(csvFormat, Path.fromLocalFile(new File("src/main/resources/employee.csv"))).build();

        DataStream<Employee> stream = env
                .fromSource(
                        source,
                        WatermarkStrategy.noWatermarks(),
                        "my_source"
                );

        stream.print();

        env.execute();
    }
}
