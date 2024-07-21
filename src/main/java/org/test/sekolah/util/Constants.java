package org.test.sekolah.util;

import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.test.sekolah.exception.DataNotValidException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Constants {
    public static final String DEFAULT_LOCATION = System.getProperty("user.home");
    public static final String EXCEL_LOCATION = DEFAULT_LOCATION + File.separator;
    public static final Set<String> timeZones = new HashSet<>(Arrays.asList("Asia/Bangkok", "Asia/Jakarta"));
    public static final SimpleDateFormat INPUT_DATE = new SimpleDateFormat("dd-MM-yyyy");
    public static final SimpleDateFormat JURNAL_PERIODE = new SimpleDateFormat("MM-yyyy");
    private static DecimalFormat DECIMAL_FORMAT = null;
    private static String MAC_ADDR = null;

    public static final String EXCEL_ACCOUNTING_FORMAT = "_-* #,##0.00_-;-* #,##0.00_-;_-* \"-\"??_-;_-@_-";
    public static final String EXCEL_CURRENCY_FORMAT = "#,##0.00;[Red]-#,##0.00";
    public static final String EXCEL_NUMBER_FORMAT = "#,##0;#,##0";
    public static final String EXCEL_SHORT_DATE = "dd/MM/yyyy";
    public static final SimpleDateFormat FAKTUR_PAJAK_DATE = new SimpleDateFormat("dd/MM/yyyy");
    public static final String BIAYA_MIN = "Minimum Cost";
    public static final String BIAYA_PEMAKAINAN = "Usage Cost";
    public static final String ADMIN_COST = "Admin Cost";
    public static final String MOBIL = "Mobil";
    public static final String MOTOR = "Motor";
    public static final String OTHER = "Other";
    public static final String CAPACITY = "Capacity";
    public static final String[] FAKTUR_HEADER = {
            "FK,KD_JENIS_TRANSAKSI,FG_PENGGANTI,NOMOR_FAKTUR,MASA_PAJAK,TAHUN_PAJAK,TANGGAL_FA,NPWP,NAMA,ALAMAT_LENGKAP,JUMLAH_DPP,JUMLAH_PPN,JUMLAH_PPNBM,ID_KETERANGAN_TAMBAH,FG_UANG_MUKA,UANG_MUKA_DPP,UANG_MUKA_PPN,UANG_MUKA_PPNBM,REFERENSI,KODE_DOKUMEN_PENDUKUNG",
            "LT,NPWP,NAMA,JALAN,BLOK,NOMOR,RT,RW,KECAMATAN,KELURAHAN,KABUPATEN,PROPINSI,KODE_POS,NOMOR_TELEPON,,,,,,",
            "OF,KODE_OBJEK,NAMA,HARGA_SATUAN,JUMLAH_BARANG,HARGA_TOTAL,DISKON,DPP,PPN,TARIF_PPNBM,PPNBM,,,,,,,,,"
    };

    public static final long DEF_ORDERING_VIEWS = 999;
    private static final DecimalFormat decimalFormat = new DecimalFormat();
    private static final DecimalFormat percent = new DecimalFormat("#,###.00");
    private static final DecimalFormat rupiah = new DecimalFormat("#,###.00");
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private static final DecimalFormat dfOne = new DecimalFormat("#.#");
    private static final DecimalFormat nd = new DecimalFormat("#");
    private static final DecimalFormat r = new DecimalFormat("0.#");
    private static final DecimalFormat d = new DecimalFormat("0.##");
    private static final DecimalFormatSymbols customSymbol = new DecimalFormatSymbols();
    private static final TreeMap<Integer, String> romanMap = new TreeMap<>();
    public static final SimpleDateFormat HH = new SimpleDateFormat("HH");
    public static final SimpleDateFormat simpleDateFormatMonthYear = new SimpleDateFormat("MMMM yyyy");
    public static final SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
    public static final SimpleDateFormat simpleDateFormatDayMonthYear = new SimpleDateFormat("dd MMMM yyyy");
    public static final SimpleDateFormat simpleDateFormatDayMonthDash = new SimpleDateFormat("dd/MM");
    public static final SimpleDateFormat simpleDateFormatDayMonthYearSlash = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat mysqlMonthYearFormat = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat simpleDateFormatDayMonthYearDash = new SimpleDateFormat("dd-MMMM-yyyy");
    public static final SimpleDateFormat simpleDateComplete = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    public static final SimpleDateFormat simpleDateCompleteSecond = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    public static final SimpleDateFormat simpleDateDayMonthYear = new SimpleDateFormat("dd-MMM-yyyy");
    public static final SimpleDateFormat simpleDateFormatStatictic = new SimpleDateFormat("dd-MMM-yyyy");
    public static final SimpleDateFormat simpleHourMinuteSecond = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat simpleHourMinute = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("dd");
    //    public static final SimpleDateFormat simpleDateFormatMonth = new SimpleDateFormat("MM");
    public static final SimpleDateFormat simpleDateFormatYear = new SimpleDateFormat("yyyy");

    public static final int DEFAULT_FACILITY_SETTING_SELECT_AVAILABILITY = 2;
    public static final double DEFAULT_FACILITY_SETTING_PPN = 11;
    public static final int DEFAULT_FACILITY_SETTING_MAX_MINUTE_PAY = 15;

    public static final Gson gson = new Gson();

    public static String formatCutOffMonth(long periode) {
        return simpleDateFormatMonthYear.format(new Date(periode * 1000));
    }

    public static String formatCutOffMonth(long periodeStart, int periode) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(periodeStart * 1000));
        Date start = calendar.getTime();
        calendar.add(Calendar.MONTH, periode - 1);
        Date end = calendar.getTime();

        return simpleDateFormatMonthYear.format(start) + " s/d " + simpleDateFormatMonthYear.format(end);
    }


    public static String formattingCutOffDate(long start, long end) {
        return "Periode " + simpleDateFormatDayMonthYear.format(new Date(start * 1000L)) + " s/d " + simpleDateFormatDayMonthYear.format(new Date(end * 1000L));
    }


    public static String periodeCutOffMonth(long start, long end) {
        return simpleDateFormatMonthYear.format(new Date(start * 1000L)) + " - " + simpleDateFormatMonthYear.format(new Date(end * 1000L));
    }


    public static String periodeCutOff(long start, long end) {
        return simpleDateFormatDayMonthDash.format(new Date(start * 1000L)) + " to " + simpleDateFormatDayMonthDash.format(new Date(end * 1000L));
    }

    public static String formattingYear(long timestamp) {
        return simpleDateFormatYear.format(new Date(timestamp * 1000L));
    }

    public static String formattingMonth(long timestamp) {
        return simpleDateFormatMonth.format(new Date(timestamp * 1000L));
    }

    public static String formattingDateOnly(Long date) {
        if (date == null) return "No data";
        return simpleDateFormatDayMonthYear.format(new Date(date * 1000L));
    }

    public static String formattingCompleteDate(Long date) {
        if (date == null) return "No data";
        return simpleDateComplete.format(new Date(date * 1000L));
    }

    public static String formattingCompleteDateSecond(Long date) {
        if (date == null) return "No data";
        return simpleDateCompleteSecond.format(new Date(date * 1000L));
    }

    public static String removeZero(double d) {
        return r.format(d);
    }

    public static String percentFormat(double p) {
        if ((int) p == 0) return dfOne.format(p) + "%";
        return r.format(p) + "%";
    }

    public static String rupiahFormat(double p) {
        return "Rp " + rupiah.format(p);
    }

    public static String decimalFormat(double x) {
        return df.format(x);
    }

    public static double deleteDecimal(double x) {
        return Double.parseDouble(nd.format(x));
    }

    public static int removeDecimal(double p) {
        return (int) p;
    }

    public static String thousandFormat(double p) {
        if (p < 1)
            return d.format(p);
        else
            return rupiah.format(p);
    }

    public static String getMacAddress() throws SocketException, UnknownHostException {
        if (MAC_ADDR == null) {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
            byte[] hardwareAddress = ni.getHardwareAddress();

            String[] hexadecimal = new String[hardwareAddress.length];
            for (int i = 0; i < hardwareAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
            }
            MAC_ADDR = String.join("-", hexadecimal);
        }
        return MAC_ADDR;

    }


    static {
        romanMap.put(1000, "M");
        romanMap.put(900, "CM");
        romanMap.put(500, "D");
        romanMap.put(400, "CD");
        romanMap.put(100, "C");
        romanMap.put(90, "XC");
        romanMap.put(50, "L");
        romanMap.put(40, "XL");
        romanMap.put(10, "X");
        romanMap.put(9, "IX");
        romanMap.put(5, "V");
        romanMap.put(4, "IV");
        romanMap.put(1, "I");
    }

    private static final String[] ROMAN = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};

    public static long addDate(long timestamp, int nextDate) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.add(Calendar.DATE, nextDate);
        long dateNext = calendar.getTimeInMillis() / 1000;
        long[] date = getDailyTimestamp(dateNext);
        return date[1];
    }

    public static long getTimestamp() {
        return new Date().getTime() / 1000L;
    }

    private static void initCalendar(Calendar calendar, long timestamp) {
        Date date = new Date(timestamp * 1000);
        calendar.setTime(date);
    }

    public static LinkedList<String> calcHourPrice(String hr, String en) {
        int startInt = Integer.parseInt(hr.split(":")[0]);
        int startMin = Integer.parseInt(hr.split(":")[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, startInt);
        calendar.set(Calendar.MINUTE, startMin);
        long startunix = calendar.getTime().getTime() / 1000L;


        int endInt = Integer.parseInt(en.split(":")[0]);
        int endMin = Integer.parseInt(en.split(":")[1]);
        if (endInt < startInt) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        calendar.set(Calendar.HOUR_OF_DAY, endInt);
        calendar.set(Calendar.MINUTE, endMin);
        long endTime = calendar.getTime().getTime() / 1000L;

        LinkedList<String> times = new LinkedList<>();
        Calendar calc = Calendar.getInstance();
        calc.setTime(new Date(startunix * 1000L));
        times.add(HH.format(calc.getTime()));

        long gapTime = endTime - startunix;
        while (gapTime > 0) {
            gapTime = gapTime - 3600;
            calc.add(Calendar.HOUR, 1);
            times.add(HH.format(calc.getTime()));
        }
        return times;
    }


    public static long getTimestamp(Calendar calendar, long timestamp, String type) {
        initCalendar(calendar, timestamp);
        setCalendarDay(calendar, type);
        return calendar.getTimeInMillis() / 1000;
    }

    public static int getFormatDayNow() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTimestampDayNow() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int getTimestampMonthNow() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.MONTH);
    }

    public static int getMonthByTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        return calendar.get(Calendar.MONTH);
    }

    public static int getFormatMonthNow() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.MONTH);
    }

    public static int getTimestampYearNow() {
        Calendar instance = Calendar.getInstance();
        return instance.get(Calendar.YEAR);
    }

    public static String getFormatYearNow() {
        return String.valueOf(getTimestampYearNow());
    }

    public static long getTimestampNextMonth(long timestamp, int nextMonth) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.add(Calendar.MONTH, nextMonth);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampNextDay(long timestamp, int nextDay) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.add(Calendar.DAY_OF_MONTH, nextDay);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampByYearMonthDay(int year, int month, int day) {
        Calendar calendar = getCalendarByYearAndMonthAndDay(year, month, day);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampByYearMonth(int year, int month) {
        Calendar calendar = getCalendarByYearAndMonthAndDay(year, month, 1);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampByYear(int year) {
        Calendar calendar = getCalendarByYearAndMonthAndDay(year, 1, 1);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampBeforeMonth(long timestamp, int beforeMonth) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.add(Calendar.MONTH, -beforeMonth);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getTimestampBeforeYear(long timestamp, int beforeYear) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.add(Calendar.YEAR, -beforeYear);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long[] getDailyTimestamp(long timestamp) {
        long[] dates = new long[2];
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        setCalendarDay(calendar, "min");
        dates[0] = calendar.getTimeInMillis() / 1000;

        setCalendarDay(calendar, "max");
        dates[1] = calendar.getTimeInMillis() / 1000;
        return dates;
    }

    public static long[] getMonthlyTimestamp(long timestamp) {
        long[] dates = new long[2];
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "min");
        dates[0] = calendar.getTimeInMillis() / 1000;

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "max");
        dates[1] = calendar.getTimeInMillis() / 1000;
        return dates;
    }

    public static long[] getMonthlyUtilityListrikTimestamp(long timestamp) {
        long timestampBefore = Constants.getTimestampBeforeMonth(timestamp, 1);

        long[] dates = getMonthlyTimestamp(timestampBefore);
        long dateMax = getTimestampNextDay(dates[1], 1);

        long[] dateUtl = new long[2];
        dateUtl[0] = dates[0];
        dateUtl[1] = dateMax;

        return dateUtl;
    }

    public static long[] getMonthlyUtilityAirTimestamp(long timestamp) {
        long timestampBeforeMin = Constants.getTimestampBeforeMonth(timestamp, 2);
        int yearMin = getYearByTimestamp(timestampBeforeMin);
        int monthMin = getMonthByTimestamp(timestampBeforeMin);
        long yearMonthDayMin = getTimestampByYearMonthDay(yearMin, monthMin, 25);

        long timestampBeforeMax = Constants.getTimestampBeforeMonth(timestamp, 1);
        int yearMax = getYearByTimestamp(timestampBeforeMax);
        int monthMax = getMonthByTimestamp(timestampBeforeMax);
        long yearMonthDayMax = getTimestampByYearMonthDay(yearMax, monthMax, 25);

        long[] datesMin = getDailyTimestamp(yearMonthDayMin);
        long[] datesMax = getDailyTimestamp(yearMonthDayMax);

        long[] dateUtl = new long[2];
        dateUtl[0] = datesMin[0];
        dateUtl[1] = datesMax[1];

        return dateUtl;
    }

    public static long[] getYearlyTimestamp(Long timestamp) {
        long[] dates = new long[2];
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);

        calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "min");
        dates[0] = calendar.getTimeInMillis() / 1000;

        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "max");
        dates[1] = calendar.getTimeInMillis() / 1000;
        return dates;
    }

    public static long[] getYearlyContract(long timestamp, int contractDurationInYear) {
        long[] dates = new long[2];
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "min");
        dates[0] = calendar.getTimeInMillis() / 1000;

        calendar.add(Calendar.MONTH, (contractDurationInYear * 12) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "max");
        dates[1] = calendar.getTimeInMillis() / 1000;
        return dates;
    }

    private static void setCalendarDay(Calendar calendar, String type) {
        int hour = 0;
        int minute = hour;
        int second = minute;
        if (type.equalsIgnoreCase("max")) {
            hour = 23;
            minute = 59;
            second = minute;
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
    }


    public static String formatCurrencyIDR(BigDecimal price) {
        customSymbol.setGroupingSeparator('.');
        decimalFormat.setDecimalFormatSymbols(customSymbol);
        return decimalFormat.format(price);
    }

    public static String convertToRoman(int number) {
        int lower = romanMap.floorKey(number);
        if (number == lower) return romanMap.get(number);
        return romanMap.get(lower) + convertToRoman(number - lower);
    }

    public static long getMinimumMonthTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "min");
        return calendar.getTimeInMillis() / 1000;
    }


    public static long getMinimumDayTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        setCalendarDay(calendar, "min");
        return calendar.getTimeInMillis() / 1000;
    }

    public static Calendar getCalendarByYearAndMonthAndDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int date = day;
        int actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (actualMaximum < date) date = actualMaximum;
        calendar.set(Calendar.DAY_OF_MONTH, date);
        return calendar;
    }

    public static long convertDateToTimestamp(Date date) {
        Timestamp ts = new Timestamp(date.getTime());
        return getMaximumDayTimestamp(ts.getTime() / 1000);
    }

    public static long getMaximumDayTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        setCalendarDay(calendar, "max");
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getMaximumMonthTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        setCalendarDay(calendar, "max");
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getDueDateInvoice(long dateTransaction) {
        return dateTransaction + 1295999L;
    }

    public static String processFormatDate(String format, Date calendarDate) {
        format = changeYearTemplate(format, calendarDate);
        format = changeMonthTemplate(format, calendarDate);
        return changeDateTemplate(format, calendarDate);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static String changeYearTemplate(String format, Date date) {
        String yearTemplate;
        try {
            yearTemplate = format.substring(format.indexOf("YYYY"), format.lastIndexOf("YYYY") + 4);
            return format.replace("YYYY", new SimpleDateFormat(yearTemplate).format(date));
        } catch (Exception e) {
            try {
                yearTemplate = format.substring(format.indexOf("YY"), format.lastIndexOf("YY") + 2);
                return format.replace("YY", new SimpleDateFormat(yearTemplate).format(date));
            } catch (IndexOutOfBoundsException ignored) {
                return format;
            }
        }
    }

    private static String changeMonthTemplate(String format, Date date) {
        String monthTemplate;
        try {
            monthTemplate = format.substring(format.indexOf("/MMM/"), format.lastIndexOf("/MMM/") + 5);
            return format.replace("/MMM/", "/" + new SimpleDateFormat(monthTemplate).format(date) + "/");
        } catch (IndexOutOfBoundsException outOfBoundsException) {
            try {
                monthTemplate = format.substring(format.indexOf("/MM/"), format.lastIndexOf("/MM/") + 4);
                return format.replace("/MM/", "/" + new SimpleDateFormat(monthTemplate).format(date) + "/");
            } catch (Exception ignored) {
                return format;
            }
        }
    }

    private static String changeDateTemplate(String format, Date date) {
        try {
            String dateTemplate = format.substring(format.indexOf("DD"), format.lastIndexOf("DD") + 2);
            return format.replace("DD", new SimpleDateFormat(dateTemplate.toLowerCase()).format(date));
        } catch (IndexOutOfBoundsException ignored) {
            return format;
        }
    }


    public static String FORMAT_CODE_FAKTUR_KWITANSI(int index, long date, String template) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date * 1000));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        // CODE_BUILDING/CODE/YYYY/MM/DD
        //todo dont forget check null
        if (template == null) {
            return "FAKTUR-KWITANSI/" + String.format("%04d", year) + "/" + String.format("%02d", month) + "/" + String.format("%02d", day) +
                    "/" + String.format("%05d", (index));
        }
        // YYYY / YY / MM / MMM / DD
        String format = template.replace("NO", String.format("%05d", (index)));

        String yeartempalte;
        try {
            yeartempalte = format.substring(format.indexOf("YYYY"), format.lastIndexOf("YYYY") + 4);
            format = format.replace("YYYY", new SimpleDateFormat(yeartempalte).format(calendar.getTime()));
        } catch (Exception e) {
            try {
                yeartempalte = format.substring(format.indexOf("YY"), format.lastIndexOf("YY") + 2);
                format = format.replace("YY", new SimpleDateFormat(yeartempalte).format(calendar.getTime()));
            } catch (Exception ignored) {
            }
        }
        String monthTemplate;
        try {
            monthTemplate = format.substring(format.indexOf("MMM"), format.lastIndexOf("MMM") + 3);
            format = format.replace("MMM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
        } catch (Exception e) {
            try {
                monthTemplate = format.substring(format.indexOf("MM"), format.lastIndexOf("MM") + 2);
                format = format.replace("MM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
            } catch (Exception ignored) {
            }
        }

        try {
            String dateTemplate = format.substring(format.indexOf("DD"), format.lastIndexOf("DD") + 2);
            format = format.replace("DD", new SimpleDateFormat(dateTemplate.toLowerCase()).format(calendar.getTime()));
        } catch (Exception ignored) {
        }

        if (format.contains("MR")) {
            int mo = calendar.get(Calendar.MONTH);
            String moStr = ROMAN[mo];
            return format.replace("MR", moStr);
        }
        return format;
    }

    public static synchronized String FORMAT_CODE_INVOICE(int index, String code, long date, String template, Integer codeSetting) {
        if (codeSetting == null) codeSetting = 5;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date * 1000));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);

        // YYYY / YY / MM / MMM / DD
        String format = template.replace("NO", String.format("%0" + codeSetting + "d", (index)));

        String yeartempalte;
        try {
            yeartempalte = format.substring(format.indexOf("YYYY"), format.lastIndexOf("YYYY") + 4);
            format = format.replace("YYYY", new SimpleDateFormat(yeartempalte).format(calendar.getTime()));
        } catch (Exception e) {
            try {
                yeartempalte = format.substring(format.indexOf("YY"), format.lastIndexOf("YY") + 2);
                format = format.replace("YY", new SimpleDateFormat(yeartempalte).format(calendar.getTime()));
            } catch (Exception ignored) {
            }
        }
        String monthTemplate;
        try {
            monthTemplate = format.substring(format.indexOf("MMM"), format.lastIndexOf("MMM") + 3);
            format = format.replace("MMM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
        } catch (Exception e) {
            try {
                monthTemplate = format.substring(format.indexOf("MM"), format.lastIndexOf("MM") + 2);
                format = format.replace("MM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
            } catch (Exception ignored) {
            }
        }

        try {
            format = format.replace("CODE", code);
        } catch (Exception ignored) {
        }

        try {
            String dateTemplate = format.substring(format.indexOf("DD"), format.lastIndexOf("DD") + 2);
            format = format.replace("DD", new SimpleDateFormat(dateTemplate.toLowerCase()).format(calendar.getTime()));
        } catch (Exception ignored) {
        }

        if (format.contains("MR")) {
            int mo = calendar.get(Calendar.MONTH);
            String moStr = ROMAN[mo];
            return format.replace("MR", moStr);
        }
        return format;
    }

    public static final String[] postPathArray = new String[]{
            "/api/authenticate",
            "/api/zzz-test/**",
            "/api/authenticate/**",
    };

    public static final String[] getPathArray = new String[]{
            "/internal/**",
            "/api/timev2",
            "/api/time",
    };

    public static final String[] putPathArray = new String[]{
    };

    public static Pageable findPageable(String field, String properties, int page, int size, Sort.Direction direction) {
        Sort sort = Sort.by(direction, field);
        if (!properties.isEmpty() && !properties.equals("0")) {
            sort = JpaSort.unsafe(direction, "FIELD(" + field + ", " + properties + ")");
        }
        return PageRequest.of(page - 1, size, sort);
    }

    public static String nominal(Double nominal) {
        StringJoiner joiner = new StringJoiner(" ");
        if (nominal < 0) joiner.add("MINUS");
        convertValue(joiner, nominal);
        return joiner.toString();
    }

    private static final String[] nominalName = {"", "SATU", "DUA", "TIGA", "EMPAT", "LIMA", "ENAM", "TUJUH", "DELAPAN", "SEMBILAN", "SEPULUH", "SEBELAS"};

    private static void convertValue(StringJoiner joiner, Double nominal) {
        if (nominal < 12D) joiner.add(nominalName[nominal.intValue()]);
        else if (nominal < 20D) {
            convertValue(joiner, nominal - 10D);
            joiner.add("BELAS");
        } else if (nominal < 100D) {
            convertValue(joiner, nominal / 10D);
            joiner.add("PULUH");
            convertValue(joiner, nominal % 10D);
        } else if (nominal < 200D) {
            joiner.add("SERATUS");
            convertValue(joiner, nominal - 100D);
        } else if (nominal < 1000D) {
            convertValue(joiner, nominal / 100D);
            joiner.add("RATUS");
            convertValue(joiner, nominal % 100D);
        } else if (nominal < 2000D) {
            joiner.add("SERIBU");
            convertValue(joiner, nominal - 1000D);
        } else if (nominal < 1000000D) {
            convertValue(joiner, nominal / 1000D);
            joiner.add("RIBU");
            convertValue(joiner, nominal % 1000D);
        } else if (nominal < 1000000000D) {
            convertValue(joiner, nominal / 1000000D);
            joiner.add("JUTA");
            convertValue(joiner, nominal % 1000000D);
        } else if (nominal < 1000000000000D) {
            convertValue(joiner, nominal / 1000000000D);
            joiner.add("MILYAR");
            convertValue(joiner, nominal % 1000000000D);
        } else if (nominal < 1000000000000000D) {
            convertValue(joiner, nominal / 1000000000000D);
            joiner.add("TRILIYUN");
            convertValue(joiner, nominal % 1000000000000D);
        }
    }

    private static final String[] indonesianMonths = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    private static final String[] englishMonths = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public static String getDateFormat(int idx, String pattern, long timestamp) {
        StringJoiner joiner = new StringJoiner(" ");
        String[] used = indonesianMonths;
        if (idx > 0) used = englishMonths;
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        if (pattern.equals("dd MMMM yyyy")) {
            joiner.add(calendar.get(Calendar.DATE) + "");
        }
        joiner.add(used[calendar.get(Calendar.MONTH)]);
        joiner.add(calendar.get(Calendar.YEAR) + "");
        return joiner.toString();
    }

    public static final DecimalFormat decimalFormatter = new DecimalFormat("0.##");
    public static final DecimalFormat decimalFormatterBulat = new DecimalFormat("#");

    public static long getEpochTimeByHourAndMinute(Calendar calendar, String hourMinutes, boolean last) {
        String[] split = hourMinutes.split(":");
        int hour = 0;
        int minute = 0;
        for (int i = 0; i < split.length; i++) {
            if (i == 0) hour = Integer.parseInt(split[i]);
            else minute = Integer.parseInt(split[i]);
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        if (last) minute = minute == 0 ? minute - 1 : minute;
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, last ? 59 : 0);
        calendar.set(Calendar.MILLISECOND, last ? 59 : 0);
        return calendar.getTimeInMillis() / 1000L;
    }

    public static String convertImageUrlToBase64(String image) {
        String image64 = null;
        try {
            URL imageUrl = new URL(image);
            URLConnection ucon = imageUrl.openConnection();
            InputStream is = ucon.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();

            byte[] encodedBytes = Base64.getEncoder().encode(baos.toByteArray());
            image64 = new String(encodedBytes);
        } catch (Exception e) {
        }

        if (image64 != null) return image64;
        else throw new DataNotValidException("Failed to convert URL to Base64");
    }

    private static final String[] month = {"Bulan", "Month"};
    private static final String[] year = {"Tahun", "Year"};

    public static String getPeriodDuration(int lang, int number) {
        StringJoiner result = new StringJoiner(" ");
        if (number < 12) return result.add(number + "").add(month[lang]).toString();

        int yearDuration = number / 12;
        result.add(yearDuration + "").add(year[lang]);
        int monthDuration = number % 12;
        if (monthDuration != 0) result.add(monthDuration + "").add(month[lang]);
        return result.toString();
    }

    public static int getDifferentMonth(Long start, Long end) {
        Calendar startCal = Calendar.getInstance();
        initCalendar(startCal, start);
        Calendar endCal = Calendar.getInstance();
        initCalendar(endCal, end);
        int diffYear = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
        return (diffYear * 12) + endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
    }

    public static int getDifferentDay(Long start, Long end) {
        start = start * 1000;
        end = end * 1000;
        long diff = end - start;
        long diffDay = diff / (60 * 60 * 1000 * 24);
        return (int) diffDay;
    }

    public static String getLatestCodeString(String plain, String processed) {
        int length = processed.length();
        int firstOf = plain.indexOf('%');
        int lastOf = length;
        for (int i = firstOf; i < length; i++) {
            if (!Character.isDigit(processed.charAt(i))) {
                lastOf = i;
                break;
            }
        }
        return processed.substring(firstOf, lastOf);
    }

    public static double roundHalfDown(double d) {
        double i = Math.floor(d);
        double f = d - i;
        return f < 0.5 ? i : i + 1d;
    }

    public static double decimalValue(double value, boolean rounding) {
        if (!rounding) return (int) (value);
        return roundHalfDown(value);
    }


    public static double decimalPercent(double value, double percent, boolean rounding) {
        if (!rounding) return (int) (value * percent / 100);
        return roundHalfDown(value * percent / 100);
    }


    public static double roundingInvoice(String rounding, double val) {
        if (rounding == null || rounding.equals("0") || rounding.equals("")) return val;
        if (rounding.equals("10")) {
            return Math.ceil(val / 10.0) * 10;
        }
        if (rounding.equals("100")) {
            return Math.ceil(val / 100.0) * 100.0;
        }
        if (rounding.equals("1000")) {
            return Math.ceil(val / 1000.0) * 1000;
        }
        return val;
    }

    public static String FORMAT_CODE_RECEIPT(int index, String code, long date, String template) {
        Calendar calendar = Calendar.getInstance();
        Date calendarDate = new Date(date * 1000);
        calendar.setTime(calendarDate);

        String format = template.replace("NO", String.format("%05d", (index)));

        try {
            format = format.replace("CODE", code);
        } catch (Exception ignored) {
        }
        String returnString = processFormatDate(format, calendarDate);
        if (returnString.contains("MR")) {
            int mo = calendar.get(Calendar.MONTH);
            String moStr = ROMAN[mo];
            return returnString.replace("MR", moStr);
        } else if (returnString.contains("MM")) {
            String monthTemplate;
            monthTemplate = format.substring(format.indexOf("MM"), format.lastIndexOf("MM") + 2);
            return returnString.replace("MM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
        } else if (returnString.contains("MMM")) {
            String monthTemplate;
            monthTemplate = format.substring(format.indexOf("MMM"), format.lastIndexOf("MMM") + 3);
            return returnString.replace("MMM", new SimpleDateFormat(monthTemplate).format(calendar.getTime()));
        }

        if (returnString.contains("//")) returnString = returnString.replace("//", "/");
        return returnString;
    }

    public static String formattingHourMinutes(long date) {
        return simpleHourMinute.format(new Date(date * 1000));
    }

    public static long getPeriodeEnd(long minimumMonthTimestamp, int periode) {
        if (periode < 1) {
            periode = 1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(minimumMonthTimestamp * 1000));
        calendar.add(Calendar.MONTH, periode - 1);
        return getMaximumMonthTimestamp(calendar.getTimeInMillis() / 1000);
    }

    public static Integer getIndex(String template, String input, String placeholder) {
        String[] split = template.split("/");

        int i = 0;
        for (String part : split) {
            if (part.contains(placeholder)) {
                break;
            }
            i++;
        }

        try {
            return Integer.parseInt(input.split("/")[i]);
        } catch (NullPointerException | NumberFormatException exception) {
            exception.printStackTrace();
            return 0;
        }
    }

    public static String getTotalHourOvertime(String dateStart, String dateStop) {
//        String dateStart = "23:30";
//        String dateStop = "02:45";

        // Custom date format
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // MY ADDITION TO YOUR CODE STARTS HERE
        if (d2.before(d1)) {
            Calendar c = Calendar.getInstance();
            c.setTime(d2);
            c.add(Calendar.DATE, 1);
            d2 = c.getTime();
        }
        // ENDS HERE

        long diff = d2.getTime() - d1.getTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
//        System.out.println("Time in minutes: " + minutes + " minutes.");

        int hours = (int) (minutes / 60);   // since both are ints, you get an int
        int minutesS = (int) minutes % 60;
//        System.out.printf("%d:%02d", hours, minutesS);
        String hoursString = String.valueOf(hours);
        String minutesString = String.valueOf(minutesS);
//        if (hoursString.length() == 1) hoursString = "0" + hoursString;
//        if (minutesString.length() == 1) minutesString = "0" + minutesString;

        if (minutesString.equals("0")) return hoursString + " Jam";

        return hoursString + " Jam " + minutesString + " Menit";
    }

    public static Calendar setTemporaryDateTime(int jam, int menit, boolean plusOneDay) {
        Calendar calendar = getCalendarByYearAndMonthAndDay(2000, 0, 1);
        if (plusOneDay) calendar = getCalendarByYearAndMonthAndDay(2000, 0, 2);
        calendar.set(Calendar.HOUR_OF_DAY, jam);
        calendar.set(Calendar.MINUTE, menit);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static int getDayNumberForFacilityBooking(long loopDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(loopDate * 1000);
        int dayNumber = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayNumber == 1) dayNumber = 7;
        else dayNumber = dayNumber - 1;
        return dayNumber;
    }

    public static String convertTotalMinuteToString(int totalTime) {
        int hours = totalTime / 60;
        int minutes = totalTime % 60;

        String hourStr = "Hour";
        String minuteStr = "Minute";
        if (hours > 1) hourStr = "Hours";
        if (minutes > 1) minuteStr = "Minutes";

        if (hours > 0) {
            if (minutes > 0) return String.format("%d " + hourStr + " %d " + minuteStr, hours, minutes);
            return String.format("%d " + hourStr, hours);
        }
        if (minutes > 0) return String.format("%d " + minuteStr, minutes);
        return "0 Hour";
    }

    public static long addTimeToUnixDate(long date, String time) {
        String[] timess = time.split(":");
        int hour = Integer.parseInt(timess[0].trim());
        int minute = Integer.parseInt(timess[1].trim());
        int second = 0;

        if (timess.length > 2) second = Integer.parseInt(timess[2].trim());

        Calendar calDate = Calendar.getInstance();
        calDate.setTimeInMillis(date * 1000);

        Calendar calendar = getCalendarByYearAndMonthAndDay(calDate.get(Calendar.YEAR), calDate.get(Calendar.MONTH), calDate.get(Calendar.DAY_OF_MONTH));

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getMinimumYearTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.set(calendar.get(Calendar.YEAR), 0, 1);
        setCalendarDay(calendar, "min");
        return calendar.getTimeInMillis() / 1000;
    }

    public static long getMaximumYearTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        calendar.set(calendar.get(Calendar.YEAR), 11, 31);
        setCalendarDay(calendar, "max");
        return calendar.getTimeInMillis() / 1000;
    }

    public static String FORMAT_CURRENCY(double val) {
        if (DECIMAL_FORMAT == null) {
            String pattern = "###,###.00";
            DECIMAL_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance(Locale.GERMAN);
            DECIMAL_FORMAT.applyPattern(pattern);
        }
        if (val == 0) return "-";
        if (val < 0) {
            return "(" + DECIMAL_FORMAT.format(Math.abs(val)) + ")";
        }
        return DECIMAL_FORMAT.format(val);
    }

    public static double calculateTax(double subtotal, double percent) {
        if (percent == 0d) return 0;
        BigDecimal ttl = new BigDecimal(subtotal);
        BigDecimal p = new BigDecimal(percent);
        return ttl.multiply(p).divide(BigDecimal.valueOf(100)).doubleValue();
    }

    public static String getFormatMonthAndYear(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return simpleDateFormatMonthYear.format(new Date(calendar.getTimeInMillis()));
    }

    public static int getYearByTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDayByTimestamp(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getTopicEvidance(long timestamp, Long buildingId) {
        return Constants.INPUT_DATE.format(new Date(timestamp * 1000)) + "-" + buildingId;
        //03-08-2023-22
    }

    public static long dayDiff(long timestamp1, long timestamp2) {
        Calendar calendar = Calendar.getInstance();
        initCalendar(calendar, timestamp1);
        LocalDateTime localDate1 = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        initCalendar(calendar, timestamp2);
        LocalDateTime localDate2 = LocalDateTime.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return Math.abs(localDate1.toLocalDate().toEpochDay() - localDate2.toLocalDate().toEpochDay());
    }

    public static JavaMailSender initMailSender(String username, String password, String host) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        /* props.put("mail.debug", "true"); */
        return mailSender;
    }

    public static String nameFile(String name) {
        String[] nameList = name.split("-");
        for (String s : nameList) {
//            System.out.println(s);
        }
//        System.out.println(nameList.length);
//        System.out.println(nameList[nameList.length - 1]);
        return name;
    }

    public static long unixTimeYearsAgo(int ago) {
        long timestamp = Constants.getTimestamp();
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(timestamp * 1000);
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR) - ago, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        return calendar.getTimeInMillis() / 1000;
    }

    public static int calculateAge(long dateOfBirth) {
        Timestamp timestamp = new Timestamp(dateOfBirth * 1000);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        Calendar calBirth = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        calBirth.setTime(timestamp);
        int firstYearValue = calBirth.get(Calendar.YEAR);
        calNow.setTime(currentTimestamp);
        int secondYearValue = calNow.get(Calendar.YEAR);
        int diff = secondYearValue - firstYearValue;
        return diff;
    }
}
