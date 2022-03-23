package com.kochasoft.opendoor.userservice.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    private Util() {
    }

    public static final DateTimeFormatter dayofYearPattern = DateTimeFormatter.ofPattern("yyD");
    public static final DateTimeFormatter fullDatePattern = DateTimeFormatter.ofPattern("MMMM d, yyyy");

    public static String generateNewCode(int n) {

        // chose a Character random from this String
        String alphaNumericString = "ABCDEFGHIJKLMNPQRSTUVWXYZ"
                + "23456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (alphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(alphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public static String getCurrentTime() {
        long epochMilli = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getMillis(LocalDateTime dateTime) {
        long epochMilli = dateTime.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getMillis(LocalDate dateTime) {
        long epochMilli = dateTime.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getFutureTimeByMonths(int months) {
        long epochMilli = LocalDateTime.now().plusMonths(months).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getFutureTimeByDays(int days) {
        long epochMilli = LocalDateTime.now().plusDays(days).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getFutureTimeByHours(int hours) {
        long epochMilli = LocalDateTime.now().plusHours(hours).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static String getFutureTimeByMinutes(int minutes) {
        long epochMilli = LocalDateTime.now().plusMinutes(minutes).atZone(ZoneId.of("UTC")).toInstant().toEpochMilli();
        return Long.toString(epochMilli);
    }

    public static LocalDateTime convertEpochMillisToDate(String epochMilli) {
        return Instant.ofEpochMilli(Long.parseLong(epochMilli)).atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static LocalDateTime now() {
        return LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }

    public static LocalDate nowDate() {
        return LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDate();
    }

    public static boolean isExpired(String epochMillis) {
        LocalDateTime futuredate = Instant.ofEpochMilli(Long.parseLong(epochMillis)).atZone(ZoneId.of("UTC"))
                .toLocalDateTime();
        System.out.println(
                futuredate + " " + LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime().isAfter(futuredate));
        return LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime().isAfter(futuredate);
    }

    public static boolean isUrl(String url) {
        String pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if (m.find()) {
            return false;
        } else {
            return true;
        }
    }

    public static String getFileType(String prefix){
        if(prefix.contains("application/pdf")){
            return "pdf";
        }else if(prefix.contains("image/png")){
            return "png";
        }else if(prefix.contains("image/jpg")){
            return "jpg";
        }else if(prefix.contains("image/jpeg")){
            return "jpeg";
        }else if(prefix.contains("image/gif")){
            return "gif";
        }else{
            return "";
        }
    }


}
