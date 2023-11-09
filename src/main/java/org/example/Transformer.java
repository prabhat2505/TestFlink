package org.example;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.io.CsvInputFormat;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.core.fs.Path;

import org.apache.flink.formats.csv.CsvReaderFormat;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.example.pojo.Employee;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Transformer {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String path = "src\\main\\resources";
        TextInputFormat textInputFormat = new TextInputFormat(new Path(path));
        DataStream<String> employeeDataStream = env.readFile(textInputFormat,path);
        employeeDataStream.print();
        env.execute();
    }
}
