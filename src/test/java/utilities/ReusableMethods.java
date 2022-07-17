package utilities;

import io.restassured.path.json.JsonPath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReusableMethods {
    public static void writeToListINTOText(List<JsonPath> list){
        LocalDateTime date=LocalDateTime.now();
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("YYMMddHHmmss");
        String dates = date.format(dtf);
        File file=new File("src/test/TextFileProducts_file"+dates+".txt");
        FileWriter fw= null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            for(int i=0;i< list.size();i++){
                fw.write(list.get(i)+"\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void writeTXT(String path, String data) {

        FileOutputStream outputStream = null;

        byte[] dataByte = data.getBytes();

        try {
            outputStream = new FileOutputStream(path);
            outputStream.write(dataByte);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
