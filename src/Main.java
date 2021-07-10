
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //String addClosureDates = "06/11/2025,10/11/2024,08/21/2021,09/11/2022,01/22/2023,07/11/2021,08/11/2021";
        String addClosureDates = "06/11/2025,10/11/2024,08/21/2021,09/11/2022,07/11/2021,08/11/2021";
        String removeClosureDates = "06/11/2021,10/11/2023,08/21/2021,09/11/2021,01/22/2023";

        List<String> addedDates = Arrays.asList(addClosureDates.split(","));
        List<String> removedDates = Arrays.asList(removeClosureDates.split(","));
        Map<Integer, String> datesMap = new HashMap<>();
//        Set<Integer> addedYears = new HashSet<>();
//        Set<Integer> removedYears = new HashSet<>();
//        Map<Integer, String> addedDatesMap = new HashMap<>();
//        Map<Integer, String> removedDatesMap = new HashMap<>();
//        Map<Integer, String> datesMap = new HashMap<>();
//
//        addedDates.forEach(date -> {
//          List<String> aYears = Arrays.asList(date.split("/"));
//            int yr = Integer.parseInt(aYears.get(2));
//            addedYears.add(yr);
//        });
//
//        removedDates.forEach(date -> {
//            List<String> rYears = Arrays.asList(date.split("/"));
//            int yr = Integer.parseInt(rYears.get(2));
//            removedYears.add(yr);
//        });
//
////        System.out.println(sortDates(addedDates));
//        addedYears.forEach(yr -> {
//            addedDatesMap.put(yr,"");
//        });
//        removedYears.forEach(yr -> {
//            removedDatesMap.put(yr,"");
//        });

//        //System.out.println(addedDatesMap);
//        System.out.println(mapDates(addedDatesMap,sortDates(addedDates)));
//        System.out.println(mapDates(removedDatesMap,sortDates(removedDates)));
        datesMap = mapDates(getYearsFromDates(removedDates),sortDates(removedDates));
        System.out.println("YESSS " + datesMap + " ---");

    }


    private static Map<Integer, String> getYearsFromDates(List<String> splitDates){
        Set<Integer> yearsSet = new HashSet<>();
        Map<Integer, String> yearsMap = new HashMap<>();
        splitDates.forEach(date -> {
            List<String> aYears = Arrays.asList(date.split("/"));
            int yr = Integer.parseInt(aYears.get(2));
            yearsSet.add(yr);
        });
        yearsSet.forEach(yr -> {
            yearsMap.put(yr,"");
        });
        return yearsMap;
    }

    private static Map<Integer, String> mapDates (Map<Integer, String> datesMap, List<String> dates){
        for(Integer key : datesMap.keySet()){
            String temp = "";
            for( int i = 0; i < dates.size(); i++){
                if(key.equals(compareYears(dates.get(i)))){
                    if(temp.isEmpty()){
                        temp += dates.get(i);
                    }else {
                        temp += "," + dates.get(i);
                    }
                }
                datesMap.put(key, temp);
            }
        }
        return datesMap;
    }

    private static int compareYears(String cDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(cDate, formatter);
        return  localDate.getYear();
    }

    private static List<String> sortDates(List<String> dates){
        Collections.sort(dates, new Comparator<String>() {
            //DateFormat f = new SimpleDateFormat("yyyy");
            DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        return dates;
    }




}
