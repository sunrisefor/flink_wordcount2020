package com.atguigu;


import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;


public class flink_bacth {

    public static void main(String[] args) {
        // 1. 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        // 2. 从文件读取数据  按行读取(存储的元素就是每行的文本)
        DataSource<String> lineDS = env.readTextFile("input/words.txt");
        //将每一行数据进行切出每一个单词



        // 3. 转换数据格式
        FlatMapOperator<String, Tuple2<String,Long>> wordAndOne=lineDS.flatMap((String line, Collector<Tuple2<String,Long>> out)->{
            String[] split = line.split(" ");
            for(String word : split){
                out.collect(Tuple2.of(word,1L));
            }
        })
        .returns(Types.TUPLE(Types.STRING,Types.LONG));



     /*   FlatMapOperator<String, Tuple2<String, Long>> wordAndOne = lineDS
                .flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
                    String[] split = line.split(" ");
                    for (String word : split) {
                        out.collect(Tuple2.of(word, 1L));
                    }
                })
                .returns(Types.TUPLE(Types.STRING, Types.LONG));*/
        // 4. 按照 word 进行分组

        // 5. 分组内聚合统计

        // 6. 打印结果


    }


}
