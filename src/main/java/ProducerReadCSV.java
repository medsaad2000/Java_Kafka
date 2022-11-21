
import kafka.log.LogManager;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ProducerReadCSV {


    public static void main(String[] args)  {


        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG,"my-app-readcsv");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"143.110.158.169:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        System.out.println("Producer has been created...Start sending Patient Record ");

        KafkaProducer<String,DiabeteModel> producer = new KafkaProducer<String,DiabeteModel>(props);
        System.out.println("Producer is created");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() ->{
            ReadCSV readCSV = new ReadCSV("src/data/diabetes.csv");
            List diabeteList = readCSV.ReadCSVFile(); //It will return the student list

            for (Object diabeteObject : diabeteList) {
                //System.out.println(diabeteObject.toString());
                DiabeteModel stdobject = (DiabeteModel) diabeteObject;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                producer.send(new ProducerRecord<String, DiabeteModel>("cloud777",stdobject.getOutcome(), stdobject), (metatda, ex) -> {

                    System.out.println(stdobject.getAge());
                });
            }
        },0,10, TimeUnit.SECONDS);
        System.out.println("Producer has sent all Patient records successfully...");
        }
    }


