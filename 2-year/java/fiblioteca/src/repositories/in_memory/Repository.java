package repositories.in_memory;

import java.io.*;
import java.util.ArrayList;

public class Repository {
  private final String filename;
  private final String filepath;

  public Repository(String filename) {

      this.filename = filename;
      this.filepath = "database/in_memory/" + filename;
  }

  public void setContent(Object entity) {
    try {

      String[] fields = this.extractObjectContent(entity);
      BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath));
      String line = this.concatLineContent(fields);

      writer.write(line);
    } catch (IOException err) {
      err.printStackTrace();
    }
  }

  public ArrayList<String[]> getContent() {
    ArrayList<String[]> result = new ArrayList<String[]>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(this.filepath));

      String line = reader.readLine();

      while (line != null) {
        String[] fields = this.splitLineContent(line);

        result.add(fields);
        line = reader.readLine();
      }
    } catch (IOException err) {
      err.printStackTrace();
    }

    return result;
  }

  private String[] extractObjectContent(Object obj) {
    // TODO
    String[] result = {};

    return result;
  }

  private String concatLineContent(String ...args) {
    String result = "";

    for (String arg : args) {
      result += ";" + arg;
    }

    result = result.substring(1);

    return result;
  }

  private String[] splitLineContent(String line) {
    return line.split(";");
  }
}
