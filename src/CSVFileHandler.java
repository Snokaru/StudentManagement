import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileHandler {
    private String filename;

    public CSVFileHandler(String filename) {
        this.filename = filename; 
    }

    public List<List<String>> readFile() throws Exception {
        List<List<String>> CSVContent = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> currentLine = Arrays.asList(line.split(","));
                CSVContent.add(currentLine);
            }
        } catch(IOException e) {
            throw new Exception("Error in working with file");
        } finally {
            try {
                reader.close();
            } catch(IOException e) {
                throw new Exception("Failed To Close File");
            }
        }

        return CSVContent;
    }

    public void writeFile(List<List<String>> CSVContent) throws Exception {
        FileWriter writer = null;

        try {
            writer = new FileWriter(filename);
            for (List<String> line : CSVContent) {
                writer.write(String.join(",", line) + "\n");
            }
        } catch (IOException e) {
            throw new Exception("Failed To Write To File");
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                throw new Exception("Failed To Close File");
            }
        }
        
    }
}
