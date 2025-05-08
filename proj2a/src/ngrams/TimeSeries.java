package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        // 显式调用父类构造函数，确保父类初始化为 TreeMap
        super();

        // 检查输入参数的有效性
        if (ts == null) {
            throw new IllegalArgumentException("The input TimeSeries cannot be null.");
        }
        if (startYear > endYear) {
            throw new IllegalArgumentException("Start year must be less than or equal to end year.");
        }

        // 获取指定范围内的子映射
        Map<Integer, Double> subMap = ts.subMap(startYear, true, endYear, true);

        // 将子映射的内容复制到当前对象
        this.putAll(subMap);
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> yearList = new ArrayList<>();
        for (Integer key : this.keySet()){
            yearList.add(key);
        }
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> dataList = new ArrayList<>();
        for (Double value : this.values()){
            dataList.add(value);
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries PlusTs = new TimeSeries();
        for (Map.Entry<Integer,Double> entry : ts.entrySet()){
            int key = entry.getKey();
            double value = entry.getValue();
            PlusTs.put(key,value);
        }

        for (Map.Entry<Integer,Double> entry : this.entrySet()){
            int key = entry.getKey();
            double value = entry.getValue();

            // 检测是否含有相同key
            if (PlusTs.containsKey(key)){
                value += PlusTs.get(key);
            }
            PlusTs.put(key,value);

        }

        return PlusTs;

    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries result = new TimeSeries();

        for (Map.Entry<Integer, Double> entry : this.entrySet()) {
            int year = entry.getKey();
            Double value = entry.getValue();

            // 检查 ts 是否包含当前年份
            if (!ts.containsKey(year)) {
                throw new IllegalArgumentException("TS is missing a year that exists in this TimeSeries: " + year);
            }

            // 获取 ts 中对应年份的值
            Double tsValue = ts.get(year);

            // 防止除以零
            if (tsValue == null || tsValue == 0) {
                throw new IllegalArgumentException("Value for year " + year + " in TS is zero or null.");
            }

            // 计算商并存入结果
            result.put(year, value / tsValue);
        }

        return result;
    }

}

