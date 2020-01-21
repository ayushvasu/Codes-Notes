import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Check {

  public static void main(String[] ar) throws ParseException {

    Scanner sc = new Scanner(System.in);
    SimpleDateFormat sobj = new SimpleDateFormat("yyyy-MM-dd");

    //SortedMap<Date, Integer> sm = new TreeMap<Date, Integer>();

    int times = sc.nextInt();

    List<DataValue> dataList = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();

    for(int i = 0; i < times; i++){
      String line = sc.next();
      int intValue= sc.nextInt();
      System.out.println(line+"  "+intValue);
      dataList.add(new DataValue(sobj.parse(line), intValue));
      //sm.put(sobj.parse(line), intValue);
    }

    Integer dataToFind = sc.nextInt();
    Boolean notFound = true;
    for(DataValue data : dataList){
      if(data.getValue() == dataToFind){
        notFound = false;
        data.setDate(sobj.parse(now.toString()));
        break;
      }
    }
    if(notFound)
      dataList.add(new DataValue(sobj.parse(now.toString()), dataToFind));

    Collections.sort(dataList);

    for(DataValue data : dataList){
      System.out.println(sobj.format(data.getDate()) +
          " " + data.getValue());
    }
  }
}

class DataValue implements Comparable<DataValue> {
  Date date;
  int value;

  public DataValue(Date date, int value){
    this.date = date;
    this.value = value;
  }

  public Date getDate(){
    return date;
  }

  public void setDate(Date date){
    this.date = date;
  }

  public int getValue(){
    return value;
  }

  public int compareTo(DataValue otherDate) {
    return this.date.compareTo(otherDate.getDate());
  }
}
