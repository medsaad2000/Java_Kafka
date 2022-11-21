import com.google.gson.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaStreamsConsumer {
    private int counter=1;
    int mean_age=0;
    float mean_bmi =0;
    int mean_bloodpress = 0;
    int mean_pregnancie = 0;

    public static void main(String[] args)  {
        new KafkaStreamsConsumer().start();
    }

    private void start() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"my-app-readcsv");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,1000);
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String,String> kStream= streamsBuilder.stream("quickstart", Consumed.with(Serdes.String(),Serdes.String()));



        kStream
                .flatMapValues(textLine -> Arrays.asList(textLine))
            .foreach((k,v)->{

                    JsonObject o =   JsonParser.parseString(v).getAsJsonObject();

                    mean_age+= Integer.parseInt(String.valueOf(o.get("age"))) ;
                    mean_bmi += Float.parseFloat(String.valueOf(o.get("bMI")));
                    mean_bloodpress +=Integer.parseInt(String.valueOf(o.get("bloodPressure")));
                    mean_pregnancie += Integer.parseInt(String.valueOf(o.get("pregnancies")));
                    int resage =mean_age / counter ;
                    float resbmi = mean_bmi / counter;
                    float resbloodpre = mean_bloodpress/counter;
                    int respregnancie = mean_pregnancie/counter;
                    counter++;
                   System.out.println("L'age moyenne : " + resage + "; La masse moyenne : "+ resbmi + "; Blood Pressure moyenne : " + resbloodpre + "; Moyenne de Grossesses : "+respregnancie);

        });

        Topology topology=streamsBuilder.build();
        KafkaStreams kafkaStreams=new KafkaStreams(topology,props);
        kafkaStreams.start();
        }
    }

